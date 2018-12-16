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

    String path = "data" + File.separator;
    final HashMap<String, BufferedOutputStream> outMap = new HashMap<>();
    final HashMap<String, MappedByteBuffer> inBuffer = new HashMap<>();

    /**
     * @param data
     * @param Topic
     */
    public void push(byte[] data, String Topic) {
        try {
            synchronized (this) {
                if (!outMap.containsKey(Topic)) {
                    outMap.put(Topic, new BufferedOutputStream(new FileOutputStream(path + Topic)));
                }
            }
            OutputStream out = outMap.get(Topic);
            out.write(data);
        } catch (Exception e) {
        }
    }


    public synchronized HashMap<String,MappedByteBuffer> pullTopicStream(List<String> topics) {
        HashMap<String,MappedByteBuffer> datastreams = new HashMap();
        try {
            for (String topic : topics) {
                File file = new File(path + topic);
                if (file.exists()) {
                    //BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
                    RandomAccessFile raf = new RandomAccessFile(file,"r");
                    FileChannel fc = raf.getChannel();
                    MappedByteBuffer mappedByteBuffer = fc.map(FileChannel.MapMode.READ_ONLY,0,fc.size());
                    datastreams.put(topic,mappedByteBuffer);
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