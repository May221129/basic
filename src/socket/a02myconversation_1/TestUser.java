package socket.a02myconversation_1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * 试着做一个及时通讯工具：让用户端和服务端可以实现一直聊天。
 * 这里只支持客服端和服务端一对一聊天，不支持多设备登录。
 */
public class TestUser {
	public static void main(String[] args)throws Exception {
		Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9090);
		
/*		while(true){
//		不能在这里直接使用while(true)，不然下面的发送信息和接收信息就会有先后执行顺序，
//		结果就是，得等到信息发送出去了，才能接到对方回复的内容，而不能是不管对方回复不回复，我都可以发送信息出去
//==>重点：明确CPU会在哪里被挂起、堵塞，这样才能知道程序哪里会停
//			发送信息：
			System.out.println("请输入聊天内容：");
			String str = scanner.next();//这里CPU会被暂停，等待用户输入
			os.write(str.getBytes());//这里如果输出的数据很大，也会在这里等待它输出完成	
//			接收信息：
			InputStream is = socket.getInputStream();
			byte[] b = new byte[1024];
			int len;
			while((len = is.read(b)) != -1){//这里CUP会暂定，等待有数据传输进来再读取到b数组中
				String s = new String(b,0,len);
				System.out.println(s);
			}
		}
*/
		
//==>解决方法：下面的发送信息和接收信息，可以考虑使用多线程的方式来实现：
//		发送信息：
		OutputStream os = socket.getOutputStream();
		Send1 send1 = new Send1(os);
		send1.start();	
//		接收信息：
		InputStream is = socket.getInputStream();
		Receive1 receive1 = new Receive1(is);
		receive1.start();
	}
}
//利用多线程的知识，来使程序不断地发送聊天内容：
class Send1 extends Thread{
	private OutputStream os = null;
	public Send1(OutputStream os){
		this.os = os;
	}
	public void run(){//发送信息：
		Scanner scanner = null;
		try {
			while(true){
				System.out.println("请输入聊天内容：");
				scanner = new Scanner(System.in);
				String str = scanner.next();//这里CPU会被暂停，等待用户输入
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
//利用多线程的知识，来使程序不断地接收聊天内容：
class Receive1 extends Thread{
	private InputStream is = null;
	public Receive1(InputStream is){
		this.is = is;
	}
	public void run(){//接收信息：
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
