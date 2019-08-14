package collection.c_hashmap;

/**
 * HashMap.remove(Object key): 
 * 	一、核心步骤： 
 * 		① 定位桶位置； 
 * 		② 遍历链表并找到键值相等的节点； 
 * 		③ 删除节点；
 * 	二、涉及的知识点：红黑树转回链表：collection.c_hashmap.A03Method_Untreeify
 * 
 * @author May 2019年8月7日
 */
public class A05Method_Remove {
	/**
	public V remove(Object key) {
		Node<K, V> e;
		return (e = removeNode(hash(key), key, null, false, true)) == null ? null : e.value;
	}

	final Node<K, V> removeNode(int hash, Object key, Object value, boolean matchValue, boolean movable) {
		Node<K, V>[] tab;
		Node<K, V> p;
		int n, index;
		if ((tab = table) != null && (n = tab.length) > 0 && (p = tab[index = (n - 1) & hash]) != null) {//定位到桶
			Node<K, V> node = null, e;
			K k;
			V v;
			//如果桶上放的节点和key相同：直接记录该节点，而不用再看该节点后面的节点了
			if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
				node = p;
			//如果桶上放的节点和key不相同，且该节点后面还有节点
			else if ((e = p.next) != null) {
				//如果跟的是红黑树：查找红黑树中与key相同的节点
				if (p instanceof TreeNode)
					node = ((TreeNode<K, V>) p).getTreeNode(hash, key);
				//如果跟的是链表：则遍历链表并找到与key相同的节点
				else {
					do {
						if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
							node = e;
							break;
						}
						p = e;
					} while ((e = e.next) != null);
				}
			}
			//删除节点node：
			if (node != null && (!matchValue || (v = node.value) == value || (value != null && value.equals(v)))) {
				if (node instanceof TreeNode) //如果node是红黑树的节点：直接在红黑树中删除
					((TreeNode<K, V>) node).removeTreeNode(this, tab, movable);//涉及到红黑树转回链表《==================
				else if (node == p) //如果node是桶上的节点：
					tab[index] = node.next;
				else //如果node是链表中的节点：
					p.next = node.next;
				++modCount;
				--size;
				afterNodeRemoval(node);
				return node;
			}
		}
		return null;
	}
	 */
}
