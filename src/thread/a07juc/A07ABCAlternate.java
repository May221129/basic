package thread.a07juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *	1.需求：线程按序交替打印——编写一个程序，开启 3个线程，这三个线程的 ID分别为 A、B、C，
 *		每个线程将自己的 ID在屏幕上打印 10遍，要求输出的结果必须按顺序显示。
 *		如：ABCABCABC…… 依次递归。
 *	2.问题：
 *		2.1 最后 A、B、C都await住了，没人唤醒，程序没法停：见printA(int printNumb)方法。
 *		2.2 如果要交替打印10次，这个次数在哪里控制比较好：run方法中。
 */
public class A07ABCAlternate {
	public static void main(String[] args) {
		PrintId printId = new PrintId();
//		ThreadA threadA = new ThreadA(printId);
//		ThreadB threadB = new ThreadB(printId);
//		ThreadC threadC = new ThreadC(printId);
//		new Thread(threadA, "A").start();
//		new Thread(threadB, "B").start();
//		new Thread(threadC, "C").start();
		
		//这里建议用匿名内部类，不然要创建三个Runnable的实现类太麻烦了。
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10; i++){
					printId.printA(i);
				}
			}
		}, "A").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10; i++){
					printId.printB(i);
				}
			}
		}, "A").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10; i++){
					printId.printC(i);
				}
			}
		}, "A").start();
	}
}

//class ThreadA implements Runnable{
//	private PrintId printId;
//	public ThreadA(PrintId printId) {
//		super();
//		this.printId = printId;
//	}
//	@Override
//	public void run() {
//		for(int i = 0; i < 10; i++){
//			printId.printA(i);
//		}
//	}
//}
//class ThreadB implements Runnable{
//	private PrintId printId;
//	public ThreadB(PrintId printId) {
//		super();
//		this.printId = printId;
//	}
//	@Override
//	public void run() {
//		for(int i = 0; i < 10; i++){
//			printId.printB(i);
//		}
//	}
//}
//class ThreadC implements Runnable{
//	private PrintId printId;
//	public ThreadC(PrintId printId) {
//		super();
//		this.printId = printId;
//	}
//	@Override
//	public void run() {
//		for(int i = 0; i < 10; i++){
//			printId.printC(i);
//		}
//	}
//}

class PrintId{
	private int number = 1;//用來控制哪个线程可以执行打印操作
	private Lock lock = new ReentrantLock();
	private Condition _a = lock.newCondition();
	private Condition _b = lock.newCondition();
	private Condition _c = lock.newCondition();
	
	/**
	 * printNumb：第几轮的打印
	 */
	public void printA(int printNumb){
		lock.lock();
		try {
			if(number != 1){
				_a.await();
			}
			System.out.println(Thread.currentThread().getName() + "\t" + printNumb);
			number = 2;
			_b.signal();
		} catch(Exception e){
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
	}
	
	public void printB(int printNumb){
		lock.lock();
		try {
			if(number != 2){
				_b.await();
			}
			System.out.println(Thread.currentThread().getName() + "\t" + printNumb);
			number = 3;
			_c.signal();
		} catch(Exception e){
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
	}
	
	public void printC(int printNumb){
		lock.lock();
		try {
			if(number != 3){
				_c.await();
			}
			System.out.println(Thread.currentThread().getName() + "\t" + printNumb);
			number = 1;
			_a.signal();
		} catch(Exception e){
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
	}
	//对比一下我一开始的实现方式：
//	public void printC(){
//		lock.lock();
//		try {
//			for(int i = 0; i < 10; i++){
//				System.out.println(Thread.currentThread().getName() + "\t" + i);
//				_a.signal();
//				try {
//					_c.await();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			_a.signal();
//		} finally {
//			lock.unlock();
//		}
//	}
}