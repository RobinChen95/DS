package pku;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 这是一个消息队列的内存实现
 */
public class DemoMessageStore {
    static final DemoMessageStore store = new DemoMessageStore();

    static boolean executed = false;

    ByteBuffer buffer = ByteBuffer.allocate(1024*1024*256);

    // 消息存储
    HashMap<String, ArrayList<ByteMessage>> msgs = new HashMap<>();
    // 遍历指针
    HashMap<String, Integer> readPos = new HashMap<>();

    HashMap<String, FileChannel> inMap = new HashMap<>();
    /**
     * @param msg
     * @param topic
     */
    // 加锁保证线程安全
    public synchronized void push(ByteMessage msg, String topic) throws Exception {
        if (msg == null) {
            return;
        }
        StoreCall(msg, topic);

    }

    //新加的
    /*public void StoreCall(ByteMessage msg, String topic) throws Exception {
        //如果没有这个Topic就新建一个序列化对象输出流
        if (!inMap.containsKey(topic)) {
            File file = new File("./data/" + topic);
            file.createNewFile();
            inMap.put(topic, new ObjectOutputStream(
                    new FileOutputStream("./data/" + topic)));
        }
        //如果有，就用这个topic写
        inMap.get(topic).writeObject(msg);
    }*/

    public void StoreCall(ByteMessage msg, String topic) throws Exception {
        //如果没有这个Topic就新建一个输出通道
        if (!inMap.containsKey(topic)){
            File file = new File("./data/" + topic);
            file.createNewFile();
            inMap.put(topic,new FileOutputStream(file).getChannel());
            //此处可以修改
        }
        //明天需要写压缩、读文件的解压缩、多线程。
        //https://blog.csdn.net/u013063153/article/details/78954012
        //如果有，就用这个topic写
        buffer.clear();
        //此处只存了消息体
        buffer.put(toByteArray(msg));
        buffer.flip();
        inMap.get(topic).write(buffer);
    }

    public byte[] toByteArray(ByteMessage msg) throws Exception {
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] msgByteArray = bos.toByteArray();
        return msgByteArray;
    }


    public void refresh() throws Exception {
       /* if (!executed) {
            msgs = new HashMap<>();
            //要遍历的路径
            String path = "./data/";
            File file = new File(path);
            //遍历path下的文件和目录，放在File数组中
            File[] fs = file.listFiles();
            for (File f : fs) {
                if (!msgs.containsKey(f.getName())) {
                    msgs.put(f.getName(), new ArrayList<>());
                    FileInputStream fis = new FileInputStream("./data/" + f.getName());
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    while (fis.available() > 0) {
                        ByteMessage bmsg = (ByteMessage) ois.readObject();
                        msgs.get(f.getName()).add(bmsg);
                    }
                }
            }
            executed = true;
        }*/

    }

    // 加锁保证线程安全
    public synchronized ByteMessage pull(String queue, String topic) throws Exception {

        refresh();

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
