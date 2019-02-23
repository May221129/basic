package socket.a03groupchat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Ⱥ�Ĺ��ߣ�֧�ֶ��豸ͬʱ��¼��ͨ��Server������User�ܹ��໥�շ���Ϣ: * @author Administrator
 */
public class TestServer {// Ҫ����������˼��
	private Map<String, Socket> map = new HashMap<>();

	public void start() {// ��������
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(8090);// �˿ں�

			while (true) {
				Socket socket = ss.accept();// �Ϳͻ��˽����ܵ����ӣ�ѭ����
				LoginAndRepeater lr = new LoginAndRepeater(socket, map);// ʵ�ֿͻ��˵ĵ�¼��ʵ�ֿͻ�����Ϣ��ת��
				lr.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {// �������
		TestServer server = new TestServer();
		server.start();
	}
}

class LoginAndRepeater extends Thread {// �̣߳�ʵ�ֿͻ��˵ĵ�¼��ʵ�ֿͻ�����Ϣ��ת��
	private Socket socket = null;
	private Map<String, Socket> map;

	public LoginAndRepeater(Socket socket, Map<String, Socket> map) {
		this.socket = socket;
		this.map = map;
	}

	@Override
	public void run() {
		InputStream is = null;
		OutputStream os = null;
		OutputStream os1 = null;
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
			byte[] b = new byte[100];
			int len;
			len = is.read(b);
			String str = new String(b, 0, len);
			if (str.length() <= 20) {
				map.put(str, socket);// �����յ����û�����socket�ܵ��洢��map�����У�ѭ����
				String s = new String("��¼�ɹ��������Ժͺ��ѽ��жԻ��ˣ�");
				os.write(s.getBytes());// �ظ��ͻ��ˣ���֪TA��¼�ɹ���
			} else {
				String s1 = "��¼ʧ�ܣ�";
				os.write(s1.getBytes());// �ظ��ͻ��ˣ���֪TA��¼ʧ����
				return;
			}
			while (true) {// �������ݵ�ת����ѭ����
				// 1�����û��������������ݴ洢��byte[] b1�У�
				byte[] b1 = new byte[2048];
				int len1;
				len1 = is.read(b1);// ����ֻ��ȡһ��2048�ֽ����ڵ���������
				// 2�����ֽ�����תΪ�ַ�����
				String thing = new String(b1, 0, len1);
				// 3�����ַ�������ĳ���ض����Ž��в�֣��õ�һ���ж��Ԫ�ص��ַ������飺
				String[] flag = thing.split("@@");
				// 4����map����(map(�û���, socket))�г��˷��������������û�֮��������û����ý��շ����õ����շ���socket�ܵ�������ͨ�ţ�
				Set<String> s = map.keySet();
				for(String key : s){
					if(key.equals(flag[0])){
						
					}else{
						Socket socket = map.get(key);
						os1 = socket.getOutputStream();
						// 5���ȸ�֪���շ����յ�������������˭������,�ٽ��������������ͨ�����շ���socket���͸����շ�:
						String chat = new String("<" + flag[0] + ">" + "\t" + flag[1]);
						os1.write(chat.getBytes());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os1 != null) {
				try {
					os1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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