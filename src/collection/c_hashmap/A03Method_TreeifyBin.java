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
 * 		putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
 * 				……
			else {
            	……
            	else {
                	for (int binCount = 0; ; ++binCount) {
                    	if ((e = p.next) == null) {
                        	p.next = newNode(hash, key, value, null);
                        	if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            	treeifyBin(tab, hash);//链表转红黑树：Node转成TreeNode《================
                        	break;
                    	}
                  ……
 * 		}
 * 		【源码见：/javase/src/collection/c_hashmap/A03Method_Put.java类中对put()方法源码的解析。】
 * 	3.通过debug断点，查看当同一个桶上的链表的长度达到多长时会做“链表转红黑树”的操作。
 * 		断点打在HashMap.putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict)方法的
 * 		“treeifyBin(tab, hash);”这里。
 * 	4.链表转红黑树的核心步骤：
 * 		（1）要做转换的链表上的所有节点右普通节点转为树节点；
 * 		（2）构建树；
 * 	5.为什么Node可以转为TreeNode？
 * 		因为 Node 是 TreeNode 的爷爷：
 * 		【树节点HashMap.TreeNode<K,V>】 extends 【双链表节点LinkedHashMap.Entry<K,V>】 extends 【单链表节点HashMap.Node<K,V>】 implements 【Map.Entry<K,V>】
 * 		Node转为TreeNode后，Node中的“Node<K,V> next”变量在TreeNode中保留了下来（父类的成员变量子类可以直接获取、使用，爷爷传承到父类，父类再传承到子类），
 * 		所以即使转成了红黑树，但已经可以通过“next、next”去遍历红黑树，原链表的节点顺序最终通过 next 引用被保存下来。
 * 	6.问：为什么双链表节点LinkedHashMap.Entry的命名是Entry，而不是Node？
 * 		答：历史原因：hashMap在jdk1.7的节点就是Entry，在1.8之后才引入了红黑树，所以所有节点的概念，但这时名字已经不能改了，要做兼容。
 * 	7.链表转成红黑树之后，是直接将这个链表删除掉吗？还是留着了？
 * 		答：我没看源码，但我猜一定是删掉，因为留着链表是多余的，TreeNode中保留了维护节点顺序的指针。
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
