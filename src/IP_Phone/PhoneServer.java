package IP_Phone;

import java.awt.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;

public class PhoneServer extends JFrame implements Runnable, ActionListener {

	
	private static final long serialVersionUID = 1L;
	int port; 
	SourceDataLine line;
	DatagramSocket socket = null; 
	JButton start_button = new JButton("开始");
	JButton stop_button = new JButton("ֹͣ结束");
	Thread thread; 
	String msg = null;
	boolean StopFlag = false; 
	
	public PhoneServer() { 
		
		setTitle("IP Phone");
		AudioFormat format=new AudioFormat(8000, 16, 2, true, true);
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		thread = new Thread(this);
		start_button.addActionListener(this);
		stop_button.addActionListener(this); 
		JPanel north = new JPanel(); 
		north.add(start_button); 
		north.add(stop_button);
		add(north, BorderLayout.NORTH);
		port = 7091; //
		try {
			socket = new DatagramSocket(port);
			line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format, 1024);
            line.start();
    		setBounds(500,500,300,200);
    		setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) { 
		if (e.getSource() == start_button) {
			start_button.setBackground(Color.red); 
			stop_button.setBackground(Color.green);
			if (!(thread.isAlive())) { 
				thread = new Thread(this); 
			}
			thread.start(); 
			StopFlag = false; 
		}
		if (e.getSource() == stop_button) { 
			start_button.setBackground(Color.green);
			stop_button.setBackground(Color.red);
			StopFlag = true;} 
	}
	
	public void run() { 
		while (true) {
			byte data[] = new byte[1024];
			DatagramPacket packet;
			packet = new DatagramPacket(data, data.length);	
			try {
				socket.receive(packet);
				msg="Hearing voice from IP:\n"+packet.getAddress();
				JTextArea showing = new JTextArea();
				showing.setText(msg);
				validate();
				add(showing,BorderLayout.CENTER);
				line.write(data, 0, data.length);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (StopFlag) {
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		new PhoneServer(); 
	}
}