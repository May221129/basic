package jdbc.updatedatabase;

//练习：数据库记录的增删改、查询,使用Statement和PreparedStatement的对比
//方法二：用面相对象思想来做：把添加进数据库的一条条记录，看作是一个个对象

//使用Statement的子接口PreparedStatement来替代Statement
/*	
	使用Statement的子接口PreparedStatement来替代Statement：
	
	优点：
		1、可以传入带占位符的aql语句，并且提供了补充占位符变量的方法；而使用Statement需要进行拼写sql语句，很吃力，也很容易出错。
		2、可以有效禁止SQL注入；
		3、代码的可读性和可维护性；
		4、能最大可能的提供性能（语法检查，语义检查，翻译成二进制，缓存。PreparedStatement有缓存，而Statement没有缓存，每次都要重复那个过程。）；
		
	注意：调用PreparedStatement的setXxx(int index， Object value)方法时，
	设置占位符的值index值是从1开始的，而不是从0开始！！！
	
	注：SQL注入是利用某些系统没有对用户输入的数据进行充分的检查，
	而在用户输入数据中注入非法的SQL语句段或命令，从而利用系统的SQL引擎完成恶意行为。
	对于Java而言，要防止SQL注入，只要用PreparedStatement来取代Statement即可。
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
import org.junit.Test;

import jdbc.jdbctools.Student;

public class PreparedStatement_Statement_Update {
	
//	7-2、测试3-2.update(String sql, Obgect... args)和5-2.addStudent(Student student)方法，并将6.返回的Student对象拿来用
	@Test
	public void testAddStudent1(){
		Student student = getStudentFromConsole();//键盘按住ctrl+1，然后回车，能够快速将选定的代码设置成一个方法。
		addStudent1(student);
	}
	
//	7-1、测试3-1.update(String sql)方法和5-1.addStudent(Student student)方法，并将6.返回的Student对象拿来用
	@Test
	public void testAddStudent(){
		Student student = getStudentFromConsole();//键盘按住ctrl+1，然后回车，能够快速将选定的代码设置成一个方法。
		addStudent(student);
	}
	
//	6、从控制台输入学生信息的方法，最后返回一个已经写好各种信息的Student对象
	private Student getStudentFromConsole() {
		Scanner scanner = new Scanner(System.in);//键盘输入
		
		Student student = new Student();
		
		System.out.print("Type: ");
		student.setType(scanner.nextInt());
		
		System.out.print("IDCard: ");
		student.setIDCard(scanner.next());
		
		System.out.print("ExamCard: ");
		student.setExamCard(scanner.next());
		
		System.out.print("StudentName: ");
		student.setStudentName(scanner.next());
		
		System.out.print("Location: ");
		student.setLocation(scanner.next());
		
		System.out.print("Grade: ");
		student.setGrade(scanner.nextInt());
		
		scanner.close();
		
		return student;//返回一个已经写好各种信息的Student对象
	}
	
	
//  	使用Statement的子接口PreparedStatement来替代Statement：
	@Test
	public void testPreparedStatement(){
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			String sql = "INSERT customers (name,birthday,email) VALUES (?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, "王五");
			ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));//
			ps.setString(3, "123@qq.com");
			ps.executeUpdate();//执行executeQuery()或executeUpdate()方法时，不再需要传入SQL语句。
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(null, ps, connection);
		}
	}
//	5-2、通过update(Student student)方法，向数据库中添加记录：
	public void addStudent1(Student student){
		String sql = "INSERT examstudent (Type,IDCard,ExamCard,StudentName,Location,Grade) "
				+ "VALUES (?,?,?,?,?,?)";
		updata(sql, 
				student.getType(),
				student.getIDCard(),
				student.getExamCard(),
				student.getStudentName(),
				student.getLocation(),
				student.getGrade());
	}
	
//	5-1、通过update(Student student)方法，向数据库中添加记录：
	public void addStudent(Student student){
		String sql = "INSERT INTO examstudent (Type,IDCard,ExamCard,StudentName,Location,Grade) VALUES("
				+ student.getType()
				+ ",'" + student.getIDCard() 
				+ "','" + student.getExamCard()
				+ "','" + student.getStudentName()
				+ "','" + student.getLocation()
				+ "'," + student.getGrade()
				+ ")";
		update(sql);
	}
	
//	4、通过调用下面写好的update(String sql)方法，删除数据库中的所有记录：
	@Test
	public void testUpdateDelete(){
		String sql = "DELETE FROM examstudent";
		update(sql);
	}

//	3-2、操作数据库：增删改。用Statement的子接口PreparedStatement来替代Statement：
	public void updata(String sql, Object... args){
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			ps = connection.prepareStatement(sql);
			for(int i = 0; i < args.length; i++){
				ps.setObject(i+1, args[i]);//这里的index为什么是i+1？
//调用PreparedStatement的setXxx(int index， Object value)方法时，设置占位符的值index值是从1开始的，而不是从0开始
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(null, ps, connection);
		}
	}
	
//	3-1、操作数据库：增删改。用Statement
	public void update(String sql){

		Connection connection = null;
		Statement statement = null;
		try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(null, statement, connection);
		}
	}
}