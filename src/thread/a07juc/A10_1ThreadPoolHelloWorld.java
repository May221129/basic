package thread.a07juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * һ���̳߳أ��ṩ��һ���̶߳��У������б��������еȴ�״̬���̡߳������˴��������ٶ��⿪�����������Ӧ���ٶȡ�
 * 
 * �����̳߳ص���ϵ�ṹ��
 * 	1.�����ϵ��
 * 		|--**java.util.concurrent.Executor : �����̵߳�ʹ������ȵĸ��ӿ�
 * 			|--**ExecutorService �ӽӿ�: �̳߳ص���Ҫ�ӿ�
 * 				|--ThreadPoolExecutor �̳߳ص�ʵ����
 * 				|--ScheduledExecutorService �ӽӿڣ������̵߳ĵ���
 * 					|--ScheduledThreadPoolExecutor ���̳� ThreadPoolExecutor�� ʵ�� ScheduledExecutorService
 * 	2.���ʣ��̳߳صĽӿ���ӿڡ�������֮��Ĺ�ϵ��
 * ���������� : Executors (����ͨ���ù�����ֱ�ӻ�ȡ����Ҫ���̳߳�)
 * 	1.ExecutorService : Executors.newFixedThreadPool() : �����̶���С���̳߳�
 * 	2.ExecutorService : Executors.newCachedThreadPool() : �����̳߳أ��̳߳ص��������̶������Ը��������Զ��ĸ���������
 * 	3.ExecutorService : Executors.newSingleThreadExecutor() : ���������̵߳��̳߳أ��̳߳���ֻ��һ���̣߳���
 * 	4.ScheduledExecutorService pool = Executors.newScheduledThreadPool(int corePoolSize) : ������Ҫ�̳߳أ�����Ҫ���߳̽��е��ȡ������̶���С���̣߳������ӳٻ�ʱ��ִ������
 * 
 * �ġ�������Ҫ�Ǽ�ʹ��Executors.newFixedThreadPool(int corePoolSize)�����õ����̳߳ص�ʹ�ã�Ϊ�̷߳������񡢹ر��̳߳أ���
 * 	������Ҫѧϰ��Ϊ�̳߳��е��̷߳�������ʱ���������������ʵ��Runnable�ӿڵġ�
 */
public class A10_1ThreadPoolHelloWorld {
	public static void main(String[] args) throws Exception {
		//1. �����̳߳أ�
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
		
		//2.Ϊ�̳߳��е��̷߳�������
		Task task = new Task();
		for(int i = 0; i < 6; i++){//ִ������6�Σ��̳߳��е�5���̷ֱ߳�ȥִ��һ�Σ��ĸ��߳�����ִ������ô���߳̾�ȥִ�е�6�ε�����
			/**
			 * submit(Runnable task) : ������ʵ��Runnable�ӿ����͵ġ�
			 * submit(Callable<T> task) : ������ʵ��Callable�ӿ����͵ġ�����A10_1ThreadPoolHelloWorld2
			 */
			fixedThreadPool.submit(task);//�����̳߳��е�һ���߳�ȥִ������
		}
		
		//3.�ر��̳߳أ�
		fixedThreadPool.shutdown();
	}
}
class Task implements Runnable{
	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			System.out.println(Thread.currentThread().getName() + " : " + i);
		}
	}
}
