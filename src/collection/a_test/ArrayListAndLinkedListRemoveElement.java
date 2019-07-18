package collection.a_test;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 测试 ArrayList和LinkedList的remove(int index)方法remove(Object target)方法的删除速度。
 *  
 *  前言：
 *  	List接口中，LinkedList比ArrayList更适合频繁做元素的删除和插入：
 * 			两者在查找要删除的元素时的速度是差不多的，都是next、next去遍历整个集合；
 * 			但是，找到元素后的删除动作，LinkedList是直接修改nextNode的地址即可，而ArrayList把指定元素删除后，需要将后面所有的元素都往前挪一位。
 * 		上面这个结论真的正确吗？现在通过ArrayListAndLinkedListRemoveElement和ArrayListAndLinkedListRemoveElement2这两个类中写的测试，
 * 			来验证上面结论的正确与否。
 * 	1. 条件：
 * 		①要做删除的集合是完全一样的；
 * 		②删除的元素也是一样的，都是从集合的中间开始往前删，删除掉size/2个元素：
 * 			for(int i = (list.size() >> 1); i > 0; i--) {
 * 				list.remove(Object i);
 * 	2.测试结果：（单位：毫秒）
* 		ArrayList.remove(int index)简称为A1；  ArrayList.remove(Object target)简称为A2；
 * 		LinkedList.remove(int index)简称为L1；LinkedList.remove(Objcet target)简称为L2；
 * 				A1				A2				L1				L2
 * 		10万		563				2,520			2,807			5,931
 * 		100万	62,006			378,604			336,792			536,878
 * 
 * 	3.总结：【删除一个元素的速度 = 找到这个元素耗时 + 删除动作耗时】
 * 		（1）A1 VS A2 : 
 * 			size=10万：A1比A2会快了4.5倍；  size=100万：A1比A2会快了6.1倍；
 * 		（2）L1 VS L2:
 * 			size=10万：L1比L2快了2.1倍；  size=100万：L1比L2 快了1.6倍；
 * 		（3）A1 VS L1:
 * 			size=10万：A1比L1快了5倍；  size=100万：A1比L1快了5.4倍；
 * 		（4）A2 VS L2:
 * 			size=10万：A2比L2快了2.3倍；  size=100万：A2比L2快了1.4倍；
 * 
 * =======》根据（1）（2）可得：不论是ArrayList还是LinkedList，“按下标做删除”都会比“按元素内容做删除”速度快；
 * 			根据（3）（4）可得：不论是“按下标做删除”还是“按元素内容做删除”，ArrayList的删除速度都会比LinkedList的快。
 * 
 * @author May
 * 2019年7月15日
 */
public class ArrayListAndLinkedListRemoveElement {
	public static void main(String[] args) {
		ArrayListAndLinkedListRemoveElement test = new ArrayListAndLinkedListRemoveElement();
		
		//准备集合：
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
		
		int removeElement = linkedList.size() >> 1;
		
		long start = System.currentTimeMillis();
		for(int i = removeElement; i > 0; i--) {
			linkedList.remove(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("linkedList.remove(index)耗时：" + (end - start));
		
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
		System.out.println("linkedList.remove(Object)耗时：" + (end - start));
		
		System.out.println("LinkedList.size() : " + linkedList.size());
	}
	
	/**
	 * ArrayList.remove(Object target)删除元素的速度
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
		System.out.println("arrayList.remove(index)耗时： " + (end-start));
		
		System.out.println("arrayList.size : " + arrayList.size());
	}
	
	/**
	 * ArrayList.remove(Object target)删除元素的速度
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
		System.out.println("arrayList.remove(Objcet)耗时： " + (end-start));
		
		System.out.println("arrayList.size : " + arrayList.size());
	}
}
