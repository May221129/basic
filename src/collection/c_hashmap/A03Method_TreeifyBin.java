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
 * 		��Դ�����/javase/src/collection/c_hashmap/A03Method_Put.java���ж�put()����Դ��Ľ�������
 * 	3.ͨ��debug�ϵ㣬�鿴��ͬһ��Ͱ�ϵ�����ĳ��ȴﵽ�೤ʱ����������ת��������Ĳ�����
 * 		�ϵ����HashMap.putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict)������
 * 		��treeifyBin(tab, hash);�����
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
