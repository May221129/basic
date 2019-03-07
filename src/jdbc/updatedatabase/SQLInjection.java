package jdbc.updatedatabase;

//SQL注入

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.Test;

import jdbc.jdbctools.JDBCTools;

/*	
	SQL注入:是利用某些系统没有对用户输入的数据进行充分的检查，而在用户输入数据中注入非法的SQL语句段或命令，从而利用系统的SQL引擎完成恶意行为。

	解决方法：对于Java而言，要防止SQL注入，只要用PreparedStatement来取代Statement即可。使用Statement的子接口PreparedStatement来替代Statement。
	
	案例：
		String sql = "SELECT * FROM users WHERE username = " + "'" + username + "'" + "AND password = " + "'" + password + "'";
		
		正常情况：让客户输入用户名和密码，来完成字符串的拼接，从而登录成功
		String username = "用户名";
		String password = "密码";
		结果：String sql = "SELECT * FROM users WHERE username = '用户名' AND password = '密码'";
		
		异常情况：这是通过字符串的拼接，如果用户将WHERE后面的条件写成恒等的呢？
		String username = "a' OR PASSWORD = ";
		String password = " OR '1'='1";
		结果：String sql = "SELECT * FROM users WHERE  username = 'a'   OR   PASSWORD = 'AND password'   OR   1 = 1";
		
		上面的不论是正常情况或者是异常情况，都可以完成登录。
*/
public class SQLInjection {
	
//	 使用 PreparedStatement 将有效的解决 SQL 注入问题.
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
				System.out.println("登录成功!");
			} else {
				System.out.println("用户名和密码不匹配或用户名不存在. ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(resultSet, preparedStatement, connection);
		}
	}

//	 SQL 注入
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
				System.out.println("登录成功!");
			} else {
				System.out.println("用户名和密码不匹配或用户名不存在. ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(resultSet, statement, connection);
		}
	}
}
