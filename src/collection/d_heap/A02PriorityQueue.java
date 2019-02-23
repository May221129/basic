package collection.d_heap;

import java.util.PriorityQueue;
import java.util.Random;

import org.junit.Test;

/**
 * �ѵ�ʵ���ࣺPriorityQueue�����ȼ����У�
 */
public class A02PriorityQueue {
	
	@Test
	public void testPriorityQueue(){
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		Random random = new Random();
		for(int i = 0; i < 10; i++){
			int nextInt = random.nextInt(101);
			System.out.println(nextInt);
			heap.offer(nextInt);
		}
		System.out.println("==========================");
		while(!heap.isEmpty()){
			System.out.println(heap.poll());
//			System.out.println(heap.peek());//������������������ͻ�����ѭ����һֱ�����Сֵ��
		}
		System.out.println("==========================");
		System.out.println(heap.isEmpty());
	}
}
