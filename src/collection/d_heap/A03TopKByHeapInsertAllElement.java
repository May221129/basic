package collection.d_heap;

import java.util.Iterator;
import java.util.PriorityQueue;

import org.junit.Test;

/**
 * 经典算法问题——Top K：给一个无序的数组，长度为N， 请输出最大/最小的K个数。 
 * 
 * 解决方案：用堆来解决——大顶堆/小顶堆。
 * 
 * @author May 2019年10月18日
 */
public class A03TopKByHeapInsertAllElement {
	/**
	 * 将所有的元素都放到堆里，然后再取出最小top5，效率测试：
	 * 	数据量为100万+10时：33毫秒；
	 * 	数据量为1000万+10时：2756毫秒；
	 */
	@Test
	public void getTopKByHeapinsertAllElement() {
		int arrayLength = 10000000 + 10;
		
		//准备一个长度为arrayLength的无序数组：
		int[] array = A03TopKByQuickSortAndNewArray.getDisorderlyArray(arrayLength);
		
		//准备一个总结点数为arrayLength的小顶堆：
		PriorityQueue<Integer> heap = new PriorityQueue<>(arrayLength);

		long start = System.currentTimeMillis();
		
		// 將100万个数全部放到堆中：
		for (int i = 0; i < array.length; i++) {
			heap.add(array[i]);
		}

		//获取最小topK
		printHeap(heap, 5);
		
		long end = System.currentTimeMillis();
		System.out.println("获得最大top5总耗时： " + (end - start));
	}
	
	/**
	 * 获取最小TopK
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
