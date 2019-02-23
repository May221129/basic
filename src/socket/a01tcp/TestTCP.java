package socket.a01tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * �����̣�Socket-TCPЭ��
 * TCP��̰���һ���ͻ��˸�����˷�����Ϣ��������������Ϣ������̨��
 * ������ʵ���Ͼ���Socket�ı��
 */
public class TestTCP {
//	�ͻ��ˣ�
	@Test
	public void test(){
		Socket socket = null;
		OutputStream os = null;
		try {
//			1.����һ��Socket�Ķ���ͨ��������ָ������˵�IP��ַ���Լ�����ճ���Ķ˿ں�
			socket = new Socket(InetAddress.getByName("192.168.0.104"), 123);
			
//			2.getOutputStream()���������ݣ���������OutputStream�Ķ���
			os = socket.getOutputStream();
			
//			 3.������������
			os.write("���ǿͷ��ˣ����Ҹ��㷢����Ϣ��".getBytes());	
			
//			4.�ر���Ӧ������Socket����:
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
//	����ˣ�
	@Test
	public void test1(){
		ServerSocket ss = null;
		Socket socket = null;
		InputStream is = null;
		try {
//			1.����һ��ServerSocket�Ķ���ͨ��������ָ������Ķ˿ں�:
			ss = new ServerSocket(123);
			
//			2.������accept()����������һ��Socket�Ķ���:
			socket = ss.accept();
			
//			3.����Socket�����getInputStream()��ȡһ���ӿͻ��˷��͹�����������:
			is = socket.getInputStream();
			
//			4.�Ի�ȡ�����������еĲ���:
			byte[] b = new byte[20];
			int len;
			while((len = is.read(b)) != -1){
				String s = new String(b, 0, len);
				System.out.print(s);
			}
			
//			 5.�ر���Ӧ�����Լ�Socket��ServerSocket�Ķ���:
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(socket != null){
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(ss != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
}
