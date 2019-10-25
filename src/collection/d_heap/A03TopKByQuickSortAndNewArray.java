package collection.d_heap;

import java.util.Random;

import org.junit.Test;

/**
 * ���ԣ�ͨ�������� + topK���顱�ķ�ʽ���������������ҳ���Сtop 5��
 * 
 * һ�����Ĳ��裺
 * 	1.ͨ�����ţ��Ƚ���������array��������
 * 	2.ȡ����СTop 5�����ŵ�topArray�У����ؼ���
 * 	3.����arrayLength�����ݺ��ֲ�����insertNumber�������ݣ�ֱ�Ӻ�topArray����Ƚϣ�Ҫ��Ҳ�Ƿŵ�topArray���ˣ����ؼ���
 * 
 * ����ʱ�临�Ӷȣ�
 * 	�������ʱ�临�Ӷȣ�O(N*logN)��
 * 	��ȡ��top k��ʱ�临�Ӷȣ�O(1)�����Ǳ������顣
 * 
 * �������֪ʶ�㣺G:\mystudy\studynotes\���ݽṹ_��.xmind
 * 
 * @author May
 * 2019��10��18��
 */
public class A03TopKByQuickSortAndNewArray {
	/**
	 * ���Է�����Ч�ʣ������Сtop 5��
	 * ���Խ����
	 * 	array.length == 100��124���롣
	 * 	array.length == 1000��1438���롣
	 */
	@Test
	public void testGetTopKByQuickSortToNewArray() {
		int topK = 5;
		int arrayLength = 10000000;
		
		//׼��һ����������
		int[] array = getDisorderlyArray(arrayLength);
		
		long start = System.currentTimeMillis();
		
		//1.ͨ�����ţ��Ƚ���������array��������
		quickSort(array, 0, array.length-1);
		
		//2.ȡ����СTop 5�����ŵ�topArray�У�
		int[] topKArray = insertToTopArrayFromDisorderlyArray(array, topK);
		
		//3.����arrayLength�����ݺ��ֲ�����insertNumber�������ݣ�ֱ�Ӻ�topArray[topKArray.length-1]�Ƚϣ�Ҫ��Ҳ�Ƿŵ�topArray����
		insertToTopKArray(topKArray, 10, 100, topKArray.length-1);//����10��100���ڵ��������Ϊ�����ݣ���topKArray[topKArray.length-1]
		
		long end = System.currentTimeMillis();
		System.out.println("������top5�ܺ�ʱ�� " + (end - start));
	}
	
	/**
	 * ���Է�������ȷ�ԡ�
	 */
	@Test
	public void testGetTopKByQuickSortToNewArrayIsTrue() {
		int topK = 5;
		int arrayLength = 10;
		
		//׼��һ����������
		int[] array = getDisorderlyArray(arrayLength);
		
		showArray(array);
		System.out.println("-----------");
		
		//1.ͨ�������Ƚ�array�е�����Ԫ�ؽ�������
		quickSort(array, 0, array.length-1);
		
		showArray(array);
		System.out.println("-----------");
		
		//2.ȡ����СTop 5�����ŵ�topArray�У�
		int[] topKArray = insertToTopArrayFromDisorderlyArray(array, topK);
		
		showArray(topKArray);
		System.out.println("-----------");
		
		//3.����arrayLength�����ݺ��ֲ�����insertNumber�������ݣ�ֱ�Ӻ�topArray[topKArray.length-1]�Ƚϣ�Ҫ��Ҳ�Ƿŵ�topArray����
		insertToTopKArrayByHavePrintRandom(topKArray, 10, 100, topKArray.length-1);//����10��100���ڵ��������Ϊ�����ݣ���topKArray[topKArray.length-1]
		System.out.println("-----------");
		
		//4. �鿴��Сtop5��topArray�е�����Ԫ��
		showArray(topKArray);
	}
	
