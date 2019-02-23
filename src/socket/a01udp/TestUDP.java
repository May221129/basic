package socket.a01udp;
//网络编程：UDP
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

//网络编程：Socket：UDP编程的实现：
import org.junit.Test;

public class TestUDP {
	@Test
	public void send(){//发送端：
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket();
			byte[] b = "我是发送端发出的数据，请收留我，感激不尽！".getBytes();
			DatagramPacket dp = new DatagramPacket(b, 0, b.length, InetAddress.getByName("192.168.0.104"), 8090);
			ds.send(dp);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(ds != null){
				ds.close();
			}
		}
	}
	@Test
	public void Server(){//服务端：
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket(8090);
			byte[] b = new byte[1024];
			DatagramPacket dp = new DatagramPacket(b, 0, b.length);
			ds.receive(dp);
			String str = new String(dp.getData(), 0, dp.getLength());
			System.out.print(str);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(ds != null){
				ds.close();
			}
		}
	}
}
