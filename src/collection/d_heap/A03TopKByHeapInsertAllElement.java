package collection.d_heap;

import java.util.Iterator;
import java.util.PriorityQueue;

import org.junit.Test;

/**
 * �����㷨���⡪��Top K����һ����������飬����ΪN�� ��������/��С��K������ 
 * 
 * ����������ö�����������󶥶�/С���ѡ�
 * 
 * @author May 2019��10��18��
 */
public class A03TopKByHeapInsertAllElement {
	/**
	 * �����е�Ԫ�ض��ŵ����Ȼ����ȡ����Сtop5��Ч�ʲ��ԣ�
	 * 	������Ϊ100��+10ʱ��33���룻
	 * 	������Ϊ1000��+10ʱ��2756���룻
	 */
	@Test
	public void getTopKByHeapinsertAllElement() {
		int arrayLength = 10000000 + 10;
		
		//׼��һ������ΪarrayLength���������飺
		int[] array = A03TopKByQuickSortAndNewArray.getDisorderlyArray(arrayLength);
		
		//׼��һ���ܽ����ΪarrayLength��С���ѣ�
		PriorityQueue<Integer> heap = new PriorityQueue<>(arrayLength);

		long start = System.currentTimeMillis();
		
		// ��100�����ȫ���ŵ����У�
		for (int i = 0; i < array.length; i++) {
			heap.add(array[i]);
		}

		//��ȡ��СtopK
		printHeap(heap, 5);
		
		long end = System.currentTimeMillis();
		System.out.println("������top5�ܺ�ʱ�� " + (end - start));
	}
	
	/**
	 * ��ȡ��СTopK
	 * @param heap
	 */
	static void printHeap(PriorityQueue<Integer> heap, int topK) {
		Iterator<Integer> iterator = heap.iterator();
		int i = 0;
		while (i < topK && iterator.hasNext()) {
			System.out.println(iterator.next());
			i++;
		}
	}
}
