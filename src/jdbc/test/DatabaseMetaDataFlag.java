package jdbc.test;

/**
 * DatabaseMetaData类
 * DatabaseMetaData是描述数据库的元数据对象；
 * 可以由Connection得到。
 * 作为了解即可。
 */

import org.junit.Test;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class DatabaseMetaDataFlag {
	@Test
	public void testDatabaseMetaData(){
		
		Connection connection = null;
		ResultSet rs = null;
		
		try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			DatabaseMetaData dmd = connection.getMetaData();
			
//			可以得到数据库本身的一些基本信息：
			int version = dmd.getDatabaseMajorVersion();//得到数据库的版本号
			System.out.println(version);
			
			String userName = dmd.getUserName();//得到连接到数据库的用户名。注意，密码是不能得到的。
			System.out.println(userName);
			
			rs = dmd.getCatalogs();//得到MySQL中有哪些数据库
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
			
			//……
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(rs, null, connection);
		}
	}
}
