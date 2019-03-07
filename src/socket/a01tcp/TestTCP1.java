package socket.a01tcp;
//TCP����������ͻ��˸�����˷�����Ϣ������˽���Ϣ��ӡ������̨�ϣ�ͬʱ���͡����յ���Ϣ�����ͻ���
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class TestTCP1 {
//	�ͻ��ˣ�
	@Test
	public void test(){
		Socket socket = null;
		OutputStream os = null;
		InputStream is = null;
		try {
//			������Ϣ������ˣ�
			socket = new Socket(InetAddress.getByName("192.168.0.104"), 5566);
			os = socket.getOutputStream();
			os.write("���ǿͷ��ˣ���ϲ���㡣".getBytes());
			
//		shutdownOutput():��ʽ�ĸ��߷���˷�����ϡ�
			socket.shutdownOutput();
			
//			���շ���˻ظ�����Ϣ��
			is = socket.getInputStream();
			byte[] b = new byte[20];
			int len;
			while((len = is.read(b)) != -1){
				String str = new String(b,0, len);
				System.out.print(str);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
//	����ˣ�
	@Test
	public void test1(){
		ServerSocket ss = null;
		Socket s = null;
		InputStream is = null;
		OutputStream os = null;
		try {
//			���տͻ��˷�������Ϣ��
			ss = new ServerSocket(5566);
			s = ss.accept();
			is = s.getInputStream();
			byte[] b = new byte[20];
			int len;
			while((len = is.read(b)) != -1){
				String str = new String(b, 0, len);
				System.out.print(str);
			}
			
//			�ظ���Ϣ���ͻ��ˣ�
			os = s.getOutputStream();
			os.write("���������Ҳ�յ����п�һ������Է��ɣ�".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{//�ر���Ӧ������socket��
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(s != null){
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ss != null){
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
