package collection.d_queue;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

/**
 * ���ݽṹ��queue�����У���stack��ջ����heap���ѣ��ĳ���ʵ���༰��ʵ����ĳ��÷�����Ϥ��
 */
public class A02ImplementsQueue {
	
	/**
	 * ���У�LikedListҲʵ����Deque(Deque��Queue������)��
	 */
	@Test
	public void testQueue(){
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0; i < 10; i++){
			queue.offer(i);//�ųɹ��˾ͷ���true����֮����false��
		}
		System.out.println("=========>" + queue.poll());//��ȡ���е�ͷ�����Ƴ���ͷ
		for(Integer i : queue){
			System.out.println(i);
		}
		System.out.println("=========>" + queue.peek());//��ȡ���е�ͷ�������Ƴ���ͷ
		for(Integer i : queue){
			System.out.println(i);
		}
	}
}
