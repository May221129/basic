package socket.a02myconversation_1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * ������һ����ʱͨѶ���ߣ����û��˺ͷ���˿���ʵ��һֱ���졣
 * ����ֻ֧�ֿͷ��˺ͷ����һ��һ���죬��֧�ֶ��豸��¼��
 */
public class TestUser {
	public static void main(String[] args)throws Exception {
		Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9090);
		
/*		while(true){
//		����������ֱ��ʹ��while(true)����Ȼ����ķ�����Ϣ�ͽ�����Ϣ�ͻ����Ⱥ�ִ��˳��
//		������ǣ��õȵ���Ϣ���ͳ�ȥ�ˣ����ܽӵ��Է��ظ������ݣ��������ǲ��ܶԷ��ظ����ظ����Ҷ����Է�����Ϣ��ȥ
//==>�ص㣺��ȷCPU�������ﱻ���𡢶�������������֪�����������ͣ
//			������Ϣ��
			System.out.println("�������������ݣ�");
			String str = scanner.next();//����CPU�ᱻ��ͣ���ȴ��û�����
			os.write(str.getBytes());//���������������ݺܴ�Ҳ��������ȴ���������	
//			������Ϣ��
			InputStream is = socket.getInputStream();
			byte[] b = new byte[1024];
			int len;
			while((len = is.read(b)) != -1){//����CUP���ݶ����ȴ������ݴ�������ٶ�ȡ��b������
				String s = new String(b,0,len);
				System.out.println(s);
			}
		}
*/
		
//==>�������������ķ�����Ϣ�ͽ�����Ϣ�����Կ���ʹ�ö��̵߳ķ�ʽ��ʵ�֣�
//		������Ϣ��
		OutputStream os = socket.getOutputStream();
		Send1 send1 = new Send1(os);
		send1.start();	
//		������Ϣ��
		InputStream is = socket.getInputStream();
		Receive1 receive1 = new Receive1(is);
		receive1.start();
	}
}
//���ö��̵߳�֪ʶ����ʹ���򲻶ϵط����������ݣ�
class Send1 extends Thread{
	private OutputStream os = null;
	public Send1(OutputStream os){
		this.os = os;
	}
	public void run(){//������Ϣ��
		Scanner scanner = null;
		try {
			while(true){
				System.out.println("�������������ݣ�");
				scanner = new Scanner(System.in);
				String str = scanner.next();//����CPU�ᱻ��ͣ���ȴ��û�����
				os.write(str.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(scanner != null){
				scanner.close();
			}
		}
	}
}
//���ö��̵߳�֪ʶ����ʹ���򲻶ϵؽ����������ݣ�
class Receive1 extends Thread{
	private InputStream is = null;
	public Receive1(InputStream is){
		this.is = is;
	}
	public void run(){//������Ϣ��
		try {
			while(true){
				byte[] b = new byte[1024];
				int len;
				while((len = is.read(b)) != -1){
					String s = new String(b,0,len);
					System.out.println(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
