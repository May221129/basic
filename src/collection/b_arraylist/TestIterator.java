package collection.b_arraylist;
//Collection��Iterator()������ʹ�ã�

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

public class TestIterator {
	@Test
	public void testFor1(){
//		ʹ������Forѭ��ʵ������ı�����
		String[] str = new String[]{"AA","BB","CC","DD"};
		for(String s : str){
			System.out.println(s);
		}
	}
	
	@Test
	public void testFor(){
		Collection<Object> c1 = new ArrayList<>();
		c1.add(1);
		c1.add(2);
		c1.add(3);
		c1.add("Hello!");
		c1.add(new Person("�������", 26));
		
//		ʹ������Forѭ��ʵ�ּ��ϵı�����
		for(Object i : c1){
			System.out.println(i);
		}
	}
	
	@Test
	public void testCollection1(){
		Collection<Object> c1 = new ArrayList<>();
		c1.add(1);
		c1.add(2);
		c1.add(3);
		c1.add("Hello!");
		c1.add(new Person("�������", 26));
		
//		����������ȷ��д����ʹ�õ�����Iteratorʵ�ּ��ϵı�����
		Iterator<Object> iterator = c1.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
//		�������ϴ����д����
//		while(iterator.next() != null){
//			System.out.println(iterator.next());//������һ��Ԫ�����
//		}
	}
}
