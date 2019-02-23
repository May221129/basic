package jdbc.connectionpool;

/**
 * ���ݿ����ӳ�-DBCP���ӳ�
 * 
 * ʹ�����ݿ����ӳصķ���һ��
 * 1. ���� dbcp �� properties �����ļ�: �����ļ��еļ���Ҫ���� BasicDataSource������.
 * 2. ���� BasicDataSourceFactory �� createDataSource �������� DataSourceʵ��
 * 3. �� DataSource ʵ���л�ȡ���ݿ�����. 
 * ����μ���testDBCPWithDataSourceFactory()
 * 
 * ʹ�����ݿ����ӳصķ�������
 * 1������commons-dbcp��jar��,������commons-pool���jar��������ͬʱҲҪ����commons-pool��jar��
 * 2���������ݿ����ӳ�
 * 3�����ñ��������
 * 4��������Դ�л�ȡ���ݿ�����
 * ����μ���testDBCP()
 * 
 * �Ƽ�ʹ�õ�һ�ַ�ʽ
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
//		1������JDPC����Դʵ����
		bds = new BasicDataSource();
		
//		2��Ϊ����Դʵ��ָ����������ԣ�
		bds.setUsername("root");
		bds.setPassword("root");
		bds.setUrl("jdbc:mysql://localhost:3306/test");
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		
//		3��ָ��һЩ����Դ�Ŀ�ѡ���ԣ�
//		3.1 ָ�����ݿ����ӳ��г�ʼ���������ĸ�����
		bds.setInitialSize(10);
		
//		3.2 �������������ͬһʱ�������ݿ������������
//		(ע�⣺��dbcpjar���У��÷�����������ͬ��setMaxActive())
		bds.setMaxTotal(50);//����50���Ͳ����������ˡ�
		
//		3.3 �������������������ݿ����ӳر�������Ŀ������ӵ�����
		bds.setMaxIdle(10);

//		3.4 ��С�����������������ݿ����ӳر�������ٵĿ������ӵ�����
		bds.setMinIdle(5);
		
//		3.5 �ȴ����ݿ����ӳط������ӵ��ʱ�䣬��λΪ���룬�����趨��ʱ�������쳣��
		bds.setMaxConnLifetimeMillis(1000 * 5);

		
//		4��������Դ�л�ȡ���ݿ����ӣ�
		Connection connection = jdbc.jdbctools.JDBCTools.getConnection();
		System.out.println(connection.getClass());
		
	}
}
