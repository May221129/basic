package jdbc.dao;

/**
 * BeanUtils�����ࣺ����Java�������
 * 
 * 1��ͨ��BeanUtils���setProperty(Object bean, String name, Object value)����������������Ը�ֵ��
 * 	���������ͨ��set��������ֵ�ģ�����setProperty()�����еġ�String name��Ҫ�����set����������һ�¡�
 * 
 * 2������BeanUtils���getProperty(Object bean, String name) ��������ȡ���������ֵ��
 * 	���з����ġ�String name��Ҫ�����get����������һ�¡�
 * 
 * ע��㣺jar�����ݵ����⣬��ʹ�ù��������������
 * 	��java.lang.NoClassDefFoundError: org/apache/commons/collections/FastHashMap���쳣��
 * 	һ������Ϊjar�������ݣ���Ҫ�ٵ��롰commons-collections-3.2.2.jar�����С�
 * 
 * jar��jar֮��Ĳ�����:
 * jar A ���õ�jar B ������࣬jar A û������������jar B ������һ���汾����ԭ��jar A ���õ����Ǹ����ȥ���ˣ��������оͻᱨ���ˡ�
 */
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import jdbc.jdbctools.Student;

public class TestBeanUtils {
	
	@Test
	public void testGetProperty() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		
		Object object = new Student();
		
		BeanUtils.setProperty(object, "IDCard", "123456789");
		System.out.println(object);
		
		Object value = BeanUtils.getProperty(object, "IDCard");
		System.out.println(value);
	}
	
	@Test
	public  void testSetProperty() throws IllegalAccessException, InvocationTargetException{
		
		Object object = new Student();
		System.out.println(object);
		
		BeanUtils.setProperty(object, "IDCard", "123456789");
		System.out.println(object);
	}
}
