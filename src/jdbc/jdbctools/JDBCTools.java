package jdbc.jdbctools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ����JDBC�Ĺ����ࣺ
 * ��ȡ���ݿ�����
 * �ر����ݿ���Դ�ȷ���
 */
public class JDBCTools {
	
	/**
	 * ������������ķ�����
	 * �ύ���ع�����ʼ����
	 */
//	�ύ����
	public static void commit(Connection connection){
		if(connection != null){
			try {
				connection.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	�ع����񷨣�
	public static void rollback(Connection connection){
		if(connection != null){
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//	��ʼ����
	public static void begin(Connection connection){
		if(connection != null){
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//	�ر�ResultSet��Statement��Connection��
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
//				���ݿ����ӳص�Connection�������closeʱ����������Ľ��йرգ�
//				���ǰѸ����ݿ����ӹ黹�����ݿ����ӳ��С�
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//	��ȡ���ӵķ���2.0. ͨ�����ݿ����ӳ�c3p0����ȡһ������:	
	private static DataSource dataSource= null;
	
//	���ݿ����ӳ�Ӧֻ����ʼ��һ�Σ�
	static{
		dataSource = new ComboPooledDataSource("helloc3p0");
	}
	
	public static Connection getConnection() throws Exception{
		return dataSource.getConnection();
	}
	
//	��ȡ���ӵķ���1.0. ͨ����ȡ�����ļ������ݿ��������ȡһ������:
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
