package jdbc.test;

/**
 * 关于事务: 
 * 1. 如果多个操作, 每个操作使用的是自己的单独的连接, 则无法保证事务. 
 * 2. 具体步骤: 
 *	1). 事务操作开始前, 开始事务: 取消 Connection 的默认提交行为. connection.setAutoCommit(false); 
 *	2). 如果事务的操作都成功,则提交事务: connection.commit(); 
 * 	3). 回滚事务: 若出现异常, 则在 catch 块中回滚事务:connection.rollback();
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;

import jdbc.dao.DataAccessObject;

public class TransactionFlag {
	
	/**
	 * 设置事务的隔离级别:
	 * 	在 JDBC 程序中可以通过 Connection 的 setTransactionIsolation 来设置事务的隔离级别.
	 */
	@Test
	public void testTransactionIsolationUpdate() {//插入操作
		
		Connection connection = null;

		try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			connection.setAutoCommit(false);
			
			String sql = "UPDATE user2 SET balance = balance - 500 WHERE username = '傻1'";
			update(connection, sql);
			
			connection.commit();//停住
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	
	@Test
	public void testTransactionIsolationRead() {//查询/读取操作
		String sql = "SELECT balance FROM user2 WHERE username = '傻1'";
		Float balance = getForValue(sql);
		System.out.println(balance); 
	}
	
	// 返回某条记录的某一个字段的值 或 一个统计的值(一共有多少条记录等.)
		public <E> E getForValue(String sql, Object... args) {

			// 1. 得到结果集: 该结果集应该只有一行, 且只有一列
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				// 1. 得到结果集
				connection = jdbc.jdbctools.JDBCTools.getConnection();
				System.out.println(connection.getTransactionIsolation()); //查看默认的隔离级别：4、可重复读
				
//				设置隔离级别：
//				connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);//隔离级别：读未提交的数据
				connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);//读已提交的数据
				
				preparedStatement = connection.prepareStatement(sql);

				for (int i = 0; i < args.length; i++) {
					preparedStatement.setObject(i + 1, args[i]);
				}

				resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					return (E) resultSet.getObject(1);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbc.jdbctools.JDBCTools.release(resultSet, preparedStatement, connection);
			}
			
			// 2. 取得结果
			return null;
		}
	
	/**
	 * 傻1 给 傻2 汇款 500 元:
	 */
	
	@Test
	public void testTransaction1(){
		Connection connection = null;
		try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			System.out.println(connection.getAutoCommit());//输出结果为turn，默认提交
			
//			开始事务：取消默认提交：
			connection.setAutoCommit(false);
			
			String sql = "UPDATE user2 SET balance = balance - 500 WHERE username = '傻1'";
			update(connection, sql);
			
			int i = 10 / 0;
			System.out.println(i);
			
			sql = "UPDATE user2 SET balance = balance + 500 WHERE username = '傻2'";
			update(connection,sql);
			
//			提交事务：
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
//			如果出现异常，就回滚事务：
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally{
			jdbc.jdbctools.JDBCTools.release(null, null, connection);
		}
	}
//	共用一个Connection
	public void update(Connection connection, String sql, Object... args) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);

			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbc.jdbctools.JDBCTools.release(null, preparedStatement, null);
		}
	}
	
//	因为下面两个updata()方法用的是独立的两个connection，所以两个操作之间没有什么关联：
	@Test
	public void testTransaction(){
		DataAccessObject dao = new DataAccessObject();
		
		String sql = "UPDATE user2 SET balance = balance + 500 WHERE username = '傻1'";
		dao.update(sql);
		
		int i = 10 / 0;
		System.out.println(i);
		
		sql = "UPDATE user2 SET balance = balance - 500 WHERE username = '傻2'";
		dao.update(sql);
	}
}
