package jdbc.connectionpool;

/**
 * 数据库连接池-DBCP连接池
 * 
 * 使用数据库连接池的方法一：
 * 1. 加载 dbcp 的 properties 配置文件: 配置文件中的键需要来自 BasicDataSource的属性.
 * 2. 调用 BasicDataSourceFactory 的 createDataSource 方法创建 DataSource实例
 * 3. 从 DataSource 实例中获取数据库连接. 
 * 代码参见：testDBCPWithDataSourceFactory()
 * 
 * 使用数据库连接池的方法二：
 * 1、加入commons-dbcp的jar包,依赖于commons-pool这个jar包，所以同时也要加入commons-pool的jar包
 * 2、创建数据库连接池
 * 3、设置必须的属性
 * 4、从数据源中获取数据库连接
 * 代码参见：testDBCP()
 * 
 * 推荐使用第一种方式
 */

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

public class TestDBCP {
	
	@Test
	public void testDBCPWithDataSourceFactory() throws Exception{
		
		Properties properties = new Properties();
		InputStream is = TestDBCP.class.getClassLoader().getResourceAsStream("dbcp.properties");
		properties.load(is);
		DataSource dataSorce = BasicDataSourceFactory.createDataSource(properties);
		System.out.println(dataSorce.getConnection());
	}
	
	@Test
	public void testDBCP() throws Exception{
		BasicDataSource bds = null;
//		1、创建JDPC数据源实例：
		bds = new BasicDataSource();
		
//		2、为数据源实例指定必须的属性：
		bds.setUsername("root");
		bds.setPassword("root");
		bds.setUrl("jdbc:mysql://localhost:3306/test");
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		
//		3、指定一些数据源的可选属性：
//		3.1 指定数据库连接池中初始化连接数的个数：
		bds.setInitialSize(10);
		
//		3.2 最大连接数量：同一时刻向数据库申请的连接数
//		(注意：在dbcpjar包中，该方法名有所不同：setMaxActive())
		bds.setMaxTotal(50);//超过50个就不再有连接了。
		
//		3.3 最大空闲连接数：在数据库连接池保存的最多的空闲连接的数量
		bds.setMaxIdle(10);

//		3.4 最小空闲连接数：在数据库连接池保存的最少的空闲连接的数量
		bds.setMinIdle(5);
		
//		3.5 等待数据库连接池分配连接的最长时间，单位为毫秒，超出设定的时间后会抛异常：
		bds.setMaxConnLifetimeMillis(1000 * 5);

		
//		4、从数据源中获取数据库连接：
		Connection connection = jdbc.jdbctools.JDBCTools.getConnection();
		System.out.println(connection.getClass());
		
	}
}
