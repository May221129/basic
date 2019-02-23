package jdbc.updatedatabase;

//��ϰ�����ݿ��¼�Ĳ�ѯ
//�����������������˼����������Ҫ��ѯ�����ݼ�¼����������
/*
 * ��ѯ��һ��ѧ������Ϣ-˼·��
 * 1���õ���ѯ���ͣ�
 * 		�ӿ���̨����һ������, ȷ��Ҫ��ѯ������ ��1. �����֤��ѯ�� 2. ��׼��֤�Ų�ѯ��  ��������Ч�� ����ʾ���û��������룻
 * 2�������ѯѧ����Ϣ��
 *	��1�� ��������� searchType, ��ʾ�û�������Ϣ:
 *  	�� searchType Ϊ 1, ��ʾ: ���������֤��. ��Ϊ 2 ��ʾ: ������׼��֤��
 * 	��2������ searchType ȷ�� SQL
 * 	��3��ִ�в�ѯ
 * 	��4�������ڲ�ѯ���, �Ѳ�ѯ�����װΪһ�� Student ����
 * 3����ӡѧ����Ϣ: ��ѧ���������ӡ�������Ϣ. ��������: ��ӡ���޴���.
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
	
//	4����ѯ������Ϣ��
	@Test
	/*
	public void testGetStudent(){
//		1���õ���ѯ���ͣ�
		int searchType = getSearchTypeFromConsole();
//		2�������ѯѧ����Ϣ��
		Student student = getSearchStudent(searchType);
//		3����ӡѧ����Ϣ:
		printStudent(student);
	}
	
//	4.3����ӡѧ����Ϣ�ķ�����
	private void printStudent(Student student) {
		if(student != null){
			System.out.println(student);
		}else{
			System.out.println("���޴��ˣ�");
		}
	}
	*/

//	4.2�����ѯѧ����Ϣ������һ�� Student ����. ��������, �򷵻� null��
	/*
	private Student getSearchStudent(int searchType) {
		
		String sql = "SELECT FlowID,Type,IDCard,ExamCard,StudentName,Location,Grade "
				+ "FROM examstudent "
				+ "WHERE ";
		//�����WHERE��ѯ��������ȷ�������֤�Ż���׼��֤�ţ������Ȳ�д
		
		Scanner scanner = new Scanner(System.in);
		
		if (searchType == 1) {
			System.out.print("������׼��֤��:");
			String examCard = scanner.next();
			sql = sql + "examcard = '" + examCard + "'";
		} else {
			System.out.print("���������֤��:");
			String examCard = scanner.next();
			sql = sql + "idcard = '" + examCard + "'";
		}
		scanner.close();
		
		Student student = getStudent(sql);

		return student;
	}
	*/

//	4.2.1������sql��䣬����Student����
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

//	4.1���õ���ѯ���͵ķ�����
	private int getSearchTypeFromConsole() {
		
		System.out.println("�����ѯ���ͣ�ע��1������׼��֤�Ų�ѯ��2���������֤�Ų�ѯ����");
		
		Scanner scanner = new Scanner(System.in);//���⣺��������ô�ر�scanner������
		int searchType = scanner.nextInt();
		
		if(searchType != 1 && searchType != 2){
			System.out.println("���������������������룡");
//			scanner.close();
			/*���ﲻ�ܹر�Scanner����ΪScanner�ǽ�final static InputStream in�����˰�װ��
			    �����������õ���ͬһ��������������һ���رվͺ������new ScannerҲû��������������
			 */
			throw new RuntimeException();
		}
//		scanner.close();
		return searchType;
	}

//1~3�ֱ��ǻ�ȡ���ݿ����ӵ�ͨ�÷������ر����ݿ���Դ��ͨ�÷�������ѯSELECT��ͨ�÷���--------------------------------------------

//	3����ѯSELECT��ͨ�÷�����
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
		
//	2���ر����ݿ���Դ��
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
	
//	1����ȡ���ݿ����ӣ�
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
