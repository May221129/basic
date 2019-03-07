package jdbc.a01connectdatebase;
/**
 *  �������ݿ�ķ�ʽ������DriverManager
 * 
 * DriverManager�������Ĺ����࣬
 * ���ƣ�
 * 1�� ����ͨ�����ص� getConnection() ������ȡ���ݿ�����. ��Ϊ���㣻
 * 2������ͬʱ��������������: ��ע���˶�����ݿ�����, ����� getConnection()����ʱ����Ĳ�����ͬ, �����ز�ͬ�����ݿ����ӡ�
 */

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.junit.Test;

public class A02UseDriverManagerConnectionDatebase {
	@Test
	public void testDriverManager1() throws Exception{//DriverManager��������������
//		1. ׼���������ݿ�� 4 ���ַ�����
//		��һ����������Ϣ��
		String driverClass = "com.mysql.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "root";
//		�ڶ�����������Ϣ��//���Ӳ�ͬ�����ݿ�
		String driverClass2 = "com.mysql.jdbc.Driver";
		String jdbcUrl2 = "jdbc:mysql://localhost:3306/imooc";
		String user2 = "root";
		String password2 = "root";
		
//		2���������ݿ���������(��Ӧ�� Driver ʵ��������ע�������ľ�̬�����)��
		Class.forName(driverClass2);
		
//		3�� ͨ�� DriverManager �� getConnection() ������ȡ���ݿ����ӣ�
		Connection connection = 
				(Connection) DriverManager.getConnection(jdbcUrl2, user2, password2);
		System.out.println(connection);
	}
	
	@Test
	public void testDriverManager() throws Exception{//ͨ�õ����ӣ���δд��
//		1. ׼���������ݿ�� 4 ���ַ���.��
		String driverClass = null;//������ȫ����
		String jdbcUrl = null;//JDBC URL
		String user = null;
		String password = null;
		
//		2����ȡ��·���µ� jdbc.properties �ļ�
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
//		3���������ݿ���������(��Ӧ�� Driver ʵ��������ע�������ľ�̬�����)��
		Class.forName(driverClass);
		
//		4�� ͨ�� DriverManager �� getConnection() ������ȡ���ݿ����ӣ�
		Connection connection = 
				(Connection) DriverManager.getConnection(jdbcUrl, user, password);
		System.out.println(connection);
	}
}
