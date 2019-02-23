package collection.a_note;
/**
 * �������ϵ�4�ַ�ʽ
 * ��2��3�ַ�����ͬ������������ı��������������޷�ʹ�õ�1��4�ֵ�����Iterator�ķ�������Ϊ����û��iterator����������
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

public class A01CommonFor_For_Iterator {
	
	/**
	 * ��ʽһ��������iterator() (�Ƽ�ʹ��)
	 */
	@Test
	public void testIterator(){
		
		Collection<Object> coll = new ArrayList<Object>();
		coll.add(1);
		coll.add(2);
		coll.add("Hello!");
		
		Iterator<Object> iterator = coll.iterator();

		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
	
	/**
	 * ��ʽ������ǿforѭ�����Ƽ�ʹ�ã�
	 */
	@Test
	public void testFor(){
		
		Collection<Object> coll = new ArrayList<Object>();
		coll.add(1);
		coll.add(2);
		coll.add("Hello!");
		
		for(Object obj : coll){
			System.out.println(obj);
		}
	}
	/**
	 * ����������ͨ��forѭ���������Ƽ�ʹ�ã���Ϊ�����Ѿ����ֳɵķ������Ե����ˣ�
	 */
	@Test
	public void testCommonFor1(){
		
		Collection<Object> coll = new ArrayList<Object>();
		coll.add(1);
		coll.add(2);
		coll.add("Hello!");
		
		Object[] obj = coll.toArray( );
		
		for(int i = 0; i < obj.length; i++){
			System.out.println(obj[i]);
		}		
	}
	
	@Test
	public void testCommonFor2(){
		
		ArrayList<Object> array = new ArrayList<Object>();
		array.add(1);
		array.add(2);
		array.add("Hello!");
		
		for(int i = 0; i < array.size(); i++){
			System.out.println(array.get(i));
		}
	}
	/**
	 * ��ʽ�ģ�iterator.next()
	 * ��������ͨ���ԣ���Ϊ�㲻��ÿ�ζ�֪���������м���Ԫ�أ�
	 */
	@Test
	public void testNext(){
		
		Collection<Object> coll = new ArrayList<Object>();
		coll.add(1);
		coll.add(2);
		coll.add("Hello!");
		
		Iterator<Object> iterator = coll.iterator();
		System.out.println(iterator.next());
		System.out.println(iterator.next());
		System.out.println(iterator.next());
	}
	
	/**
	 * �������ϴ����д����������һ��Ԫ���������
	 * next():���ص����е���һ��Ԫ��
	 * hasNext():��������и����Ԫ�أ��򷵻�{@ code true}��
	 */
	@Test
	public void testMiss(){
		
		Collection<Object> coll = new ArrayList<Object>();
		coll.add(1);
		coll.add(2);
		coll.add("Hello!");
		coll.add("��è");
		
		Iterator<Object> iterator = coll.iterator();
		
		while(iterator.next() != null){//����ģ�
			System.out.println(iterator.next());
		}
		
		while(iterator.hasNext()){//��ȷ�ģ�
			System.out.println(iterator.next());
		}
	}
}
