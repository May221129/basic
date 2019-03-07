package collection.b_arraylist;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

/**
 * 测试：边遍历集合，边往该集合中添加元素。
 * 结果：抛出“ConcurrentModificationException”。
 */
public class InteratorAndAddTest {
	
	/**
	 * 抛出“ConcurrentModificationException”。
	 */
	@Test
	public void iteratorAndAddTest(){
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("A");
		arrayList.add("B");
		arrayList.add("C");
		arrayList.add("D");
		arrayList.add("E");
		
		Iterator<String> iterator = arrayList.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
			arrayList.add("AA");
		}
	}
	
	/**
	 * 抛出“ConcurrentModificationException”。
	 */
	@Test
	public void iteratorAndAddTest1(){
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("A");
		arrayList.add("B");
		arrayList.add("C");
		arrayList.add("D");
		arrayList.add("E");
		
		Iterator<String> iterator = arrayList.iterator();
		while(iterator.hasNext()){
			if(iterator.next().equals("C")){
				arrayList.remove("C");
			}
		}
	}
	
	/**
	 * 不会报错。
	 */
	@Test
	public void iteratorAndAddTest2(){
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("A");
		arrayList.add("B");
		arrayList.add("C");
		arrayList.add("D");
		arrayList.add("E");
		
		Iterator<String> iterator = arrayList.iterator();
		while(iterator.hasNext()){
			if(iterator.next().equals("C")){
				iterator.remove();
			}
		}
	}
}
