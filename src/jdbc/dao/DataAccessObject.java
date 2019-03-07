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
 * 数据库访问：DAO（Data Access Object）
 * 
 * why：实现功能的模块化。更有利于代码的维护和升级。
 * 	DAO可以被子类继承或直接使用。
 * 
 * what：访问数据信息的类，包含了对数据的CRUD（crete,read,update,delete。增删改查），
 * 	而不包含任何业务相关的信息。
 * 
 * how：使用JDBC编写DAO可能包含哪些方法：
 * 
 * 	INSERT,UPDATE,DELETE操作都可以包含在其中
 * 		void update (String sql, Object... args)
 * 
 * 	查询一条记录，返回对应的对象：
 * 		<T> T get(Class<T> clazz, String sql, Object... args)
 * 
 * 	查询多条记录，返回对应的对象的集合：
 * 		<T> List<T> getForList(Class<T> clazz, String sql, Object... args)
 * 
 * 	返回某条记录的某一个字段的值，或一个统计的值（一共有多少条记录，等等）
 * 		<E> E getForValue(String sql, Object... args);
 */
public class DataAccessObject {
//	INSERT,UPDATE,DELETE操作都可以包含在其中
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
	
//	对下面的get()方法进行升级,和查询多条记录的方法进行统一。
//	查询一条记录，返回对应的对象：
	public <T> T get2(Class<T> clazz, String sql, Object... args){
		
		List<T> result = getForList(clazz, sql, args);
		
		if(result.size() > 0){
			return result.get(0);
		}
		
		return null;
	}
	
//	 查询一条记录，返回对应的对象：
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
				
//				由ResultSetMetaData对象得到结果集中有多少列：
				int columnCount = rsmd.getColumnCount();
				
//				由ResultSetMetaData对象得到每一列的别名，由ResultSet得到具体每一列的值：
				for(int i = 0; i < columnCount; i++){
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Object colnmValue = rs.getObject(columnLabel);//这里也可以写getObject(i+1)
					
					map.put(columnLabel, colnmValue);
				}
				
//				用反射创建Class对应的对象：
				entity = clazz.newInstance();
				
//				遍历Map集合，用反射填充对象的属性值：属性名是Map中的Key，值是Map中的Value
				for(Map.Entry<String, Object> entry: map.entrySet()){
					String propertyName = entry.getKey();
					Object value = entry.getValue();
					
//					下面两种方法都可以为属性赋值，但是推荐使用后面一种：
//					jdbctools.ReflectionUtils.setFieldValue(entity, propertyName, value);//利用反射来赋值
					BeanUtils.setProperty(entity, propertyName, value);//利用操作类的属性的工具包BeanUtils的setProperty()方法来赋值
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(rs, ps, connection);
		}
		 
		 return entity;
	 }
	 
//	 查询多条记录，返回对应的对象的集合：
	 public <T> List<T> getForList(Class<T> clazz, String sql, Object... args){
		
		 List<T> list = new ArrayList<>();

//		 1. 得到结果集: 该结果集应该只有一行, 且只有一列
		 Connection connection = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 try {

//			得到结果集:
			connection = jdbc.jdbctools.JDBCTools.getConnection();
			ps = connection.prepareStatement(sql);
			
//			填充占位符：
			for(int i = 0; i < args.length; i++){
				ps.setObject(i+1, args[i]);
			}
			
			rs = ps.executeQuery();
			
//			2、处理结果集，得到 Map 的 List, 其中一个 Map 对象就是一条记录. 
//			Map 的 key 为 reusltSet 中列的别名, Map 的 value为列的值.
			List<Map<String, Object>> values = handleResultSetToMapList(rs);
			
//			3. 把 Map 的 List 转为 clazz 对应的 List
//			其中 Map 的 key 即为 clazz 对应的对象的 propertyName, 
//			而 Map 的 value 即为 clazz 对应的对象的 propertyValue
			list = transferMapListToBeanList(clazz, values);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			jdbc.jdbctools.JDBCTools.release(rs, ps, connection);
		}
		 
		 return list;
	 }
	 
//	判断List是否为空集，若不为空，则遍历List，得到一个个的Map对象，再把一个个Map对象转为Class参数对应的Object对象：
	private <T> List<T> transferMapListToBeanList(Class<T> clazz, List<Map<String, Object>> values)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		
//		准备一个List<Map<String, Object>>：键存放列的别名，值存放列的值，其中一个Map对象对应着一条记录		
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

//	处理结果集，得到Map的一个List，其中一个Map对象对应一条记录：
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
		
//			把一条记录的一个Map对象，放入准备好的List集合中：
			 values.add(map);
		 }
		 return values;
	 }
	 
//	 获取结果集的ColumnLabel对应的List：
	 public List<String> getColumnLabels(ResultSet rs) throws SQLException{
		 
		 List<String> columnLabels = new ArrayList<>();
		 
		 ResultSetMetaData rsmd = rs.getMetaData();
		 
		 for(int i = 0; i < rsmd.getColumnCount(); i++){
			 columnLabels.add(rsmd.getColumnLabel(i+1));
		 }
		 
		 return columnLabels;
	 }
	 
//	 返回某条记录的某一个字段的值，或一个统计的值（一共有多少条记录，等等）
	 /*
	  * 具体步骤：
	  * 1、得到结果集：该结果集应该只有一行，且只有一列；
	  * 2、取得结果集的
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
