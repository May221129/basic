package collection.c_hashmap;

/**
 * jdk1.8 HashMapԴ����֮resize()������
 * 	resize()�����漰����ɢ�б�ĳ�ʼ����  �ڼ��HashMap�����Ķ�ֵ��һ����2���ݴη���
 * 	�����н�����Դ����ֱ�Ӵ�HashMap�и��ƶ�����
 * 
 * @author May
 * 2019��7��26��
 */
public class A03MethodResize {
	/**
	final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;//�������Ƿ��ѳ�ʼ��
        int oldThr = threshold;//�´�����Ҫ�ﵽ����ֵ��threshold����ֵ��threshold = capacity * loadFactor��
        int newCap, newThr = 0;
        if (oldCap > 0) {//�Ѿ���Ͱ����
            if (oldCap >= MAXIMUM_CAPACITY) {//�Ѿ��ﵽ������capacity���ֵ
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY)//����һ���ݺ�������û�ﵽ������capacity���ֵ
                newThr = oldThr << 1; // double threshold
        }
        else if (oldThr > 0) //��ʼ��������Ϊ��ֵ��ʲôʱ��������㣺ʹ��HashMap(int initialCapacity, float loadFactor)���캯��ʱ��threshold�Ż���ֵ��
            newCap = oldThr;
        else {               //��ʼ��ֵΪ�㣬��ʾʹ��Ĭ��ֵ��������ֵû�г�ʼ������Ҳûʹ���вι��캯����ָ�������ͼ������Ӵ�Сʱ��
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {//ʲô����²Ż��������жϿ�:ǰ��ִ����else if (oldThr > 0)����û��ΪnewThr��ֵ���ͻ��������жϿ�
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
