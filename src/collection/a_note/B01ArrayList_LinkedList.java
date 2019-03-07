package collection.a_note;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.junit.Test;

/**
 * 测试：分别用ArrayList和LinkedList遍历10万级别的数，用时各为多少？
 * 重点：1.用的循环方式(for？Iterator？)； 2.使用什么方法(get(i)获取下标的方式？next()?)
 * 
 * 问题1：为什么LinkedList遍历时那么慢？和ArrayList相比是指数倍的慢。
 * 答：【需要看两个集合的get(i)方法的底层代码得知】
 * 	ArrayList是直接通过数组来进行存储，而LinkedList则是使用数组模拟指针，来实现链表的方式。
 *  ==>ArrayList在使用下标的方式循环遍历的时候性能最好，通过下标可以直接取数据，速度最快。
 *  ==>linkedList则是先将集合的第一个元素的节点和最后一个元素的节点存为成员变量，
 * 	再将index和集合中间的那个数据元素下标进行比较，
 * 	如果index小，则从第一个元素的节点一直next下去，直到找到index那个元素为止；
 * 	如果index大，则从最后一个元素的节点一直prev回来，直到找到index那个元素为止。
 * 	当遍历的级别很大的时候，每次get(index)都是从第一个元素节点开始，或者从最后一个元素节点回来，一直找到index那个元素。index越大，next和prev要走越多。
 * 
 * 问题2：既然使用普通的循环，获取get(i),会使LinkedList遍历效率很慢，那么怎样才能提高LinkedList的遍历效率呢？
 * 答：使用迭代器方式。
 * 	LinkedList在使用迭代器遍历时会效率最高，迭代器直接通过LinkedList的指针进行遍历，
 * 	ArrayList在使用迭代器时，因为要通过ArrayList先生成指针，因此效率就会低于下标方式，但还是快于LinkedList。
 * 
 * 总结：在进行list遍历时，如果是对ArrayList进行遍历，推荐使用下标方式，如果是LinkedList则推荐使用迭代器方式。
 */
public class B01ArrayList_LinkedList {
	
	/**
	 * 1.使用获取下标的方式，普通for循环：
	 * ArrayList：1毫秒；
	 * LinkedList：6000毫秒左右；
	 */
	@Test
	public void testArrayListAndLinkedList(){
		
		int count = 10000*10;
		ArrayList<Integer> array = new ArrayList<Integer>(count);
		LinkedList<Integer> linked = new LinkedList<Integer>();
		for(int i = 0; i < count; i++){
			array.add(i);
			linked.add(i);
		}
		//遍历ArrayList：
		long startA = System.currentTimeMillis();
		for(int i = 0; i < array.size(); i++){
			array.get(i);
		}
		long endA = System.currentTimeMillis();
		System.out.println("A:" + (endA - startA));
		
		//遍历LinkedList：
		long startL = System.currentTimeMillis();
		for(int i = 0; i < linked.size(); i++){
			linked.get(i);
		}
		long endL = System.currentTimeMillis();
		System.out.println("L:" + (endL - startL));
	}
	
	/**
	 * 2.使用next()方法，Iterator迭代器：
	 * ArrayList：5毫秒；
	 * LinkedList：90+毫秒；
	 */
	@Test
	public void testIterator(){
		
		int count = 10000*1000;
		ArrayList<Integer> array = new ArrayList<Integer>(count);
		LinkedList<Integer> linked = new LinkedList<Integer>();
		for(int i = 0; i < count; i++){
			array.add(i);
			linked.add(i);
		}
		
		//遍历ArrayList：
		long startA = System.currentTimeMillis();
		Iterator<Integer> ai = array.iterator();
		while(ai.hasNext()){
			ai.next();
		}
		long endA = System.currentTimeMillis();
		System.out.println("A:" + (endA - startA));
		
		long startL = System.currentTimeMillis();
		Iterator<Integer> li = linked.iterator();
		while(li.hasNext()){
			li.next();
		}
		long endL = System.currentTimeMillis();
		System.out.println("L:" + (endL - startL));
	}
}
