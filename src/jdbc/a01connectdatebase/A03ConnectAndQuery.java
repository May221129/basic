package jdbc.a01connectdatebase;
/**
 * �������ݿ�ķ�ʽ������DriverManager
 * 
 * һ��ͨ�� JDBC ��ָ�������ݱ�����ɾ�Ĳ��¼�ľ��岽�裺
 * 1.Statement: ����ִ�� SQL ���Ķ���
 *  �� ͨ�� Connection �� createStatement() ��������ȡ
 *  ��ͨ�� executeUpdate(sql) ����ִ�� SQL ���.
 *  �۴���� SQL ������ INSRET, UPDATE �� DELETE. �������� SELECT
 * 2.Connection��Statement ����Ӧ�ó�������ݿ��������������Դ. ʹ�ú�һ��Ҫ�ر�.
 * 	��Ҫ�� finally �йر� Connection �� Statement ����. 
 * 3.�رյ�˳����: �ȹرպ��ȡ��. ���ȹر� Statement ��ر� Connection
 * 
 * ����ͨ������ʵ�����ݿ��SELECT���ܣ�
 * ResultSet: �����. ��װ��ʹ�� JDBC ���в�ѯ�Ľ��. 
 * 1. ���� Statement ����� executeQuery(sql) ���Եõ������.
 * 2. ResultSet ���ص�ʵ���Ͼ���һ�����ݱ�. ��һ��ָ��ָ�����ݱ�ĵ�һ�е�ǰ��.
 * 	���Ե��� next() ���������һ���Ƿ���Ч. ����Ч�÷������� true, ��ָ������. �൱��Iterator ����� hasNext() �� next() �����Ľ����
 * 3. ��ָ���λ��һ��ʱ, ����ͨ������ getXxx(index) �� getXxx(columnName)��ȡÿһ�е�ֵ. ����: getInt(1), getString("name")
 * 4. ResultSet ��ȻҲ��Ҫ���йر�. 
 */

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import org.junit.Test;

import jdbc.jdbctools.JDBCTools;

public class A03ConnectAndQuery {
	
//	SELECT��������ѯ����ӡ��ѯ���:���������¼
	@Test
	public void resultSet2(){
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = JDBCTools.getConnection();
			statement = connection.createStatement();
			//�����Ҫ��ѯ�������еļ�¼�����ﲻҪдWHERE������
			String sql = "SELECT id,name,birthday,email FROM customers";
			rs = statement.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				Date birthday = rs.getDate(3);
				String email = rs.getString(4);
				
				System.out.println(id);
				System.out.println(name);
				System.out.println(birthday);
				System.out.println(email);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(rs, statement, connection);
		}
	}

	
//	SELECT��������ѯ����ӡ��ѯ���:����һ����¼	
	@Test
	public void resultSet(){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
//			1�����Ӳ���¼�����ݿ⣺
			connection = JDBCTools.getConnection();
			statement = connection.createStatement();
//			2��׼��sql��䣺
			String sql = "SELECT id,name,birthday,email FROM customers WHERE id=1";
//			3��ִ�в�ѯ���õ�ResultSet��
			resultSet = statement.executeQuery(sql);
//			4������ոյõ���ResultSet��
			if(resultSet.next()){
				//����ͨ��������ȡ��Ҳ����ͨ��������ȡ��
				int id = resultSet.getInt(1);//��һ��
				String name = resultSet.getString("name");//��Ϊ��name��
				Date birthday = resultSet.getDate(3);
				String email = resultSet.getString("email");
				
				System.out.println(id);
				System.out.println(name);
				System.out.println(birthday);
				System.out.println(email);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			5���ر����ݿ���Դ��
			JDBCTools.release(resultSet, statement, connection);
		}
	}
	
//	ͨ�õĸ��µķ���: ���� INSERT��UPDATE��DELETE�����������ݿ�ͶԼ�¼����ɾ�Ĳ�д��ͨ�õķ�����
	public void update(String sql){
		Connection connection = null;
		Statement statement = null;
		try {
			connection = JDBCTools.getConnection();
			statement = connection.createStatement();
			statement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(null, statement, connection);
		}
	}
	
//	�������ݿ�֮�󣬲������ݿ⣺��ɾ�Ĳ�
	@Test
	public void update() throws Exception{
		
//		1. ��ȡ���ݿ�����
		Connection conn = null;
		Statement statement = null;
		try {
			conn = getConnection2();
			
//			2. ׼������� SQL ���
			String sql = null;
//			����
//			sql = "INSERT INTO customers (name,birthday,email) VALUES('�����','1992-3-9','123@qq.com')";
//			ɾ��
//			sql = "DELETE FROM customers WHERE name='�����'";
//			�ģ�
			sql = "UPDATE customers SET name = '��Ƥ÷' WHERE name = '����÷'" ;
			
//			3. ִ�в���. 
//			3.1 ��ȡ���� SQL ���� Statement ����: 
			//���� Connection �� createStatement() ��������ȡ
				statement = conn.createStatement();
					
//			3.2���� Statement ����� executeUpdate(sql) ִ�� SQL �����в���
					statement.executeUpdate(sql);
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				try {
//					4. �ر� Statement ����.
					if(statement != null)
						statement.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally{
//						 5.�ر�����
						if(conn != null)
							conn.close();							
					}
				}
	}
	
//	���ӵ�MySQL����¼��
	public Connection getConnection2() throws Exception{
		//1. ׼���������ݿ�� 4 ���ַ���. 
		//1). ���� Properties ����
		Properties properties = new Properties();
		
		//2). ��ȡ jdbc.properties ��Ӧ��������
		InputStream in = 
				this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		
		//3). ���� 2�� ��Ӧ��������
		properties.load(in);
		
		//4). ������� user, password ��4 ���ַ���. 
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String jdbcUrl = properties.getProperty("jdbcUrl");
		String driver = properties.getProperty("driver");
		
		//2. �������ݿ���������(��Ӧ�� Driver ʵ��������ע�������ľ�̬�����.)
		Class.forName(driver);
		
		//3. ͨ�� DriverManager �� getConnection() ������ȡ���ݿ�����. 
		return (Connection) DriverManager.getConnection(jdbcUrl, user, password);
	}

}
