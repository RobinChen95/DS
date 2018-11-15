package com.pku.programmingTest2018;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
/***
 * 第三次作业，考察多线程知识点
 */
public class Main {
    // 每个pusher向每个topic发送的消息数目
    static int PUSH_COUNT = 100;
    // 发送消息的线程数
    static int PUSH_THREAD_COUNT = 4;
    // 发送线程往n个topic发消息
    static int PUSH_TOPIC_COUNT = 10;
    // 消费消息的线程数
    static int PULL_THREAD_COUNT = 4;
    // 每个消费者消费的topic数量
    static int PULL_TOPIC_COUNT = 10;
    // topic数量
    static int TOPIC_COUNT = 20;
    // 统计push/pull消息的数量
    static AtomicInteger pushCount = new AtomicInteger();
    static AtomicInteger pullCount = new AtomicInteger();
    static Random rand = new Random(100);

    public static void main(String[] args) throws Exception {
        testPush();
        testPull();
    }

    static void testPush() throws Exception {
        // topic的名字是topic+序号的形式
        System.out.println("开始push");

        ArrayList<Thread> pushers = new ArrayList<>();
        for (int i = 0; i < PUSH_THREAD_COUNT; i++) {
            // 随机选择连续的topic
            ArrayList<String> tops = new ArrayList<>();
            int start = rand.nextInt(TOPIC_COUNT);
            for (int j = 0; j < PUSH_TOPIC_COUNT; j++) {
                int v = (start + j) % TOPIC_COUNT;
                tops.add("topic" + Integer.toString(v));
            }
            // 用参数tops和i创建一个PushTester实例，将该实例作为参数创建一个线程t，将线程t启动运行
            // *********** 第一处 开始 ***********

            // *********** 第一处 结束 ***********
            pushers.add(t);
        }
        for (int i = 0; i < pushers.size(); i++) {
            // 从pushers list集合中拿到第i个线程，并执行join方法
            // *********** 第二处 开始 ***********

            // *********** 第二处 结束 ***********
        }

        System.out.println(String.format("push 结束  push count %d", pushCount.get()));
    }

    static void testPull() throws Exception {
        System.out.println("开始pull");
        int queue = 0;
        ArrayList<Thread> pullers = new ArrayList<>();
        for (int i = 0; i < PULL_THREAD_COUNT; i++) {
            // 随机选择topic
            ArrayList<String> tops = new ArrayList<>();
            int start = rand.nextInt(TOPIC_COUNT);
            for (int j = 0; j < PULL_TOPIC_COUNT; j++) {
                int v = (start + j) % TOPIC_COUNT;
                tops.add("topic" + Integer.toString(v));
            }
            Thread t = new Thread(new PullTester(Integer.toString(queue), tops));
            queue++;
            t.start();
            pullers.add(t);
        }
        for (int i = 0; i < pullers.size(); i++) {
            pullers.get(i).join();
        }

        System.out.println(String.format("pull 结束  pull count %d", pullCount.get()));
    }

    static class PushTester implements Runnable {
        // 随机向以下topic发送消息
        List<String> topics = new ArrayList<>();
        Producer producer = new Producer();
        int id;

