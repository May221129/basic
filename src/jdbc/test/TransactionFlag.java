package jdbc.test;

/**
 * ��������: 
 * 1. ����������, ÿ������ʹ�õ����Լ��ĵ���������, ���޷���֤����. 
 * 2. ���岽��: 
 *	1). ���������ʼǰ, ��ʼ����: ȡ�� Connection ��Ĭ���ύ��Ϊ. connection.setAutoCommit(false); 
 *	2). �������Ĳ������ɹ�,���ύ����: connection.commit(); 
 * 	3). �ع�����: �������쳣, ���� catch ���лع�����:connection.rollback();
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;

import jdbc.dao.DataAccessObject;

public class TransactionFlag {
	
	/**
	 * ��������ĸ��뼶��:
	 * 	�� JDBC �����п���ͨ�� Connection �� setTransactionIsolation ����������ĸ��뼶��.
	 */
	@Test
	public void testTransactionIsolationUpdate() {//�������
		
		Connection connection = null;

		try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			connection.setAutoCommit(false);
			
			String sql = "UPDATE user2 SET balance = balance - 500 WHERE username = 'ɵ1'";
			update(connection, sql);
			
			connection.commit();//ͣס
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	
	@Test
	public void testTransactionIsolationRead() {//��ѯ/��ȡ����
		String sql = "SELECT balance FROM user2 WHERE username = 'ɵ1'";
		Float balance = getForValue(sql);
		System.out.println(balance); 
	}
	
	// ����ĳ����¼��ĳһ���ֶε�ֵ �� һ��ͳ�Ƶ�ֵ(һ���ж�������¼��.)
		public <E> E getForValue(String sql, Object... args) {

			// 1. �õ������: �ý����Ӧ��ֻ��һ��, ��ֻ��һ��
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				// 1. �õ������
				connection = jdbc.jdbctools.JDBCTools.getConnection();
				System.out.println(connection.getTransactionIsolation()); //�鿴Ĭ�ϵĸ��뼶��4�����ظ���
				
//				���ø��뼶��
//				connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);//���뼶�𣺶�δ�ύ������
				connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);//�����ύ������
				
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
			
			// 2. ȡ�ý��
			return null;
		}
	
	/**
	 * ɵ1 �� ɵ2 ��� 500 Ԫ:
	 */
	
	@Test
	public void testTransaction1(){
		Connection connection = null;
		try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			System.out.println(connection.getAutoCommit());//������Ϊturn��Ĭ���ύ
			
//			��ʼ����ȡ��Ĭ���ύ��
			connection.setAutoCommit(false);
			
			String sql = "UPDATE user2 SET balance = balance - 500 WHERE username = 'ɵ1'";
			update(connection, sql);
			
			int i = 10 / 0;
			System.out.println(i);
			
			sql = "UPDATE user2 SET balance = balance + 500 WHERE username = 'ɵ2'";
			update(connection,sql);
			
//			�ύ����
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
//			��������쳣���ͻع�����
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
//	����һ��Connection
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
	
//	��Ϊ��������updata()�����õ��Ƕ���������connection��������������֮��û��ʲô������
	@Test
	public void testTransaction(){
		DataAccessObject dao = new DataAccessObject();
		
		String sql = "UPDATE user2 SET balance = balance + 500 WHERE username = 'ɵ1'";
		dao.update(sql);
		
		int i = 10 / 0;
		System.out.println(i);
		
		sql = "UPDATE user2 SET balance = balance - 500 WHERE username = 'ɵ2'";
		dao.update(sql);
	}
}
