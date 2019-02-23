package collection.a_note;
/**
 * 遍历集合的4种方式
 * 第2、3种方法，同样适用于数组的遍历，但是数组无法使用第1、4种迭代器Iterator的方法，因为数组没有iterator（）方法。
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

public class A01CommonFor_For_Iterator {
	
	/**
	 * 方式一：迭代器iterator() (推荐使用)
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
	 * 方式二：增强for循环（推荐使用）
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
	 * 方法三：普通的for循环（并不推荐使用，因为上面已经有现成的方法可以调用了）
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
	 * 方式四：iterator.next()
	 * （不具有通用性，因为你不会每次都知道集合中有几个元素）
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
	 * 遍历集合错误的写法（会间隔着一个元素输出）、
	 * next():返回迭代中的下一个元素
	 * hasNext():如果迭代有更多的元素，则返回{@ code true}。
	 */
	@Test
	public void testMiss(){
		
		Collection<Object> coll = new ArrayList<Object>();
		coll.add(1);
		coll.add(2);
		coll.add("Hello!");
		coll.add("阿猫");
		
		Iterator<Object> iterator = coll.iterator();
		
		while(iterator.next() != null){//错误的！
			System.out.println(iterator.next());
		}
		
		while(iterator.hasNext()){//正确的！
			System.out.println(iterator.next());
		}
	}
}
