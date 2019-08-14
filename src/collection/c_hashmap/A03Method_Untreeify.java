package collection.c_hashmap;

/**
 * 红黑树转回链表的具体时机：
 * 一、真正做红黑树转链表的方法：HashMap$TreeNode.final Node<K,V> untreeify(HashMap<K,V> map)
 * 二、红黑树转回链表的具体时机：
 * 	1.扩容时：
 * 		resize()方法的源码解析见：collection.c_hashmap.A04Method_Resize，其中涉及到红黑树转链表的代码在第96、97行。
 * 		resize(){
 * 			……
 * 			else if (e instanceof TreeNode)//e节点后面是红黑树：先将红黑树拆成2个子链表，再将子链表的头节点放到newCap中《========== ②桶上是红黑树
 * 				((TreeNode<K,V>)e).split(this, newTab, j, oldCap);{《==========具体的拆分方法
 * 					……
 * 					if (lc <= UNTREEIFY_THRESHOLD)//如果拆分所得的子链表长度 <= 6:
 * 						tab[index] = loHead.untreeify(map);《============将TreeNode转为Node
 * 					……
 * 				}
 * 			……
 * 		}
 * 	2.删除元素时：
 * 		remove()方法的源码解析见：collection.c_hashmap.A05Method_Remove
 * 		2.1 方法的调用关系：
 * 			public V remove(Object key) {
        		Node<K,V> e;
        		return (e = removeNode(hash(key), key, null, false, true)) == null ? null : e.value;
    		}
 * 			removeNode(int hash, Object key, Object value, boolean matchValue, boolean movable) {
 * 				……
 * 				removeTreeNode(HashMap<K,V> map, Node<K,V>[] tab, boolean movable){
 * 					……
                	tab[index] = first.untreeify(map);  // too small
 * 				}
 * 			}
 * 		2.2 removeTreeNode()方法：
 * 			① 该方法的注释中有提到：如果当前树的节点太少，则将该红黑树转换回普通链表。
 * 			② 代码中也有涉及到调用红黑树转为链表：tab[index] = first.untreeify(map);  // too small
 * 三、博客：https://segmentfault.com/a/1190000012926722#articleHeader6“3.4插入：红黑树拆分”
 * 
 * @author May
 * 2019年8月5日
 */
public class A03Method_Untreeify {
	/*
	final void split(HashMap<K,V> map, Node<K,V>[] tab, int index, int bit) {
        TreeNode<K,V> b = this;
        // Relink into lo and hi lists, preserving order
        TreeNode<K,V> loHead = null, loTail = null;
        TreeNode<K,V> hiHead = null, hiTail = null;
        int lc = 0, hc = 0;
        
		//红黑树节点仍然保留了 next 引用，故仍可以按链表方式遍历红黑树，且遍历出来的元素顺序也保持链表时的元素顺序。
		//下面的循环是对红黑树节点进行分组:
        for (TreeNode<K,V> e = b, next; e != null; e = next) {
            next = (TreeNode<K,V>)e.next;
            e.next = null;
            //通过“hash & oldCap"得到一个值（该值要么是0，要么是正整数，我们姑且称呼该值为“定位值”吧）
            if ((e.hash & bit) == 0) {//“定位值等于0”的节点组成子链表loHead：
                if ((e.prev = loTail) == null)
                    loHead = e;
                else
                    loTail.next = e;
                loTail = e;
                ++lc;
            }
            else {//“定位值不等于0”的节点组成子链表hiHead：
                if ((e.prev = hiTail) == null)
                    hiHead = e;
                else
                    hiTail.next = e;
                hiTail = e;
                ++hc;
            }
        }
		// 如果 loHead 链表不为空：
		// 映射到newCap中的位置：和红黑树在oldCap上的位置相同，如本来该红黑树在oldCap中的index[5],那在newCap中也是index[5]
        if (loHead != null) {
            if (lc <= UNTREEIFY_THRESHOLD) //如果loHead的长度小于等于6，则将TreeNode转为Node后，再将链表映射到newCap中：
                tab[index] = loHead.untreeify(map);《================================== 真正将红黑树转回链表了：untreeify(map)
            else {//如果 loHead的长度 > 6，则不用将TreeNode转为Node：
                tab[index] = loHead;
                if (hiHead != null) // 红黑树成功分成两条链表了，此时将loHead链表重新树化。
                    loHead.treeify(tab);
                    
                // if (hiHead == null)：表示所有节点都是“定位值 == 0”的，也就是只有loHead一条链表，
                // 这种情况下，扩容后，所有节点直接映射到newCap中，树结构不变，无需先转成链表后再重新树化。
            }
        }
        // 如果liHead不为空：
        // 映射到newCap的index[oldCap + 定位值]；其他的和上面的类似。
        if (hiHead != null) {
            if (hc <= UNTREEIFY_THRESHOLD)
                tab[index + bit] = hiHead.untreeify(map);
            else {
                tab[index + bit] = hiHead;
                if (loHead != null)
                    hiHead.treeify(tab);
            }
        }
    }
    */
}
