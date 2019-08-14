package collection.c_hashmap;

import java.util.HashMap;

/**
 * ̽����HashMap����ת������ľ���ʱ����
 * 	1.HashMap���йء�����ת���������ֵ��������
 * 		static final int TREEIFY_THRESHOLD = 8;
 * 		ע�ͣ�ʹ�ú����(����������)�����Ԫ�ؽڵ㡣�������پ�����ô��ڵ�����������Ԫ��ʱ������ת��Ϊ�������
 * 			��ֵ�������2������Ӧ������Ϊ8���Ա���ɾ�������ʱת������
 * 	2.����Դ�룺
 * 		putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict)�����У��漰������ת�������
 * 		putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
 * 				����
			else {
            	����
            	else {
                	for (int binCount = 0; ; ++binCount) {
                    	if ((e = p.next) == null) {
                        	p.next = newNode(hash, key, value, null);
                        	if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            	treeifyBin(tab, hash);//����ת�������Nodeת��TreeNode��================
                        	break;
                    	}
                  ����
 * 		}
 * 		��Դ�����/javase/src/collection/c_hashmap/A03Method_Put.java���ж�put()����Դ��Ľ�������
 * 	3.ͨ��debug�ϵ㣬�鿴��ͬһ��Ͱ�ϵ�����ĳ��ȴﵽ�೤ʱ����������ת��������Ĳ�����
 * 		�ϵ����HashMap.putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict)������
 * 		��treeifyBin(tab, hash);�����
 * 	4.����ת������ĺ��Ĳ��裺
 * 		��1��Ҫ��ת���������ϵ����нڵ�����ͨ�ڵ�תΪ���ڵ㣻
 * 		��2����������
 * 	5.ΪʲôNode����תΪTreeNode��
 * 		��Ϊ Node �� TreeNode ��үү��
 * 		�����ڵ�HashMap.TreeNode<K,V>�� extends ��˫����ڵ�LinkedHashMap.Entry<K,V>�� extends ��������ڵ�HashMap.Node<K,V>�� implements ��Map.Entry<K,V>��
 * 		NodeתΪTreeNode��Node�еġ�Node<K,V> next��������TreeNode�б���������������ĳ�Ա�����������ֱ�ӻ�ȡ��ʹ�ã�үү���е����࣬�����ٴ��е����ࣩ��
 * 		���Լ�ʹת���˺���������Ѿ�����ͨ����next��next��ȥ�����������ԭ����Ľڵ�˳������ͨ�� next ���ñ�����������
 * 	6.�ʣ�Ϊʲô˫����ڵ�LinkedHashMap.Entry��������Entry��������Node��
 * 		����ʷԭ��hashMap��jdk1.7�Ľڵ����Entry����1.8֮��������˺�������������нڵ�ĸ������ʱ�����Ѿ����ܸ��ˣ�Ҫ�����ݡ�
 * 	7.����ת�ɺ����֮����ֱ�ӽ��������ɾ�����𣿻��������ˣ�
 * 		����û��Դ�룬���Ҳ�һ����ɾ������Ϊ���������Ƕ���ģ�TreeNode�б�����ά���ڵ�˳���ָ�롣
 * 
 * @author May
 * 2019��7��31��
 */
public class A03Method_TreeifyBin {
	public static void main(String[] args) {
		HashMap<A03Bean, Integer> hashMap = new HashMap<>();
		hashMap.put(new A03Bean(4), 0);
		hashMap.put(new A03Bean(8), 1);
		hashMap.put(new A03Bean(12), 2);
		hashMap.put(new A03Bean(16), 3);
		hashMap.put(new A03Bean(20), 4);
		hashMap.put(new A03Bean(24), 5);
		hashMap.put(new A03Bean(28), 6);
		hashMap.put(new A03Bean(32), 7);
		hashMap.put(new A03Bean(36), 8);
		hashMap.put(new A03Bean(40), 9);
		hashMap.put(new A03Bean(44), 10);
		
		System.out.println("hashMap.size = " + hashMap.size());
		
		//�鿴�Ƿ����ж��󶼷ŵ�HashMap���ˣ�
		for(A03Bean key : hashMap.keySet()) {
			System.out.println(key.number);
		}
	}
}
