package jdbc.updatedatabase;

/*
 * ���÷���==��ͨ�õĲ�ѯ���������Ը��ݴ����SQL��class���󣬷���SQL��Ӧ�ļ�¼�Ķ���
 * 	public <T> T getEntity(Class<T> clazz, String sql, Object ...args){}
 * 
 * ResultSetMetaData 
 * 1��why:���ֻ��һ�������������֪���ý�������ж����У��е����ֱַ���ʲô��
 * 		��дͨ�õĲ�ѯ����ʱ����ʹ�ã�public <T> T getEntity(Class<T> clazz, String sql, Object ...args){}
 * 1�����ã�
 * 		����ResultSet��Ԫ���ݶ��󣬼����п��Ի�ȡ����������ж����У�������ʲô�ȡ�
 * 2����ô�ã�
 * 		��1���õ�ResultSetMetaData���󣬵���ResultSet��getMetaData()������
 * 		��2��ResultSetMetaData���õķ�����
 * 			>int getColumnCount()��SQL����а�����Щ�У�
 * 			>String getColumnLabel(int column)����ȡָ���еı���������������1��ʼ��
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
	
//	ʹ�������ͨ�÷�����
	@Test
	public void testGetEntity() {
		
//		SQL��䣬�б����ģ������Ͷ�����������Ӧ��
		String sql = "SELECT Type, id_card IDCard, "
				+ "exam_card ExamCard, student_name StudentName, "
				+ "Location, Grade " + "FROM examstudent WHERE flow_id = ?";
//		System.out.println(sql);

		Student stu = getEntity(Student.class, sql, 22);
		System.out.println(stu);
	}
	
/*
 * ͨ�õĲ�ѯ���������Ը��ݴ����SQL��class���󣬷���SQL��Ӧ�ļ�¼�Ķ���
 * clazz���������������
 * aql:SQL��䣬���Դ�ռλ��
 * args:���ռλ���Ŀɱ����
 * 
 * ʵ�ָ÷�����Ҫ�ܺ�ʹ�÷��䡢JDBC��JDBCԪ���ݵ�֪ʶ�����岽��������ʾ��
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
//			1. �õ� ResultSet ����
			rs = ps.executeQuery();
			
//			2. �õ� ResultSetMetaData ����
			ResultSetMetaData rsmd = rs.getMetaData();
			
//			3. ����һ�� Map<String, Object> ����, ��: SQL ��ѯ���еı���, ֵ: �е�ֵ
			Map<String, Object> map = new HashMap<>();
			
//			4. ��������. ���� ResultSetMetaData ��� 3 ��Ӧ�� Map ����
			if(rs.next()){
				for(int i = 0; i < rsmd.getColumnCount(); i++){
					String columnLabel = rsmd.getColumnLabel(i + 1);//��ȡ�������ÿһ�еı���
					Object columnValue = rs.getObject(i + 1);
/* ע�⣺���ݱ��е�int���������Ƿ������
* ������ݱ��е��ֶ����޷���λ�ģ�ͨ��getObject()������ȡָ���ֶζ�Ӧ��ֵʱ��
* Java����û�С��޷���λ��һ˵�ģ�����ת������ʱ����Ϊ�����ݱ��л�ȡ�ĸ��ֶ����з���λ�����޷���
* �������£�
* 	case Types.INTEGER:
 	if (!field.isUnsigned() || field.getMysqlType() == MysqlDefs.FIELD_TYPE_INT24) {
 	return Integer.valueOf(getInt(columnIndex));
    }
	return Long.valueOf(getLong(columnIndex));
						
	�ӿ�����ķ�������û�о���Ĵ���ģ���Ҫ��getObject()������Դ���룺
		Ctrl+����Ƶ�getObject(i + 1)���==��Open Implementation==> ResultSetInternalMethods-com����
		==��public Object getObject(int columnIndex) throws SQLException {}==��switch_case
*/
					
					map.put(columnLabel, columnValue);
				}
			}
			
//			5. �� Map ��Ϊ�ռ�, ���÷��䴴�� clazz ��Ӧ�Ķ���
			if(map.size() > 0){
				entity = clazz.newInstance();
				
//				6. ���� Map ����, ���÷���Ϊ Class �����Ӧ�����Ը�ֵ. 
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
