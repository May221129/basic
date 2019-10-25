package collection.d_heap;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * �öѵ�ʵ����PriorityQueue����
 * 
 * @author May
 * 2019��10��15��
 */
public class A02PriorityQueue2 {
	public static void main(String[] args) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList.add(4);
		arrayList.add(7);
		arrayList.add(90);
		arrayList.add(67);
		arrayList.add(23);
		arrayList.add(3);
		arrayList.add(10);
		arrayList.add(56);
		arrayList.add(8);
		arrayList.add(34);
		PriorityQueue<Object> priorityQueue = new PriorityQueue<>(arrayList);
		for(int i = 0; i < 10; i++) {
			System.out.println(priorityQueue.poll());
		}
	}
}
