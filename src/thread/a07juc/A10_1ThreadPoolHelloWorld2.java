package thread.a07juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ʹ��Executors.newFixedThreadPool(int corePoolSize)�����õ����̳߳ص�ʹ�ã�Ϊ�̷߳������񡢹ر��̳߳أ���
 * ������Ҫѧϰ��Ϊ�̳߳��е��̷߳�������ʱ���������������ʵ��Callable�ӿڵġ�
 */
public class A10_1ThreadPoolHelloWorld2 {
	public static void main(String[] args) throws Exception {
		//1. �����̳߳أ�
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
		
		//2.Ϊ�̳߳��е��̷߳�������
		Task01 task01 = new Task01();
		Integer sum = 0;
		for(int i = 0; i < 6; i++){//ִ������6�Σ��̳߳��е�5���̷ֱ߳�ȥִ��һ�Σ��ĸ��߳�����ִ������ô���߳̾�ȥִ�е�6�ε�����
			/**
			 * submit(Runnable task) : ������ʵ��Runnable�ӿ����͵ġ�����A10_1ThreadPoolHelloWorld
			 * submit(Callable<T> task) : ������ʵ��Callable�ӿ����͵ġ�
			 */
			Future<Integer> future = fixedThreadPool.submit(task01);//�����̳߳��е�һ���߳�ȥִ������
			sum += future.get();
		}
		System.out.println(sum);
		
		//3.�ر��̳߳أ�
		fixedThreadPool.shutdown();
	}
}
class Task01 implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		Integer sum = 0;
		for(int i = 0; i < 10; i++){
			sum += i;
		}
		System.out.println(Thread.currentThread().getName() + " : " + sum);
		return sum;
	}
}
