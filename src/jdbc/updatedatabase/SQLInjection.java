package jdbc.updatedatabase;

//SQLע��

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.Test;

import jdbc.jdbctools.JDBCTools;

/*	
	SQLע��:������ĳЩϵͳû�ж��û���������ݽ��г�ֵļ�飬�����û�����������ע��Ƿ���SQL���λ�����Ӷ�����ϵͳ��SQL������ɶ�����Ϊ��

	�������������Java���ԣ�Ҫ��ֹSQLע�룬ֻҪ��PreparedStatement��ȡ��Statement���ɡ�ʹ��Statement���ӽӿ�PreparedStatement�����Statement��
	
	������
		String sql = "SELECT * FROM users WHERE username = " + "'" + username + "'" + "AND password = " + "'" + password + "'";
		
		����������ÿͻ������û��������룬������ַ�����ƴ�ӣ��Ӷ���¼�ɹ�
		String username = "�û���";
		String password = "����";
		�����String sql = "SELECT * FROM users WHERE username = '�û���' AND password = '����'";
		
		�쳣���������ͨ���ַ�����ƴ�ӣ�����û���WHERE���������д�ɺ�ȵ��أ�
		String username = "a' OR PASSWORD = ";
		String password = " OR '1'='1";
		�����String sql = "SELECT * FROM users WHERE  username = 'a'   OR   PASSWORD = 'AND password'   OR   1 = 1";
		
		����Ĳ�������������������쳣�������������ɵ�¼��
*/
public class SQLInjection {
	
//	 ʹ�� PreparedStatement ����Ч�Ľ�� SQL ע������.
	@Test
	public void testSQLInjection2() {
		String username = "a' OR PASSWORD = ";
		String password = " OR '1'='1";

		String sql = "SELECT * FROM users WHERE username = ? "
				+ "AND password = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				System.out.println("��¼�ɹ�!");
			} else {
				System.out.println("�û��������벻ƥ����û���������. ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(resultSet, preparedStatement, connection);
		}
	}

//	 SQL ע��
	@Test
	public void testSQLInjection() {
		String username = "a' OR PASSWORD = ";
		String password = " OR '1'='1";

		String sql = "SELECT * FROM users WHERE username = '" + username
				+ "' AND " + "password = '" + password + "'";

		System.out.println(sql);

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = JDBCTools.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				System.out.println("��¼�ɹ�!");
			} else {
				System.out.println("�û��������벻ƥ����û���������. ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(resultSet, statement, connection);
		}
	}
}
