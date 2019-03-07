package jdbc.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

/**
 * ���ݿ���ʣ�DAO��Data Access Object��
 * 
 * why��ʵ�ֹ��ܵ�ģ�黯���������ڴ����ά����������
 * 	DAO���Ա�����̳л�ֱ��ʹ�á�
 * 
 * what������������Ϣ���࣬�����˶����ݵ�CRUD��crete,read,update,delete����ɾ�Ĳ飩��
 * 	���������κ�ҵ����ص���Ϣ��
 * 
 * how��ʹ��JDBC��дDAO���ܰ�����Щ������
 * 
 * 	INSERT,UPDATE,DELETE���������԰���������
 * 		void update (String sql, Object... args)
 * 
 * 	��ѯһ����¼�����ض�Ӧ�Ķ���
 * 		<T> T get(Class<T> clazz, String sql, Object... args)
 * 
 * 	��ѯ������¼�����ض�Ӧ�Ķ���ļ��ϣ�
 * 		<T> List<T> getForList(Class<T> clazz, String sql, Object... args)
 * 
 * 	����ĳ����¼��ĳһ���ֶε�ֵ����һ��ͳ�Ƶ�ֵ��һ���ж�������¼���ȵȣ�
 * 		<E> E getForValue(String sql, Object... args);
 */
public class DataAccessObject {
//	INSERT,UPDATE,DELETE���������԰���������
	public void update (String sql, Object... args){
		 Connection connection = null;
		 PreparedStatement ps = null;
		 try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			ps = connection.prepareStatement(sql);
			
			for(int i = 0; i < args.length; i++){
				ps.setObject(i+1, args[i]);
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(null, ps, connection);
		}
	 }
	
//	�������get()������������,�Ͳ�ѯ������¼�ķ�������ͳһ��
//	��ѯһ����¼�����ض�Ӧ�Ķ���
	public <T> T get2(Class<T> clazz, String sql, Object... args){
		
		List<T> result = getForList(clazz, sql, args);
		
		if(result.size() > 0){
			return result.get(0);
		}
		
		return null;
	}
	
//	 ��ѯһ����¼�����ض�Ӧ�Ķ���
	 public <T> T get(Class<T> clazz, String sql, Object... args){
		 
		 T entity = null;
		 
		 Connection connection = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			ps = connection.prepareStatement(sql);
			
			for(int i = 0; i < args.length; i++){
				ps.setObject(i+1, args[i]);
			}
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				ResultSetMetaData rsmd = rs.getMetaData();
				
//				��ResultSetMetaData����õ���������ж����У�
				int columnCount = rsmd.getColumnCount();
				
//				��ResultSetMetaData����õ�ÿһ�еı�������ResultSet�õ�����ÿһ�е�ֵ��
				for(int i = 0; i < columnCount; i++){
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Object colnmValue = rs.getObject(columnLabel);//����Ҳ����дgetObject(i+1)
					
					map.put(columnLabel, colnmValue);
				}
				
//				�÷��䴴��Class��Ӧ�Ķ���
				entity = clazz.newInstance();
				
//				����Map���ϣ��÷��������������ֵ����������Map�е�Key��ֵ��Map�е�Value
				for(Map.Entry<String, Object> entry: map.entrySet()){
					String propertyName = entry.getKey();
					Object value = entry.getValue();
					
//					�������ַ���������Ϊ���Ը�ֵ�������Ƽ�ʹ�ú���һ�֣�
//					jdbctools.ReflectionUtils.setFieldValue(entity, propertyName, value);//���÷�������ֵ
					BeanUtils.setProperty(entity, propertyName, value);//���ò���������ԵĹ��߰�BeanUtils��setProperty()��������ֵ
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(rs, ps, connection);
		}
		 
		 return entity;
	 }
	 
//	 ��ѯ������¼�����ض�Ӧ�Ķ���ļ��ϣ�
	 public <T> List<T> getForList(Class<T> clazz, String sql, Object... args){
		
		 List<T> list = new ArrayList<>();

//		 1. �õ������: �ý����Ӧ��ֻ��һ��, ��ֻ��һ��
		 Connection connection = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 try {

//			�õ������:
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			ps = connection.prepareStatement(sql);
			
//			���ռλ����
			for(int i = 0; i < args.length; i++){
				ps.setObject(i+1, args[i]);
			}
			
			rs = ps.executeQuery();
			
//			2�������������õ� Map �� List, ����һ�� Map �������һ����¼. 
//			Map �� key Ϊ reusltSet ���еı���, Map �� valueΪ�е�ֵ.
			List<Map<String, Object>> values = handleResultSetToMapList(rs);
			
//			3. �� Map �� List תΪ clazz ��Ӧ�� List
//			���� Map �� key ��Ϊ clazz ��Ӧ�Ķ���� propertyName, 
//			�� Map �� value ��Ϊ clazz ��Ӧ�Ķ���� propertyValue
			list = transferMapListToBeanList(clazz, values);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(rs, ps, connection);
		}
		 
		 return list;
	 }
	 
//	�ж�List�Ƿ�Ϊ�ռ�������Ϊ�գ������List���õ�һ������Map�����ٰ�һ����Map����תΪClass������Ӧ��Object����
	private <T> List<T> transferMapListToBeanList(Class<T> clazz, List<Map<String, Object>> values)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		
//		׼��һ��List<Map<String, Object>>��������еı�����ֵ����е�ֵ������һ��Map�����Ӧ��һ����¼		
		List<T> result = new ArrayList<>();
		
