package collection.d_heap;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

/**
 * ���ԣ�ͨ�������� + ���ַ����ķ�ʽ���������������ҳ���Сtop 5��
 * 
 * һ�����Ĳ��裺 
 * 	1.ͨ�����ţ��Ƚ���������array���д�С�������� 
 * 	2.����������ǰK�����ݾ�����СtopK��
 * 	3.����listSize�����ݺ��ֲ�����insertNumber�������ݣ�ͨ�����ַ��������ݲ��뵽�Ѿ������array�У����ؼ���
 * 	4.���������ݺ������Ѿ�������ģ������ǰk�������Ѿ�����Сtopk;���ؼ���
 * 	��ע�⣺��Ϊ����һ�������䳤�ȾͲ����ٸı䣬����Ҫ�õ�ArrayList��Ϊ��������ײ�Ҳ�����飬ֻ����֧���Զ����ݡ���
 * 
 * ����ʱ�临�Ӷȣ�
 * 	�������ʱ�临�Ӷȣ�O(N*logN)��
 * 	��ȡ��top k��ʱ�临�Ӷȣ�O(1)�����Ǳ������顣
 * 
 * �������֪ʶ�㣺G:\mystudy\studynotes\���ݽṹ_��.xmind
 * 
 * @author May 2019��10��18��
 */
public class A03TopKByQuickSortAndBisectionMethod {
	/**
	 * ���ԣ�ͨ�������� + ���ַ�����ȡtopK��Ч�ʡ�
	 * ���Խ����
	 * 	listSize == 100�� �� 326����
	 *  listSize == 1000��9379����
	 */
	@Test
	public void testgetTopKByQuickSortAndBisectionMethod() {
		int listSize = 10000000;
		
		//׼��һ������list
		ArrayList<Integer> list = getDisorderlyList(listSize);

		long start = System.currentTimeMillis();

		// 1.�Ƚ�array�е�����Ԫ�ؽ��д�С��������������array[0]~array[4]��ǰ5����������Сtop5
		quickSort(list, 0, list.size() - 1);

		// 2. ����listSize�����ݺ��ֲ�����insertNumber�������ݣ�ͨ�����ַ��������ݲ��뵽�Ѿ������array��
		insertToOrderlyList(list, 10, 100);

		long end = System.currentTimeMillis();
		System.out.println("������top5�ܺ�ʱ�� " + (end - start));
	}
	
	/**
	 * ���ԣ�ͨ�������� + ���ַ�����ȡtopK����ȷ�ԡ�
	 */
	@Test
	public void testgetTopKByQuickSortAndBisectionMethodIsTrue() {
		int listSize = 10;
		int topK = 5;
		
		//׼��һ������list
		ArrayList<Integer> list = getDisorderlyList(listSize);
		
		System.out.println("�������飺");
		showList(list);
		

		// 1.�Ƚ�array�е�����Ԫ�ؽ��д�С��������������array[0]~array[4]��ǰ5����������Сtop5
		quickSort(list, 0, list.size() - 1);
		
		System.out.println("���������飺");
		showList(list);
		
		System.out.println("topK��");
		getTopKFromOrderlyList(list, topK);

		// 2. ����listSize�����ݺ��ֲ�����insertNumber�������ݣ�ͨ�����ַ��������ݲ��뵽�Ѿ������array��
		insertToOrderlyListHavePrintRandomInt(list, 10, 100);
		
		System.out.println("���������ݺ��ٴβ鿴top K��");
		getTopKFromOrderlyList(list, topK);
	}
	
	/**
	 * �鿴��Сtop K��
	 * 
	 * @param list
	 * @param topK
	 */
	private static void getTopKFromOrderlyList(ArrayList<Integer> list, int topK) {
		for(int i = 0; i < topK; i++) {
			System.out.println(list.get(i));
		}
	}

	/**
	 * ����listSize�����ݺ��ֲ�����insertNumber�������ݣ�ͨ�����ַ��������ݲ��뵽�Ѿ������array��
	 * ���÷�����insertToOrderlyList(����)������Ψһ����������ӡ���������������
	 * 
	 * @param insertNumber �²��������ݵĸ���
	 * @param randomIntRange ��ʲô��Χ�ڲ��������ݣ�������10���ڵ��������
	 */
	private static void insertToOrderlyList(ArrayList<Integer> list, int insertNumber, int randomIntRange) {
		Random random = new Random();
		for(int i = 0; i < insertNumber; i++) {
			insertByBisectionMethod(list, 0, list.size()-1, random.nextInt(randomIntRange));
		}
	}
	
