package pku;


import java.io.*;
import java.util.*;

/**
 * 这是一个消息队列的内存实现
 */

public class MessageStore {
    static final MessageStore store = new MessageStore();

    String path = "data"+ File.separator;
    HashMap<String, BufferedOutputStream> outMap = new HashMap<>();


    //synchronized修饰词为同步线程加了一个java的内置锁，即互斥锁

    /**
     * @param data
     * @param Topic
     */
    public void push(byte[] data, String Topic) {
        try {
            synchronized (this) {
                if (!outMap.containsKey(Topic))
                    outMap.put(Topic, new BufferedOutputStream(new FileOutputStream(path  + Topic)));
            }
            OutputStream out = outMap.get(Topic);
            out.write(data);
        } catch (Exception e) {
        }
    }


    public synchronized ArrayList<BufferedInputStream> pullTopicStream(List<String> topics) {
        ArrayList<BufferedInputStream> datastreams = new ArrayList<>();
        try {
            for (String topic: topics) {
                File file = new File(path + topic);
                if (file.exists()) {
                    BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
                    datastreams.add(stream);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datastreams;
    }


    public void flush() throws Exception {
        Iterator<Map.Entry<String, BufferedOutputStream>> iter = outMap.entrySet().iterator();
        while (iter.hasNext()) {
            OutputStream val = iter.next().getValue();
            val.flush();
        }
    }

}