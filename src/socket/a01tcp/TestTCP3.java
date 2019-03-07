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
//	客户端：
	@Test
	public void send(){	
		Socket socket = null;
		Scanner scanner = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			socket = new Socket(InetAddress.getByName("192.168.0.104"),9090);
			os = socket.getOutputStream();
//		os.write("abc".getBytes());//写死了具体要发送什么
			System.out.println("请输入小写的英文字母");
			scanner = new Scanner(System.in);//键盘输入
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
//	服务端：
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
			String str = new String();//这里不能写成String str = null，否则输出时会显示null
			while((len = is.read(b)) != -1){
				String s = new String(b,0,len);
				str += s;
			}
			String su = str.toUpperCase();//将小写的字母转换为大写的字母
			
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
