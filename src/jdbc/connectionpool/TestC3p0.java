package jdbc.connectionpool;

/**
 * ���ݿ����ӳ�-C3p0
 * 
 * �Ƽ�ʹ��C3P0���ݿ����ӳأ�DBCPҲͦ�á�
 */

import javax.sql.DataSource;
import org.junit.Test;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestC3p0 {
	
	/**
	 * ͨ���޸������ļ��ķ�ʽ��������C3p0���ݿ����ӳ�
	 * 
	 * 1. ���� c3p0-config.xml �ļ�, �ο������ĵ��� Appendix B: Configuation Files ������
	 * 2. ���� ComboPooledDataSource ʵ����
	 * 	DataSource dataSource = new ComboPooledDataSource("helloc3p0");  
	 * 3. �� DataSource ʵ���л�ȡ���ݿ�����. 
	 */
	
	@Test
	public void testC3poWithConfigFile() throws Exception{
		DataSource dataSource = 
				new ComboPooledDataSource("helloc3p0");  
		
		System.out.println(dataSource.getConnection()); 
		
		ComboPooledDataSource comboPooledDataSource = 
				(ComboPooledDataSource) dataSource;
		System.out.println(comboPooledDataSource.getMaxStatements()); 
	}
	
//	ͨ��д���˵����ԣ�
	@Test
	public void testC3P0() throws Exception{
		
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver            
		cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
		cpds.setUser("root");                                  
		cpds.setPassword("root");   
		
		System.out.println(cpds.getConnection()); 
	}
}
