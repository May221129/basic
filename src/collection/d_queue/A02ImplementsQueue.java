package collection.d_queue;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

/**
 * 数据结构：queue（队列）、stack（栈）、heap（堆）的常用实现类及该实现类的常用方法熟悉。
 */
public class A02ImplementsQueue {
	
	/**
	 * 队列：LikedList也实现了Deque(Deque是Queue的子类)。
	 */
	@Test
	public void testQueue(){
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0; i < 10; i++){
			queue.offer(i);//放成功了就返回true，反之返回false。
		}
		System.out.println("=========>" + queue.poll());//获取队列的头，并移除此头
		for(Integer i : queue){
			System.out.println(i);
		}
		System.out.println("=========>" + queue.peek());//获取队列的头，但不移除此头
		for(Integer i : queue){
			System.out.println(i);
		}
	}
}
