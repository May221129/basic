package collection.a_test;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * ���� ArrayList��LinkedList��remove(int index)����remove(Object target)������ɾ���ٶȡ�
 *  
 *  ǰ�ԣ�
 *  	List�ӿ��У�LinkedList��ArrayList���ʺ�Ƶ����Ԫ�ص�ɾ���Ͳ��룺
 * 			�����ڲ���Ҫɾ����Ԫ��ʱ���ٶ��ǲ��ģ�����next��nextȥ�����������ϣ�
 * 			���ǣ��ҵ�Ԫ�غ��ɾ��������LinkedList��ֱ���޸�nextNode�ĵ�ַ���ɣ���ArrayList��ָ��Ԫ��ɾ������Ҫ���������е�Ԫ�ض���ǰŲһλ��
 * 		����������������ȷ������ͨ��ArrayListAndLinkedListRemoveElement��ArrayListAndLinkedListRemoveElement2����������д�Ĳ��ԣ�
 * 			����֤������۵���ȷ���
 * 	1. ������
 * 		��Ҫ��ɾ���ļ�������ȫһ���ģ�
 * 		��ɾ����Ԫ��Ҳ��һ���ģ����ǴӼ��ϵ��м俪ʼ��ǰɾ��ɾ����size/2��Ԫ�أ�
 * 			for(int i = (list.size() >> 1); i > 0; i--) {
 * 				list.remove(Object i);
 * 	2.���Խ��������λ�����룩
* 		ArrayList.remove(int index)���ΪA1��  ArrayList.remove(Object target)���ΪA2��
 * 		LinkedList.remove(int index)���ΪL1��LinkedList.remove(Objcet target)���ΪL2��
 * 				A1				A2				L1				L2
 * 		10��		563				2,520			2,807			5,931
 * 		100��	62,006			378,604			336,792			536,878
 * 
 * 	3.�ܽ᣺��ɾ��һ��Ԫ�ص��ٶ� = �ҵ����Ԫ�غ�ʱ + ɾ��������ʱ��
 * 		��1��A1 VS A2 : 
 * 			size=10��A1��A2�����4.5����  size=100��A1��A2�����6.1����
 * 		��2��L1 VS L2:
 * 			size=10��L1��L2����2.1����  size=100��L1��L2 ����1.6����
 * 		��3��A1 VS L1:
 * 			size=10��A1��L1����5����  size=100��A1��L1����5.4����
 * 		��4��A2 VS L2:
 * 			size=10��A2��L2����2.3����  size=100��A2��L2����1.4����
 * 
 * =======�����ݣ�1����2���ɵã�������ArrayList����LinkedList�������±���ɾ��������ȡ���Ԫ��������ɾ�����ٶȿ죻
 * 			���ݣ�3����4���ɵã������ǡ����±���ɾ�������ǡ���Ԫ��������ɾ������ArrayList��ɾ���ٶȶ����LinkedList�Ŀ졣
 * 
 * @author May
 * 2019��7��15��
 */
public class ArrayListAndLinkedListRemoveElement {
	public static void main(String[] args) {
		ArrayListAndLinkedListRemoveElement test = new ArrayListAndLinkedListRemoveElement();
		
		//׼�����ϣ�
		int number = 10000 * 10;
		ArrayList<Integer> arrayListByIndex = new ArrayList<>(number);
		ArrayList<Integer> arrayListByElement = new ArrayList<>(number);
		LinkedList<Integer> linkedListByIndex = new LinkedList<>();
		LinkedList<Integer> linkedListByElement = new LinkedList<>();
		for(Integer i = 0; i < number; i++) {
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
		
		int removeElement = linkedList.size() >> 1;
		
		long start = System.currentTimeMillis();
		for(int i = removeElement; i > 0; i--) {
			linkedList.remove(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("linkedList.remove(index)��ʱ��" + (end - start));
		
		System.out.println("LinkedList.size() : " + linkedList.size());
	}
	
	private void linkedListRemoveByElement(LinkedList<Integer> linkedList) {
		System.out.println("LinkedList.size() : " + linkedList.size());
		
		int removeElement = linkedList.size() >> 1;
		
		long start = System.currentTimeMillis();
		for(Integer i = removeElement; i > 0; i--) {
			linkedList.remove(i);
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
		
		int removeElement = arrayList.size() >> 1;
		
		long start = System.currentTimeMillis();
		for(int i = removeElement; i > 0 ; i--) {
			arrayList.remove(i);
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
		
		int removeElement = arrayList.size() >> 1;
		
		long start = System.currentTimeMillis();
		for(Integer i = removeElement; i > 0 ; i--) {
			arrayList.remove(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("arrayList.remove(Objcet)��ʱ�� " + (end-start));
		
		System.out.println("arrayList.size : " + arrayList.size());
	}
}
