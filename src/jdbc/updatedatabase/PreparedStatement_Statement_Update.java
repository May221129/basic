package jdbc.updatedatabase;

//��ϰ�����ݿ��¼����ɾ�ġ���ѯ,ʹ��Statement��PreparedStatement�ĶԱ�
//�����������������˼������������ӽ����ݿ��һ������¼��������һ��������

//ʹ��Statement���ӽӿ�PreparedStatement�����Statement
/*	
	ʹ��Statement���ӽӿ�PreparedStatement�����Statement��
	
	�ŵ㣺
		1�����Դ����ռλ����aql��䣬�����ṩ�˲���ռλ�������ķ�������ʹ��Statement��Ҫ����ƴдsql��䣬�ܳ�����Ҳ�����׳���
		2��������Ч��ֹSQLע�룻
		3������Ŀɶ��ԺͿ�ά���ԣ�
		4���������ܵ��ṩ���ܣ��﷨��飬�����飬����ɶ����ƣ����档PreparedStatement�л��棬��Statementû�л��棬ÿ�ζ�Ҫ�ظ��Ǹ����̡�����
		
	ע�⣺����PreparedStatement��setXxx(int index�� Object value)����ʱ��
	����ռλ����ֵindexֵ�Ǵ�1��ʼ�ģ������Ǵ�0��ʼ������
	
	ע��SQLע��������ĳЩϵͳû�ж��û���������ݽ��г�ֵļ�飬
	�����û�����������ע��Ƿ���SQL���λ�����Ӷ�����ϵͳ��SQL������ɶ�����Ϊ��
	����Java���ԣ�Ҫ��ֹSQLע�룬ֻҪ��PreparedStatement��ȡ��Statement���ɡ�
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
import org.junit.Test;

import jdbc.jdbctools.Student;

public class PreparedStatement_Statement_Update {
	
//	7-2������3-2.update(String sql, Obgect... args)��5-2.addStudent(Student student)����������6.���ص�Student����������
	@Test
	public void testAddStudent1(){
		Student student = getStudentFromConsole();//���̰�סctrl+1��Ȼ��س����ܹ����ٽ�ѡ���Ĵ������ó�һ��������
		addStudent1(student);
	}
	
//	7-1������3-1.update(String sql)������5-1.addStudent(Student student)����������6.���ص�Student����������
	@Test
	public void testAddStudent(){
		Student student = getStudentFromConsole();//���̰�סctrl+1��Ȼ��س����ܹ����ٽ�ѡ���Ĵ������ó�һ��������
		addStudent(student);
	}
	
//	6���ӿ���̨����ѧ����Ϣ�ķ�������󷵻�һ���Ѿ�д�ø�����Ϣ��Student����
	private Student getStudentFromConsole() {
		Scanner scanner = new Scanner(System.in);//��������
		
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
		
		return student;//����һ���Ѿ�д�ø�����Ϣ��Student����
	}
	
	
//  	ʹ��Statement���ӽӿ�PreparedStatement�����Statement��
	@Test
	public void testPreparedStatement(){
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			String sql = "INSERT customers (name,birthday,email) VALUES (?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, "����");
			ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));//
			ps.setString(3, "123@qq.com");
			ps.executeUpdate();//ִ��executeQuery()��executeUpdate()����ʱ��������Ҫ����SQL��䡣
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(null, ps, connection);
		}
	}
//	5-2��ͨ��update(Student student)�����������ݿ�����Ӽ�¼��
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
	
//	5-1��ͨ��update(Student student)�����������ݿ�����Ӽ�¼��
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
	
//	4��ͨ����������д�õ�update(String sql)������ɾ�����ݿ��е����м�¼��
	@Test
	public void testUpdateDelete(){
		String sql = "DELETE FROM examstudent";
		update(sql);
	}

//	3-2���������ݿ⣺��ɾ�ġ���Statement���ӽӿ�PreparedStatement�����Statement��
	public void updata(String sql, Object... args){
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			ps = connection.prepareStatement(sql);
			for(int i = 0; i < args.length; i++){
				ps.setObject(i+1, args[i]);//�����indexΪʲô��i+1��
//����PreparedStatement��setXxx(int index�� Object value)����ʱ������ռλ����ֵindexֵ�Ǵ�1��ʼ�ģ������Ǵ�0��ʼ
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(null, ps, connection);
		}
	}
	
//	3-1���������ݿ⣺��ɾ�ġ���Statement
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