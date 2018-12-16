package pku;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.zip.Deflater;

/**
 * 生产者
 */

public class Producer {
    HashMap<String, Character> keyTable = buildKeyTable();

    //生成指定Topic的字节消息，并返回
    public ByteMessage createBytesMessageToTopic(String topic, byte[] body) {
        ByteMessage msg = new DefaultMessage(body);
        msg.putHeaders("Topic", topic);
        return msg;
    }


    //将字节消息发送出去
    public void send(ByteMessage defaultMessage) {
        String topic = defaultMessage.headers().getString("Topic");
        String tempValue;
        int tempLen;

        //消息头
        DefaultKeyValue header = (DefaultKeyValue) defaultMessage.headers();
        HashMap<String, String> headers = ((DefaultKeyValue) defaultMessage.headers()).getMap2();
        byte[] bytebody = defaultMessage.getBody();
        Set<String> headkey = headers.keySet();
        int headnum = headkey.size();
        int len = header.getLength() + 3*headnum+1 + bytebody.length;
        byte[] data = new byte[len];
        data[0] = (byte) headnum;
        int idx = 1;

        for (String key : headkey) {
            if (keyTable.containsKey(key)) {
                data[idx++] = (byte) keyTable.get(key).charValue();
                tempValue = headers.get(key);
                tempLen = tempValue.length();
                data[idx++] = (byte) ((tempLen >>> 8) & 0xff);
                data[idx++] = (byte) ((tempLen >>> 0) & 0xff);
                System.arraycopy(tempValue.getBytes(), 0, data, idx, tempLen);
                idx += tempLen;
            }
        }

        System.arraycopy(bytebody, 0, data, idx, bytebody.length);

        //数据压缩
        byte[] compressdata;
        byte iscomress;
        if (len >= 1024) {
            compressdata = compress(data);
            iscomress = 0;
        } else {
            compressdata = data;
            iscomress = 1;
        }

        byte[] storedata = new byte[compressdata.length + 5];
        byte[] datalength = new byte[4];
        datalength[3] = (byte) (compressdata.length + 1 & 0xFF);
        datalength[2] = (byte) ((compressdata.length + 1) >>> 8 & 0xFF);
        datalength[1] = (byte) ((compressdata.length + 1) >>> 16 & 0xFF);
        datalength[0] = (byte) ((compressdata.length + 1) >>> 24);
        System.arraycopy(datalength, 0, storedata, 0, 4);
        storedata[4] = iscomress;
        System.arraycopy(compressdata, 0, storedata, 5, compressdata.length);

        MessageStore.store.push(storedata, topic);
    }

    public void flush() throws Exception {
        MessageStore.store.flush();
    }

    public byte[] compress(byte[] indata) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Deflater compressor = new Deflater(3);
        try {
            compressor.setInput(indata);
            compressor.finish();
            final byte[] buf = new byte[indata.length];
            while (!compressor.finished()) {
                int count = compressor.deflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            compressor.end();
        }
        return bos.toByteArray();
    }

    private HashMap<String, Character> buildKeyTable() {
        HashMap<String, Character> keyTable = new HashMap<>();
        keyTable.put("MessageId", 'a');
        keyTable.put("Topic", 'b');
        keyTable.put("BornTimestamp", 'c');
        keyTable.put("BornHost", 'd');
        keyTable.put("StoreTimestamp", 'e');
        keyTable.put("StoreHost", 'f');
        keyTable.put("StartTime", 'g');
        keyTable.put("StopTime", 'h');
        keyTable.put("Timeout", 'i');
        keyTable.put("Priority", 'j');
        keyTable.put("Reliability", 'k');
        keyTable.put("SearchKey", 'l');
        keyTable.put("ScheduleExpression", 'm');
        keyTable.put("ShardingKey", 'n');
        keyTable.put("ShardingPartition", 'o');
        keyTable.put("TraceId", 'p');
        return keyTable;
    }
}