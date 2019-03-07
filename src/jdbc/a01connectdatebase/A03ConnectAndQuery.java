package jdbc.a01connectdatebase;
/**
 * 连接数据库的方式二：用DriverManager
 * 
 * 一、通过 JDBC 向指定的数据表中增删改查记录的具体步骤：
 * 1.Statement: 用于执行 SQL 语句的对象
 *  ① 通过 Connection 的 createStatement() 方法来获取
 *  ②通过 executeUpdate(sql) 可以执行 SQL 语句.
 *  ③传入的 SQL 可以是 INSRET, UPDATE 或 DELETE. 但不能是 SELECT
 * 2.Connection、Statement 都是应用程序和数据库服务器的连接资源. 使用后一定要关闭.
 * 	需要在 finally 中关闭 Connection 和 Statement 对象. 
 * 3.关闭的顺序是: 先关闭后获取的. 即先关闭 Statement 后关闭 Connection
 * 
 * 二、通过代码实现数据库的SELECT功能：
 * ResultSet: 结果集. 封装了使用 JDBC 进行查询的结果. 
 * 1. 调用 Statement 对象的 executeQuery(sql) 可以得到结果集.
 * 2. ResultSet 返回的实际上就是一张数据表. 有一个指针指向数据表的第一行的前面.
 * 	可以调用 next() 方法检测下一行是否有效. 若有效该方法返回 true, 且指针下移. 相当于Iterator 对象的 hasNext() 和 next() 方法的结合体
 * 3. 当指针对位到一行时, 可以通过调用 getXxx(index) 或 getXxx(columnName)获取每一列的值. 例如: getInt(1), getString("name")
 * 4. ResultSet 当然也需要进行关闭. 
 */

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import org.junit.Test;

import jdbc.jdbctools.JDBCTools;

public class A03ConnectAndQuery {
	
//	SELECT操作：查询并打印查询结果:处理多条记录
	@Test
	public void resultSet2(){
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = JDBCTools.getConnection();
			statement = connection.createStatement();
			//如果需要查询表中所有的记录，这里不要写WHERE条件：
			String sql = "SELECT id,name,birthday,email FROM customers";
			rs = statement.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				Date birthday = rs.getDate(3);
				String email = rs.getString(4);
				
				System.out.println(id);
				System.out.println(name);
				System.out.println(birthday);
				System.out.println(email);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(rs, statement, connection);
		}
	}

	
//	SELECT操作：查询并打印查询结果:处理一条记录	
	@Test
	public void resultSet(){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
//			1、连接并登录到数据库：
			connection = JDBCTools.getConnection();
			statement = connection.createStatement();
//			2、准备sql语句：
			String sql = "SELECT id,name,birthday,email FROM customers WHERE id=1";
//			3、执行查询，得到ResultSet：
			resultSet = statement.executeQuery(sql);
//			4、处理刚刚得到的ResultSet：
			if(resultSet.next()){
				//可以通过列来获取，也可以通过名来获取：
				int id = resultSet.getInt(1);//第一列
				String name = resultSet.getString("name");//名为“name”
				Date birthday = resultSet.getDate(3);
				String email = resultSet.getString("email");
				
				System.out.println(id);
				System.out.println(name);
				System.out.println(birthday);
				System.out.println(email);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			5、关闭数据库资源：
			JDBCTools.release(resultSet, statement, connection);
		}
	}
	
//	通用的更新的方法: 包括 INSERT、UPDATE、DELETE（把连接数据库和对记录的增删改查写成通用的方法）
	public void update(String sql){
		Connection connection = null;
		Statement statement = null;
		try {
			connection = JDBCTools.getConnection();
			statement = connection.createStatement();
			statement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(null, statement, connection);
		}
	}
	
//	连接数据库之后，操作数据库：增删改查
	@Test
	public void update() throws Exception{
		
//		1. 获取数据库连接
		Connection conn = null;
		Statement statement = null;
		try {
			conn = getConnection2();
			
//			2. 准备插入的 SQL 语句
			String sql = null;
//			增：
//			sql = "INSERT INTO customers (name,birthday,email) VALUES('李光荣','1992-3-9','123@qq.com')";
//			删：
//			sql = "DELETE FROM customers WHERE name='李光猪'";
//			改：
			sql = "UPDATE customers SET name = '赖皮梅' WHERE name = '赖丽梅'" ;
			
//			3. 执行插入. 
//			3.1 获取操作 SQL 语句的 Statement 对象: 
			//调用 Connection 的 createStatement() 方法来获取
				statement = conn.createStatement();
					
//			3.2调用 Statement 对象的 executeUpdate(sql) 执行 SQL 语句进行插入
					statement.executeUpdate(sql);
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				try {
//					4. 关闭 Statement 对象.
					if(statement != null)
						statement.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally{
//						 5.关闭连接
						if(conn != null)
							conn.close();							
					}
				}
	}
	
//	连接到MySQL并登录：
	public Connection getConnection2() throws Exception{
		//1. 准备连接数据库的 4 个字符串. 
		//1). 创建 Properties 对象
		Properties properties = new Properties();
		
		//2). 获取 jdbc.properties 对应的输入流
		InputStream in = 
				this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		
		//3). 加载 2） 对应的输入流
		properties.load(in);
		
		//4). 具体决定 user, password 等4 个字符串. 
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String jdbcUrl = properties.getProperty("jdbcUrl");
		String driver = properties.getProperty("driver");
		
		//2. 加载数据库驱动程序(对应的 Driver 实现类中有注册驱动的静态代码块.)
		Class.forName(driver);
		
		//3. 通过 DriverManager 的 getConnection() 方法获取数据库连接. 
		return (Connection) DriverManager.getConnection(jdbcUrl, user, password);
	}

}
