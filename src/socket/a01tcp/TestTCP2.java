package socket.a01tcp;
//TCP编程例三：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端。并关闭相应的连接：
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import org.junit.Test;

public class TestTCP2 {
//	客户端：
	@Test
	public void test(){
		Socket socket = null;
		OutputStream os = null;
		FileInputStream fis = null;
		InputStream is = null;
		try {
//		发送文件给服务端：
//			创建Socket的对象：
			socket = new Socket(InetAddress.getByName("192.168.0.104"), 8989);
//			从本地获取一个文件发送给服务端：
			os = socket.getOutputStream();
			fis = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\壁纸1.jpg"));
			byte[] b = new byte[1024];
			int len;
			while((len = fis.read(b)) != -1){
				os.write(b,0,len);
			}
			socket.shutdownOutput();//显式的告诉服务端发送完毕	
			
//			接收服务端回复的信息： 
			is = socket.getInputStream();
			byte[] b1 = new byte[20];
			int len1;
			while((len1 = is.read(b1)) != -1){
				String str = new String(b1, 0, len1);
				System.out.print(str);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{//关闭相应的流、Socket对象：
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis != null){
				try {
					fis.close();
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
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
//	服务端：
	@Test
	public void  test1(){
		ServerSocket ss = null;
		Socket socket = null;
		InputStream is = null;
		FileOutputStream fos = null;
		OutputStream os = null;
		try {
//			接收客户端发来的文件，并保存到指定文件夹中：
			ss = new ServerSocket(8989);
			socket = ss.accept();
			is = socket.getInputStream();
			fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\壁纸2.jpg"));
			byte[] b = new byte[1024];
			int len;
			while((len = is.read(b)) != -1){
				fos.write(b, 0, len);
			}
//			回复信息给客户端：
			os = socket.getOutputStream();
			os.write("您发送的文件我已收到，谢谢！".getBytes());
			
			System.out.println("收到来自于" + socket.getInetAddress().getHostAddress() + "的文件");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{//关闭相应的流、Socket及ServerSocket对象：
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null){
				try {
					fos.close();
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
