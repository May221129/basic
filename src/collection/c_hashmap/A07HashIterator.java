package collection.c_hashmap;

import java.util.HashMap;

/**
 * HashMap的迭代器HashIterator的源码解析。
 * 	博客：https://segmentfault.com/a/1190000012926722#articleHeader5 的 “3.3 遍历”
 * 
 * 提问：
 * 	1.通过源码可以发现，遍历时只遍历了桶和桶后面的链表，那如果桶后面跟的是个红黑树呢？这里没发现有红黑树的遍历啊~
 * 
 * @author May
 * 2019年7月30日
 */
public class A07HashIterator {
	/**
	 * HashIterator()构造器 和 nextNode()方法其实就是在做一件事：类似遍历一个二维数组。
	 * 	①找到第一个有放元素的桶并返回其元素，
	 * 	②如果该桶后面有nextNode，则一个一个遍历并返回（遍历链表），直到遍历完该桶后面的整个链表；
	 * 	③找到第二个有放元素的桶并返回其元素，重复②；
	 * 	不断地重复①②③，直至把所有元素都遍历完。
	 */
	/**
    abstract class HashIterator {
        Node<K,V> next;        // 下一个返回的节点
        Node<K,V> current;     // 当前节点
        int expectedModCount;  // 快速失败
        int index;             // 当前位置
		
        HashIterator() {
            expectedModCount = modCount;
            Node<K,V>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { //容量初始化了，且桶中有元素时
            	//下面这行代码等价于：while(next = t[index]; index < t.length && t[index] == null; index++;){}
                //代码的意图：遍历桶，找到第一个内容不为null的桶，将该桶的位置标记为index，并将该桶位置上的节点赋值给next。
                do {} while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Node<K,V> nextNode() {
            Node<K,V>[] t;
            Node<K,V> e = next; //next：通过构造器完成了赋值
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (e == null)
                throw new NoSuchElementException();
            //当前节点.next如果是null，且table!=null,则继续寻找遍历桶，找到内容不为null的桶：
            if ((next = (current = e).next) == null && (t = table) != null) {
                do {} while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }

        public final void remove() {
            Node<K,V> p = current;
            if (p == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            current = null;
            K key = p.key;
            removeNode(hash(key), key, null, false, false);
            expectedModCount = modCount;
        }
    }
    */
	
	public static void main(String[] args) {
		HashMap<Integer, String> hashMap = new HashMap<>();
		hashMap.put(1, "a");
		hashMap.put(2, "b");
		hashMap.put(3, "c");
		for(Integer key : hashMap.keySet()) {
			System.out.println(key);
		}
	}
}
