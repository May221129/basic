package jdbc.dao;

/**
 * BeanUtils工具类：操作Java类的属性
 * 
 * 1、通过BeanUtils类的setProperty(Object bean, String name, Object value)方法，给对象的属性赋值；
 * 	类的属性是通过set方法来赋值的，所以setProperty()方法中的“String name”要和类的set方法的名称一致。
 * 
 * 2、调用BeanUtils类的getProperty(Object bean, String name) 方法，获取对象的属性值；
 * 	其中方法的“String name”要和类的get方法的名称一致。
 * 
 * 注意点：jar包兼容的问题，在使用过程中如果出现了
 * 	“java.lang.NoClassDefFoundError: org/apache/commons/collections/FastHashMap”异常，
 * 	一般是因为jar包不兼容，需要再导入“commons-collections-3.2.2.jar”才行。
 * 
 * jar与jar之间的不兼容:
 * jar A 会用到jar B 里面的类，jar A 没有升级，但是jar B 升级了一个版本，把原本jar A 会用到的那个类给去掉了，程序运行就会报错了。
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
