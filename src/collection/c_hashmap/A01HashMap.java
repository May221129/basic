package collection.c_hashmap;
/**
 * Map常用的方法。
 * 
 * 1.HashMap默认的桶容量是16个位置，每个位置上放的都是一个链表(元素的hash值  % 桶容量  = 余数，余数决定了这个元素放在哪个位置上，余数相同的放同一位置的链表上（JDK1.7及1.7以前是链表）。)
 * 	所以在没有扩容机制的前提下，容量为16的HashMap是可以存放无穷多的元素的。
 * 	但是同一位置上的链表如果过长，就会失去HashMap的O1的查找速度的意义，所以当元素的数量达到了容量的0.75L时，HashMap就会进行扩容。
 * 2.TreeMap是一棵红黑树，HashMap的桶的每个位置后面都是一棵红黑树（JDK1.8及1.8以后以后是红黑树）。选择什么集合，要考虑这个集合你要做什么功能：频繁的增删改查存放的元素很少？
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class A01HashMap {
	@Test
	public void testMap(){
		Map<String,Integer> m1 = new HashMap<String,Integer>();
		m1.put("李光荣", 26);//向Map中添加一个元素
		m1.put("赖丽梅", 26);
		m1.put("李天昱", 12);
		m1.put("李光荣", 18);
		m1.put("拉拉", 2);	
		m1.put(null, null);
		System.out.println(m1);
		m1.remove("赖丽梅");//删除指定的value值
		System.out.println(m1);
		
		System.out.println("………………遍历key集………………");
		
//		遍历key集：
		Set<String> s = m1.keySet();
		for(Object o : s){
			System.out.println(o);
		}
		
		System.out.println("………………遍历value集………………");
		
//		遍历value集：
		Collection<Integer> c = m1.values();
		Iterator<Integer> i = c.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
		
		System.out.println("………………遍历key-value对,方式一………………");
		
//		遍历key-value对：
//		方法一：
		Set<String> set = m1.keySet();
		for(Object o : set){
			System.out.println(o + "=" + m1.get(o));
		}
		
		System.out.println("………………遍历key-value对,方式二………………");
		
//		方法二：
		Set<Map.Entry<String, Integer>> set2 = m1.entrySet();
		for(Map.Entry<String, Integer> entry: set2){
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}
}
