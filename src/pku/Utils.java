package pku;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Utils {

	public static byte[] longToBytes(long x) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(x);
		return buffer.array();
	}

	public static long bytesToLong(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.put(bytes);
		buffer.flip();
		return buffer.getLong();
	}

	public static byte[] doubleToBytes(double x) {
		ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
		buffer.putDouble(x);
		return buffer.array();
	}

	public static double bytesToDouble(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
		buffer.put(bytes);
		buffer.flip();
		return buffer.getDouble();
	}

	public static byte[] intToBytes(int x) throws IOException {
		return new byte[] { (byte) ((x >> 24) & 0xFF), (byte) ((x >> 16) & 0xFF), (byte) ((x >> 8) & 0xFF),
				(byte) (x & 0xFF)};
	}

	public static int bytesToInt(byte[] bytes) throws IOException {
		return bytes[3] & 0xFF | (bytes[2] & 0xFF) << 8 | (bytes[1] & 0xFF) << 16 | (bytes[0] & 0xFF) << 24;
	}

	public static byte[] shortToBytes(Short v) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream d = new DataOutputStream(baos);
		d.writeShort(v);
		byte[] bytes = baos.toByteArray();
		d.close();
		return bytes;
	}

	public static Short bytesToShort(byte[] bytes) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		DataInputStream d = new DataInputStream(bais);
		Short result = d.readShort();
		d.close();
		return result;
	}

	public static byte[] compress(byte[] bytes, int off, int len) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(bytes, off, len);
		gzip.close();

		return out.toByteArray();
	}

	public static byte[] uncompress(byte[] bytes) throws Exception {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);

		GZIPInputStream ungzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = ungzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}

		return out.toByteArray();
	}
}
