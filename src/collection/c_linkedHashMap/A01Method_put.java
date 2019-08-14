package collection.c_linkedHashMap;

import java.util.LinkedHashMap;

/**
 * LinkedHashMap中并没有重写HashMap.put(key, value)方法，那LinkedHashMap是如何维护“head_tail双向链表”的呢？
 * 
 * 答：
 * 1.LinkedHashMap重写了newNode()方法，
 * 	在put进元素时调用的是父类HashMap的put方法，但Put方法中创建Node时，用的是LinkedHashMap子类自己的newNode()方法。
 * 	在重写的newNode()方法中，有对head_tail的新增。详情如下：
 * ①在HashMap的putVal(……)方法中，有调用newNode()方法来创建节点：
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);//直接创建节点，并把节点放到桶上《===============关键：LinkedHashMap重写了newNode(……)方法
        else {
            Node<K,V> e; K k;
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)//问题：树节点怎么办？《===============
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {//桶上跟的是链表：
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);《==========关键
                        ……
                    }
                    ……
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);//《==========回调方法
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);//<==========回调方法
        return null;
    }
 * 
 * ②LinkedHashMap重写了newNode(……)方法：
 *  Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
        LinkedHashMap.Entry<K,V> p = new LinkedHashMap.Entry<K,V>(hash, key, value, e);
        linkNodeLast(p); //代码实现见③《==========
        return p;
    }
    
 * ③private void linkNodeLast(LinkedHashMap.Entry<K,V> p) {//head_tail双向链表的维护：将 Entry 接在双向链表的尾部，实现了双向链表的建立
        LinkedHashMap.Entry<K,V> last = tail;
        tail = p;
        if (last == null)
            head = p;
        else {
            p.before = last;
            last.after = p;
        }
	}
 * 
 * 2.在HashMap中，有after方法值得我们注意，这三个方法是用于维护head_tail链表的，会在增、删、改方法中调用：
 * 	// Callbacks to allow LinkedHashMap post-actions
    void afterNodeAccess(Node<K,V> p) { }
    void afterNodeInsertion(boolean evict) { }
    void afterNodeRemoval(Node<K,V> p) { }
 * 
 * 3.博客：https://segmentfault.com/a/1190000012964859#articleHeader4
 * 
 * @author May
 * 2019年8月13日
 */
public class A01Method_put {
	public static void main(String[] args) {
		LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put(1, "a");
	}
}
