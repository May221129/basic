package collection.c_hashmap;

/**
 * 探究：HashMap.get(Object key)
 * 核心步骤：
 * 	①计算key的哈希值：hash(key)；
 * 	②通过key的哈希值定位到桶；
 * 	③通过遍历桶后面的链表/红黑树，找到key。
 * 
 * @author May
 * 2019年7月26日
 */
public class A06Method_Get {
	/**
	public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }
	
	final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        //1.同时满足三个条件：①容量有初始化过；②容量的大小大于零；③通过key的hash值能定位到桶；
        if ((tab = table) != null && (n = tab.length) > 0 && (first = tab[(n - 1) & hash]) != null) {//这里为什么要先赋值再判断？作用相当于“快照”，参照LinkedList.getFirst()。
            if (first.hash == hash && //1.1 总是检查第一个节点
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {//1.2 第一个节点没有找到：
                if (first instanceof TreeNode)//1.2.1 判断当前位置的桶后面跟的是否是红黑树
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);//如果是红黑树，则根据红黑树的方式来查找元素；
                do {//1.2.2 如果不是红黑树，那就是链表了，遍历链表查找元素：
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        //2. 上面的三个条件一旦某个不满足，就直接返回null：
        return null;
    }
    */
}
