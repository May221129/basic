package socket.a03groupchat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Ⱥ�Ĺ��ߣ�֧�ֶ��豸ͬʱ��¼��ͨ��Server������User�ܹ��໥�շ���Ϣ:
 */
public class TestUser {// Ҫ����������˼��
	public void start() {// ��������
		Socket socket = null;
		Scanner scanner = null;
		OutputStream os = null;
		InputStream is = null;
		try {
//			1�������Ϸ���ˣ�
			socket = new Socket(InetAddress.getByName("0.tcp.ngrok.io"), 18262);// ���ӷ���ˣ�����ֻ��ִ��һ�Σ���
//			�Ϲ���IP��ַ�Ͷ˿ںţ�(InetAddress.getByName("0.tcp.ngrok.io"), 18262)
			
//			2�������û���������˽��е�¼��
			System.out.println("�����������û�����20�����ڣ����磺����");
			scanner = new Scanner(System.in);
			String myUserName = scanner.nextLine();
			os = socket.getOutputStream();
			os.write(myUserName.getBytes());// �����û������е�¼����¼ֻ��ִ��һ�Σ�
			
//			3�����շ�����Ƿ��¼�ɹ�����Ϣ��
			is = socket.getInputStream();
			byte[] b = new byte[100];
			int len;
			len = is.read(b);
			String login = new String(b,0,len);
			System.out.println(login);
			
//			4��������Ϣ������(�ȷ��͵������������ɷ�����ת������ĺ���)��
			Send send = new Send(myUserName, os);
			send.start();// ������Ϣ��ȥ��ѭ����
			
//			5�����պ��ѷ�������Ϣ(�Ƿ�����Ƚ����˺��ѷ�����Ϣ����ת��������):
			Receive receive = new Receive(is);
			receive.start();// �ӷ���˽�����Ϣ������ʾ�ڿ���̨�ϣ�ѭ����
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public static void main(String[] args) {// �������
		TestUser user = new TestUser();
		user.start();
	}
}

class Send extends Thread {// �̣߳�ʵ��ѭ���ĴӼ���������Ϣ�����ͳ�ȥ
	private String myUserName;
	private OutputStream os;
	public Send(String myUserName, OutputStream os) {
		this.myUserName = myUserName;
		this.os = os;
	}
	public void run() {//run()������������Ϣ��ȥ��ѭ����
		Scanner scanner = null;
		try {
			while (true) {
				scanner = new Scanner(System.in);
				String thing = scanner.nextLine();
				SimpleDateFormat sdf = new SimpleDateFormat();
				String date = sdf.format(new Date());
				String all = new String(myUserName + "@@" + date + "\n" + thing);
				byte[] b = all.getBytes();
				os.write(b);// ������Ϣ��ȥ��ѭ����
				System.out.println("<��>" + "\t" + date);//��ʾ��ǰʱ��
				System.out.println(thing);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null)
					os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(scanner != null) scanner.close();//Ч���൱��if����{}
		}
	}
}

class Receive extends Thread {// �̣߳�ʵ��ѭ���Ľ�����Ϣ����ʾ�ڿ���̨��
	InputStream is = null;

	public Receive(InputStream is) {
		this.is = is;
	}

	public void run() {
		try {
			while (true) {
				byte[] b = new byte[1024];
				int len;
				while ((len = is.read(b)) != -1) {// ������Ϣ��ѭ����
					String str = new String(b, 0, len);
					System.out.println(str);
				}
			}
		} catch (IOException e) {
			try {
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}