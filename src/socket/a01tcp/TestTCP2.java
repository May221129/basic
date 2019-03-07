package socket.a01tcp;
//TCP����������ӿͻ��˷����ļ�������ˣ�����˱��浽���ء������ء����ͳɹ������ͻ��ˡ����ر���Ӧ�����ӣ�
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
//	�ͻ��ˣ�
	@Test
	public void test(){
		Socket socket = null;
		OutputStream os = null;
		FileInputStream fis = null;
		InputStream is = null;
		try {
//		�����ļ�������ˣ�
//			����Socket�Ķ���
			socket = new Socket(InetAddress.getByName("192.168.0.104"), 8989);
//			�ӱ��ػ�ȡһ���ļ����͸�����ˣ�
			os = socket.getOutputStream();
			fis = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\��ֽ1.jpg"));
			byte[] b = new byte[1024];
			int len;
			while((len = fis.read(b)) != -1){
				os.write(b,0,len);
			}
			socket.shutdownOutput();//��ʽ�ĸ��߷���˷������	
			
//			���շ���˻ظ�����Ϣ�� 
			is = socket.getInputStream();
			byte[] b1 = new byte[20];
			int len1;
			while((len1 = is.read(b1)) != -1){
				String str = new String(b1, 0, len1);
				System.out.print(str);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{//�ر���Ӧ������Socket����
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
	
//	����ˣ�
	@Test
	public void  test1(){
		ServerSocket ss = null;
		Socket socket = null;
		InputStream is = null;
		FileOutputStream fos = null;
		OutputStream os = null;
		try {
//			���տͻ��˷������ļ��������浽ָ���ļ����У�
			ss = new ServerSocket(8989);
			socket = ss.accept();
			is = socket.getInputStream();
			fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\��ֽ2.jpg"));
			byte[] b = new byte[1024];
			int len;
			while((len = is.read(b)) != -1){
				fos.write(b, 0, len);
			}
//			�ظ���Ϣ���ͻ��ˣ�
			os = socket.getOutputStream();
			os.write("�����͵��ļ������յ���лл��".getBytes());
			
			System.out.println("�յ�������" + socket.getInetAddress().getHostAddress() + "���ļ�");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{//�ر���Ӧ������Socket��ServerSocket����
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
