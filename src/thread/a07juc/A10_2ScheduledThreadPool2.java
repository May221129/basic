package thread.a07juc;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 需求：1个线程池，池中有3个线程。4个任务：都是从0~10的累加。完成4个任务的累加后，再将这些和进行求和。
 */
public class A10_2ScheduledThreadPool2 {
	public static void main(String[] args) throws Exception {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
		
		Integer allSum = 0;//总和
		
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
			 * future.get():获取子线程的的执行结果.
			 * allSum += future.get(); ==> 这里主线程会被挂起，拿到子线程的执行结果，再解挂做"+="操作。
			 */
			allSum += future.get();
		}
		
		System.out.println("allSum = " + allSum);
		
		scheduledThreadPool.shutdown();
	}
}
