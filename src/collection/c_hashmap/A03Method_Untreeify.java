package collection.c_hashmap;

/**
 * �����ת������ľ���ʱ����
 * 	1.����ʱ�漰�������ת������
 * 		resize()������Դ���������collection.c_hashmap.A04Method_Resize�������漰�������ת����Ĵ����ڵ�96��97�С�
 * 		resize(){
 * 			����
 * 			else if (e instanceof TreeNode)//e�ڵ�����Ǻ�������Ƚ���������2������������ӳ�䵽�������С�========== ��Ͱ���Ǻ����
 * 				((TreeNode<K,V>)e).split(this, newTab, j, oldCap);{��==========����Ĳ�ַ���
 * 					����
 * 					if (lc <= UNTREEIFY_THRESHOLD)
 * 						tab[index] = loHead.untreeify(map);��============��TreeNodeתΪNode
 * 					����
 * 				}
 * 			����
 * 		}
 * 	2.remove()�������漰�����ת������
 * 		
 * 	���ͣ�https://segmentfault.com/a/1190000012926722#articleHeader6��3.4���룺�������֡�
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
        
		//������ڵ���Ȼ������ next ���ã����Կ��԰�����ʽ�����������
		//�����ѭ���ǶԺ�����ڵ���з���:
        for (TreeNode<K,V> e = b, next; e != null; e = next) {
            next = (TreeNode<K,V>)e.next;
            e.next = null;
            //ͨ����hash & oldCap"�õ�һ��ֵ����ֵҪô��0��Ҫô�������������ǹ��ҳƺ���ֵΪ���������ɣ�
            if ((e.hash & bit) == 0) {//������ == 0���Ľڵ����loHead����
                if ((e.prev = loTail) == null)
                    loHead = e;
                else
                    loTail.next = e;
                loTail = e;
                ++lc;
            }
            else {//������ == ���������Ľڵ����hiHead����
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
                if (hiHead != null) // ������ɹ��ֳ����������ˣ���ʱ��loHead������newCap������������
                    loHead.treeify(tab);
                    
                // if (hiHead == null)����ʾ���нڵ㶼�ǡ����� == 0���ģ�Ҳ����ֻ��loHeadһ������
                // ��������£����ݺ����нڵ�ֱ��ӳ�䵽newCap�У����ṹ���䣬������ת�����������newCap������������
            }
        }
        // ���liHead��Ϊ�գ�
        // ӳ�䵽newCap��index[������ֵ]�������ĺ���������ơ�
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
