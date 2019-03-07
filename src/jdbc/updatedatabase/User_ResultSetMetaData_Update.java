package jdbc.updatedatabase;

/*
 * 利用反射==》通用的查询方法：可以根据传入的SQL、class对象，返回SQL对应的记录的对象
 * 	public <T> T getEntity(Class<T> clazz, String sql, Object ...args){}
 * 
 * ResultSetMetaData 
 * 1、why:如果只有一个结果集，但不知道该结果集中有多少列，列的名字分别是什么。
 * 		编写通用的查询方法时可以使用：public <T> T getEntity(Class<T> clazz, String sql, Object ...args){}
 * 1、作用：
 * 		描述ResultSet的元数据对象，即从中可以获取到结果集中有多少列，列名是什么等。
 * 2、怎么用：
 * 		（1）得到ResultSetMetaData对象，调用ResultSet的getMetaData()方法；
 * 		（2）ResultSetMetaData常用的方法：
 * 			>int getColumnCount()：SQL语句中包含哪些列；
 * 			>String getColumnLabel(int column)：获取指定列的别名，其中索引从1开始。
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import com.mysql.jdbc.MysqlDefs;

import jdbc.jdbctools.JDBCTools;
import jdbc.jdbctools.ReflectionUtils;
import jdbc.jdbctools.Student;

public class User_ResultSetMetaData_Update {
	
//	使用下面的通用方法：
	@Test
	public void testGetEntity() {
		
//		SQL语句，有别名的，别名和对象的属性相对应：
		String sql = "SELECT Type, id_card IDCard, "
				+ "exam_card ExamCard, student_name StudentName, "
				+ "Location, Grade " + "FROM examstudent WHERE flow_id = ?";
//		System.out.println(sql);

		Student stu = getEntity(Student.class, sql, 22);
		System.out.println(stu);
	}
	
/*
 * 通用的查询方法：可以根据传入的SQL、class对象，返回SQL对应的记录的对象
 * clazz：描述对象的类型
 * aql:SQL语句，可以带占位符
 * args:填充占位符的可变参数
 * 
 * 实现该方法需要总和使用反射、JDBC、JDBC元数据的知识。具体步骤如下所示：
 */
	public <T> T getEntity(Class<T> clazz, String sql, Object ...args){
		
		T entity = null;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = JDBCTools.getConnection();
			ps = connection.prepareStatement(sql);
			for(int i = 0; i < args.length; i++){
				ps.setObject(i+1, args[i]);
			}
//			1. 得到 ResultSet 对象
			rs = ps.executeQuery();
			
//			2. 得到 ResultSetMetaData 对象
			ResultSetMetaData rsmd = rs.getMetaData();
			
//			3. 创建一个 Map<String, Object> 对象, 键: SQL 查询的列的别名, 值: 列的值
			Map<String, Object> map = new HashMap<>();
			
//			4. 处理结果集. 利用 ResultSetMetaData 填充 3 对应的 Map 对象
			if(rs.next()){
				for(int i = 0; i < rsmd.getColumnCount(); i++){
					String columnLabel = rsmd.getColumnLabel(i + 1);//获取结果集的每一列的别名
					Object columnValue = rs.getObject(i + 1);
/* 注意：数据表中的int数据类型是否带符号
* 如果数据表中的字段是无符号位的，通过getObject()方法获取指定字段对应的值时，
* Java中是没有“无符号位”一说的，所以转换过来时，分为从数据表中获取的该字段是有符号位还是无符号
* 案例如下：
* 	case Types.INTEGER:
 	if (!field.isUnsigned() || field.getMysqlType() == MysqlDefs.FIELD_TYPE_INT24) {
 	return Integer.valueOf(getInt(columnIndex));
    }
	return Long.valueOf(getLong(columnIndex));
						
	接口里面的方法，是没有具体的代码的；想要看getObject()方法的源代码：
		Ctrl+鼠标移到getObject(i + 1)这边==》Open Implementation==> ResultSetInternalMethods-com……
		==》public Object getObject(int columnIndex) throws SQLException {}==》switch_case
*/
					
					map.put(columnLabel, columnValue);
				}
			}
			
//			5. 若 Map 不为空集, 利用反射创建 clazz 对应的对象
			if(map.size() > 0){
				entity = clazz.newInstance();
				
//				6. 遍历 Map 对象, 利用反射为 Class 对象对应的属性赋值. 
				for(Map.Entry<String, Object> entry: map.entrySet()){
					String fieldName = entry.getKey();
					Object value = entry.getValue();
					ReflectionUtils.setFieldValue(entity, fieldName, value);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(rs, ps, connection);
		}
		return entity;
	}
}
