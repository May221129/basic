package jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * ResultSeteMetaData��
 * 
 * ��ʲô��ResultSetMetaData�������������Ԫ���ݶ���
 * 
 * ��ʲô�ã����Եõ��Ľ�����еĻ�����Ϣ���磺�����������Щ�У��������еı������ȵ�
 * 
 * ��ô�ã���Ϸ������д��ͨ�õĲ�ѯ��������
 */

import org.junit.Test;

public class ResultSetMetaDataFlag {
	@Test
	public void testResultSetMetaData(){
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
//			�б����Ĳ�ѯ��䣺
			String sql = "SELECT id customers_id,name customers_name,birthday customers_birthday,email customers_email FROM customers";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
//			1���õ�ResultSetMetaData����
			ResultSetMetaData rsmd = rs.getMetaData();
//			2���õ��еĸ�����
			int columnCount = rsmd.getColumnCount();
			System.out.println(columnCount);
			
			for(int i = 0; i < columnCount; i++){
//				3���õ�������
				String columnName = rsmd.getColumnName(i + 1);
//				4���õ��еı�����
				String columnLabel = rsmd.getColumnLabel(i + 1);
				System.out.println(columnName + ":" + columnLabel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(rs, ps, connection);
		}
	}
}
