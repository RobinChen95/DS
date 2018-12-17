package pku;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;


public class Consumer {
	static final String[] keylist = {"MessageId", "Topic", "BornTimestamp", "BornHost", "StoreTimestamp", "StoreHost",
			"StartTime", "StopTime", "Timeout", "Priority", "Reliability", "SearchKey", "ScheduleExpression", "ShardingKey",
			"ShardingPartition", "TraceId"};
	private List<String> topics = new LinkedList<>();
	private int currentTopic = 0;
	private String queue;
	private Map<String, LinkedList<BufferedInputStream>> topicInput = new HashMap<>();
	private int readPos = 0;
	private byte[] size1 = new byte[1];
	private byte[] size2 = new byte[2];
	private byte[] size4 = new byte[4];
	private byte[] size8 = new byte[8];
	

	private static byte[] getBooleanArray2(byte[] b) {	//only for b.length == 2
		byte[] array = new byte[16];
		for (int i = 15; i >= 0; --i) {
			if (i >= 8) {
				array[i] = (byte) (b[1] & 1);
				b[1] = (byte) (b[1] >> 1);
			} else {
				array[i] = (byte) (b[0] & 1);
				b[0] = (byte) (b[0] >> 1);
			}
		}
		return array;
	}
	
	private static byte[] getBooleanArray4(byte[] b) {	//only for b.length == 4
		byte[] array = new byte[32];
		for (int i = 31; i >= 0; --i) {
			if (i >= 24) {
				array[i] = (byte) (b[3] & 1);
				b[3] = (byte) (b[3] >> 1);
			} else if (i >= 16){
				array[i] = (byte) (b[2] & 1);
				b[2] = (byte) (b[2] >> 1);
			} else if (i >= 8) {
				array[i] = (byte) (b[1] & 1);
				b[1] = (byte) (b[1] >> 1);
			} else {
				array[i] = (byte) (b[0] & 1);
				b[0] = (byte) (b[0] >> 1);
			}
		}
		return array;
	}

	private ByteMessage readMessage(BufferedInputStream b) throws Exception {

		ByteMessage message = new DefaultMessage();

		// Get 'u' or 'c'
		if (b==null) return null;
		else {
			if (b.read(size1) == -1) {
				b.close();
				return null;
			}
		}

		byte[] msg = null;
		if (size1[0] == 'u') {
			// Get size of msg;
			b.read(size2);
			// Get msg;
			msg = new byte[Utils.bytesToShort(size2)];
			b.read(msg);
		} else if ((size1[0] == 'c')) {
			// Get size of msg;
			b.read(size4);
			// Get msg;
			byte[] msg_c = new byte[Utils.bytesToInt(size4)];
			b.read(msg_c);
			msg = Utils.uncompress(msg_c);
		}
		// BUG!!! 可能恰好有'u'/'c'
//		else {
//			b.close();
//			return null;
//		}

		System.arraycopy(msg, readPos, size2, 0, 2);
		readPos += 2;
		byte[] array1 = getBooleanArray2(size2);
		System.arraycopy(msg, readPos, size4, 0, 4);
		readPos += 4;
		byte[] array2 = getBooleanArray4(size4);
		for (int i = 0; i < 16; ++i) {
			if (array1[i] == 1) {
				if (array2[i << 1] == 0) {
					if (array2[(i << 1) + 1] == 0) {
						System.arraycopy(msg, readPos, size4, 0, 4);
						readPos += 4;
						message.putHeaders(keylist[i], Utils.bytesToInt(size4));
					} else {
						System.arraycopy(msg, readPos, size8, 0, 8);
						readPos += 8;
						message.putHeaders(keylist[i], Utils.bytesToDouble(size8));
					}
				} else {
					if (array2[(i << 1) + 1] == 0) {
						System.arraycopy(msg, readPos, size8, 0, 8);
						readPos += 8;
						message.putHeaders(keylist[i], Utils.bytesToLong(size8));
					} else {
						System.arraycopy(msg, readPos, size2, 0, 2);
						readPos += 2;
						int slen = Utils.bytesToShort(size2);
						byte[] s = new byte[slen];
						System.arraycopy(msg, readPos, s, 0, slen);
						readPos += slen;
						message.putHeaders(keylist[i], new String(s));
					}
				}
			}
		}
		System.arraycopy(msg, readPos, size4, 0, 4);
		readPos += 4;
		int blen = Utils.bytesToInt(size4);
		byte[] s = new byte[blen];
		System.arraycopy(msg, readPos, s, 0, blen);
		// 重置readPos(未加上blen，因无必要)
		readPos = 0;
		message.setBody(s);

		return message;
	}

	private ByteMessage loadByteMessage(String topic) throws Exception {

		// If InputStream of current topic is opened in the first place;
		// Then open it and put it in the map;

		if (!topicInput.containsKey(topic)) {
			LinkedList<BufferedInputStream> bis_list = new LinkedList<>();
			for (int i = 0; i < 4; ++i) {
//				try {
//					FileChannel fci = new RandomAccessFile("./data/" + topic + i + ".txt", "r").getChannel();
//					MappedByteBuffer mbbi = fci.map(FileChannel.MapMode.READ_ONLY, 0, fci.size());
//					mbbi_list.add(mbbi);
//				} catch (Exception e) {
//
//				}
				File f = new File("./data/" + topic + i + ".txt");
				if (f.exists()) {
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
					bis_list.add(bis);
				}
			}
			topicInput.put(topic, bis_list);
		}

		LinkedList<BufferedInputStream> bis_list = topicInput.get(topic);
		ByteMessage msg = readMessage(bis_list.peek());
		if (msg == null) {
			bis_list.poll();
			if (bis_list.isEmpty()) {
				return null;
			} else {
				return readMessage(bis_list.peek());
			}
		} else {
			return msg;
		}
	}

	public void attachQueue(String queueName, Collection<String> t) throws Exception {
		if (queue != null) {
			throw new Exception("Cannot rebinding");
		}
		queue = queueName;
		topics.addAll(t);
	}

	public ByteMessage poll() throws Exception {
		ByteMessage re = loadByteMessage(topics.get(currentTopic));
		if (re != null) {
			return re;
		} else {
			// If re == null means that current topic isnt exist or is done with it;
			// Move to next topic;
			// If All of topics have been read;
			if (++currentTopic >= topics.size())
				return null;
			else
				return poll();
		}
	}
	/*
	 * //一个consumer绑定4个topic List<String> topics = new LinkedList<>(); int
	 * readPos = 0; String queue;
	 * 
	 * public void attachQueue(String queueName, Collection<String> t) throws
	 * Exception { if (queue != null) { throw new Exception("只允许绑定一次"); } queue
	 * = queueName; topics.addAll(t); }
	 * 
	 * public ByteMessage poll() throws IOException { ByteMessage re =
	 * MessageStore.store.pull(queue,topics.get(readPos)); if (re != null) {
	 * return re; }else { readPos++; if(readPos >= topics.size()) return null;
	 * else return poll(); } ByteMessage re = null; //先读第一个topic, 再读第二个topic...
	 * //直到所有topic都读完了, 返回null, 表示无消息 for (int i = 0; i < topics.size(); i++) {
	 * int index = (i + readPos) % topics.size(); re =
	 * MessageStore.store.pull(queue, topics.get(index)); if (re != null) {
	 * readPos = index + 1; break; } } return re; }
	 */
}
