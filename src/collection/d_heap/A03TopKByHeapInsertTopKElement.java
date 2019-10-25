package collection.d_heap;

import java.util.Iterator;
import java.util.PriorityQueue;

import org.junit.Test;

/**
 * 经典算法问题——Top K：给一个无序的数组，长度为N， 请输出最大的top 5。
 *
 * 解决方案：用堆来解决——大顶堆/小顶堆。
 * 
 * 一、步骤梳理： ①创建一个结点个数为k的小顶堆；
 *  ②当数据量 < k 时，将数据直接放到这个小顶堆中，此时堆的顶结点是最小值； 
 *  ③当数据量 >= k时，每产生一个新数据都与堆的顶结点进行比较: 
 *  	如果新数据 > 顶结点数据，则将顶结点删除，将新数据放到堆中，此时堆会进行排序，且维护了堆的总结点数为k;
 * 
 * 二、中心思想：使堆的总结点数维持在k个。 
 * 
 * 三、提问：如果将所有的元素都放到堆里，然后再取出top5，效率是否会变高？测试见A03TopKByHeap2。
 * 	测试结果：效率变低了。
 * 
 * 四、相关知识点：G:\mystudy\studynotes\数据结构_树.xmind
 * 
 * @author May 2019年10月18日
 */
public class A03TopKByHeapInsertTopKElement {
	/**
	 * 通过“小顶堆”的方式，来获取topK，效率测试：
	 * 	数据量为100万+10时：11毫秒；
	 * 	数据量为1000万+10时：28毫秒；
	 */
	@Test
	public void getTopKByHeapInsertTopKElement() {
		int arrayLength = 10000000 + 10;
		int topK = 5;

		// 准备一个长度为arrayLength的无序数组：
		int[] array = A03TopKByQuickSortAndNewArray.getDisorderlyArray(arrayLength);

		// 准备一个总结点数为topK的小顶堆：
		PriorityQueue<Integer> heap = new PriorityQueue<>(topK);

		long start = System.currentTimeMillis();
		
		// 始终维持一个总结点个数为k的堆：
		insertButmaintainTheHeapAtTopK(heap, array, topK);
		
		//获得最大topK：
		printHeap(heap);
		
		long end = System.currentTimeMillis();
		System.out.println("获得最大top5总耗时： " + (end - start));
	}
	
	/**
	 * 用小顶堆来获取topK：当数据量超过topK后，新产生的数据直接和heap的顶结点进行比较。
	 */
	private static void insertButmaintainTheHeapAtTopK(PriorityQueue<Integer> heap, int[] array, int topK) {
		for (int i = 0; i < array.length; i++) {
			if (i < topK) {
				heap.add(array[i]);
			} else {// 怎么维持堆的总结点个数，下面的代码是关键：
				if (null != heap.peek() && array[i] > heap.peek()) {
					heap.poll();
					heap.add(array[i]);
				}
			}
		}
	}
	
	/**
	 * 获取最大TopK
	 * @param heap
	 */
	static void printHeap(PriorityQueue<Integer> heap) {
		Iterator<Integer> iterator = heap.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
