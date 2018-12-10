package pku;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 这是一个消息队列的内存实现
 */
public class test {
    static final DemoMessageStore store = new DemoMessageStore();

    static int PushThread = 4;

    static int PullCount = 4;

    static ByteMessage storeMSG;

    static String storeTopic;

    static int taskAssigner;

    static ArrayList<Thread> pushers = new ArrayList<>();

    // 消息存储
    HashMap<String, ArrayList<ByteMessage>> msgs = new HashMap<>();
    // 遍历指针
    HashMap<String, Integer> readPos = new HashMap<>();

    static HashMap<String, ObjectOutputStream> inMap = new HashMap<>();

    //静态初始化块，启动四个写线程,并让其等待
    static{
        int temp = PushThread;
        while(temp>0){
            Thread t = new Thread(new PushStore());
            t.start();
            pushers.add(t);
            temp--;
        }
        pushers.get(taskAssigner).run();
    }

    // 加锁保证线程安全
    /**
     * @param msg
     * @param topic
     */

    public synchronized void push(ByteMessage msg, String topic) throws Exception{
        if (msg == null) {
            return;
        }
        if (!msgs.containsKey(topic)) {
            msgs.put(topic, new ArrayList<>());
        }
        // 加入消息
        msgs.get(topic).add(msg);

        ThreadCall(msg,topic);

    }

    //新加的
    public synchronized void ThreadCall(ByteMessage msg, String topic) throws Exception {
        //首先赋值
        storeMSG = msg;
        storeTopic=topic;
        taskAssigner = (++PushThread)%pushers.size();
        //这句话可能会因为线程死亡而出错
    }

    static class PushStore implements Runnable {

        @Override
        public void run() {

        }

        public void write(){
            try {
                //如果没有这个Topic就新建一个序列化对象输出流
                while(true){
                    if (!inMap.containsKey(storeTopic)){
                        File file = new File("./src/main/java/ChenGQ/data/"+storeTopic);
                        file.createNewFile();
                        inMap.put(storeTopic,new ObjectOutputStream(
                                new FileOutputStream("./src/main/java/ChenGQ/data/"+storeTopic)));
                    }
                    System.out.println("while循环中");
                    //如果有，就用这个topic写
                    inMap.get(storeTopic).writeObject(storeMSG);
                    wait();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 加锁保证线程安全
    public synchronized ByteMessage pull(String queue, String topic) {
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
            // 将键k的值+1，表示当前读到第pos个msg，下一次应该读+1个
            readPos.put(k, pos + 1);
            return msg;
        }
    }

}
