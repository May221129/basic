package collection.c_hashmap;

/**
 * �����ת������ľ���ʱ����
 * һ�������������ת����ķ�����HashMap$TreeNode.final Node<K,V> untreeify(HashMap<K,V> map)
 * ���������ת������ľ���ʱ����
 * 	1.����ʱ��
 * 		resize()������Դ���������collection.c_hashmap.A04Method_Resize�������漰�������ת����Ĵ����ڵ�96��97�С�
 * 		resize(){
 * 			����
 * 			else if (e instanceof TreeNode)//e�ڵ�����Ǻ�������Ƚ���������2���������ٽ��������ͷ�ڵ�ŵ�newCap�С�========== ��Ͱ���Ǻ����
 * 				((TreeNode<K,V>)e).split(this, newTab, j, oldCap);{��==========����Ĳ�ַ���
 * 					����
 * 					if (lc <= UNTREEIFY_THRESHOLD)//���������õ��������� <= 6:
 * 						tab[index] = loHead.untreeify(map);��============��TreeNodeתΪNode
 * 					����
 * 				}
 * 			����
 * 		}
 * 	2.ɾ��Ԫ��ʱ��
 * 		remove()������Դ���������collection.c_hashmap.A05Method_Remove
 * 		2.1 �����ĵ��ù�ϵ��
 * 			public V remove(Object key) {
        		Node<K,V> e;
        		return (e = removeNode(hash(key), key, null, false, true)) == null ? null : e.value;
    		}
 * 			removeNode(int hash, Object key, Object value, boolean matchValue, boolean movable) {
 * 				����
 * 				removeTreeNode(HashMap<K,V> map, Node<K,V>[] tab, boolean movable){
 * 					����
                	tab[index] = first.untreeify(map);  // too small
 * 				}
 * 			}
 * 		2.2 removeTreeNode()������
 * 			�� �÷�����ע�������ᵽ�������ǰ���Ľڵ�̫�٣��򽫸ú����ת������ͨ����
 * 			�� ������Ҳ���漰�����ú����תΪ����tab[index] = first.untreeify(map);  // too small
 * �������ͣ�https://segmentfault.com/a/1190000012926722#articleHeader6��3.4���룺�������֡�
 * 
 * @author May
 * 2019��8��5��
 */
public class A03Method_Untreeify {
	/*
	final void split(HashMap<K,V> map, Node<K,V>[] tab, int index, int bit) {
        TreeNode<K,V> b = this;
        // Relink into lo and hi lists, preserving order
        TreeNode<K,V> loHead = null, loTail = null;
        TreeNode<K,V> hiHead = null, hiTail = null;
        int lc = 0, hc = 0;
        
		//������ڵ���Ȼ������ next ���ã����Կ��԰�����ʽ������������ұ���������Ԫ��˳��Ҳ��������ʱ��Ԫ��˳��
		//�����ѭ���ǶԺ�����ڵ���з���:
        for (TreeNode<K,V> e = b, next; e != null; e = next) {
            next = (TreeNode<K,V>)e.next;
            e.next = null;
            //ͨ����hash & oldCap"�õ�һ��ֵ����ֵҪô��0��Ҫô�������������ǹ��ҳƺ���ֵΪ����λֵ���ɣ�
            if ((e.hash & bit) == 0) {//����λֵ����0���Ľڵ����������loHead��
                if ((e.prev = loTail) == null)
                    loHead = e;
                else
                    loTail.next = e;
                loTail = e;
                ++lc;
            }
            else {//����λֵ������0���Ľڵ����������hiHead��
                if ((e.prev = hiTail) == null)
                    hiHead = e;
                else
                    hiTail.next = e;
                hiTail = e;
                ++hc;
            }
        }
		// ��� loHead ����Ϊ�գ�
		// ӳ�䵽newCap�е�λ�ã��ͺ������oldCap�ϵ�λ����ͬ���籾���ú������oldCap�е�index[5],����newCap��Ҳ��index[5]
        if (loHead != null) {
            if (lc <= UNTREEIFY_THRESHOLD) //���loHead�ĳ���С�ڵ���6����TreeNodeתΪNode���ٽ�����ӳ�䵽newCap�У�
                tab[index] = loHead.untreeify(map);��================================== �����������ת�������ˣ�untreeify(map)
            else {//��� loHead�ĳ��� > 6�����ý�TreeNodeתΪNode��
                tab[index] = loHead;
                if (hiHead != null) // ������ɹ��ֳ����������ˣ���ʱ��loHead��������������
                    loHead.treeify(tab);
                    
                // if (hiHead == null)����ʾ���нڵ㶼�ǡ���λֵ == 0���ģ�Ҳ����ֻ��loHeadһ������
                // ��������£����ݺ����нڵ�ֱ��ӳ�䵽newCap�У����ṹ���䣬������ת�������������������
            }
        }
        // ���liHead��Ϊ�գ�
        // ӳ�䵽newCap��index[oldCap + ��λֵ]�������ĺ���������ơ�
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
