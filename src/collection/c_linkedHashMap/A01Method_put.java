package collection.c_linkedHashMap;

import java.util.LinkedHashMap;

/**
 * LinkedHashMap�в�û����дHashMap.put(key, value)��������LinkedHashMap�����ά����head_tail˫���������أ�
 * 
 * ��
 * 1.LinkedHashMap��д��newNode()������
 * 	��put��Ԫ��ʱ���õ��Ǹ���HashMap��put��������Put�����д���Nodeʱ���õ���LinkedHashMap�����Լ���newNode()������
 * 	����д��newNode()�����У��ж�head_tail���������������£�
 * ����HashMap��putVal(����)�����У��е���newNode()�����������ڵ㣺
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);//ֱ�Ӵ����ڵ㣬���ѽڵ�ŵ�Ͱ�ϡ�===============�ؼ���LinkedHashMap��д��newNode(����)����
        else {
            Node<K,V> e; K k;
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)//���⣺���ڵ���ô�죿��===============
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {//Ͱ�ϸ���������
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);��==========�ؼ�
                        ����
                    }
                    ����
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);//��==========�ص�����
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);//<==========�ص�����
        return null;
    }
 * 
 * ��LinkedHashMap��д��newNode(����)������
 *  Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
        LinkedHashMap.Entry<K,V> p = new LinkedHashMap.Entry<K,V>(hash, key, value, e);
        linkNodeLast(p); //����ʵ�ּ��ۡ�==========
        return p;
    }
    
 * ��private void linkNodeLast(LinkedHashMap.Entry<K,V> p) {//head_tail˫�������ά������ Entry ����˫�������β����ʵ����˫������Ľ���
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
 * 2.��HashMap�У���after����ֵ������ע�⣬����������������ά��head_tail����ģ���������ɾ���ķ����е��ã�
 * 	// Callbacks to allow LinkedHashMap post-actions
    void afterNodeAccess(Node<K,V> p) { }
    void afterNodeInsertion(boolean evict) { }
    void afterNodeRemoval(Node<K,V> p) { }
 * 
 * 3.���ͣ�https://segmentfault.com/a/1190000012964859#articleHeader4
 * 
 * @author May
 * 2019��8��13��
 */
public class A01Method_put {
	public static void main(String[] args) {
		LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put(1, "a");
	}
}
