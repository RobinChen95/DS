package pku;


import java.io.*;
import java.util.*;

/**
 * 这是一个消息队列的内存实现
 */

public class MessageStore {
    static final MessageStore store = new MessageStore();

    String path = "data" + File.separator;
    boolean full = false;
    HashMap<String, BufferedOutputStream> outMap = new HashMap<>();

    /**
     * @param data
     * @param Topic
     */
    public void push(byte[] data, String Topic) {
        try {
            synchronized (this) {
                if (!full&&!outMap.containsKey(Topic)) {
                    outMap.put(Topic, new BufferedOutputStream(new FileOutputStream(path + Topic)));
                    if (outMap.size()==20)full=true;
                }
            }
            OutputStream out = outMap.get(Topic);
            out.write(data);
        } catch (Exception e) {
        }
    }


    public synchronized ArrayList<BufferedInputStream> pullTopicStream(List<String> topics) {
        ArrayList<BufferedInputStream> datastreams = new ArrayList<>();
        try {
            for (String topic : topics) {
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