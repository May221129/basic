package collection.a_test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * 测试 ArrayList和LinkedList的remove(int index)方法remove(Object target)方法的删除速度。
 * 	1. 条件：
 * 		①要做删除的集合的元素个数及其排序都是一样的；
 * 		②随机删除，删除的元素个数相同：都是删除"size() >> 1"个元素；
 * 	2.测试结果：（单位：毫秒）
 * 		ArrayList.remove(int index)简称为A1；  ArrayList.remove(Object target)简称为A2；
 * 		LinkedList.remove(int index)简称为L1；LinkedList.remove(Objcet target)简称为L2；
 * 				A1				A2				L1				L2
 * 		10万		669				11,142			14,351			22,552
 * 		100万	71,379			953,500			1,183,541		1,875,837
 * 
 * 	3.总结：【删除一个元素的速度 = 找到这个元素耗时 + 删除动作耗时】
 * 		（1）A1 VS A2 : 
 * 			size=10万：A1比A2会快了16倍；  size=100万：A1比A2会快了13倍；
 * 		（2）L1 VS L2:
 * 			size=10万：L1比L2快了1.6倍；  size=100万：L1比L2 快了1.6倍；
 * 		（3）A1 VS L1:
 * 			size=10万：A1比L1快了21倍；  size=100万：A1比L1快了17倍；
 * 		（4）A2 VS L2:
 * 			size=10万：A2比L2快了2倍；  size=100万：A2比L2快了2倍；
 * 
 * =======》根据（1）（2）可得：不论是ArrayList还是LinkedList，“按下标做删除”都会比“按元素内容做删除”速度快；
 * 			根据（3）（4）可得：不论是“按下标做删除”还是“按元素内容做删除”，ArrayList的删除速度都会比LinkedList的快。
 * 
 * @author May
 * 2019年7月15日
 */
public class ArrayListAndLinkedListRemoveElement2 {
	public static void main(String[] args) {
		ArrayListAndLinkedListRemoveElement2 test = new ArrayListAndLinkedListRemoveElement2();
		
		//准备集合：
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
		
		//执行删除：
		test.arryaListRemoveElementByIndex(arrayListByIndex);
		System.out.println("-----------------------------------------------");
		test.arryaListRemoveElementByElement(arrayListByElement);
		System.out.println("-----------------------------------------------");
		test.linkedListRemoveByIndex(linkedListByIndex);
		System.out.println("-----------------------------------------------");
		test.linkedListRemoveByElement(linkedListByElement);
	}
	
	/**
	 * LinkedList.remove(Object target)删除元素的速度。
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
		System.out.println("linkedList.remove(index)耗时：" + (end - start));
		
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
		System.out.println("linkedList.remove(Object)耗时：" + (end - start));
		
		System.out.println("LinkedList.size() : " + linkedList.size());
	}
	
	/**
	 * ArrayList.remove(Object target)删除元素的速度
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
		System.out.println("arrayList.remove(index)耗时： " + (end-start));
		
		System.out.println("arrayList.size : " + arrayList.size());
	}
	
	/**
	 * ArrayList.remove(Object target)删除元素的速度
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
		System.out.println("arrayList.remove(Objcet)耗时： " + (end-start));
		
		System.out.println("arrayList.size : " + arrayList.size());
	}
}
