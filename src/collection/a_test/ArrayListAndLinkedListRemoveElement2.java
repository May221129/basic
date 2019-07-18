package collection.a_test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * ���� ArrayList��LinkedList��remove(int index)����remove(Object target)������ɾ���ٶȡ�
 * 	1. ������
 * 		��Ҫ��ɾ���ļ��ϵ�Ԫ�ظ�������������һ���ģ�
 * 		�����ɾ����ɾ����Ԫ�ظ�����ͬ������ɾ��"size() >> 1"��Ԫ�أ�
 * 	2.���Խ��������λ�����룩
 * 		ArrayList.remove(int index)���ΪA1��  ArrayList.remove(Object target)���ΪA2��
 * 		LinkedList.remove(int index)���ΪL1��LinkedList.remove(Objcet target)���ΪL2��
 * 				A1				A2				L1				L2
 * 		10��		669				11,142			14,351			22,552
 * 		100��	71,379			953,500			1,183,541		1,875,837
 * 
 * 	3.�ܽ᣺��ɾ��һ��Ԫ�ص��ٶ� = �ҵ����Ԫ�غ�ʱ + ɾ��������ʱ��
 * 		��1��A1 VS A2 : 
 * 			size=10��A1��A2�����16����  size=100��A1��A2�����13����
 * 		��2��L1 VS L2:
 * 			size=10��L1��L2����1.6����  size=100��L1��L2 ����1.6����
 * 		��3��A1 VS L1:
 * 			size=10��A1��L1����21����  size=100��A1��L1����17����
 * 		��4��A2 VS L2:
 * 			size=10��A2��L2����2����  size=100��A2��L2����2����
 * 
 * =======�����ݣ�1����2���ɵã�������ArrayList����LinkedList�������±���ɾ��������ȡ���Ԫ��������ɾ�����ٶȿ죻
 * 			���ݣ�3����4���ɵã������ǡ����±���ɾ�������ǡ���Ԫ��������ɾ������ArrayList��ɾ���ٶȶ����LinkedList�Ŀ졣
 * 
 * @author May
 * 2019��7��15��
 */
public class ArrayListAndLinkedListRemoveElement2 {
	public static void main(String[] args) {
		ArrayListAndLinkedListRemoveElement2 test = new ArrayListAndLinkedListRemoveElement2();
		
		//׼�����ϣ�
		int number = 10000 * 10;
		ArrayList<Integer> arrayListByIndex = new ArrayList<>(number);
		ArrayList<Integer> arrayListByElement = new ArrayList<>(number);
		LinkedList<Integer> linkedListByIndex = new LinkedList<>();
		LinkedList<Integer> linkedListByElement = new LinkedList<>();
		for(int i = 0; i < number; i++) {
			arrayListByIndex.add(i);
			arrayListByElement.add(i);
			linkedListByIndex.add(i);
			linkedListByElement.add(i);
		}
		
		//ִ��ɾ����
		test.arryaListRemoveElementByIndex(arrayListByIndex);
		System.out.println("-----------------------------------------------");
		test.arryaListRemoveElementByElement(arrayListByElement);
		System.out.println("-----------------------------------------------");
		test.linkedListRemoveByIndex(linkedListByIndex);
		System.out.println("-----------------------------------------------");
		test.linkedListRemoveByElement(linkedListByElement);
	}
	
	/**
	 * LinkedList.remove(Object target)ɾ��Ԫ�ص��ٶȡ�
	 * @param number
	 */
	private void linkedListRemoveByIndex(LinkedList<Integer> linkedList) {
		System.out.println("LinkedList.size() : " + linkedList.size());
		Random random = new Random();
		int range = linkedList.size() >> 1;
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < range; i++) {
//			linkedList.remove(random.nextInt(range));
			linkedList.remove(random.nextInt(linkedList.size()));
		}
		long end = System.currentTimeMillis();
		System.out.println("linkedList.remove(index)��ʱ��" + (end - start));
		
		System.out.println("LinkedList.size() : " + linkedList.size());
	}
	
	private void linkedListRemoveByElement(LinkedList<Integer> linkedList) {
		System.out.println("LinkedList.size() : " + linkedList.size());
		Random random = new Random();
		int range = linkedList.size() >> 1;
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < range; i++) {
//			linkedList.remove(new Integer(random.nextInt(range)));
			linkedList.remove(new Integer(random.nextInt(linkedList.size())));
		}
		long end = System.currentTimeMillis();
		System.out.println("linkedList.remove(Object)��ʱ��" + (end - start));
		
		System.out.println("LinkedList.size() : " + linkedList.size());
	}
	
	/**
	 * ArrayList.remove(Object target)ɾ��Ԫ�ص��ٶ�
	 * @param number
	 */
	private void arryaListRemoveElementByIndex(ArrayList<Integer> arrayList) {
		System.out.println("arrayList.size : " + arrayList.size());
		Random random = new Random();
		int range = arrayList.size() >> 1;
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < range; i++) {
//			arrayList.remove(random.nextInt(range));
			arrayList.remove(random.nextInt(arrayList.size()));
		}
		long end = System.currentTimeMillis();
		System.out.println("arrayList.remove(index)��ʱ�� " + (end-start));
		
		System.out.println("arrayList.size : " + arrayList.size());
	}
	
	/**
	 * ArrayList.remove(Object target)ɾ��Ԫ�ص��ٶ�
	 * @param number
	 */
	private void arryaListRemoveElementByElement(ArrayList<Integer> arrayList) {
		System.out.println("arrayList.size : " + arrayList.size());
		Random random = new Random();
		int range = arrayList.size() >> 1;
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < range; i++) {
//			arrayList.remove(new Integer(random.nextInt(range)));
			arrayList.remove(new Integer(random.nextInt(arrayList.size())));
		}
		long end = System.currentTimeMillis();
		System.out.println("arrayList.remove(Objcet)��ʱ�� " + (end-start));
		
		System.out.println("arrayList.size : " + arrayList.size());
	}
}
