package jdbc.test;

/**
 * DatabaseMetaData��
 * DatabaseMetaData���������ݿ��Ԫ���ݶ���
 * ������Connection�õ���
 * ��Ϊ�˽⼴�ɡ�
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
			
//			���Եõ����ݿⱾ���һЩ������Ϣ��
			int version = dmd.getDatabaseMajorVersion();//�õ����ݿ�İ汾��
			System.out.println(version);
			
			String userName = dmd.getUserName();//�õ����ӵ����ݿ���û�����ע�⣬�����ǲ��ܵõ��ġ�
			System.out.println(userName);
			
			rs = dmd.getCatalogs();//�õ�MySQL������Щ���ݿ�
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
			
			//����
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(rs, null, connection);
		}
	}
}
