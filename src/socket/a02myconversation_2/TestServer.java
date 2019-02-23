package socket.a02myconversation_2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ͨѶ���ߣ�֧�ֶ��豸ͬʱ��¼��ͨ��Server������User�ܹ��໥�շ���Ϣ.
 */
public class TestServer {// Ҫ����������˼��
//	Map�Ƿ��̰߳�ȫ�ļ��ϣ�������Ҫͨ�� Collections.synchronizedMap()����ת��Ϊ�̰߳�ȫ�ļ���
	private Map<String, Socket> map = new HashMap<>();
	Map<String, Socket> userinfo = Collections.synchronizedMap(map);
	
	public void start() {// ��������
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(8090);// �˿ں�

			while (true) {
				Socket socket = ss.accept();// �Ϳͻ��˽����ܵ����ӣ�ѭ����
				LoginAndRepeater lr = new LoginAndRepeater(socket, userinfo);// ʵ�ֿͻ��˵ĵ�¼��ʵ�ֿͻ�����Ϣ��ת��
				lr.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
/*		finally {
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
*/
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
			String str = new String(b, 0, len);//�û���������20������
			if (null != str && str.equals("") && str.length() <= 20) {
				map.put(str, socket);// �����յ����û�����socket�ܵ��洢��map�����У�ѭ����
				String s = new String("��¼�ɹ��������Ժͺ��ѽ��жԻ��ˣ���ʽ:��������@@������");
				os.write(s.getBytes());// �ظ��ͻ��ˣ���֪TA��¼�ɹ���
			} else {
				String s1 = "��¼ʧ�ܣ�";
				os.write(s1.getBytes());// �ظ��ͻ��ˣ���֪TA��¼ʧ����
				return;
			}

			while (true) {//�������ݵ�ת����ѭ����
//				1�����û��������������ݴ洢��byte[] b1�У�
				byte[] chat = new byte[2048];
				int len1;
				len1 = is.read(chat);//����ֻ��ȡһ��2048�ֽ����ڵ���������
//				2�����ֽ�����תΪ�ַ�����
				String thing = new String(chat, 0, len1);
//				3�����ַ�������ĳ���ض����Ž��в�֣��õ�һ���ж��Ԫ�ص��ַ������飺
				String[] flag = thing.split("@@");
//				4�������洢�������û��˺ź͹ܵ���Ϣ�ļ��ϣ����жϼ������Ƿ����û���Ҫ����Ķ���(��Ϣ�����Ľ��շ�)��
				Set<String> user = map.keySet();
				for(String userName : user){
					if(userName.equals(flag[flag.length - 1]) == true){//����������н��շ�������Ϣת�������շ�
//						4�����ַ��������е����һ��Ԫ�أ�ָ�����������ݽ��շ�����map����(map(�û���, socket))�л�ȡ���õ����շ���socket�ܵ�������ͨ�ţ�
						Socket socket = map.get(flag[flag.length - 1]);
						os1 = socket.getOutputStream();
//						5���ȸ�֪���շ����յ�������������˭������,�����������������ͨ�����շ���socket���͸����շ�:
						String all = new String("<" + flag[0] + ">" + "��" + flag[1]);
						os1.write(all.getBytes());
					}else{//���������û�н��շ������֪��Ϣ���ͷ������շ������ߣ�
						String strchat = "�û�<" + flag[flag.length - 1] + ">�����ߣ�TA�޷����������͵���Ϣ��";
						os.write(strchat.getBytes());
					}
				}

			}
		} catch (IOException e) {
			System.out.println("С����ע�⣬�пͻ����߿�~");
		} 
/*		finally {
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
*/
	}
}