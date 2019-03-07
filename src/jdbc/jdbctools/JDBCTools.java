package jdbc.jdbctools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 操作JDBC的工具类：
 * 获取数据库连接
 * 关闭数据库资源等方法
 */
public class JDBCTools {
	
	/**
	 * 处理数据事务的方法：
	 * 提交、回滚、开始事务
	 */
//	提交事务
	public static void commit(Connection connection){
		if(connection != null){
			try {
				connection.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	回滚事务法：
	public static void rollback(Connection connection){
		if(connection != null){
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//	开始事务：
	public static void begin(Connection connection){
		if(connection != null){
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//	关闭ResultSet、Statement和Connection：
	public static void release(ResultSet resultSet, Statement statement, Connection connection){
		if(resultSet != null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection != null){
			try {
//				数据库连接池的Connection对象进行close时，并不是真的进行关闭，
//				而是把该数据库连接归还给数据库连接池中。
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//	获取连接的方法2.0. 通过数据库连接池c3p0来获取一个连接:	
	private static DataSource dataSource= null;
	
//	数据库连接池应只被初始化一次：
	static{
		dataSource = new ComboPooledDataSource("helloc3p0");
	}
	
	public static Connection getConnection() throws Exception{
		return dataSource.getConnection();
	}
	
//	获取连接的方法1.0. 通过读取配置文件从数据库服务器获取一个连接:
/*	public static Connection getConnection() throws Exception{
		
		InputStream in = JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		String driverClass = properties.getProperty("driver");
		String jdbcUrl = properties.getProperty("jdbcUrl");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		
		Class.forName(driverClass);
		
		return DriverManager.getConnection(jdbcUrl, user, password);
	}
*/
}
