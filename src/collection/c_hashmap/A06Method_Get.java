package collection.c_hashmap;

/**
 * ̽����HashMap.get(Object key)
 * ���Ĳ��裺
 * 	�ټ���key�Ĺ�ϣֵ��hash(key)��
 * 	��ͨ��key�Ĺ�ϣֵ��λ��Ͱ��
 * 	��ͨ������Ͱ���������/��������ҵ�key��
 * 
 * @author May
 * 2019��7��26��
 */
public class A06Method_Get {
	/**
	public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }
	
	final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        //1.ͬʱ���������������������г�ʼ�������������Ĵ�С�����㣻��ͨ��key��hashֵ�ܶ�λ��Ͱ��
        if ((tab = table) != null && (n = tab.length) > 0 && (first = tab[(n - 1) & hash]) != null) {//����ΪʲôҪ�ȸ�ֵ���жϣ������൱�ڡ����ա�������LinkedList.getFirst()��
            if (first.hash == hash && //1.1 ���Ǽ���һ���ڵ�
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {//1.2 ��һ���ڵ�û���ҵ���
                if (first instanceof TreeNode)//1.2.1 �жϵ�ǰλ�õ�Ͱ��������Ƿ��Ǻ����
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);//����Ǻ����������ݺ�����ķ�ʽ������Ԫ�أ�
                do {//1.2.2 ������Ǻ�������Ǿ��������ˣ������������Ԫ�أ�
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        //2. �������������һ��ĳ�������㣬��ֱ�ӷ���null��
        return null;
    }
    */
}
