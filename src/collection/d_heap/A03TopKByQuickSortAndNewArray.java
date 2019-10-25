package collection.d_heap;

import java.util.Random;

import org.junit.Test;

/**
 * 测试：通过“快排 + topK数组”的方式，从无序数组中找出最小top 5。
 * 
 * 一、核心步骤：
 * 	1.通过快排，先将无序数组array进行排序；
 * 	2.取出最小Top 5，并放到topArray中；【关键】
 * 	3.超过arrayLength个数据后，又产生了insertNumber个新数据：直接和topArray数组比较，要放也是放到topArray中了；【关键】
 * 
 * 二、时间复杂度：
 * 	①排序的时间复杂度：O(N*logN)；
 * 	②取出top k的时间复杂度：O(1)，就是遍历数组。
 * 
 * 三、相关知识点：G:\mystudy\studynotes\数据结构_树.xmind
 * 
 * @author May
 * 2019年10月18日
 */
public class A03TopKByQuickSortAndNewArray {
	/**
	 * 测试方法的效率：获得最小top 5。
	 * 测试结果：
	 * 	array.length == 100万：124毫秒。
	 * 	array.length == 1000万：1438毫秒。
	 */
	@Test
	public void testGetTopKByQuickSortToNewArray() {
		int topK = 5;
		int arrayLength = 10000000;
		
		//准备一个无序数组
		int[] array = getDisorderlyArray(arrayLength);
		
		long start = System.currentTimeMillis();
		
		//1.通过快排，先将无序数组array进行排序
		quickSort(array, 0, array.length-1);
		
		//2.取出最小Top 5，并放到topArray中：
		int[] topKArray = insertToTopArrayFromDisorderlyArray(array, topK);
		
		//3.超过arrayLength个数据后，又产生了insertNumber个新数据：直接和topArray[topKArray.length-1]比较，要放也是放到topArray中了
		insertToTopKArray(topKArray, 10, 100, topKArray.length-1);//生成10个100以内的随机数作为新数据，和topKArray[topKArray.length-1]
		
		long end = System.currentTimeMillis();
		System.out.println("获得最大top5总耗时： " + (end - start));
	}
	
	/**
	 * 测试方法的正确性。
	 */
	@Test
	public void testGetTopKByQuickSortToNewArrayIsTrue() {
		int topK = 5;
		int arrayLength = 10;
		
		//准备一个无序数组
		int[] array = getDisorderlyArray(arrayLength);
		
		showArray(array);
		System.out.println("-----------");
		
		//1.通过快排先将array中的所有元素进行排序：
		quickSort(array, 0, array.length-1);
		
		showArray(array);
		System.out.println("-----------");
		
		//2.取出最小Top 5，并放到topArray中：
		int[] topKArray = insertToTopArrayFromDisorderlyArray(array, topK);
		
		showArray(topKArray);
		System.out.println("-----------");
		
		//3.超过arrayLength个数据后，又产生了insertNumber个新数据：直接和topArray[topKArray.length-1]比较，要放也是放到topArray中了
		insertToTopKArrayByHavePrintRandom(topKArray, 10, 100, topKArray.length-1);//生成10个100以内的随机数作为新数据，和topKArray[topKArray.length-1]
		System.out.println("-----------");
		
		//4. 查看最小top5：topArray中的所有元素
		showArray(topKArray);
	}
	
	/**
	 * 产生新的数据后，再和topKArray数组进行比较，看新数据时候需要插入到topKArray中，若需要插入，则堆topKArray进行重新快排。
	 * 【该方法和insertToTopKArrayByHavePrintRandom(……)方法的唯一区别：它不打印出产生的随机数】
	 * 
	 * @param topKArray topK数组
	 * @param insertNumber 新产生的数据的个数
	 * @param randomIntRange 在什么范围内产生新数据，如生成10以内的随机数。
	 * @param topK 在topKArray中，确定要替换的元素的下标。获得最小topK，则topK是从小到大排序的topKArray的最后一个元素。
	 */
	private static void insertToTopKArray(int[] topKArray, int insertNumber, int randomIntRange, int topK) {
		Random random = new Random();
		int randomInt;
		for(int i = 0; i < insertNumber; i++) {
			randomInt = random.nextInt(100);
			if(randomInt < topKArray[topK]) {//新数据如果小于topArray[topK]，则直接用该数去替换topArray，然后再将topArray进行重新排序。
				topKArray[topK] = randomInt;
				quickSort(topKArray, 0, topKArray.length-1);
			}
		}
	}
	
	/**
	 * 产生新的数据后，再和topKArray数组进行比较，看新数据时候需要插入到topKArray中，若需要插入，则堆topKArray进行重新快排。
	 * 【该方法用于测试方法的正确性，它有打印出产生的随机数】
	 * 
	 * @param topKArray topK数组
	 * @param insertNumber 新产生的数据的个数
	 * @param randomIntRange 在什么范围内产生新数据，如生成10以内的随机数。
	 * @param topK 在topKArray中，确定要替换的元素的下标。获得最小topK，则topK是从小到大排序的topKArray的最后一个元素。
	 */
	private static void insertToTopKArrayByHavePrintRandom(int[] topKArray, int insertNumber, int randomIntRange, int topK) {
		Random random = new Random();
		int randomInt;
		for(int i = 0; i < insertNumber; i++) {
			randomInt = random.nextInt(100);
			System.out.println("randomInt == " + randomInt);
			if(randomInt < topKArray[topK]) {//新数据如果小于topArray[topK]，则直接用该数去替换topArray，然后再将topArray进行重新排序。
				topKArray[topK] = randomInt;
				quickSort(topKArray, 0, topKArray.length-1);
			}
		}
	}
	
	/**
	 * 从有序数组中取出需要的TopK，放到TopK数组中。
	 * 
	 * @param sourceArray 有序数组
	 * @param topK 需要获取到Top K
	 * @return TopK数组
	 */
	private static int[] insertToTopArrayFromDisorderlyArray(int[] sourceArray, int topK) {
		int[] topArray = new int[topK];
		for(int i = 0; i < 5; i++) {
			topArray[i] = sourceArray[i];
		}
		return topArray;
	}
	
	/**
	 * 快排
	 * @param target
	 * @param left
	 * @param right
	 */
	static void quickSort(int[] target, int left, int right) {
		if (left >= right) {
			return;
		}
		int pivot = target[left];// 基准点
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
		// left和right相遇了：
		// ①将相遇点的元素和pivot做交换：
		target[left] = target[j];
		target[j] = pivot;
		// ②基准点两边的元素的分别再做排序：
		quickSort(target, left, j - 1);
		quickSort(target, j + 1, right);
	}
	
	/**
	 * 准备一个无序数组
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
	 * 遍历数组
	 */
	static void showArray(int[] target) {
		for (Integer element : target) {
			System.out.println(element);
		}
	}
}
