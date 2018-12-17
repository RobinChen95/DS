package pku;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Producer {
	private boolean first = true;
	private boolean firstWrite = true;
	private int id;
	private int count = 0;
	static final int COUNT_SIZE = 5000;
	HashMap<String, LinkedList<ByteMessage>> msgs = new HashMap<>();
	static final String[] keylist = { "MessageId", "Topic", "BornTimestamp", "BornHost", "StoreTimestamp", "StoreHost",
			"StartTime", "StopTime", "Timeout", "Priority", "Reliability", "SearchKey", "ScheduleExpression",
			"ShardingKey", "ShardingPartition", "TraceId" };
	WriteToFile w;

    {
        boolean executed = false;
        synchronized (this){
            if (!executed){
                File file = new File("data");
                if (file.exists())
                    deleteDir(file);
                file.mkdirs();
                executed = true;
            }
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

	public ByteMessage createBytesMessageToTopic(String topic, byte[] body) throws Exception {
		ByteMessage msg = new DefaultMessage(body);
		msg.putHeaders(MessageHeader.TOPIC, topic);
		return msg;
	}

	public void send(ByteMessage defaultMessage) throws Exception {
		String topic = defaultMessage.headers().getString(MessageHeader.TOPIC);
		if (first) {
			id = (int) Thread.currentThread().getId() % 4;
			first = false;
		}
		if (!msgs.containsKey(topic)) {
			msgs.put(topic, new LinkedList<ByteMessage>());
		}
		// 加入消息
		((LinkedList<ByteMessage>) msgs.get(topic)).add(defaultMessage);
		if (++count >= COUNT_SIZE) {
			// WriteToFile(id);
			if (!firstWrite) {
				w.join();
			} else {
				firstWrite = false;
			}
			w = new WriteToFile(msgs);
			w.start();
			msgs = new HashMap<String, LinkedList<ByteMessage>>();
			count = 0;
		}
	}

	public void flush() throws Exception {
		// MessageStore.store.WriteToFile((int)Thread.currentThread().getId()%4);//一个线程结束时调用
	}

	class WriteToFile extends Thread {

		private HashMap<String, LinkedList<ByteMessage>> msgs = new HashMap<>();

		public WriteToFile(HashMap msgs) {
			this.msgs = msgs;
		}

		@Override
		public void run() {
			try {
				byte[] buf = new byte[205000];
				for (Object key : msgs.keySet()) {// 先取出Key值，将相同的Topic放到一个文件
					FileOutputStream fos = new FileOutputStream("./data/" + (String) key + id + ".txt", true);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					List<ByteMessage> msglist = (List<ByteMessage>) msgs.get(key);
					// RandomAccessFile
					for (ByteMessage msg : msglist) {
//						int buf_size = msg.getBody().length + 512;
						int len = 0;
						byte[] hkey = new byte[2];
						byte[] vtype = new byte[4];
						for (int i = 0; i < 16; ++i) {
							Object obj = msg.headers().getObj(keylist[i]);
							if (obj != null) {
								int j = i / 4;
								int off = i % 4;
								if (obj instanceof Integer) {
									
								} else if (obj instanceof Double) {
									if (off == 0) {
										vtype[j] = (byte) (vtype[j] | (byte) 0x40);
									} else if (off == 1) {
										vtype[j] = (byte) (vtype[j] | (byte) 0x10);
									} else if (off == 2) {
										vtype[j] = (byte) (vtype[j] | (byte) 0x04);
									} else {
										vtype[j] = (byte) (vtype[j] | (byte) 0x01);
									}
								} else if (obj instanceof Long) {
									if (off == 0) {
										vtype[j] = (byte) (vtype[j] | (byte) 0x80);
									} else if (off == 1) {
										vtype[j] = (byte) (vtype[j] | (byte) 0x20);
									} else if (off == 2) {
										vtype[j] = (byte) (vtype[j] | (byte) 0x08);
									} else {
										vtype[j] = (byte) (vtype[j] | (byte) 0x02);
									}
								} else {
									if (off == 0) {
										vtype[j] = (byte) (vtype[j] | (byte) 0xc0);
									} else if (off == 1) {
										vtype[j] = (byte) (vtype[j] | (byte) 0x30);
									} else if (off == 2) {
										vtype[j] = (byte) (vtype[j] | (byte) 0x0c);
									} else {
										vtype[j] = (byte) (vtype[j] | (byte) 0x03);
									}
								}
								String h = keylist[i];
								switch (h) {
								case "MessageId":
									hkey[0] = (byte) (hkey[0] | (byte) 0x80);
									break;
								case "Topic":
									hkey[0] = (byte) (hkey[0] | 0x40);
									break;
								case "BornTimestamp":
									hkey[0] = (byte) (hkey[0] | 0x20);
									break;
								case "BornHost":
									hkey[0] = (byte) (hkey[0] | 0x10);
									break;
								case "StoreTimestamp":
									hkey[0] = (byte) (hkey[0] | 0x08);
									break;
								case "StoreHost":
									hkey[0] = (byte) (hkey[0] | 0x04);
									break;
								case "StartTime":
									hkey[0] = (byte) (hkey[0] | 0x02);
									break;
								case "StopTime":
									hkey[0] = (byte) (hkey[0] | 0x01);
									break;
								case "Timeout":
									hkey[1] = (byte) (hkey[1] | (byte) 0x80);
									break;
								case "Priority":
									hkey[1] = (byte) (hkey[1] | 0x40);
									break;
								case "Reliability":
									hkey[1] = (byte) (hkey[1] | 0x20);
									break;
								case "SearchKey":
									hkey[1] = (byte) (hkey[1] | 0x10);
									break;
								case "ScheduleExpression":
									hkey[1] = (byte) (hkey[1] | 0x08);
									break;
								case "ShardingKey":
									hkey[1] = (byte) (hkey[1] | 0x04);
									break;
								case "ShardingPartition":
									hkey[1] = (byte) (hkey[1] | 0x02);
									break;
								case "TraceId":
									hkey[1] = (byte) (hkey[1] | 0x01);
									break;
								}
							}
						}
						System.arraycopy(hkey, 0, buf, len, 2);
						len += 2;
						System.arraycopy(vtype, 0, buf, len, 4);
						len += 4;
						for (int i = 0; i < 16; ++i) {
							Object obj = msg.headers().getObj(keylist[i]);
							if (obj != null) {
								if (obj instanceof Integer) {
//									buf[len++] = 'i';
									System.arraycopy(Utils.intToBytes((int) obj), 0, buf, len, 4);
									len += 4;
								} else if (obj instanceof Double) {
//									buf[len++] = 'd';
									System.arraycopy(Utils.doubleToBytes((double) obj), 0, buf, len, 8);
									len += 8;
								} else if (obj instanceof Long) {
//									buf[len++] = 'l';
									System.arraycopy(Utils.longToBytes((long) obj), 0, buf, len, 8);
									len += 8;
								} else {
//									buf[len++] = 's';
									System.arraycopy(Utils.shortToBytes((short) ((String) obj).length()), 0, buf, len, 2);
									len += 2;
									System.arraycopy(((String) obj).getBytes(), 0, buf, len, ((String) obj).length());
									len += ((String) obj).length();
								}
							}
						}
						System.arraycopy(Utils.intToBytes(msg.getBody().length), 0, buf, len, 4);
						len += 4;
						System.arraycopy(msg.getBody(), 0, buf, len, msg.getBody().length);
						len += msg.getBody().length;

						if (len > 1024) {
							byte[] com_buf = Utils.compress(buf, 0, len);
							bos.write('c');
							bos.write(Utils.intToBytes(com_buf.length));
							bos.write(com_buf);
						} else {
							bos.write('u');
							bos.write(Utils.shortToBytes((short) len));
							bos.write(buf, 0, len);
						}
					}
					bos.flush();
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}
}