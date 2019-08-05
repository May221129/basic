package collection.c_hashmap;

/**
 * ̽����HashMap.put(K key, V value)������
 * һ�����Ĳ��裺
 * 	1. hash = hash(Object key)��
 * 	2. (���� - 1) & hash���õ�Ͱ��λ�ã������λ����Ч���ȼ��ڡ�hash % ������������λ����Ч�ʸ��ߣ���
 * 	3. Ͱ��û��Ԫ�أ���ֱ�ӷŽ�ȥ���ɣ�
 * 		���Ͱ���Ѿ���Ԫ���ˣ��򰤸�key.equals��Щ�Ѿ�������Ͱ�е�Ԫ�أ�
 * 			��1���������ͬ�ģ���keyey����oldKey��
 * 			��2�����û����ͬ�ģ���ֱ�ӽ�keyey�ŵ���Ͱ��������һλ��
 * 
 * @author May
 * 2019��7��31��
 */
public class A03Method_Put {
	/**
	public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

	final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
		Node<K, V>[] tab;
		Node<K, V> p;
		int n, i;
		if ((tab = table) == null || (n = tab.length) == 0) //���������δ��ʼ�������������Ĵ�СΪ�㣺���Ƚ��������ĳ�ʼ��/��ֵ��
			n = (tab = resize()).length; 
		if ((p = tab[i = (n - 1) & hash]) == null) //���ͨ��key�Ĺ�ϣֵ��������Ͱ��λ����û�нڵ㣺ֱ�ӽ�Ԫ�طŵ���λ���ϡ�
			tab[i] = newNode(hash, key, value, null); //ֱ�ӷ���ɢ�б��ϵĽڵ㣬��û�������ʶ��Ϊͷ�ڵ㣬��ʵ������"����/�����.index(0)"
		else { //������ʼ�����ˣ��������д�С�ˣ���ͨ��key�Ĺ�ϣֵ��������Ͱ��λ�����Ѿ��нڵ��ˣ�������Ĵ�����̽��������ת����������ص㡿
			Node<K, V> e;
			K k;
			//��Ͱ��λ���ϵĽڵ�p�Ĺ�ϣֵ��key�Ĺ�ϣֵ��ͬ && (p.key��key��ͬ)��
			if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
				e = p;
			else if (p instanceof TreeNode) //���p�ڵ��Ǻ������ʵ����
				e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value); //ֱ�������룬��key-value�����Ͻڵ�p�ϵ�����
			else { //���p�ڵ�Ĺ�ϣֵ��key�Ĺ�ϣֵ����ͬ����p.key��keyҲ����ͬ����p�ڵ�Ҳ���Ǻ������ʵ����
				for (int binCount = 0;; ++binCount) {
					if ((e = p.next) == null) { //����p�ڵ㣬�ҵ���Ͱ�ϵ����һ���ڵ㣺
						p.next = newNode(hash, key, value, null); //ֱ�������½ڵ㣬�������һ���ڵ�ĺ��棻
						
						 //��binCount >= 7����p������.index(0)��ʼ����binCount == 7ʱ��p.index == 7,newNode.index == 8�� 
                        //Ҳ����˵���������Ѿ���8���ڵ��ˣ���ʱ�������ϵ�9���ڵ㣬�ڳɹ����������½ڵ�֮������������ת�������
						if (binCount >= TREEIFY_THRESHOLD - 1) 
							treeifyBin(tab, hash); //����ת�����
						break;
					}
					//�����ϵ�ĳ���ڵ�Ĺ�ϣֵ��key�Ĺ�ϣֵ��ͬ && (p.key��key��ͬ)���������������㣬��ʾ��ǰ�������Ҫ����ļ�ֵ�ԣ���ֹ����
					if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) 
						break;
					p = e;
				}
			}
			// �ж�Ҫ����ļ�ֵ���Ƿ���� HashMap ��
			if (e != null) { // existing mapping for key
				V oldValue = e.value;
				// onlyIfAbsent ��ʾ�Ƿ���� oldValue Ϊ null ������¸��¼�ֵ�Ե�ֵ
				if (!onlyIfAbsent || oldValue == null)
					e.value = value;
				afterNodeAccess(e);
				return oldValue;
			}
		}
		++modCount;
		// ��ֵ������������ֵʱ����������ݡ�==========
		if (++size > threshold)
			resize();
		afterNodeInsertion(evict);
		return null;
	}
	*/
}
