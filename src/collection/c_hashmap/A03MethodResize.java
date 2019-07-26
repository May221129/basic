package collection.c_hashmap;

/**
 * jdk1.8 HashMap源码解读之resize()方法，
 * 	resize()方法涉及：①散列表的初始化；  ②检查HashMap容量的定值：一定是2的幂次方；
 * 	本类中解析的源码是直接从HashMap中复制而来。
 * 
 * @author May
 * 2019年7月26日
 */
public class A03MethodResize {
	/**
	final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;//看容量是否已初始化
        int oldThr = threshold;//下次扩容要达到的阈值。threshold：阈值，threshold = capacity * loadFactor。
        int newCap, newThr = 0;
        if (oldCap > 0) {//已经有桶数了
            if (oldCap >= MAXIMUM_CAPACITY) {//已经达到声明的capacity最大值
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY)//再扩一次容后容量还没达到申明的capacity最大值
                newThr = oldThr << 1; // double threshold
        }
        else if (oldThr > 0) //初始容量设置为阈值。什么时候会进入这层：使用HashMap(int initialCapacity, float loadFactor)构造函数时，threshold才会有值。
            newCap = oldThr;
        else {               //初始阈值为零，表示使用默认值。即：阈值没有初始化，且也没使用有参构造函数来指定容量和加载因子大小时。
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {//什么情况下才会进入这个判断框:前面执行了else if (oldThr > 0)，并没有为newThr赋值，就会进入这个判断框。
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
            Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
    */
}
