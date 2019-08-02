package collection.c_hashmap;

import java.util.HashMap;

/**
 * HashMap�ĵ�����HashIterator��Դ�������
 * 	���ͣ�https://segmentfault.com/a/1190000012926722#articleHeader5 �� ��3.3 ������
 * 
 * ���ʣ�
 * 	1.ͨ��Դ����Է��֣�����ʱֻ������Ͱ��Ͱ��������������Ͱ��������Ǹ�������أ�����û�����к�����ı�����~
 * 
 * @author May
 * 2019��7��30��
 */
public class A07HashIterator {
	/**
	 * HashIterator()������ �� nextNode()������ʵ��������һ���£����Ʊ���һ����ά���顣
	 * 	���ҵ���һ���з�Ԫ�ص�Ͱ��������Ԫ�أ�
	 * 	�������Ͱ������nextNode����һ��һ�����������أ�����������ֱ���������Ͱ�������������
	 * 	���ҵ��ڶ����з�Ԫ�ص�Ͱ��������Ԫ�أ��ظ��ڣ�
	 * 	���ϵ��ظ��٢ڢۣ�ֱ��������Ԫ�ض������ꡣ
	 */
	/**
    abstract class HashIterator {
        Node<K,V> next;        // ��һ�����صĽڵ�
        Node<K,V> current;     // ��ǰ�ڵ�
        int expectedModCount;  // ����ʧ��
        int index;             // ��ǰλ��
		
        HashIterator() {
            expectedModCount = modCount;
            Node<K,V>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { //������ʼ���ˣ���Ͱ����Ԫ��ʱ
            	//�������д���ȼ��ڣ�while(next = t[index]; index < t.length && t[index] == null; index++;){}
                //�������ͼ������Ͱ���ҵ���һ�����ݲ�Ϊnull��Ͱ������Ͱ��λ�ñ��Ϊindex��������Ͱλ���ϵĽڵ㸳ֵ��next��
                do {} while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Node<K,V> nextNode() {
            Node<K,V>[] t;
            Node<K,V> e = next; //next��ͨ������������˸�ֵ
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (e == null)
                throw new NoSuchElementException();
            //��ǰ�ڵ�.next�����null����table!=null,�����Ѱ�ұ���Ͱ���ҵ����ݲ�Ϊnull��Ͱ��
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
