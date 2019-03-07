package thread.a07juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用Executors.newFixedThreadPool(int corePoolSize)，及得到的线程池的使用（为线程分配任务、关闭线程池）。
 * 这里主要学习：为线程池中的线程分配任务时，该任务的类型是实现Callable接口的。
 */
public class A10_1ThreadPoolHelloWorld2 {
	public static void main(String[] args) throws Exception {
		//1. 创建线程池：
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
		
		//2.为线程池中的线程分配任务：
		Task01 task01 = new Task01();
		Integer sum = 0;
		for(int i = 0; i < 6; i++){//执行任务6次，线程池中的5个线程分别去执行一次，哪个线程最先执行完那么该线程就去执行第6次的任务。
			/**
			 * submit(Runnable task) : 任务是实现Runnable接口类型的。见：A10_1ThreadPoolHelloWorld
			 * submit(Callable<T> task) : 任务是实现Callable接口类型的。
			 */
			Future<Integer> future = fixedThreadPool.submit(task01);//调度线程池中的一个线程去执行任务
			sum += future.get();
		}
		System.out.println(sum);
		
		//3.关闭线程池：
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
