package pku;


import java.io.*;
import java.util.*;

/**
 * 这是一个消息队列的内存实现
 */

public class MessageStore {
    static final MessageStore store = new MessageStore();

    String path = "data" + File.separator;
    final HashMap<String, BufferedOutputStream> outMap = new HashMap<>();
    final HashMap<String, ArrayList<Byte>> outData = new HashMap<>();

    /**
     * @param data
     * @param Topic
     */
    public void push(byte[] data, String Topic) {
        try {
            synchronized (this) {
                if (!outMap.containsKey(Topic)) {
                    outMap.put(Topic, new BufferedOutputStream(new FileOutputStream(path + Topic)));
                    outData.put(Topic,new ArrayList<>());
                }
            }
            for (int i = 0; i < data.length; i++) {
                outData.get(Topic).add(data[i]);
            }
            if (outData.get(Topic).size()>10000){
                for (int i = 0; i < data.length; i++) {
                    outData.get(Topic).add(data[i]);
                }
                byte[] temp = new byte[outData.get(Topic).size()];
                for (int i = 0; i < outData.get(Topic).size(); i++) {
                    temp[i]= outData.get(Topic).get(i);
                }
                OutputStream out = outMap.get(Topic);
                out.write(temp);
                outData.replace(Topic,new ArrayList<>());
            }
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