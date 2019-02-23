package socket.a02myconversation_1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * ������һ����ʱͨѶ���ߣ����û��˺ͷ���˿���ʵ��һֱ���졣
 *  ����ֻ֧�ֿͷ��˺ͷ����һ��һ���죬��֧�ֶ��豸��¼��
 */
public class TestServer {
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(9090);
		Socket socket = ss.accept();
		// ���յ��û�A��������Ϣ��
		InputStream is = socket.getInputStream();
		Receive receive = new Receive(is);
		receive.start();
		// ������Ϣ���û�A��
		OutputStream os = socket.getOutputStream();
		Send send = new Send(os);
		send.start();
	}
}

// ���ö��̵߳�֪ʶ����ʹ���򲻶ϵؽ����������ݣ�
class Receive extends Thread {
	private InputStream is = null;

	public Receive(InputStream is) {
		this.is = is;
	}

	public void run() {// ������Ϣ��
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

// ���ö��̵߳�֪ʶ����ʹ���򲻶ϵط����������ݣ�
class Send extends Thread {
	private OutputStream os = null;

	public Send(OutputStream os) {
		this.os = os;
	}

	public void run() {// ������Ϣ��
		Scanner scanner = new Scanner(System.in);
		try {
			while (true) {
				System.out.println("�������������ݣ�");
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
