package collection.c_hashmap;

import java.util.HashMap;

/**
 * 探究：HashMap链表转红黑树的具体时机。
 * 	1.HashMap中有关“链表转红黑树”阈值的声明：
 * 		static final int TREEIFY_THRESHOLD = 8;
 * 		注释：使用红黑树(而不是链表)来存放元素节点。当向至少具有这么多节点的链表再添加元素时，链表将转换为红黑树。
 * 			该值必须大于2，并且应该至少为8，以便于删除红黑树时转回链表。
 * 	2.解析源码：
 * 		putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict)方法中，涉及到链表转红黑树。
 * 		【源码见：/javase/src/collection/c_hashmap/A03Method_Put.java类中对put()方法源码的解析。】
 * 	3.通过debug断点，查看当同一个桶上的链表的长度达到多长时会做“链表转红黑树”的操作。
 * 		断点打在HashMap.putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict)方法的
 * 		“treeifyBin(tab, hash);”这里。
 * 
 * @author May
 * 2019年7月31日
 */
public class A03Method_TreeifyBin {
	public static void main(String[] args) {
		HashMap<A03Bean, Integer> hashMap = new HashMap<>();
		hashMap.put(new A03Bean(4), 0);
		hashMap.put(new A03Bean(8), 1);
		hashMap.put(new A03Bean(12), 2);
		hashMap.put(new A03Bean(16), 3);
		hashMap.put(new A03Bean(20), 4);
		hashMap.put(new A03Bean(24), 5);
		hashMap.put(new A03Bean(28), 6);
		hashMap.put(new A03Bean(32), 7);
		hashMap.put(new A03Bean(36), 8);
		hashMap.put(new A03Bean(40), 9);
		hashMap.put(new A03Bean(44), 10);
		
		System.out.println("hashMap.size = " + hashMap.size());
		
		//查看是否所有对象都放到HashMap中了：
		for(A03Bean key : hashMap.keySet()) {
			System.out.println(key.number);
		}
	}
}
