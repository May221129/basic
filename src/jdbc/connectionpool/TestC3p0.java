package jdbc.connectionpool;

/**
 * 数据库连接池-C3p0
 * 
 * 推荐使用C3P0数据库连接池，DBCP也挺好。
 */

import javax.sql.DataSource;
import org.junit.Test;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestC3p0 {
	
	/**
	 * 通过修改配置文件的方式，来创建C3p0数据库连接池
	 * 
	 * 1. 创建 c3p0-config.xml 文件, 参考帮助文档中 Appendix B: Configuation Files 的内容
	 * 2. 创建 ComboPooledDataSource 实例；
	 * 	DataSource dataSource = new ComboPooledDataSource("helloc3p0");  
	 * 3. 从 DataSource 实例中获取数据库连接. 
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
	
//	通过写死了的属性：
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