	/**
	 * ����listSize�����ݺ��ֲ�����insertNumber�������ݣ�ͨ�����ַ��������ݲ��뵽�Ѿ������array��
	 * ���÷������ӡ���������������
	 * 
	 * @param insertNumber �²��������ݵĸ���
	 * @param randomIntRange ��ʲô��Χ�ڲ��������ݣ�������10���ڵ��������
	 */
	private static void insertToOrderlyListHavePrintRandomInt(ArrayList<Integer> list, int insertNumber, int randomIntRange) {
		Random random = new Random();
		int randomInt;
		for(int i = 0; i < insertNumber; i++) {
			randomInt = random.nextInt(randomIntRange);//����10����Χ��100�����������Ϊ������
			System.out.println("randomInt == " + randomInt);
			insertByBisectionMethod(list, 0, list.size()-1, randomInt);
		}
	}
	
	/**
	 * ���ö��ַ�����Ԫ�ز��뵽����������
	 */
	private static void insertByBisectionMethod(ArrayList<Integer> targetArray, int left, int right, Integer newNumber) {
		// ���ݴ���
		if ((right - left) < 0) {
			return;
		}
		// ֻ��һ����
		if ((right - left) == 0) {
			if (newNumber < targetArray.get(left)) {
				targetArray.add(left, newNumber);
				return;
			}
			targetArray.add(left + 1, newNumber);
			return;
		}
		
		// ֻ��������
		if ((right - left) == 1) {
			if (newNumber < targetArray.get(left)) {
				targetArray.add(left, newNumber);// ����left��ǰ��
				return;
			}
			if (newNumber > targetArray.get(right)) {
				targetArray.add(right + 1, newNumber);// ����right�ĺ���
				return;
			}
			targetArray.add(right, newNumber);// ����left��right���м�
			return;
		}
		
		// >= 3����ʱ�����ֲ���
		int middleIndex = (left + right) / 2;
		if (newNumber == targetArray.get(middleIndex)) {
			targetArray.add(middleIndex, newNumber);// ����middleIndexλ�ã�middleIndex������������Ԫ�ض�����һλ
			return;
		}
		if (newNumber < targetArray.get(middleIndex)) {// newNumber�����
			insertByBisectionMethod(targetArray, left, middleIndex - 1, newNumber);
			return;
		} else {// newNumber���ұ�
			insertByBisectionMethod(targetArray, middleIndex + 1, right, newNumber);
			return;
		}
	}

	/**
	 * ����
	 * 
	 * @param arrayList
	 * @param left
	 * @param right
	 */
	private static void quickSort(ArrayList<Integer> arrayList, int left, int right) {
		if (left >= right) {
			return;
		}
		int pivot = arrayList.get(left);// ��׼��
		int temp;
		int i = left;
		int j = right;
		while (i < j) {
			while (arrayList.get(j) >= pivot && i < j) {
				j--;
			}
			while (arrayList.get(i) <= pivot && i < j) {
				i++;
			}
			if (i < j) {
				temp = arrayList.get(i);
				arrayList.set(i, arrayList.get(j));
				arrayList.set(j, temp);
			}
		}
		// left��right�����ˣ�
		// �ٽ��������Ԫ�غ�pivot��������
		arrayList.set(left, arrayList.get(j));
		arrayList.set(j, pivot);
		// �ڻ�׼�����ߵ�Ԫ�صķֱ���������
		quickSort(arrayList, left, j - 1);
		quickSort(arrayList, j + 1, right);
	}

	/**
	 * ׼��һ�����򼯺�
	 * 
	 * @param arrayLength
	 * @return ArrayList<Integer>
	 */
	static ArrayList<Integer> getDisorderlyList(int arrayLength) {
		ArrayList<Integer> list = new ArrayList<>(arrayLength);
		Random random = new Random();
		for (int i = 0; i < arrayLength; i++) {
			list.add(random.nextInt(arrayLength));
		}
		return list;
	}
	
	/**
	 * ��������
	 */
	static void showList(ArrayList<Integer> target) {
		for (Integer element : target) {
			System.out.println(element);
		}
	}
}
