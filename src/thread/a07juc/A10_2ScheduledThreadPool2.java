package thread.a07juc;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * ����1���̳߳أ�������3���̡߳�4�����񣺶��Ǵ�0~10���ۼӡ����4��������ۼӺ��ٽ���Щ�ͽ�����͡�
 */
public class A10_2ScheduledThreadPool2 {
	public static void main(String[] args) throws Exception {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
		
		Integer allSum = 0;//�ܺ�
		
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
			/**
			 * future.get():��ȡ���̵߳ĵ�ִ�н��.
			 * allSum += future.get(); ==> �������̻߳ᱻ�����õ����̵߳�ִ�н�����ٽ����"+="������
			 */
			allSum += future.get();
		}
		
		System.out.println("allSum = " + allSum);
		
		scheduledThreadPool.shutdown();
	}
}
