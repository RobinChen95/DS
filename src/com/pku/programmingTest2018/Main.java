package com.pku.programmingTest2018;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        List<String> topics = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            String topic = "topic " + j;
            // 把字符串topic加入队列中
            // **********第一处 开始 **********
            topics.add(topic);
            // **********第一处 结束 **********
            byte[] data = (topic + " " + j).getBytes();
            Message msg = producer.createBytesMessageToTopic(topic, data);
            producer.send(msg);
        }

        consumer.attachQueue("1", topics);
        while (consumer.poll()!=null);
=======
import java.util.HashMap;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        byte[] body = "this is body".getBytes();
        KeyValue keyValue = new KeyValue();
        keyValue.put("MESSAGE_ID", 123456);
        keyValue.put("TOPIC", "Java");
        keyValue.put("BORN_TIMESTAMP", 1234567890123457890l);
        keyValue.put("PI", 3.1415926535897932384626433832795d);
        ByteMessage byteMessage = new ByteMessage(body);
        byteMessage.setHeaders(keyValue);
        System.out.println(byteMessage.headers().getInt("MESSAGE_ID"));
        System.out.println(byteMessage.headers().getLong("BORN_TIMESTAMP"));
        System.out.println(byteMessage.headers().getDouble("PI"));
        System.out.println(byteMessage.headers().getString("TOPIC"));
    }

}
//消息的实现
class ByteMessage {

    private KeyValue headers = new KeyValue();
    private byte[] body;

    public void setHeaders(KeyValue headers) {
        this.headers = headers;
    }

    public ByteMessage(byte[] body) {
        this.body = body;
>>>>>>> 258f0ef533b082cb189ce0416ea25a7995127cf8
    }

<<<<<<< HEAD

class Producer {
    public Message createBytesMessageToTopic(String topic, byte[] body) {
        String content = new String(body);
        Message msg = new Message(topic, content);
        return msg;
    }

    // 将message发送出去
    public void send(Message msg) {
        System.out.println("发送了消息：" + msg.getTopic());
        Store.store.push(msg);
=======
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
        //********** 第一处 开始 **********
        headers.put(key,value);
        //********** 第一处 结束 **********
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


//一个Key-Value的实现类
class KeyValue {
    private final HashMap<String, Object> kvs = new HashMap<>();

    public Object getObj(String key) {
        return kvs.get(key);
    }

    public HashMap<String, Object> getMap() {
        return kvs;
>>>>>>> 258f0ef533b082cb189ce0416ea25a7995127cf8
    }

<<<<<<< HEAD
class Consumer {
    List<String> topics = new LinkedList<>();
    //记录上一次读取的位置
    int readPos = 0;
    String queue;


    public void attachQueue(String queueName, Collection<String> t) throws Exception {
        if (queue != null) {
            throw new Exception("只允许绑定一次");
        }
        queue = queueName;
        // 将消费者订阅的topics进行绑定
        // **********第二处 开始 **********

        // **********第二处 结束 **********
    }

    // 每次消费读取一个message
    public Message poll() {
        Message re = null;
        // 先读第一个topic, 再读第二个topic...
        // 直到所有topic都读完了, 返回null, 表示无消息
        for (int i = 0; i < topics.size(); i++) {
            int index = (i + readPos) % topics.size();
            re = Store.store.pull(queue, topics.get(index));
            if (re != null) {
                System.out.println("拿到了消息：" + re.getTopic());
                //在index基础上，更改readPos的值，+1
                // **********第三处 开始 **********

                // **********第三处 结束 **********
                break;
            }
        }
        return re;
=======
    public KeyValue put(String key, int value) {
        kvs.put(key, value);
        return this;
    }

    public KeyValue put(String key, long value) {
        kvs.put(key, value);
        return this;
    }

    public KeyValue put(String key, double value) {
        //********** 第二处 开始 **********
        kvs.put(key,value);
        //********** 第二处 结束 **********
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
        //********** 第三处 开始 **********
        return (Long)kvs.getOrDefault(key,0);
        //********** 第三处 结束 **********
    }

    public double getDouble(String key) {
        return (Double) kvs.getOrDefault(key, 0.0d);
    }

    public String getString(String key) {
        return (String) kvs.getOrDefault(key, null);
    }

    public Set<String> keySet() {
        return kvs.keySet();
>>>>>>> 258f0ef533b082cb189ce0416ea25a7995127cf8
    }

<<<<<<< HEAD
class Store {
    static final Store store = new Store();

    // 消息存储，每个topic对应一个ArrayList
    HashMap<String, ArrayList<Message>> msgs = new HashMap<>();
    // 遍历指针，记录每个queue与topic的组合读取message的位置
    HashMap<String, Integer> readPos = new HashMap<>();

    public void push(Message msg) {
        if (msg == null) {
            return;
        }
        String topic = msg.getTopic();
        if (!msgs.containsKey(topic)) {
            //HashMap中还未出现过该topic，创建一个ArrayList给该topic
            // **********第四处 开始 **********

            // **********第四处 结束 **********
        }
        // 往该msg对应的topic的List里面加入消息
        // **********第五处 开始 **********

        // **********第五处 结束 **********

    }

    public Message pull(String queue, String topic) {
        //k表示一个queue和一个topic的组合
        String k = queue + " " + topic;
        if (!readPos.containsKey(k)) {
            readPos.put(k, 0);
        }
        int pos = readPos.get(k);
        if (!msgs.containsKey(topic)) {
            return null;
        }
        // 获取topic对应的ArrayList对象list
        // **********第六处 开始 **********

        // **********第六处 结束 **********
        if (list.size() <= pos) {
            return null;
        } else {
            Message msg = list.get(pos);
            // 将键k对应的值更新，表示当前读到第pos个msg，下一次应该读第+1个
            // **********第七处 开始 **********

            // **********第七处 结束 **********
            return msg;
        }
=======
    public boolean containsKey(String key) {
        return kvs.containsKey(key);
>>>>>>> 258f0ef533b082cb189ce0416ea25a7995127cf8
    }
}

class Message {
    private String topic;
    private String content;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message(String topic, String content) {
        this.topic = topic;
        this.content = content;
    }

}

