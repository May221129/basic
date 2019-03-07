package collection.b_arraylist;
//Collection中Iterator()方法的使用：

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

public class TestIterator {
	@Test
	public void testFor1(){
//		使用增加For循环实现数组的遍历：
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
		c1.add(new Person("李光渣渣", 26));
		
//		使用增加For循环实现集合的遍历：
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
		c1.add(new Person("李光渣渣", 26));
		
//		遍历集合正确的写法：使用迭代器Iterator实现集合的遍历：
		Iterator<Object> iterator = c1.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
//		遍历集合错误的写法：
//		while(iterator.next() != null){
//			System.out.println(iterator.next());//会间隔着一个元素输出
//		}
	}
}
