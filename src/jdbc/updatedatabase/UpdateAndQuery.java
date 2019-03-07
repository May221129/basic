package jdbc.updatedatabase;

//���ܣ��������ݿ⡢�����ݿ�ļ�¼������ɾ�ġ���ѯ
/*
 * ע��㣺
 * 1�����ĸ�����
 * 2������Ҫ����
 */

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.junit.Test;

public class UpdateAndQuery{
	
//	4���������ݿ⣺��ѯselect==��ResultSet
	@Test
	public void testSelect(){
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			
			statement = (Statement) connection.createStatement();
			
			String sql = "SELECT id,name,birthday,email FROM customers";
//			1����дWHERE�����ǲ�ѯ���ű�.
//			2����ѯ���ű��ʱ����ò�Ҫд*����Ϊ���ڵ�ά����Ա�������*�����ô����ݿ�ȥ�����ֶξ�����ʲô��
			
			rs = statement.executeQuery(sql);//executeQuery():ִ�в�ѯ
			
			while(rs.next()){//��������
				int id = rs.getInt(1);
				String name = rs.getString(2);
				Date birthday = rs.getDate(3);
				String email = rs.getString(4);
				
				System.out.println(id);
				System.out.println(name);
				System.out.println(birthday);
				System.out.println(email);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			release(rs, statement, connection);
		}
	}
	
//	3���������ݿ⣺��ɾ��==��Statement
	@Test
	public void update(){
		
		Connection connection = null;
		Statement statement = null;//Statement �����ڲ��� SQL �Ķ���
		
		try {
			connection = getConnection();//���������Ѿ�д�õķ�������ȡ����
			
			statement = (Statement) connection.createStatement();
			
			String sql = "INSERT INTO customers (name,birthday,email) VALUES ('����÷','1992-11-22','1234@qq.con')";
			
			statement.executeUpdate(sql);//����Statement�����executeUpdate()����������sql���
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			release(null, statement, connection);//���������Ѿ�д�õķ������ر����ݿ���Դ
		}
	}
	
//	2���ر����ݿ���Դ�ķ�����
	public void release(ResultSet rs, Statement statement, Connection connection){
		if(rs != null){
			try {
				rs.close();
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
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//	1����ȡ���ӣ���Ӧ�ó�������ݿ���������
	public Connection getConnection() throws Exception{
		
		/* ��ȡ jdbc.properties
		 * 1�������ļ���Ӧ Java �е� Properties �ࣻ
		 * 2������ʹ������������� bin Ŀ¼(��·����)���ļ���
		 */
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		
		String driverClass = properties.getProperty("driver");
		String jdbcUrl = properties.getProperty("jdbcUrl");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		
		Class.forName(driverClass);
		Connection connection = (Connection)DriverManager.getConnection(jdbcUrl, user, password);//������ҪǿתΪConnection������ᱨ��
		
		return connection;
	}
}
