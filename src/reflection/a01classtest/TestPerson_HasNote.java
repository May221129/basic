package reflection.a01classtest;
/**
 * getFields():��ȡ�����ܹ���ȡ�������ԣ�
 * getDeclaredFields()����ȡ�����Ѿ������˵ķ�����
 * setAccessible(true):����Ȩ��Ϊ�ܹ����ʣ�false������Ȩ��Ϊ���ܷ��ʡ�
 * 
 * ����Ƚ����֪ʶ��com.llm.bookstore.dao.impl.BaseDAO<T>
 * һ. �������漰���ķ����֪ʶ��
 * �������е� this.getClass().getName() ==> this����˭��
 * ����
 *  ��BaseDAO�������com.llm.bookstore.dao.impl.BaseDAO
 *  �ڼ̳���BaseDAO��BookDAOImpl�������com.llm.bookstore.dao.impl.BookDAOImpl
 * ���ۣ�
 * this����ʲô��
 *  ����ʱ�Ķ���������������this�����������
 *  ����ʱ�Ķ�������Ǹ������this���Ǹ������
 * 
 * ��. ��ôȷ������T��Class�أ�
 * ��
 * 	getSuperGenericType(getClass())����ȡ��ǰ��ĸ���ķ��͡�
 * 	�������ġ�һ����֪ʶ��
 * 	�磺class BookDAOImpl extends BaseDAO<Book>��
 * 		��ʱBookDAOImpl.getClass()=BookDAOImpl;
 * 		BookDAOImpl.getSuperGenericType(getClass())=BaseDAO�ķ��͡�
 */

import java.lang.reflect.Field;
import java.lang.reflect.Method;

//Person���ʵ�����������ԡ������ĵ��ã�
import org.junit.Test;

public class TestPerson_HasNote {
//	java.lang.Class���Ƿ����Դͷ��
	@Test
	public void test2(){
		Person p = new Person();
		Class clazz = p.getClass();//ͨ������ʱ��Ķ��󣬵�����getClass()������������ʱ�ࡣ
		System.out.println(clazz);//class teseclass.Person
	}
	
//	���˷���󣬿���ͨ�����䴴��һ����Ķ��󣬲��������еĽṹ��
	@Test
	public void test1() throws InstantiationException, Exception{
		Class clazz = Person.class;
//		1������clazz��Ӧ������ʱ��Person�Ķ���
		Person p = (Person)clazz.newInstance();
//		2��ͨ�������������ʱ���ָ�������ԣ�
//		2.1������public�����ԣ�
		Field f1 = clazz.getField("name");
		f1.set(p, "��Ƥ÷");
//		2.2�����÷�public�����ԣ�
		Field f2 = clazz.getDeclaredField("age");
		f2.setAccessible(true);
		f2.set(p, 26);
//		3��ͨ�������������ʱ���ָ���ķ�����
//		3.1����������εķ�����
		Method m1 = clazz.getMethod("toString");
		System.out.println(m1.invoke(p));
//		3.2�����ÿղεķ�����
		Method m2 = clazz.getMethod("display", String.class);
		m2.invoke(p, "China");
	}
//	��û�з���ʱ������һ������󣬲������䷽��������:
	@Test
	public void test(){
		Person p = new Person();
		p.setName("��Ƥ÷");
		p.setAge(26);
		System.out.println(p.toString());//Person [name=��Ƥ÷, age=26]
	}
}
