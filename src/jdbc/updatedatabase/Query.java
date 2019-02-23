package jdbc.updatedatabase;

//练习：数据库记录的查询
//方法二：用面相对象思想来做：把要查询的数据记录，看作是象
/*
 * 查询到一个学生的信息-思路：
 * 1、得到查询类型：
 * 		从控制台读入一个整数, 确定要查询的类型 ：1. 用身份证查询； 2. 用准考证号查询；  其他的无效， 并提示请用户重新输入；
 * 2、具体查询学生信息：
 *	（1） 根据输入的 searchType, 提示用户输入信息:
 *  	若 searchType 为 1, 提示: 请输入身份证号. 若为 2 提示: 请输入准考证号
 * 	（2）根据 searchType 确定 SQL
 * 	（3）执行查询
 * 	（4）若存在查询结果, 把查询结果封装为一个 Student 对象
 * 3、打印学生信息: 若学生存在则打印其具体信息. 若不存在: 打印查无此人.
 */
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import org.junit.Test;

import jdbc.jdbctools.Student;


public class Query {
	
//	4、查询考生信息：
	@Test
	/*
	public void testGetStudent(){
//		1、得到查询类型：
		int searchType = getSearchTypeFromConsole();
//		2、具体查询学生信息：
		Student student = getSearchStudent(searchType);
//		3、打印学生信息:
		printStudent(student);
	}
	
//	4.3、打印学生信息的方法：
	private void printStudent(Student student) {
		if(student != null){
			System.out.println(student);
		}else{
			System.out.println("查无此人！");
		}
	}
	*/

//	4.2具体查询学生信息，返回一个 Student 对象. 若不存在, 则返回 null：
	/*
	private Student getSearchStudent(int searchType) {
		
		String sql = "SELECT FlowID,Type,IDCard,ExamCard,StudentName,Location,Grade "
				+ "FROM examstudent "
				+ "WHERE ";
		//这里的WHERE查询条件还不确定是身份证号还是准考证号，所以先不写
		
		Scanner scanner = new Scanner(System.in);
		
		if (searchType == 1) {
			System.out.print("请输入准考证号:");
			String examCard = scanner.next();
			sql = sql + "examcard = '" + examCard + "'";
		} else {
			System.out.print("请输入身份证号:");
			String examCard = scanner.next();
			sql = sql + "idcard = '" + examCard + "'";
		}
		scanner.close();
		
		Student student = getStudent(sql);

		return student;
	}
	*/

//	4.2.1、根据sql语句，返回Student对象：
	/*
	public Student getStudent(String sql){
		
		Student student = null;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			if(rs.next()){
				student = new Student(
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			release(rs, statement, connection);
		}
		return student;
	}
	*/

//	4.1、得到查询类型的方法：
	private int getSearchTypeFromConsole() {
		
		System.out.println("请输查询类型（注：1代表用准考证号查询；2代表用身份证号查询）：");
		
		Scanner scanner = new Scanner(System.in);//问题：在这里怎么关闭scanner？？？
		int searchType = scanner.nextInt();
		
		if(searchType != 1 && searchType != 2){
			System.out.println("您的输入有误，请重新输入！");
//			scanner.close();
			/*这里不能关闭Scanner，因为Scanner是将final static InputStream in进行了包装，
			    整个程序中用的是同一个输入流，所以一旦关闭就后面就算new Scanner也没将输入流开启。
			 */
			throw new RuntimeException();
		}
//		scanner.close();
		return searchType;
	}

//1~3分别是获取数据库连接的通用方法、关闭数据库资源的通用方法、查询SELECT的通用方法--------------------------------------------

//	3、查询SELECT的通用方法：
	public Student select(String sql){
		
		Student student = null;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			statement = (Statement) connection.createStatement();
			rs = statement.executeQuery(sql);
			if(rs.next()){
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			release(rs, statement, connection);
		}
		
		return student;
	}
		
//	2、关闭数据库资源：
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
	
//	1、获取数据库连接：
	public Connection getConnection() throws Exception{
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		String driverClass = properties.getProperty("driver");
		String jdbcUrl = properties.getProperty("jdbcUrl");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		Class.forName(driverClass);
		Connection connection = (Connection) DriverManager.getConnection(jdbcUrl, user, password);
		return connection;
	}
}
