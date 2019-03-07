package jdbc.a01connectdatebase;
/**
 * 连接数据库的方式一：用Driver驱动
 *  
 * 通过jdbc连接数据库的具体步骤：
 *  public Connection getConnection2() throws Exception{
 * 	1. 准备连接数据库的 4 个字符串. 
 * 	 1). 创建 Properties 对象
 * 	  Properties properties = new Properties();
 * 	
 * 	 2). 获取 jdbc.properties 对应的输入流
 * 	  InputStream in = 
 * 		this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
 * 	
 * 	 3). 加载 2） 对应的输入流
 * 	  properties.load(in);
 * 	
 * 	 4). 具体决定 user, password 等4 个字符串. 
 *    String user = properties.getProperty("user");
 * 	  String password = properties.getProperty("password");
 * 	  String jdbcUrl = properties.getProperty("jdbcUrl");
 * 	  String driver = properties.getProperty("driver");
 * 	
 * 	2. 加载数据库驱动程序(对应的 Driver 实现类中有注册驱动的静态代码块.)
 * 	 Class.forName(driver);
 * 	
 * 	3. 通过 DriverManager 的 getConnection() 方法获取数据库连接. 
 * 	 return DriverManager.getConnection(jdbcUrl, user, password);
 * }
 */
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import org.junit.Test;

import com.mysql.jdbc.Driver;

/**
 * 这个方法不是通用的：
 * Driver:是一个数据库厂商必须提供实现的接口，能够从中获取数据库连接。
 */
public class A01UseDriverConnectDatabase {
	@Test
	public  void  test1() throws Exception{//连接数据库test
//		1、创建一个Driver实现类的对象：
		Driver driver = new Driver();
		
//		2、准备连接数据库的基本信息：url,user,password:
		String url = "jdbc:mysql://localhost:3306/test";//"jdbc:mysql://127.0.0.1:3306/test"
		Properties info = new Properties();//导入哪个包里的 Properties？
		info.put("user", "root");
		info.put("password", "root");
		
//		3、调用Driver接口的connect（url,info）获取数据库连接：
		Connection connection = driver.connect(url, info);
		System.out.println(connection);
	}
	
/**
 * 要求：编写一个通用的方法，在不修改程序的情况下，可以获取任何数据库的连接：
 * 解决方法：把数据库驱动Driver实现类的全名，url,user,password放入一个配置文件中，
 * 通过修改配置文件的方式实现和具体的数据库解耦。
 * 具体程序如下所示：
 */
	public Connection getConnection() throws Exception{
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		
		//读取类路径下的 jdbc.properties 文件
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
		//通过反射常见 Driver 对象. 
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		
		Properties info = new Properties();
		info.put("user", user);
		info.put("password", password);
		
		//通过 Driver 的 connect 方法获取数据库连接. 
		Connection connection = driver.connect(jdbcUrl, info);
		
		return connection;
	}
	@Test
	public void testGetConnection() throws Exception{//测试上面的方法执行结果
		System.out.println(getConnection());
	}
}