        PushTester(List<String> t, int id) {
            topics.addAll(t);
            this.id = id;
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("producer%d push to:", id));
            for (int i = 0; i < t.size(); i++) {
                sb.append(t.get(i) + " ");
            }
            System.out.println(sb.toString());
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < topics.size(); i++) {
                    String topic = topics.get(i);
                    for (int j = 0; j < PUSH_COUNT; j++) {
                        // topic加j作为数据部分
                        // j是序号, 在consumer中会用来校验顺序
                        byte[] data = (topic + " " + id + " " + j).getBytes();
                        ByteMessage msg = producer.createBytesMessageToTopic(topics.get(i), data);
                        // 设置一个header
                        msg.putHeaders("SEARCH_KEY", "hello");
                        // producer发送msg消息,pushCount自增1个数
                        // *********** 第三处 开始 ***********


                        // *********** 第三处 结束 ***********
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    static class PullTester implements Runnable {
        // 拉消息
        String queue;
        List<String> topics = new ArrayList<>();
        Consumer consumer = new Consumer();

        public PullTester(String s, ArrayList<String> tops) throws Exception {
            queue = s;
            topics.addAll(tops);
            consumer.attachQueue(s, tops);

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("queue%s attach:", s));
            for (int i = 0; i < topics.size(); i++) {
                sb.append(topics.get(i) + " ");
            }
            System.out.println(sb.toString());
        }

        @Override
        public void run() {
            try {
                // 检查顺序, 保存每个topic-producer对应的序号, 新获得的序号必须严格+1
                HashMap<String, Integer> posTable = new HashMap<>();
                while (true) {
                    // consumer执行拉取一个消息，并赋值给ByteMessage的msg对象
                    // *********** 第四处 开始 ***********

                    // *********** 第四处 结束 ***********
                    if (msg == null) {
                        return;
                    } else {
                        byte[] data = msg.getBody();
                        String str = new String(data);
                        String[] strs = str.split(" ");
                        String topic = strs[0];
                        String prod = strs[1];
                        int j = Integer.parseInt(strs[2]);
                        String mapkey = topic + " " + prod;
                        if (!posTable.containsKey(mapkey)) {
                            posTable.put(mapkey, 0);
                        }
                        //校验顺序
                        if (j != posTable.get(mapkey)) {
                            System.out.println(String.format("数据错误 topic %s 序号:%d", topic, j));
                            System.exit(0);
                        }
                        //校验头部
                        if (!msg.headers().getString("SEARCH_KEY").equals("hello")) {
                            System.out.println(String.format("header错误 topic %s 序号:%d", topic, j));
                            System.exit(0);
                        }
                        posTable.put(mapkey, posTable.get(mapkey) + 1);
                        pullCount.incrementAndGet();

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

class Producer {
    public ByteMessage createBytesMessageToTopic(String topic, byte[] body) {
        ByteMessage msg = new ByteMessage(body);
        // 将topic赋给msg的header部分，key值叫"TOPIC"，并返回msg
        // *********** 第五处 开始 ***********

        // *********** 第五处 结束 ***********
    }

    // 将message发送出去
    public void send(ByteMessage msg) {
        Store.store.push(msg);
    }
}

class Consumer {
    List<String> topics = new LinkedList<>();
    // 记录上一次读取的位置
    int readPos = 0;
    String queue;

    // 将消费者订阅的topic进行绑定
    public void attachQueue(String queueName, Collection<String> t) throws Exception {
        if (queue != null) {
            throw new Exception("只允许绑定一次");
        }
        queue = queueName;
        topics.addAll(t);
    }

    // 每次消费读取一个message
    public ByteMessage poll() {
        ByteMessage re = null;
        // 先读第一个topic, 再读第二个topic...
        // 直到所有topic都读完了, 返回null, 表示无消息
        for (int i = 0; i < topics.size(); i++) {
            int index = (i + readPos) % topics.size();
            re = Store.store.pull(queue, topics.get(index));
            if (re != null) {
                readPos = index + 1;
                break;
            }
        }
        return re;
    }
}

class Store {
    static final Store store = new Store();

    // 消息存储
    HashMap<String, ArrayList<ByteMessage>> msgs = new HashMap<>();
    // 遍历指针
    HashMap<String, Integer> readPos = new HashMap<>();

    // 同步执行push方法，防止多个线程同时访问互斥资源
    // *********** 第六处 开始 ***********
    public void push(ByteMessage msg) {
        // *********** 第六处 结束 ***********
        if (msg == null) {
            return;
        }
        String topic = msg.headers().getString("TOPIC");
        if (!msgs.containsKey(topic)) {
            msgs.put(topic, new ArrayList<>());
        }
        msgs.get(topic).add(msg);

    }

    public synchronized ByteMessage pull(String queue, String topic) {
        // k表示一个queue和一个topic的组合
        String k = queue + " " + topic;
        if (!readPos.containsKey(k)) {
            readPos.put(k, 0);
        }
        int pos = readPos.get(k);
        if (!msgs.containsKey(topic)) {
            return null;
        }
        ArrayList<ByteMessage> list = msgs.get(topic);
        if (list.size() <= pos) {
            return null;
        } else {
            ByteMessage msg = list.get(pos);
            readPos.put(k, pos + 1);
            return msg;
        }
    }
}

// 消息的实现
class ByteMessage {

    private KeyValue headers = new KeyValue();
    private byte[] body;

    public void setHeaders(KeyValue headers) {
        this.headers = headers;
    }

    public ByteMessage(byte[] body) {
        this.body = body;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public KeyValue headers() {
        return headers;
    }

    public ByteMessage putHeaders(String key, int value) {
        headers.put(key, value);
        return this;
    }

    public ByteMessage putHeaders(String key, long value) {
        headers.put(key, value);
        return this;
    }

    public ByteMessage putHeaders(String key, double value) {
        headers.put(key, value);
        return this;
    }

    public ByteMessage putHeaders(String key, String value) {
        headers.put(key, value);
        return this;
    }

}

// 一个Key-Value的实现类
class KeyValue {
    private final HashMap<String, Object> kvs = new HashMap<>();

    public Object getObj(String key) {
        return kvs.get(key);
    }

    public HashMap<String, Object> getMap() {
        return kvs;
    }

    public KeyValue put(String key, int value) {
        kvs.put(key, value);
        return this;
    }

    public KeyValue put(String key, long value) {
        kvs.put(key, value);
        return this;
    }

    public KeyValue put(String key, double value) {
        kvs.put(key, value);
        return this;
    }

    public KeyValue put(String key, String value) {
        kvs.put(key, value);
        return this;
    }

    public int getInt(String key) {
        return (Integer) kvs.getOrDefault(key, 0);
    }

    public long getLong(String key) {
        return (Long) kvs.getOrDefault(key, 0L);
    }

    public double getDouble(String key) {
        return (Double) kvs.getOrDefault(key, 0.0d);
    }

    public String getString(String key) {
        return (String) kvs.getOrDefault(key, null);
    }

    public Set<String> keySet() {
        return kvs.keySet();
    }

    public boolean containsKey(String key) {
        return kvs.containsKey(key);
    }
}