	/**
	 * �����µ����ݺ��ٺ�topKArray������бȽϣ���������ʱ����Ҫ���뵽topKArray�У�����Ҫ���룬���topKArray�������¿��š�
	 * ���÷�����insertToTopKArrayByHavePrintRandom(����)������Ψһ����������ӡ���������������
	 * 
	 * @param topKArray topK����
	 * @param insertNumber �²��������ݵĸ���
	 * @param randomIntRange ��ʲô��Χ�ڲ��������ݣ�������10���ڵ��������
	 * @param topK ��topKArray�У�ȷ��Ҫ�滻��Ԫ�ص��±ꡣ�����СtopK����topK�Ǵ�С���������topKArray�����һ��Ԫ�ء�
	 */
	private static void insertToTopKArray(int[] topKArray, int insertNumber, int randomIntRange, int topK) {
		Random random = new Random();
		int randomInt;
		for(int i = 0; i < insertNumber; i++) {
			randomInt = random.nextInt(100);
			if(randomInt < topKArray[topK]) {//���������С��topArray[topK]����ֱ���ø���ȥ�滻topArray��Ȼ���ٽ�topArray������������
				topKArray[topK] = randomInt;
				quickSort(topKArray, 0, topKArray.length-1);
			}
		}
	}
	
	/**
	 * �����µ����ݺ��ٺ�topKArray������бȽϣ���������ʱ����Ҫ���뵽topKArray�У�����Ҫ���룬���topKArray�������¿��š�
	 * ���÷������ڲ��Է�������ȷ�ԣ����д�ӡ���������������
	 * 
	 * @param topKArray topK����
	 * @param insertNumber �²��������ݵĸ���
	 * @param randomIntRange ��ʲô��Χ�ڲ��������ݣ�������10���ڵ��������
	 * @param topK ��topKArray�У�ȷ��Ҫ�滻��Ԫ�ص��±ꡣ�����СtopK����topK�Ǵ�С���������topKArray�����һ��Ԫ�ء�
	 */
	private static void insertToTopKArrayByHavePrintRandom(int[] topKArray, int insertNumber, int randomIntRange, int topK) {
		Random random = new Random();
		int randomInt;
		for(int i = 0; i < insertNumber; i++) {
			randomInt = random.nextInt(100);
			System.out.println("randomInt == " + randomInt);
			if(randomInt < topKArray[topK]) {//���������С��topArray[topK]����ֱ���ø���ȥ�滻topArray��Ȼ���ٽ�topArray������������
				topKArray[topK] = randomInt;
				quickSort(topKArray, 0, topKArray.length-1);
			}
		}
	}
	
	/**
	 * ������������ȡ����Ҫ��TopK���ŵ�TopK�����С�
	 * 
	 * @param sourceArray ��������
	 * @param topK ��Ҫ��ȡ��Top K
	 * @return TopK����
	 */
	private static int[] insertToTopArrayFromDisorderlyArray(int[] sourceArray, int topK) {
		int[] topArray = new int[topK];
		for(int i = 0; i < 5; i++) {
			topArray[i] = sourceArray[i];
		}
		return topArray;
	}
	
	/**
	 * ����
	 * @param target
	 * @param left
	 * @param right
	 */
	static void quickSort(int[] target, int left, int right) {
		if (left >= right) {
			return;
		}
		int pivot = target[left];// ��׼��
		int temp;
		int i = left;
		int j = right;
		while (i < j) {
			while (target[j] >= pivot && i < j) {
				j--;
			}
			while (target[i] <= pivot && i < j) {
				i++;
			}
			if (i < j) {
				temp = target[i];
				target[i] = target[j];
				target[j] = temp;
			}
		}
		// left��right�����ˣ�
		// �ٽ��������Ԫ�غ�pivot��������
		target[left] = target[j];
		target[j] = pivot;
		// �ڻ�׼�����ߵ�Ԫ�صķֱ���������
		quickSort(target, left, j - 1);
		quickSort(target, j + 1, right);
	}
	
	/**
	 * ׼��һ����������
	 * 
	 * @param arrayLength
	 * @return int[]
	 */
	static int[] getDisorderlyArray(int arrayLength) {
		int[] disorderlyArray = new int[arrayLength];
		Random random = new Random();
		for (int i = 0; i < arrayLength; i++) {
			disorderlyArray[i] = random.nextInt(arrayLength);
		}
		return disorderlyArray;
	}
	
	/**
	 * ��������
	 */
	static void showArray(int[] target) {
		for (Integer element : target) {
			System.out.println(element);
		}
	}
}
