package collection.c_hashmap;

/**
 * 探究：HashMap.put(K key, V value)方法。
 * 一、核心步骤：
 * 	1. hash = hash(Object key)；
 * 	2. (容量 - 1) & hash，得到桶的位置（这里的位运算效果等价于“hash % 容量”，等做位运算效率更高）；
 * 	3. 桶里没有元素，则直接放进去即可；
 * 		如果桶里已经有元素了，则挨个key.equals那些已经存在于桶中的元素：
 * 			（1）如果有相同的，则keyey覆盖oldKey；
 * 			（2）如果没有相同的，则直接将keyey放到该桶链表的最后一位。
 * 
 * @author May
 * 2019年7月31日
 */
public class A03Method_Put {
	/**
	public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

	final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
		Node<K, V>[] tab;
		Node<K, V> p;
		int n, i;
		if ((tab = table) == null || (n = tab.length) == 0) //如果容量还未初始化过，或容量的大小为零：则先进行容量的初始化/赋值。
			n = (tab = resize()).length; 
		if ((p = tab[i = (n - 1) & hash]) == null) //如果通过key的哈希值计算所得桶的位置上没有节点：直接将元素放到该位置上。
			tab[i] = newNode(hash, key, value, null); //直接放在散列表上的节点，并没有特意标识其为头节点，其实它就是"链表/红黑树.index(0)"
		else { //容量初始化过了，或容量有大小了，或通过key的哈希值计算所得桶的位置上已经有节点了：【下面的代码是探究“链表转红黑树”的重点】
			Node<K, V> e;
			K k;
			//该桶的位置上的节点p的哈希值和key的哈希值相同 && (p.key和key相同)：
			if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
				e = p;
			else if (p instanceof TreeNode) //如果p节点是红黑树的实例：
				e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value); //直接做插入，用key-value覆盖老节点p上的内容
			else { //如果p节点的哈希值和key的哈希值不相同，且p.key和key也不相同，且p节点也不是红黑树的实例：
				for (int binCount = 0;; ++binCount) {
					if ((e = p.next) == null) { //沿着p节点，找到该桶上的最后一个节点：
						p.next = newNode(hash, key, value, null); //直接生成新节点，链在最后一个节点的后面；
						
						 //“binCount >= 7”：p从链表.index(0)开始，当binCount == 7时，p.index == 7,newNode.index == 8； 
                        //也就是说，当链表已经有8个节点了，此时再新链上第9个节点，在成功添加了这个新节点之后，立马做链表转红黑树。
						if (binCount >= TREEIFY_THRESHOLD - 1) 
							treeifyBin(tab, hash); //链表转红黑树
						break;
					}
					//链表上的某个节点的哈希值和key的哈希值相同 && (p.key和key相同)：三个条件都满足，表示当前链表包含要插入的键值对，终止遍历
					if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) 
						break;
					p = e;
				}
			}
			// 判断要插入的键值对是否存在 HashMap 中
			if (e != null) { // existing mapping for key
				V oldValue = e.value;
				// onlyIfAbsent 表示是否仅在 oldValue 为 null 的情况下更新键值对的值
				if (!onlyIfAbsent || oldValue == null)
					e.value = value;
				afterNodeAccess(e);
				return oldValue;
			}
		}
		++modCount;
		// 键值对数量超过阈值时，则进行扩容《==========
		if (++size > threshold)
			resize();
		afterNodeInsertion(evict);
		return null;
	}
	*/
}