		T bean = null;
		
		if(values.size() > 0){
			for(Map<String, Object> map2: values){
				bean = clazz.newInstance();
				for(Map.Entry<String, Object> entry : map2.entrySet()){
					String propertyName = entry.getKey();
					Object value = entry.getValue();
					
					BeanUtils.setProperty(bean, propertyName, value);
				}
				result.add(bean);
			}
		}
		return result;
	}

//	�����������õ�Map��һ��List������һ��Map�����Ӧһ����¼��
	 private List<Map<String, Object>> handleResultSetToMapList(ResultSet rs) throws SQLException {
		 List<Map<String, Object>> values = new ArrayList<>();
	
		 List<String> columnLabels = getColumnLabels(rs);
	
		 Map<String, Object> map = null;
	
		 while(rs.next()){		
			 map = new HashMap<>();
		
			 for(String columnLabel : columnLabels){
				 Object value = rs.getObject(columnLabel);
				 map.put(columnLabel, value);
			 }
		
//			��һ����¼��һ��Map���󣬷���׼���õ�List�����У�
			 values.add(map);
		 }
		 return values;
	 }
	 
//	 ��ȡ�������ColumnLabel��Ӧ��List��
	 public List<String> getColumnLabels(ResultSet rs) throws SQLException{
		 
		 List<String> columnLabels = new ArrayList<>();
		 
		 ResultSetMetaData rsmd = rs.getMetaData();
		 
		 for(int i = 0; i < rsmd.getColumnCount(); i++){
			 columnLabels.add(rsmd.getColumnLabel(i+1));
		 }
		 
		 return columnLabels;
	 }
	 
//	 ����ĳ����¼��ĳһ���ֶε�ֵ����һ��ͳ�Ƶ�ֵ��һ���ж�������¼���ȵȣ�
	 /*
	  * ���岽�裺
	  * 1���õ���������ý����Ӧ��ֻ��һ�У���ֻ��һ�У�
	  * 2��ȡ�ý������
	  */
	public  <E> E getForValue(String sql, Object... args){
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		 
		try {
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			ps = connection.prepareStatement(sql);
			
			for(int i = 0; i < args.length; i++){
				ps.setObject(i+1, args[i]);
			}
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				return (E) rs.getObject(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(rs, ps, connection); 
		}
		return null;
	}
}
