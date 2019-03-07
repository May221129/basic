package jdbc.a01connectdatebase;
/**
 * �������ݿ�ķ�ʽһ����Driver����
 *  
 * ͨ��jdbc�������ݿ�ľ��岽�裺
 *  public Connection getConnection2() throws Exception{
 * 	1. ׼���������ݿ�� 4 ���ַ���. 
 * 	 1). ���� Properties ����
 * 	  Properties properties = new Properties();
 * 	
 * 	 2). ��ȡ jdbc.properties ��Ӧ��������
 * 	  InputStream in = 
 * 		this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
 * 	
 * 	 3). ���� 2�� ��Ӧ��������
 * 	  properties.load(in);
 * 	
 * 	 4). ������� user, password ��4 ���ַ���. 
 *    String user = properties.getProperty("user");
 * 	  String password = properties.getProperty("password");
 * 	  String jdbcUrl = properties.getProperty("jdbcUrl");
 * 	  String driver = properties.getProperty("driver");
 * 	
 * 	2. �������ݿ���������(��Ӧ�� Driver ʵ��������ע�������ľ�̬�����.)
 * 	 Class.forName(driver);
 * 	
 * 	3. ͨ�� DriverManager �� getConnection() ������ȡ���ݿ�����. 
 * 	 return DriverManager.getConnection(jdbcUrl, user, password);
 * }
 */
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import org.junit.Test;

import com.mysql.jdbc.Driver;

/**
 * �����������ͨ�õģ�
 * Driver:��һ�����ݿ⳧�̱����ṩʵ�ֵĽӿڣ��ܹ����л�ȡ���ݿ����ӡ�
 */
public class A01UseDriverConnectDatabase {
	@Test
	public  void  test1() throws Exception{//�������ݿ�test
//		1������һ��Driverʵ����Ķ���
		Driver driver = new Driver();
		
//		2��׼���������ݿ�Ļ�����Ϣ��url,user,password:
		String url = "jdbc:mysql://localhost:3306/test";//"jdbc:mysql://127.0.0.1:3306/test"
		Properties info = new Properties();//�����ĸ������ Properties��
		info.put("user", "root");
		info.put("password", "root");
		
//		3������Driver�ӿڵ�connect��url,info����ȡ���ݿ����ӣ�
		Connection connection = driver.connect(url, info);
		System.out.println(connection);
	}
	
/**
 * Ҫ�󣺱�дһ��ͨ�õķ������ڲ��޸ĳ��������£����Ի�ȡ�κ����ݿ�����ӣ�
 * ��������������ݿ�����Driverʵ�����ȫ����url,user,password����һ�������ļ��У�
 * ͨ���޸������ļ��ķ�ʽʵ�ֺ;�������ݿ���
 * �������������ʾ��
 */
	public Connection getConnection() throws Exception{
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		
		//��ȡ��·���µ� jdbc.properties �ļ�
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
		//ͨ�����䳣�� Driver ����. 
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		
		Properties info = new Properties();
		info.put("user", user);
		info.put("password", password);
		
		//ͨ�� Driver �� connect ������ȡ���ݿ�����. 
		Connection connection = driver.connect(jdbcUrl, info);
		
		return connection;
	}
	@Test
	public void testGetConnection() throws Exception{//��������ķ���ִ�н��
		System.out.println(getConnection());
	}
}