package socket.a01url_inetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

//�����̣�InetAddress
public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		
		InetAddress inet = InetAddress.getByName("www.baidu.com");//����InetAddress�Ķ���
		System.out.println(inet);
		InetAddress inet1 = InetAddress.getByName("14.215.177.38");//����InetAddress�Ķ���
		System.out.println(inet1);
		System.out.println(inet.getHostAddress());//��ȡIP��ַ
		System.out.println(inet.getHostName());//��ȡ����
		
		System.out.println();
		
		InetAddress inet2 = InetAddress.getLocalHost();//��ȡ������������IP��ַ
		System.out.println(inet2);
		System.out.println(inet2.getHostName());//��ȡ����������
		System.out.println(inet2.getHostAddress());//��ȡ������IP��ַ
	}
}
