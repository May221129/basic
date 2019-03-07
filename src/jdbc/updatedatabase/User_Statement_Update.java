package jdbc.updatedatabase;

//��ϰ�����ݿ��¼����ɾ��
//����һ��ͨ��дһ��һ��sql����������ݱ��������Ϣ

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.junit.Test;


public class User_Statement_Update {
	
//	5��ͨ����������д�õ�update(String sql)�����������ݿ�����Ӽ�¼��
	@Test
	public void testUpdateInsert(){
		/*
		INSERT INTO examstudent (Type,IDCard,ExamCard,StudentName,Location,Grade) VALUES (4,"412824195263214584","200523164754000","�ŷ�","֣��",85);
		INSERT INTO examstudent (Type,IDCard,ExamCard,StudentName,Location,Grade) VALUES (4,"222224195263214584","200523164754001","����","����",56);
		INSERT INTO examstudent (Type,IDCard,ExamCard,StudentName,Location,Grade) VALUES (6,"342824195263214584","200523164754002","����","����",72);
		INSERT INTO examstudent (Type,IDCard,ExamCard,StudentName,Location,Grade) VALUES (6,"100824195263214584","200523164754003","�Ի�","������",95);
		INSERT INTO examstudent (Type,IDCard,ExamCard,StudentName,Location,Grade) VALUES (4,"454524195263214584","200523164754004","����","����",64);
		INSERT INTO examstudent (Type,IDCard,ExamCard,StudentName,Location,Grade) VALUES (4,"854524195263214584","200523164754005","��С��","̫ԭ",60);
		*/
		String sql = "";//������ļ�¼һ��һ�����ƹ�����һ��һ����ӵ�examstudent���ݱ���
		update(sql);
	}
	
//	4��ͨ����������д�õ�update(String sql)������ɾ�����ݿ��е����м�¼��
	@Test
	public void testUpdateDelete(){
		String sql = "DELETE FROM examstudent";
		update(sql);
	}
	
//	3���������ݿ⣺��ɾ��
	public void update(String sql){
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection();
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			release(null, statement, connection);
		}
	}
	
//	2���ر����ݿ���Դ��
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
	
//	1����ȡ���ݿ����ӣ�
	public Connection getConnection() throws Exception{
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		String driverClass = properties.getProperty("driver");
		String jdbcUrl = properties.getProperty("jdbcUrl");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		Class.forName(driverClass);
		Connection connection = (Connection) DriverManager.getConnection(jdbcUrl, user, password);
		return connection;
	}
}
