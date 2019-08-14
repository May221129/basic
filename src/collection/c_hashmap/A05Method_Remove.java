package collection.c_hashmap;

/**
 * HashMap.remove(Object key): 
 * 	һ�����Ĳ��裺 
 * 		�� ��λͰλ�ã� 
 * 		�� ���������ҵ���ֵ��ȵĽڵ㣻 
 * 		�� ɾ���ڵ㣻
 * 	�����漰��֪ʶ�㣺�����ת������collection.c_hashmap.A03Method_Untreeify
 * 
 * @author May 2019��8��7��
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
		if ((tab = table) != null && (n = tab.length) > 0 && (p = tab[index = (n - 1) & hash]) != null) {//��λ��Ͱ
			Node<K, V> node = null, e;
			K k;
			V v;
			//���Ͱ�ϷŵĽڵ��key��ͬ��ֱ�Ӽ�¼�ýڵ㣬�������ٿ��ýڵ����Ľڵ���
			if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
				node = p;
			//���Ͱ�ϷŵĽڵ��key����ͬ���Ҹýڵ���滹�нڵ�
			else if ((e = p.next) != null) {
				//��������Ǻ���������Һ��������key��ͬ�Ľڵ�
				if (p instanceof TreeNode)
					node = ((TreeNode<K, V>) p).getTreeNode(hash, key);
				//�����������������������ҵ���key��ͬ�Ľڵ�
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
			//ɾ���ڵ�node��
			if (node != null && (!matchValue || (v = node.value) == value || (value != null && value.equals(v)))) {
				if (node instanceof TreeNode) //���node�Ǻ�����Ľڵ㣺ֱ���ں������ɾ��
					((TreeNode<K, V>) node).removeTreeNode(this, tab, movable);//�漰�������ת������==================
				else if (node == p) //���node��Ͱ�ϵĽڵ㣺
					tab[index] = node.next;
				else //���node�������еĽڵ㣺
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
