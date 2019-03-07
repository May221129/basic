package thread.a07juc;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 1.ScheduledExecutorService
 * 	不仅需要线程池，还需要对线程进行调度时，使用ScheduledExecutorService接口的实现类，
 * 	具体的获得该线程池的方式：Executors.newScheduledThreadPool(int corePoolSize)
 * 	获得的线程池的特点：创建固定大小的线程，可以延迟或定时的执行任务。
 * 	注意：这里说的对想成的调度，是指对线程的控制，如延迟执行任务。
 * 2.问题：
 * 	2.1 探究1
 * 	2.2 ①schedule(Runnable command, long delay, TimeUnit unit)和
 * 		②schedule(Callable<Integer> callable, long delay, TimeUnit unit)的区别。
 * 		通过下面的测试，可以发现，①是等待了3秒之后开始打印，打印是连续不间断的，中间并没有一个线程打印完等待3秒后另一个线程打印。
 * 		而②是等待3秒后开始打印，且每个线程都是间隔3秒后再打印。
 * 	猜测：①是延迟首个线程的开始时间。②是延迟所有线程的开始时间。
 * 	结论：看两个方法的注释。
 * 	2.3 Submit和scheduled方法的区别。
 * 	2.4 怎么配置线程池（大小，用的队列，等）。
 */
public class A10_2ScheduledThreadPool {
	public static void main(String[] args) throws Exception {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
		//分配5次任务：
		for(int i = 0; i < 5; i++){
			/**
			 * schedule(Runnable command, long delay, TimeUnit unit)
			 * command：任务。  delay：数量。  unit：计时单位。
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
		 * 探究1.等上面的任务全部完成后，在打印出下面的内容：
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
