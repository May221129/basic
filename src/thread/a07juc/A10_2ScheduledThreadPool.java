package thread.a07juc;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 1.ScheduledExecutorService
 * 	������Ҫ�̳߳أ�����Ҫ���߳̽��е���ʱ��ʹ��ScheduledExecutorService�ӿڵ�ʵ���࣬
 * 	����Ļ�ø��̳߳صķ�ʽ��Executors.newScheduledThreadPool(int corePoolSize)
 * 	��õ��̳߳ص��ص㣺�����̶���С���̣߳������ӳٻ�ʱ��ִ������
 * 	ע�⣺����˵�Ķ���ɵĵ��ȣ���ָ���̵߳Ŀ��ƣ����ӳ�ִ������
 * 2.���⣺
 * 	2.1 ̽��1
 * 	2.2 ��schedule(Runnable command, long delay, TimeUnit unit)��
 * 		��schedule(Callable<Integer> callable, long delay, TimeUnit unit)������
 * 		ͨ������Ĳ��ԣ����Է��֣����ǵȴ���3��֮��ʼ��ӡ����ӡ����������ϵģ��м䲢û��һ���̴߳�ӡ��ȴ�3�����һ���̴߳�ӡ��
 * 		�����ǵȴ�3���ʼ��ӡ����ÿ���̶߳��Ǽ��3����ٴ�ӡ��
 * 	�²⣺�����ӳ��׸��̵߳Ŀ�ʼʱ�䡣�����ӳ������̵߳Ŀ�ʼʱ�䡣
 * 	���ۣ�������������ע�͡�
 * 	2.3 Submit��scheduled����������
 * 	2.4 ��ô�����̳߳أ���С���õĶ��У��ȣ���
 */
public class A10_2ScheduledThreadPool {
	public static void main(String[] args) throws Exception {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
		//����5������
		for(int i = 0; i < 5; i++){
			/**
			 * schedule(Runnable command, long delay, TimeUnit unit)
			 * command������  delay��������  unit����ʱ��λ��
			 */
			scheduledThreadPool.schedule(new Runnable() {
				@Override
				public void run() {
					Integer sum = 0;
					for(int i = 0; i < 10; i++){
						sum += i;
					}
					System.out.println(Thread.currentThread().getName() + " : " + sum);
				}
			}, 3, TimeUnit.SECONDS);
		}
		
		/**
		 * ̽��1.�����������ȫ����ɺ��ڴ�ӡ����������ݣ�
		 */
		System.out.println("-----------------------------------");
		
		for(int i = 0; i < 5; i++){
			ScheduledFuture<Integer> future = scheduledThreadPool.schedule(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Integer sum = 0;
					for(int i = 0; i < 10; i++){
						sum += i;
					}
					System.out.println(Thread.currentThread().getName() + " : " + sum);
					return sum;
				}
			}, 3, TimeUnit.SECONDS);
		}
		scheduledThreadPool.shutdown();
	}
}
