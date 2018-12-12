package pku;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * 消费者
 */

public class Consumer {

    String queue;
    List<String> topics = new LinkedList<>();
    List<Integer> readpos = new ArrayList<>();
    List<FileChannel> inList = new ArrayList<>();
    List<MappedByteBuffer> bufferList = new ArrayList<>();
    int rePos = 0;

    public void attachQueue(String queueName, Collection<String> t) throws Exception {
        if (queue != null) {
            throw new Exception("只允许绑定一次");
        }
        queue = queueName;
        topics.addAll(t);
        inList = MessageStore.store.pullTopicStream(topics);
        bufferList = MessageStore.mappedByteBufferList;
    }

    public ByteMessage poll() {
        byte[] data;
        for (int i = 0; i < inList.size(); i++) {
            readpos.add(0);
        }
        for (int i = rePos; i < inList.size(); i++) {
            data = readData(i);
            if (data==null) {
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


    public byte[] readData(int n) {
        try {
            if (bufferList.get(n).hasRemaining()){
                byte[] datalength = new byte[4];
                for (int i = 0; i < 4; i++) {
                    if (readpos.get(n)>=bufferList.get(n).limit())return null;
                    /*System.out.println("limit:"+bufferList.get(n).limit());
                    System.out.println("readpos:"+readpos.get(n));*/
                    byte b = bufferList.get(n).get(readpos.get(n));
                    datalength[i]=b;
                    readpos.set(n,readpos.get(n)+1);
                }
                int length = ((datalength[1] & 0xff) << 24) | ((datalength[1] & 0xff) << 16) | ((datalength[2] & 0xff) << 8) | (datalength[3] & 0xff);
                byte[] data = new byte[length];
                for (int i = 0; i < length; i++) {
                    if (readpos.get(n)>=bufferList.get(n).limit())return null;
                    byte b = bufferList.get(n).get(readpos.get(n));
                    data[i]=b;
                    readpos.set(n,readpos.get(n)+1);
                }
                return data;
            }
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

        ByteMessage msg = new DefaultMessage(null);
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
            switch (key) {
                case 'a':
                    msg.putHeaders("MessageId", Integer.parseInt(value));
                    break;
                case 'b':
                    msg.putHeaders("Topic", value);
                    break;
                case 'c':
                    msg.putHeaders("BornTimestamp", Long.parseLong(value));
                    break;
                case 'd':
                    msg.putHeaders("BornHost", value);
                    break;
                case 'e':
                    msg.putHeaders("StoreTimestamp", Long.parseLong(value));
                    break;
                case 'f':
                    msg.putHeaders("StoreHost", value);
                    break;
                case 'g':
                    msg.putHeaders("StartTime", Long.parseLong(value));
                    break;
                case 'h':
                    msg.putHeaders("StopTime", Long.parseLong(value));
                    break;
                case 'i':
                    msg.putHeaders("Timeout", Integer.parseInt(value));
                    break;
                case 'j':
                    msg.putHeaders("Priority", Integer.parseInt(value));
                    break;
                case 'k':
                    msg.putHeaders("Reliability", Integer.parseInt(value));
                    break;
                case 'l':
                    msg.putHeaders("SearchKey", value);
                    break;
                case 'm':
                    msg.putHeaders("ScheduleExpression", value);
                    break;
                case 'n':
                    msg.putHeaders("ShardingKey", Double.parseDouble(value));
                    break;
                case 'o':
                    msg.putHeaders("ShardingPartition", Double.parseDouble(value));
                    break;
                case 'p':
                    msg.putHeaders("TraceId", value);
                    break;
            }
        }
        byte[] body = new byte[datalength - pos];
        System.arraycopy(data,pos,body,0,body.length);
        msg.setBody(body);
        return msg;
    }
}
