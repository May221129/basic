package reflection.a01classtest;
/**
 * getFields():获取所有能够获取到的属性；
 * getDeclaredFields()：获取所有已经声明了的方法。
 * setAccessible(true):设置权限为能够访问，false：设置权限为不能访问。
 * 
 * 反射比较深的知识：com.llm.bookstore.dao.impl.BaseDAO<T>
 * 一. 泛型所涉及到的反射的知识；
 * 构造器中的 this.getClass().getName() ==> this代表谁？
 * 现象：
 *  在BaseDAO中输出：com.llm.bookstore.dao.impl.BaseDAO
 *  在继承了BaseDAO的BookDAOImpl中输出：com.llm.bookstore.dao.impl.BookDAOImpl
 * 结论：
 * this代表什么？
 *  运行时的对象如果是子类对象，this就是子类对象；
 *  运行时的对象如果是父类对象，this就是父类对象；
 * 
 * 二. 怎么确定泛型T的Class呢？
 * 答：
 * 	getSuperGenericType(getClass())：获取当前类的父类的泛型。
 * 	结合上面的“一”的知识。
 * 	如：class BookDAOImpl extends BaseDAO<Book>，
 * 		此时BookDAOImpl.getClass()=BookDAOImpl;
 * 		BookDAOImpl.getSuperGenericType(getClass())=BaseDAO的泛型。
 */

import java.lang.reflect.Field;
import java.lang.reflect.Method;

//Person类的实例化，及属性、方法的调用：
import org.junit.Test;

public class TestPerson_HasNote {
//	java.lang.Class：是反射的源头：
	@Test
	public void test2(){
		Person p = new Person();
		Class clazz = p.getClass();//通过运行时类的对象，调用其getClass()，返回其运行时类。
		System.out.println(clazz);//class teseclass.Person
	}
	
//	有了反射后，可以通过反射创建一个类的对象，并调用其中的结构：
	@Test
	public void test1() throws InstantiationException, Exception{
		Class clazz = Person.class;
//		1、创建clazz对应的运行时类Person的对象：
		Person p = (Person)clazz.newInstance();
//		2、通过反射调用运行时类的指定的属性：
//		2.1、调用public的属性：
		Field f1 = clazz.getField("name");
		f1.set(p, "赖皮梅");
//		2.2、调用非public的属性：
		Field f2 = clazz.getDeclaredField("age");
		f2.setAccessible(true);
		f2.set(p, 26);
//		3、通过反射调用运行时类的指定的方法：
//		3.1、调用有入参的方法：
		Method m1 = clazz.getMethod("toString");
		System.out.println(m1.invoke(p));
//		3.2、调用空参的方法：
		Method m2 = clazz.getMethod("display", String.class);
		m2.invoke(p, "China");
	}
//	在没有反射时，创建一个类对象，并调用其方法、属性:
	@Test
	public void test(){
		Person p = new Person();
		p.setName("赖皮梅");
		p.setAge(26);
		System.out.println(p.toString());//Person [name=赖皮梅, age=26]
	}
}
