package jdbc.a01connectdatebase;
/**
 *  连接数据库的方式二：用DriverManager
 * 
 * DriverManager是驱动的管理类，
 * 优势：
 * 1、 可以通过重载的 getConnection() 方法获取数据库连接. 较为方便；
 * 2、可以同时管理多个驱动程序: 若注册了多个数据库连接, 则调用 getConnection()方法时传入的参数不同, 即返回不同的数据库连接。
 */

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.junit.Test;

public class A02UseDriverManagerConnectionDatebase {
	@Test
	public void testDriverManager1() throws Exception{//DriverManager管理多个驱动程序：
//		1. 准备连接数据库的 4 个字符串：
//		第一个驱动的信息：
		String driverClass = "com.mysql.jdbc.Driver";
		String jdbcUrl = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "root";
//		第二个驱动的信息：//连接不同的数据库
		String driverClass2 = "com.mysql.jdbc.Driver";
		String jdbcUrl2 = "jdbc:mysql://localhost:3306/imooc";
		String user2 = "root";
		String password2 = "root";
		
//		2、加载数据库驱动程序(对应的 Driver 实现类中有注册驱动的静态代码块)：
		Class.forName(driverClass2);
		
//		3、 通过 DriverManager 的 getConnection() 方法获取数据库连接：
		Connection connection = 
				(Connection) DriverManager.getConnection(jdbcUrl2, user2, password2);
		System.out.println(connection);
	}
	
	@Test
	public void testDriverManager() throws Exception{//通用的连接，并未写死
//		1. 准备连接数据库的 4 个字符串.：
		String driverClass = null;//驱动的全类名
		String jdbcUrl = null;//JDBC URL
		String user = null;
		String password = null;
		
//		2、读取类路径下的 jdbc.properties 文件
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
//		3、加载数据库驱动程序(对应的 Driver 实现类中有注册驱动的静态代码块)：
		Class.forName(driverClass);
		
//		4、 通过 DriverManager 的 getConnection() 方法获取数据库连接：
		Connection connection = 
				(Connection) DriverManager.getConnection(jdbcUrl, user, password);
		System.out.println(connection);
	}
}
