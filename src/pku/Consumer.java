package pku;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * 实现消费者
 */

public class Consumer {

    String queue;
    List<String> topics = new LinkedList<>();
    List<BufferedInputStream> inList = new ArrayList<>();
    HashMap<Character, String> keyBackTable = buildBackKeyTable();
    BufferedInputStream in;
    ByteMessage msg;
    int rePos = 0;
    int inListSize;


    public void attachQueue(String queueName, Collection<String> t) {
        queue = queueName;
        topics.addAll(t);
        inList = MessageStore.store.pullTopicStream(topics);
        inListSize = inList.size();
    }

    public ByteMessage poll() {
        byte[] data;
        for (int i = rePos; i < inListSize; i++) {
            in = inList.get(rePos);
            data = readData();
            if (data == null) {
                rePos += 1;
                continue;
            }
            byte[] redata = new byte[data.length - 1];
            System.arraycopy(data, 1, redata, 0, data.length - 1);
            if ((int) data[0] == 0) {
                try {
                    return getMessage(uncompress(redata));
                } catch (DataFormatException e) {}
            }
            else return getMessage(redata);
        }

        return null;
    }


    public byte[] readData() {
        try {
            if (in.available() <= 0) return null;
            byte[] datalength = new byte[4];
            in.read(datalength, 0, 4);
            int length = ((datalength[0] & 0xff) << 24) | ((datalength[1] & 0xff) << 16) | ((datalength[2] & 0xff) << 8) | (datalength[3] & 0xff);
            byte[] data = new byte[length];
            in.read(data, 0, length);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public byte[] uncompress(byte[] input) throws DataFormatException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Inflater decompressor = new Inflater();
        try {
            decompressor.setInput(input);
            final byte[] buf = new byte[2048];
            while (!decompressor.finished()) {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            decompressor.end();
        }
        return bos.toByteArray();
    }


    public ByteMessage getMessage(byte[] data) {

        msg = new DefaultMessage(null);
        int datalength = data.length;
        int headernum = (int) data[0];
        int pos = 1;
        for (int i = 0; i < headernum; i++) {
            char key = (char) data[pos++];
            int valuelegth = ((data[pos++] & 0xff) << 8) | (data[pos++] & 0xff);
            byte[] valuebytes = new byte[valuelegth];
            System.arraycopy(data,pos,valuebytes,0,valuelegth);
            pos+=valuelegth;
            String value = new String(valuebytes);
            msg.putHeaders(keyBackTable.get(key), value);
        }
        byte[] body = new byte[datalength - pos];
        System.arraycopy(data,pos,body,0,body.length);
        msg.setBody(body);
        return msg;
    }

    private HashMap<Character, String> buildBackKeyTable(){

        HashMap<Character, String> keyTable = new HashMap<>();
        keyTable.put('a',"MessageId");
        keyTable.put('b',"Topic");
        keyTable.put('c',"BornTimestamp");
        keyTable.put('d',"BornHost");
        keyTable.put('e',"StoreTimestamp");
        keyTable.put('f',"StoreHost");
        keyTable.put('g',"StartTime");
        keyTable.put('h',"StopTime");
        keyTable.put('i',"Timeout");
        keyTable.put('j',"Priority");
        keyTable.put('k',"Reliability");
        keyTable.put('l',"SearchKey");
        keyTable.put('m',"ScheduleExpression");
        keyTable.put('n',"ShardingKey");
        keyTable.put('o',"ShardingPartition");
        keyTable.put('p',"TraceId");
        return keyTable;
    }
}