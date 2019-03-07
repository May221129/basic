package jdbc.updatedatabase;

//汇总：连接数据库、对数据库的记录进行增删改、查询
/*
 * 注意点：
 * 1、导哪个包？
 * 2、步骤要清晰
 */

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.junit.Test;

public class UpdateAndQuery{
	
//	4、操作数据库：查询select==》ResultSet
	@Test
	public void testSelect(){
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			
			statement = (Statement) connection.createStatement();
			
			String sql = "SELECT id,name,birthday,email FROM customers";
//			1、不写WHERE，就是查询整张表.
//			2、查询整张表格时，最好不要写*，因为后期的维护人员如果看到*，还得打开数据库去对列字段具体是什么。
			
			rs = statement.executeQuery(sql);//executeQuery():执行查询
			
			while(rs.next()){//处理结果集
				int id = rs.getInt(1);
				String name = rs.getString(2);
				Date birthday = rs.getDate(3);
				String email = rs.getString(4);
				
				System.out.println(id);
				System.out.println(name);
				System.out.println(birthday);
				System.out.println(email);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			release(rs, statement, connection);
		}
	}
	
//	3、操作数据库：增删改==》Statement
	@Test
	public void update(){
		
		Connection connection = null;
		Statement statement = null;//Statement 是用于操作 SQL 的对象
		
		try {
			connection = getConnection();//调用下面已经写好的方法来获取连接
			
			statement = (Statement) connection.createStatement();
			
			String sql = "INSERT INTO customers (name,birthday,email) VALUES ('赖丽梅','1992-11-22','1234@qq.con')";
			
			statement.executeUpdate(sql);//调用Statement对象的executeUpdate()方法来发送sql语句
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			release(null, statement, connection);//调用下面已经写好的方法来关闭数据库资源
		}
	}
	
//	2、关闭数据库资源的方法：
	public void release(ResultSet rs, Statement statement, Connection connection){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//	1、获取连接：将应用程序和数据库连接起来
	public Connection getConnection() throws Exception{
		
		/* 读取 jdbc.properties
		 * 1、属性文件对应 Java 中的 Properties 类；
		 * 2、可以使用类加载器加载 bin 目录(类路径下)的文件；
		 */
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		
		String driverClass = properties.getProperty("driver");
		String jdbcUrl = properties.getProperty("jdbcUrl");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		
		Class.forName(driverClass);
		Connection connection = (Connection)DriverManager.getConnection(jdbcUrl, user, password);//这里需要强转为Connection，否则会报错。
		
		return connection;
	}
}
