package socket.a01tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * 网络编程：Socket-TCP协议
 * TCP编程案例一：客户端给服务端发送信息。服务端输出此信息到控制台上
 * 网络编程实际上就是Socket的编程
 */
public class TestTCP {
//	客户端：
	@Test
	public void test(){
		Socket socket = null;
		OutputStream os = null;
		try {
//			1.创建一个Socket的对象，通过构造器指明服务端的IP地址，以及其接收程序的端口号
			socket = new Socket(InetAddress.getByName("192.168.0.104"), 123);
			
//			2.getOutputStream()：发送数据，方法返回OutputStream的对象
			os = socket.getOutputStream();
			
//			 3.具体的输出过程
			os.write("我是客服端，是我给你发的信息。".getBytes());	
			
//			4.关闭相应的流和Socket对象:
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
//	服务端：
	@Test
	public void test1(){
		ServerSocket ss = null;
		Socket socket = null;
		InputStream is = null;
		try {
//			1.创建一个ServerSocket的对象，通过构造器指明自身的端口号:
			ss = new ServerSocket(123);
			
//			2.调用其accept()方法，返回一个Socket的对象:
			socket = ss.accept();
			
//			3.调用Socket对象的getInputStream()获取一个从客户端发送过来的输入流:
			is = socket.getInputStream();
			
//			4.对获取的输入流进行的操作:
			byte[] b = new byte[20];
			int len;
			while((len = is.read(b)) != -1){
				String s = new String(b, 0, len);
				System.out.print(s);
			}
			
//			 5.关闭相应的流以及Socket、ServerSocket的对象:
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
