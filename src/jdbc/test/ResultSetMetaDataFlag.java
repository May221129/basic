package jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * ResultSeteMetaData类
 * 
 * 是什么：ResultSetMetaData是描述结果集的元数据对象；
 * 
 * 有什么用：可以得到的结果集中的基本信息，如：结果集中有哪些列，列名，列的别名，等等
 * 
 * 怎么用：结合反射可以写出通用的查询方法来。
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
//			有别名的查询语句：
			String sql = "SELECT id customers_id,name customers_name,birthday customers_birthday,email customers_email FROM customers";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
//			1、得到ResultSetMetaData对象：
			ResultSetMetaData rsmd = rs.getMetaData();
//			2、得到列的个数：
			int columnCount = rsmd.getColumnCount();
			System.out.println(columnCount);
			
			for(int i = 0; i < columnCount; i++){
//				3、得到列名：
				String columnName = rsmd.getColumnName(i + 1);
//				4、得到列的别名：
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
