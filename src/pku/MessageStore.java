package pku;


import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * 这是一个消息队列的内存实现
 */

public class MessageStore {
    static final MessageStore store = new MessageStore();

    static List<MappedByteBuffer> mappedByteBufferList = new ArrayList<>();

    String path = "data";
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
                    outMap.put(Topic, new BufferedOutputStream(new FileOutputStream(path + File.separator + Topic)));
            }
            OutputStream out = outMap.get(Topic);
            out.write(data);
        } catch (Exception e) {
        }
    }


    public synchronized ArrayList<FileChannel> pullTopicStream(List<String> topics) {
        ArrayList<FileChannel> datastreams = new ArrayList<>();

        try {
            for (int i = 0; i < topics.size(); i++) {
                String topic = topics.get(i);
                File file = new File(path + File.separator + topic);
                if (file.exists()) {
                    //BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
                    FileChannel fc = new FileInputStream(file).getChannel();
                    mappedByteBufferList.add(fc.map(FileChannel.MapMode.READ_ONLY,0,fc.size()));
                    datastreams.add(fc);
                }
            }
            for (int i = 0; i < mappedByteBufferList.size(); i++) {
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
