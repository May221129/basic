package socket.a01url_inetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

//网络编程：InetAddress
public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		
		InetAddress inet = InetAddress.getByName("www.baidu.com");//创建InetAddress的对象
		System.out.println(inet);
		InetAddress inet1 = InetAddress.getByName("14.215.177.38");//创建InetAddress的对象
		System.out.println(inet1);
		System.out.println(inet.getHostAddress());//获取IP地址
		System.out.println(inet.getHostName());//获取域名
		
		System.out.println();
		
		InetAddress inet2 = InetAddress.getLocalHost();//获取本机的域名和IP地址
		System.out.println(inet2);
		System.out.println(inet2.getHostName());//获取本机的域名
		System.out.println(inet2.getHostAddress());//获取本机的IP地址
	}
}
