package collection.d_heap;

import java.util.Iterator;
import java.util.PriorityQueue;

import org.junit.Test;

/**
 * �����㷨���⡪��Top K����һ����������飬����ΪN�� ���������top 5��
 *
 * ����������ö�����������󶥶�/С���ѡ�
 * 
 * һ���������� �ٴ���һ��������Ϊk��С���ѣ�
 *  �ڵ������� < k ʱ��������ֱ�ӷŵ����С�����У���ʱ�ѵĶ��������Сֵ�� 
 *  �۵������� >= kʱ��ÿ����һ�������ݶ���ѵĶ������бȽ�: 
 *  	��������� > ��������ݣ��򽫶����ɾ�����������ݷŵ����У���ʱ�ѻ����������ά���˶ѵ��ܽ����Ϊk;
 * 
 * ��������˼�룺ʹ�ѵ��ܽ����ά����k���� 
 * 
 * �������ʣ���������е�Ԫ�ض��ŵ����Ȼ����ȡ��top5��Ч���Ƿ���ߣ����Լ�A03TopKByHeap2��
 * 	���Խ����Ч�ʱ���ˡ�
 * 
 * �ġ����֪ʶ�㣺G:\mystudy\studynotes\���ݽṹ_��.xmind
 * 
 * @author May 2019��10��18��
 */
public class A03TopKByHeapInsertTopKElement {
	/**
	 * ͨ����С���ѡ��ķ�ʽ������ȡtopK��Ч�ʲ��ԣ�
	 * 	������Ϊ100��+10ʱ��11���룻
	 * 	������Ϊ1000��+10ʱ��28���룻
	 */
	@Test
	public void getTopKByHeapInsertTopKElement() {
		int arrayLength = 10000000 + 10;
		int topK = 5;

		// ׼��һ������ΪarrayLength���������飺
		int[] array = A03TopKByQuickSortAndNewArray.getDisorderlyArray(arrayLength);

		// ׼��һ���ܽ����ΪtopK��С���ѣ�
		PriorityQueue<Integer> heap = new PriorityQueue<>(topK);

		long start = System.currentTimeMillis();
		
		// ʼ��ά��һ���ܽ�����Ϊk�Ķѣ�
		insertButmaintainTheHeapAtTopK(heap, array, topK);
		
		//������topK��
		printHeap(heap);
		
		long end = System.currentTimeMillis();
		System.out.println("������top5�ܺ�ʱ�� " + (end - start));
	}
	
	/**
	 * ��С��������ȡtopK��������������topK���²���������ֱ�Ӻ�heap�Ķ������бȽϡ�
	 */
	private static void insertButmaintainTheHeapAtTopK(PriorityQueue<Integer> heap, int[] array, int topK) {
		for (int i = 0; i < array.length; i++) {
			if (i < topK) {
				heap.add(array[i]);
			} else {// ��ôά�ֶѵ��ܽ�����������Ĵ����ǹؼ���
				if (null != heap.peek() && array[i] > heap.peek()) {
					heap.poll();
					heap.add(array[i]);
				}
			}
		}
	}
	
	/**
	 * ��ȡ���TopK
	 * @param heap
	 */
	static void printHeap(PriorityQueue<Integer> heap) {
		Iterator<Integer> iterator = heap.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
