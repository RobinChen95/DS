package pku;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        //消息头
        HashMap headers = defaultMessage.headers().getMap();
        Set<String> headkey = headers.keySet();
        List<Byte> keytpe = new ArrayList<>();
        List<String> value = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        int headnum = 0;
        int headerslen = 0;
        for (String key : headkey) {
            headnum += 1;
            if (keyTable.containsKey(key)) {
                keytpe.add((byte) keyTable.get(key).charValue());
                value.add(String.valueOf(headers.get(key)));
                count.add(String.valueOf(headers.get(key)).length());
                headerslen += String.valueOf(headers.get(key)).length();
            } else {
                keytpe.add((byte) 'z');
                value.add((String) headers.get(key));
                count.add(String.valueOf(headers.get(key)).length());
                headerslen += String.valueOf(headers.get(key)).length();
            }
        }
        byte[] byteheader = new byte[3 * headnum + headerslen + 1];
        byteheader[0] = (byte) headnum;
        int pos = 1;
        for (int i = 0; i < headnum; i++) {
            byteheader[pos++] = keytpe.get(i);
            byteheader[pos++] = (byte) ((count.get(i) >>> 8) & 0xff);
            byteheader[pos++] = (byte) ((count.get(i) >>> 0) & 0xff);
            byte[] a = value.get(i).getBytes();
            System.arraycopy(a, 0, byteheader, pos, count.get(i));
            pos += count.get(i);
        }

        //消息体
        byte[] bytebody = defaultMessage.getBody();

        int len = byteheader.length + bytebody.length;
        byte[] data = new byte[len];
        System.arraycopy(byteheader, 0, data, 0, byteheader.length);
        System.arraycopy(bytebody, 0, data, byteheader.length, bytebody.length);

        //数据压缩
        byte[] compressdata;
        byte[] iscomress = new byte[1];
        if (len >= 1024) {
            compressdata = compress(data);
            iscomress[0] = (byte) 0;
        } else {
            compressdata = data;
            iscomress[0] = (byte) 1;
        }

        byte[] storedata = new byte[compressdata.length + 5];
        byte[] datalength = new byte[4];
        datalength[3] = (byte) (compressdata.length + 1 & 0xFF);
        datalength[2] = (byte) ((compressdata.length + 1) >>> 8 & 0xFF);
        datalength[1] = (byte) ((compressdata.length + 1) >>> 16 & 0xFF);
        datalength[0] = (byte) ((compressdata.length + 1) >>> 24);
        System.arraycopy(datalength, 0, storedata, 0, 4);
        System.arraycopy(iscomress, 0, storedata, 4, 1);
        System.arraycopy(compressdata, 0, storedata, 5, compressdata.length);

        MessageStore.store.push(storedata, topic);
    }

    public void flush() throws Exception {
        MessageStore.store.flush();
        System.out.println("flush");
    }

    public byte[] compress(byte[] indata) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Deflater compressor = new Deflater(1);
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
        HashMap<String, Character> keyTable = new HashMap<String, Character>();
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
