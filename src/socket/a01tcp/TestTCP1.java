package socket.a01tcp;
//TCP编程例二：客户端给服务端发送信息，服务端将信息打印到控制台上，同时发送“已收到信息”给客户端
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class TestTCP1 {
//	客户端：
	@Test
	public void test(){
		Socket socket = null;
		OutputStream os = null;
		InputStream is = null;
		try {
//			发送消息给服务端：
			socket = new Socket(InetAddress.getByName("192.168.0.104"), 5566);
			os = socket.getOutputStream();
			os.write("我是客服端，我喜欢你。".getBytes());
			
//		shutdownOutput():显式的告诉服务端发送完毕。
			socket.shutdownOutput();
			
//			接收服务端回复的消息：
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
	
//	服务端：
	@Test
	public void test1(){
		ServerSocket ss = null;
		Socket s = null;
		InputStream is = null;
		OutputStream os = null;
		try {
//			接收客户端发来的消息：
			ss = new ServerSocket(5566);
			s = ss.accept();
			is = s.getInputStream();
			byte[] b = new byte[20];
			int len;
			while((len = is.read(b)) != -1){
				String str = new String(b, 0, len);
				System.out.print(str);
			}
			
//			回复消息给客户端：
			os = s.getOutputStream();
			os.write("你的心意我也收到，有空一起出来吃饭吧！".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{//关闭相应的流、socket：
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
