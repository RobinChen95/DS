package IP_Phone;

import java.net.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class PhoneClient extends Thread { 
	private TargetDataLine line;
	int port = 7091; 
	InetAddress iaddress = null; 
	DatagramSocket socket = null; 
	
	PhoneClient() {
		AudioFormat format=new AudioFormat(8000, 16, 2, true, true);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		try {
        	String ip_sender = JOptionPane.showInputDialog("输入IP");
			iaddress = InetAddress.getByName(ip_sender);
			JOptionPane.showMessageDialog(null,"正在拨打电话...","IP Phone",JOptionPane.PLAIN_MESSAGE);
			socket = new DatagramSocket(5151); 
			line = (TargetDataLine)AudioSystem.getLine(info);
            line.open(format, line.getBufferSize());
            line.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() { 
		while (true) {
			DatagramPacket packet;
			byte data[] = new byte[1024];
			line.read(data, 0, data.length);
			packet = new DatagramPacket(data, data.length, iaddress, port);
			try {
				socket.send(packet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new PhoneClient().run(); 
	}
}
