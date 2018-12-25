package IP_Phone;

import java.awt.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;

public class Server extends JFrame implements Runnable, ActionListener {

	
	private static final long serialVersionUID = 1L;
	int port; 
	SourceDataLine line;
	DatagramSocket socket = null; 
	JButton start_button = new JButton("��ʼ�����ź�"); 
	Thread thread; 
	String msg = null;
	boolean StopFlag = false; 
	
	public Server() { 
		
		setTitle("IP Phone");
		AudioFormat format=new AudioFormat(8000, 16, 2, true, true);
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		thread = new Thread(this);
		start_button.addActionListener(this); //ע��Listener 
		JPanel north = new JPanel(); 
		north.add(start_button);
		add(north, BorderLayout.NORTH);
		port = 7091; // �˿ں�
		try {
			socket = new DatagramSocket(port);
			line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format, 1024);
            line.start();
    		setBounds(300,300,200,200);
    		setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) { 
		if (e.getSource() == start_button) { 
			if (!(thread.isAlive())) { 
				thread = new Thread(this); 
			}
			thread.start(); }
		}
	
	public void run() { 
		while (true) {
			byte data[] = new byte[1024];
			DatagramPacket packet = null; 
			packet = new DatagramPacket(data, data.length);	
			try {
				socket.receive(packet); // ��������
				JTextArea showing = new JTextArea();
				showing.setText(msg);
				validate();
				add(showing,BorderLayout.CENTER);
				line.write(data, 0, data.length);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (StopFlag) { // ����ֹͣ������
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		new Server(); 
	}
}