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
 * 群聊工具：支持多设备同时登录，通过Server，各个User能够相互收发信息: * @author Administrator
 */
public class TestServer {// 要有面相对象的思想
	private Map<String, Socket> map = new HashMap<>();

	public void start() {// 启动方法
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(8090);// 端口号

			while (true) {
				Socket socket = ss.accept();// 和客户端建立管道连接（循环）
				LoginAndRepeater lr = new LoginAndRepeater(socket, map);// 实现客户端的登录，实现客户端信息的转发
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

	public static void main(String[] args) {// 程序入口
		TestServer server = new TestServer();
		server.start();
	}
}

class LoginAndRepeater extends Thread {// 线程：实现客户端的登录，实现客户端信息的转发
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
				map.put(str, socket);// 将接收到的用户名和socket管道存储到map集合中（循环）
				String s = new String("登录成功！您可以和好友进行对话了！");
				os.write(s.getBytes());// 回复客户端，告知TA登录成功了
			} else {
				String s1 = "登录失败！";
				os.write(s1.getBytes());// 回复客户端，告知TA登录失败了
				return;
			}
			while (true) {// 聊天内容的转发（循环）
				// 1、将用户发来的聊天内容存储在byte[] b1中：
				byte[] b1 = new byte[2048];
				int len1;
				len1 = is.read(b1);// 这里只读取一次2048字节以内的聊天内容
				// 2、将字节数组转为字符串：
				String thing = new String(b1, 0, len1);
				// 3、将字符串按照某个特定符号进行拆分，得到一个有多个元素的字符串数组：
				String[] flag = thing.split("@@");
				// 4、将map数组(map(用户名, socket))中除了发聊天内容来的用户之外的所有用户作用接收方，得到接收方的socket管道来用作通信：
				Set<String> s = map.keySet();
				for(String key : s){
					if(key.equals(flag[0])){
						
					}else{
						Socket socket = map.get(key);
						os1 = socket.getOutputStream();
						// 5、先告知接收方，收到的聊天内容是谁发来的,再将具体的聊天内容通过接收方的socket发送给接收方:
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