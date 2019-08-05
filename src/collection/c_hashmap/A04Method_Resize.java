package collection.c_hashmap;

/**
 * jdk1.8 HashMapԴ����֮resize()�����������ݡ�
 *	 һ��HashMap.resize()�е�ע�ͣ�
 * 		���������г�ʼ����2�����ݡ�
 * 		���Ϊ�գ������ֶ���ֵ�а����ĳ�ʼ����Ŀ����䡣
 * 		������Ϊ����ʹ�õ���2����չ��������ÿ�������е�Ԫ�ر��뱣����ͬ���������������±�����2����ƫ�����ƶ���
 * 
 * 	�����÷�����Ҫ����3���£�
 * 		1.��������������Ͱ�� newCap ������ֵ newThr��
 * 		2.���ݼ������ newCap �����µ�Ͱ����table������table����ʼ����
 * 		3.����ֵ�Խڵ�����ӳ�䵽�µ�Ͱ�����
 * 			�� ���Ͱ��ֻ��һ���ڵ㣨���漴û����Ҳû������ֱ��������ϣֵ ^ (newCap - 1)�����㣬��λ���������У�
 * 			�� ���Ͱ���������������ϵ����нڵ�������ϣֵ ^ oldCap�����㣨ע�⣬����û��-1������õ�����ֵ������0��������������
 * 				����������ֵ������һ��Ϊ���õ���������������������ݸ��Ե�ֱֵ��ӳ�䵽newCap�У�
 * 			�����Ͱ���Ǻ����������ӳ���������߼�������ӳ��������߼�����һ�¡�
 * 				��֮ͬ�����ڣ�����ӳ��󣬻Ὣ�������ֳ������� TreeNode ��ɵ�����
 * 				��������� <= UNTREEIFY_THRESHOLD��������ת������ͨ������������������½� TreeNode����������
 * 
 * 	�ġ�����֮���Ƿ�����ɢ�б���ع�����������ع�������Ԫ�ص�����ӳ�䵽Ͱ���������ˡ�������/�����һ��Ϊ�����������Ż���������һ���ڵ�һ���ڵ�put��ȥ����
 * 		JDK 1.8 �汾�� HashMap����Ч��Ҫ����֮ǰ�汾��
 * 		JDK 1.7 Ϊ�˷�ֹ�� hash��ײ�����ľܾ����񹥻����ڼ���hashֵ�����������������,����ǿhashֵ������ԣ�ʹ�ü�ֵ�Ծ��ȷֲ���Ͱ�����С�
 * 		�����ݹ����У���ط�������������ж��Ƿ���Ҫ�����µ�������ӣ������¼������нڵ�� hash��
 * 		���� JDK 1.8 �У���ͨ��������������˸��ַ�ʽ���Ӷ������˶�μ��� hash �Ĳ��������������Ч�ʡ�
 * 
 * 	�塢jdk1.8��HashMap��1.7�ģ�����ʱЧ�ʸ����Ķ���
 * 		1.7
 * 			��һ��һ��Ԫ�����¼����ϣֵ��
 * 			���ٸ��ݹ�ϣֵ���¼���Ͱ��λ�ã�
 * 			��Ԫ��һ��������ϣ��ײ���͵���key.equals()����ȥ�Ƚ�����Ԫ���Ƿ���ͬ��
 * 			�ܹ�ϣ��ײ���������Ԫ�ز���ͬ����������
 * 		1.8
 * 			��ʡ�����¼���Ԫ�صĹ�ϣֵ����ʡ��
 * 			�����Ͱ�������������������һ��Ϊ������ʱҲ��һ���ڵ�һ���ڵ�ȥ���㡾�൱�ڢڣ���ʡ���������½����µ������������൱�ڢܣ���ʡ��
 * 			�ۺ����һ��Ϊ����
 * 				���������С�� UNTREEIFY_THRESHOLD��������ת������ͨ����������ġ�
 * 				����ֱ�ӽ�����������������ġ���
 * 	====�����ۣ�
 * 			1. �ӡ����¼�������Ԫ�صĹ�ϣֵ������Ƕȳ�����1.8�������¼���Ԫ�صĹ�ϣֵ��1.7����Ҫ���¼�������Ԫ�صĹ�ϣֵ������1.8����������1.7��
 * 			2. �ӡ���Ԫ��ӳ�䵽newCap�С�����Ƕȳ��������߸��������ƣ����Խ������˭�첢��֪����
 * 
 * 	�������Ų��ͽ���Դ������https://segmentfault.com/a/1190000012926722#articleHeader6�ġ�3.4���롱
 * 	
 * @author May
 * 2019��7��26��
 */
public class A04Method_Resize {
	/**
	final Node<K,V>[] resize() {
	
//---------------- -------------------------- 1.��������������Ͱ�� newCap ������ֵ newThr�� ---------------------------------
 
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;//�������Ƿ��ѳ�ʼ��
        int oldThr = threshold;//�´�����Ҫ�ﵽ����ֵ��threshold(��ֵ) = capacity * loadFactor��
        int newCap, newThr = 0;
        if (oldCap > 0) {//�����ѳ�ʼ�����ˣ������������ֵ�Ƿ�ﵽ���ޡ�==========
            if (oldCap >= MAXIMUM_CAPACITY) {//oldCap >= 2^30���Ѵﵽ�������ޣ�ֹͣ����
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY)//newCap < 2^30 && oldCap > 16,����������
                newThr = oldThr << 1; // ���ݣ���ֵ*2��newThr���ݺ���ܻ��������������ϸ�������͡���
        }
        //����δ��ʼ�� && ��ֵ > 0��
        //��ɶʱ��������жϣ�ʹ��HashMap(int initialCapacity, float loadFactor)�� HashMap(int initialCapacity)���캯��ʵ����HashMapʱ��threshold�Ż���ֵ����
        else if (oldThr > 0) 
            newCap = oldThr;//��ʼ������Ϊ��ֵ
        else { //����δ��ʼ�� && ��ֵ <= 0 �� 
        	//��ɶʱ����������жϣ���ʹ���޲ι��캯��ʵ����HashMapʱ�����ڡ�if (oldCap > 0)���жϲ�newThr����ˡ���
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {//ʲô����²Ż��������жϿ�:ǰ��ִ����else if (oldThr > 0)����û��ΪnewThr��ֵ���ͻ��������жϿ�
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        
//------------------------------------------------------2.���ݣ�------------------------------------------------------------------
        
        @SuppressWarnings({"rawtypes","unchecked"})
            Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];//���ݡ�==========
        table = newTab;
        
//--------------------------------------------- 3.����ֵ�Խڵ�����ӳ�䵽�µ�Ͱ�����------------------------------------------------
        
        if (oldTab != null) {//�����Ѿ���ʼ�����ˣ�
            for (int j = 0; j < oldCap; ++j) {//һ��Ͱһ��Ͱȥ����
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {//��ǰͰ���нڵ㣬�͸�ֵ��e�ڵ�
                    oldTab[j] = null;//�Ѹýڵ���Ϊnull���������Ͱ��ʲô��û���ˣ�
                    if (e.next == null)//e�ڵ��û�нڵ��ˣ��������������¼���e�ڵ�ķ���λ�á�========== ��Ͱ��ֻ��һ���ڵ�
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode)//e�ڵ�����Ǻ�������Ƚ���������2������������ӳ�䵽�������С�========== ��Ͱ���Ǻ����
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {  //����������������ڵ㰴ԭ˳����з��顶========== ��Ͱ��������
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
