package collection.c_hashmap;

/**
 * jdk1.8 HashMap源码解读之resize()方法——扩容。
 *	 一、HashMap.resize()中的注释：
 * 		对容量进行初始化或2倍扩容。
 * 		如果为空，则按照字段阈值中包含的初始容量目标分配。
 * 		否则，因为我们使用的是2的幂展开，所以每个容量中的元素必须保持相同的索引，或者在新表中以2的幂偏移量移动。
 * 
 * 	二、该方法主要做了3件事：
 * 		1.计算新容量（新桶） newCap 和新阈值 newThr；
 * 		2.根据计算出的 newCap 创建新的桶数组table，并对table做初始化；
 * 		3.将键值对节点重新映射到新的桶数组里：
 * 			① 如果桶上只有一个节点（后面即没链表也没树）：直接做“哈希值 ^ (newCap - 1)”运算，定位到新容量中；
 * 			② 如果桶上是链表：将链表上的所有节点做“哈希值 ^ oldCap”运算（注意，这里没有-1），会得到两个值（等于0，或正整数），
 * 				根据这两个值将链表一分为二得到两个链表，这两个链表根据各自的值直接映射到newCap中；
 * 			③如果桶上是红黑树：重新映射红黑树的逻辑和重新映射链表的逻辑基本一致。
 * 				不同之处在于，重新映射后，会将红黑树拆分成两条由 TreeNode 组成的链表：
 * 				如果链表长度 <= UNTREEIFY_THRESHOLD，则将链表转换成普通链表；否则根据条件重新将 TreeNode链表树化。
 * 
 * 	四、扩容之后是否会进行散列表的重构：【会进行重构，但“元素的重新映射到桶”操作做了“将链表/红黑树一分为两个链表”的优化，而不是一个节点一个节点put进去。】
 * 		JDK 1.8 版本下 HashMap扩容效率要高于之前版本。
 * 		JDK 1.7 为了防止因 hash碰撞引发的拒绝服务攻击，在计算hash值过程中引入随机种子,以增强hash值的随机性，使得键值对均匀分布在桶数组中。
 * 		在扩容过程中，相关方法会根据容量判断是否需要生成新的随机种子，并重新计算所有节点的 hash。
 * 		而在 JDK 1.8 中，则通过引入红黑树替代了该种方式。从而避免了多次计算 hash 的操作，提高了扩容效率。
 * 
 * 	五、jdk1.8的HashMap比1.7的，扩容时效率高在哪儿？
 * 		1.7
 * 			①一个一个元素重新计算哈希值，
 * 			②再根据哈希值重新计算桶的位置，
 * 			③元素一旦发生哈希碰撞，就调用key.equals()方法去比较两个元素是否相同；
 * 			④哈希碰撞结果是两个元素不相同，则建立链表；
 * 		1.8
 * 			①省了重新计算元素的哈希值；【省】
 * 			②如果桶上是链表或红黑树，则将其一分为二：此时也是一个节点一个节点去计算【相当于②，不省】，且重新建立新的两个链表；【相当于④，不省】
 * 			③红黑树一分为二后：
 * 				如果链表长度小于 UNTREEIFY_THRESHOLD，则将链表转换成普通链表；【多出的】
 * 				否则直接将链表再树化【多出的】；
 * 	====》结论：
 * 			1. 从“重新计算所有元素的哈希值”这个角度出发，1.8不用重新计算元素的哈希值，1.7则需要重新计算所有元素的哈希值，所以1.8明显有优于1.7；
 * 			2. 从“将元素映射到newCap中”这个角度出发，两者各有优劣势，所以结果到底谁快并不知道。
 * 
 * 	三、跟着博客进行源码解读：https://segmentfault.com/a/1190000012926722#articleHeader6的“3.4插入”
 * 	
 * @author May
 * 2019年7月26日
 */
public class A04Method_Resize {
	/**
	final Node<K,V>[] resize() {
	
//---------------- -------------------------- 1.计算新容量（新桶） newCap 和新阈值 newThr。 ---------------------------------
 
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;//看容量是否已初始化
        int oldThr = threshold;//下次扩容要达到的阈值。threshold(阈值) = capacity * loadFactor。
        int newCap, newThr = 0;
        if (oldCap > 0) {//容量已初始化过了：检查容量和阈值是否达到上限《==========
            if (oldCap >= MAXIMUM_CAPACITY) {//oldCap >= 2^30，已达到扩容上限，停止扩容
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY)//newCap < 2^30 && oldCap > 16,还能再扩容
                newThr = oldThr << 1; // 扩容：阈值*2。newThr扩容后可能会溢出！！！（详细解答见博客。）
        }
        //容量未初始化 && 阈值 > 0。
        //【啥时会满足层判断：使用HashMap(int initialCapacity, float loadFactor)或 HashMap(int initialCapacity)构造函数实例化HashMap时，threshold才会有值。】
        else if (oldThr > 0) 
            newCap = oldThr;//初始容量设为阈值
        else { //容量未初始化 && 阈值 <= 0 ： 
        	//【啥时会满足这层判断：①使用无参构造函数实例化HashMap时；②在“if (oldCap > 0)”判断层newThr溢出了。】
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {//什么情况下才会进入这个判断框:前面执行了else if (oldThr > 0)，并没有为newThr赋值，就会进入这个判断框。
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        
//------------------------------------------------------2.扩容：------------------------------------------------------------------
        
        @SuppressWarnings({"rawtypes","unchecked"})
            Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];//扩容《==========
        table = newTab;
        
//--------------------------------------------- 3.将键值对节点重新映射到新的桶数组里。------------------------------------------------
        
        if (oldTab != null) {//容量已经初始化过了：
            for (int j = 0; j < oldCap; ++j) {//一个桶一个桶去遍历
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {//当前桶上有节点，就赋值给e节点
                    oldTab[j] = null;//把该节点置为null（现在这个桶上什么都没有了）
                    if (e.next == null)//e节点后没有节点了：在新容器上重新计算e节点的放置位置《========== ①桶上只有一个节点
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode)//e节点后面是红黑树：先将红黑树拆成2个链表，再重新映射到新容器中《========== ②桶上是红黑树
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {  //遍历链表，并将链表节点按原顺序进行分组《========== ③桶上是链表
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
