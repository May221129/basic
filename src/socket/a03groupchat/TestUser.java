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
 * 群聊工具：支持多设备同时登录，通过Server，各个User能够相互收发信息:
 */
public class TestUser {// 要有面相对象的思想
	public void start() {// 启动方法
		Socket socket = null;
		Scanner scanner = null;
		OutputStream os = null;
		InputStream is = null;
		try {
//			1、连接上服务端：
			socket = new Socket(InetAddress.getByName("0.tcp.ngrok.io"), 18262);// 连接服务端（连接只需执行一次）；
//			老公的IP地址和端口号：(InetAddress.getByName("0.tcp.ngrok.io"), 18262)
			
//			2、发送用户名到服务端进行登录：
			System.out.println("请输入您的用户名（20字以内），如：张三");
			scanner = new Scanner(System.in);
			String myUserName = scanner.nextLine();
			os = socket.getOutputStream();
			os.write(myUserName.getBytes());// 发送用户名进行登录（登录只需执行一次）
			
//			3、接收服务端是否登录成功的信息：
			is = socket.getInputStream();
			byte[] b = new byte[100];
			int len;
			len = is.read(b);
			String login = new String(b,0,len);
			System.out.println(login);
			
//			4、发送信息给好友(先发送到服务器，再由服务器转发给你的好友)：
			Send send = new Send(myUserName, os);
			send.start();// 发送信息出去（循环）
			
//			5、接收好友发来的信息(是服务端先接受了好友发的信息，再转发过来的):
			Receive receive = new Receive(is);
			receive.start();// 从服务端接收信息，并显示在控制台上（循环）
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public static void main(String[] args) {// 程序入口
		TestUser user = new TestUser();
		user.start();
	}
}

class Send extends Thread {// 线程：实现循环的从键盘输入信息并发送出去
	private String myUserName;
	private OutputStream os;
	public Send(String myUserName, OutputStream os) {
		this.myUserName = myUserName;
		this.os = os;
	}
	public void run() {//run()方法：发送信息出去（循环）
		Scanner scanner = null;
		try {
			while (true) {
				scanner = new Scanner(System.in);
				String thing = scanner.nextLine();
				SimpleDateFormat sdf = new SimpleDateFormat();
				String date = sdf.format(new Date());
				String all = new String(myUserName + "@@" + date + "\n" + thing);
				byte[] b = all.getBytes();
				os.write(b);// 发送信息出去（循环）
				System.out.println("<我>" + "\t" + date);//显示当前时间
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
			if(scanner != null) scanner.close();//效果相当于if（）{}
		}
	}
}

class Receive extends Thread {// 线程：实现循环的接收信息并显示在控制台上
	InputStream is = null;

	public Receive(InputStream is) {
		this.is = is;
	}

	public void run() {
		try {
			while (true) {
				byte[] b = new byte[1024];
				int len;
				while ((len = is.read(b)) != -1) {// 接收信息（循环）
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