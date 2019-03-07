package jdbc.updatedatabase;

//练习：数据库记录的增删改
//方法一：通过写一条一条sql语句来向数据表中添加信息

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.junit.Test;


public class User_Statement_Update {
	
//	5、通过调用下面写好的update(String sql)方法，向数据库中添加记录：
	@Test
	public void testUpdateInsert(){
		/*
		INSERT INTO examstudent (Type,IDCard,ExamCard,StudentName,Location,Grade) VALUES (4,"412824195263214584","200523164754000","张锋","郑州",85);
		INSERT INTO examstudent (Type,IDCard,ExamCard,StudentName,Location,Grade) VALUES (4,"222224195263214584","200523164754001","孙鹏","大连",56);
		INSERT INTO examstudent (Type,IDCard,ExamCard,StudentName,Location,Grade) VALUES (6,"342824195263214584","200523164754002","刘明","沈阳",72);
		INSERT INTO examstudent (Type,IDCard,ExamCard,StudentName,Location,Grade) VALUES (6,"100824195263214584","200523164754003","赵虎","哈尔滨",95);
		INSERT INTO examstudent (Type,IDCard,ExamCard,StudentName,Location,Grade) VALUES (4,"454524195263214584","200523164754004","杨丽","北京",64);
		INSERT INTO examstudent (Type,IDCard,ExamCard,StudentName,Location,Grade) VALUES (4,"854524195263214584","200523164754005","王小红","太原",60);
		*/
		String sql = "";//把上面的记录一条一条复制过来，一条一条添加到examstudent数据表中
		update(sql);
	}
	
//	4、通过调用下面写好的update(String sql)方法，删除数据库中的所有记录：
	@Test
	public void testUpdateDelete(){
		String sql = "DELETE FROM examstudent";
		update(sql);
	}
	
//	3、操作数据库：增删改
	public void update(String sql){
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection();
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			release(null, statement, connection);
		}
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
