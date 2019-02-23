package socket.a01udp;
//�����̣�UDP
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

//�����̣�Socket��UDP��̵�ʵ�֣�
import org.junit.Test;

public class TestUDP {
	@Test
	public void send(){//���Ͷˣ�
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket();
			byte[] b = "���Ƿ��Ͷ˷��������ݣ��������ң��м�������".getBytes();
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
	public void Server(){//����ˣ�
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
