package socket.a02myconversation_1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 试着做一个及时通讯工具：让用户端和服务端可以实现一直聊天。
 *  这里只支持客服端和服务端一对一聊天，不支持多设备登录。
 */
public class TestServer {
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(9090);
		Socket socket = ss.accept();
		// 接收到用户A传来的信息：
		InputStream is = socket.getInputStream();
		Receive receive = new Receive(is);
		receive.start();
		// 发送信息给用户A：
		OutputStream os = socket.getOutputStream();
		Send send = new Send(os);
		send.start();
	}
}

// 利用多线程的知识，来使程序不断地接收聊天内容：
class Receive extends Thread {
	private InputStream is = null;

	public Receive(InputStream is) {
		this.is = is;
	}

	public void run() {// 接收信息：
		try {
			while (true) {
				byte[] b = new byte[1024];
				int len;
				while ((len = is.read(b)) != -1) {
					String s = new String(b, 0, len);
					System.out.println(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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

// 利用多线程的知识，来使程序不断地发送聊天内容：
class Send extends Thread {
	private OutputStream os = null;

	public Send(OutputStream os) {
		this.os = os;
	}

	public void run() {// 发送信息：
		Scanner scanner = new Scanner(System.in);
		try {
			while (true) {
				System.out.println("请输入聊天内容：");
				String str = scanner.next();
				os.write(str.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
