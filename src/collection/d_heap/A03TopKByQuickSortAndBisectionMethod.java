package collection.d_heap;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

/**
 * 测试：通过“快排 + 二分法”的方式，从无序数组中找出最小top 5。
 * 
 * 一、核心步骤： 
 * 	1.通过快排，先将无序数组array进行从小到大排序； 
 * 	2.排序后，数组的前K个数据就是最小topK；
 * 	3.超过listSize个数据后，又产生了insertNumber个新数据：通过二分法将新数据插入到已经有序的array中；【关键】
 * 	4.插入新数据后，数组已经是有序的，数组的前k个数据已经是最小topk;【关键】
 * 	【注意：因为数组一定声明其长度就不能再改变，所以要用到ArrayList作为容器，其底层也是数组，只是它支持自动扩容。】
 * 
 * 二、时间复杂度：
 * 	①排序的时间复杂度：O(N*logN)；
 * 	②取出top k的时间复杂度：O(1)，就是遍历数组。
 * 
 * 三、相关知识点：G:\mystudy\studynotes\数据结构_树.xmind
 * 
 * @author May 2019年10月18日
 */
public class A03TopKByQuickSortAndBisectionMethod {
	/**
	 * 测试：通过“快排 + 二分法”获取topK的效率。
	 * 测试结果：
	 * 	listSize == 100万 ： 326毫秒
	 *  listSize == 1000万：9379毫秒
	 */
	@Test
	public void testgetTopKByQuickSortAndBisectionMethod() {
		int listSize = 10000000;
		
		//准备一个无序list
		ArrayList<Integer> list = getDisorderlyList(listSize);

		long start = System.currentTimeMillis();

		// 1.先将array中的所有元素进行从小到大排序：排序后的array[0]~array[4]这前5个数就是最小top5
		quickSort(list, 0, list.size() - 1);

		// 2. 超过listSize个数据后，又产生了insertNumber个新数据：通过二分法将新数据插入到已经有序的array中
		insertToOrderlyList(list, 10, 100);

		long end = System.currentTimeMillis();
		System.out.println("获得最大top5总耗时： " + (end - start));
	}
	
	/**
	 * 测试：通过“快排 + 二分法”获取topK的正确性。
	 */
	@Test
	public void testgetTopKByQuickSortAndBisectionMethodIsTrue() {
		int listSize = 10;
		int topK = 5;
		
		//准备一个无序list
		ArrayList<Integer> list = getDisorderlyList(listSize);
		
		System.out.println("无序数组：");
		showList(list);
		

		// 1.先将array中的所有元素进行从小到大排序：排序后的array[0]~array[4]这前5个数就是最小top5
		quickSort(list, 0, list.size() - 1);
		
		System.out.println("排序后的数组：");
		showList(list);
		
		System.out.println("topK：");
		getTopKFromOrderlyList(list, topK);

		// 2. 超过listSize个数据后，又产生了insertNumber个新数据：通过二分法将新数据插入到已经有序的array中
		insertToOrderlyListHavePrintRandomInt(list, 10, 100);
		
		System.out.println("产生新数据后，再次查看top K：");
		getTopKFromOrderlyList(list, topK);
	}
	
	/**
	 * 查看最小top K。
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
	 * 超过listSize个数据后，又产生了insertNumber个新数据：通过二分法将新数据插入到已经有序的array中
	 * 【该方法和insertToOrderlyList(……)方法的唯一区别：它不打印出产生的随机数】
	 * 
	 * @param insertNumber 新产生的数据的个数
	 * @param randomIntRange 在什么范围内产生新数据，如生成10以内的随机数。
	 */
	private static void insertToOrderlyList(ArrayList<Integer> list, int insertNumber, int randomIntRange) {
		Random random = new Random();
		for(int i = 0; i < insertNumber; i++) {
			insertByBisectionMethod(list, 0, list.size()-1, random.nextInt(randomIntRange));
		}
	}
	
	/**
	 * 超过listSize个数据后，又产生了insertNumber个新数据：通过二分法将新数据插入到已经有序的array中
	 * 【该方法会打印出产生的随机数】
	 * 
	 * @param insertNumber 新产生的数据的个数
	 * @param randomIntRange 在什么范围内产生新数据，如生成10以内的随机数。
	 */
	private static void insertToOrderlyListHavePrintRandomInt(ArrayList<Integer> list, int insertNumber, int randomIntRange) {
		Random random = new Random();
		int randomInt;
		for(int i = 0; i < insertNumber; i++) {
			randomInt = random.nextInt(randomIntRange);//生成10个范围在100以内随机数作为新数据
			System.out.println("randomInt == " + randomInt);
			insertByBisectionMethod(list, 0, list.size()-1, randomInt);
		}
	}
	
	/**
	 * 利用二分法将新元素插入到有序数组中
	 */
	private static void insertByBisectionMethod(ArrayList<Integer> targetArray, int left, int right, Integer newNumber) {
		// 数据错误
		if ((right - left) < 0) {
			return;
		}
		// 只有一个数
		if ((right - left) == 0) {
			if (newNumber < targetArray.get(left)) {
				targetArray.add(left, newNumber);
				return;
			}
			targetArray.add(left + 1, newNumber);
			return;
		}
		
		// 只有两个数
		if ((right - left) == 1) {
			if (newNumber < targetArray.get(left)) {
				targetArray.add(left, newNumber);// 放在left的前面
				return;
			}
			if (newNumber > targetArray.get(right)) {
				targetArray.add(right + 1, newNumber);// 放在right的后面
				return;
			}
			targetArray.add(right, newNumber);// 放在left和right的中间
			return;
		}
		
		// >= 3个数时：二分查找
		int middleIndex = (left + right) / 2;
		if (newNumber == targetArray.get(middleIndex)) {
			targetArray.add(middleIndex, newNumber);// 放在middleIndex位置，middleIndex及其后面的所有元素都后移一位
			return;
		}
		if (newNumber < targetArray.get(middleIndex)) {// newNumber在左边
			insertByBisectionMethod(targetArray, left, middleIndex - 1, newNumber);
			return;
		} else {// newNumber在右边
			insertByBisectionMethod(targetArray, middleIndex + 1, right, newNumber);
			return;
		}
	}

	/**
	 * 快排
	 * 
	 * @param arrayList
	 * @param left
	 * @param right
	 */
	private static void quickSort(ArrayList<Integer> arrayList, int left, int right) {
		if (left >= right) {
			return;
		}
		int pivot = arrayList.get(left);// 基准点
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
		// left和right相遇了：
		// ①将相遇点的元素和pivot做交换：
		arrayList.set(left, arrayList.get(j));
		arrayList.set(j, pivot);
		// ②基准点两边的元素的分别再做排序：
		quickSort(arrayList, left, j - 1);
		quickSort(arrayList, j + 1, right);
	}

	/**
	 * 准备一个无序集合
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
	 * 遍历数组
	 */
	static void showList(ArrayList<Integer> target) {
		for (Integer element : target) {
			System.out.println(element);
		}
	}
}
