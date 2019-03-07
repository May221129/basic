package socket.a01tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.junit.Test;

public class TestTCP3 {
//	�ͻ��ˣ�
	@Test
	public void send(){	
		Socket socket = null;
		Scanner scanner = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			socket = new Socket(InetAddress.getByName("192.168.0.104"),9090);
			os = socket.getOutputStream();
//		os.write("abc".getBytes());//д���˾���Ҫ����ʲô
			System.out.println("������Сд��Ӣ����ĸ");
			scanner = new Scanner(System.in);//��������
			String str = scanner.next();
			os.write(str.getBytes());
			socket.shutdownOutput();
			
			is = socket.getInputStream();
			byte[] b = new byte[10];
			int len;
			while((len = is.read(b)) != -1){
				String str1 = new String(b,0,len);
				System.out.println(str1);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
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
			if(scanner != null){
				scanner.close();
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
	public void server(){
		ServerSocket ss = null;
		Socket socket = null;
		InputStream is = null;
		OutputStream os = null;
		try {
			ss = new ServerSocket(9090);
			socket = ss.accept();
			is = socket.getInputStream();
			byte[] b = new byte[10];
			int len;
			String str = new String();//���ﲻ��д��String str = null���������ʱ����ʾnull
			while((len = is.read(b)) != -1){
				String s = new String(b,0,len);
				str += s;
			}
			String su = str.toUpperCase();//��Сд����ĸת��Ϊ��д����ĸ
			
			os = socket.getOutputStream();
			os.write(su.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(ss != null){
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
}
