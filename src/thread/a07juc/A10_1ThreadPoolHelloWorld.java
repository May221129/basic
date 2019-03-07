package thread.a07juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一、线程池：提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁额外开销，提高了响应的速度。
 * 
 * 二、线程池的体系结构：
 * 	1.这个体系：
 * 		|--**java.util.concurrent.Executor : 负责线程的使用与调度的根接口
 * 			|--**ExecutorService 子接口: 线程池的主要接口
 * 				|--ThreadPoolExecutor 线程池的实现类
 * 				|--ScheduledExecutorService 子接口：负责线程的调度
 * 					|--ScheduledThreadPoolExecutor ：继承 ThreadPoolExecutor， 实现 ScheduledExecutorService
 * 	2.提问：线程池的接口与接口、类与类之间的关系。
 * 三、工具类 : Executors (可以通过该工具类直接获取到需要的线程池)
 * 	1.ExecutorService : Executors.newFixedThreadPool() : 创建固定大小的线程池
 * 	2.ExecutorService : Executors.newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
 * 	3.ExecutorService : Executors.newSingleThreadExecutor() : 创建单例线程的线程池（线程池中只有一个线程）。
 * 	4.ScheduledExecutorService pool = Executors.newScheduledThreadPool(int corePoolSize) : 不仅需要线程池，还需要对线程进行调度。创建固定大小的线程，可以延迟或定时的执行任务。
 * 
 * 四、本类主要是简单使用Executors.newFixedThreadPool(int corePoolSize)，及得到的线程池的使用（为线程分配任务、关闭线程池）。
 * 	这里主要学习：为线程池中的线程分配任务时，该任务的类型是实现Runnable接口的。
 */
public class A10_1ThreadPoolHelloWorld {
	public static void main(String[] args) throws Exception {
		//1. 创建线程池：
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
		
		//2.为线程池中的线程分配任务：
		Task task = new Task();
		for(int i = 0; i < 6; i++){//执行任务6次，线程池中的5个线程分别去执行一次，哪个线程最先执行完那么该线程就去执行第6次的任务。
			/**
			 * submit(Runnable task) : 任务是实现Runnable接口类型的。
			 * submit(Callable<T> task) : 任务是实现Callable接口类型的。见：A10_1ThreadPoolHelloWorld2
			 */
			fixedThreadPool.submit(task);//调度线程池中的一个线程去执行任务
		}
		
		//3.关闭线程池：
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
