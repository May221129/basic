package thread.a07juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 需求：主线程负责计算0~1000的总和，t1负责计算0~3000的总和，t2负责计算0~5000的总和，然后把这三个结果再求和。
 * 实现：1.用CountDownLatch来实现（这里用的是这种方式）； 2.用Callable来实现（见A04Callable01）。
 * 两种实现方式的比较：
 * 	用CountDownLatch比较麻烦，还需要声明多个sum来接收各个线程计算的和，且还要考虑sum的线程安全问题(原子变量)。
 * 	用Callable比较简单，因为它可以直接返回结果。
 */
public class A03CountDownLatch02 {
	public static void main(String[] args) {
		GetSum getSum = new GetSum();
		CountDownLatch latch = new CountDownLatch(2);
		
		Thread2 thread2 = new Thread2(getSum, latch);
		new Thread(thread2).start();
		
		Thread3 thread3 = new Thread3(getSum, latch);
		new Thread(thread3).start();
		
		//在哪里让主线程挂起去等待两个子线程计算完？如果是在获取sum值之后，会怎样？
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		AtomicInteger sum1 = getSum.getSum1();
		AtomicInteger sum2 = getSum.getSum22();
		AtomicInteger sum3 = getSum.getSum33();	
		//如果在这里让主线程挂起，就失去了闭锁的意义，因为有可能子线程还没计算完，然后主线程拿到的不是最终结果的sum来求和了。
//		try {
//			latch.await();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		System.out.println("sum1 = " + sum1);
		System.out.println("sum2 = " + sum2);
		System.out.println("sum3 = " + sum3);
		System.out.println("总和 = " + (sum1.get() + sum2.get() + sum3.get()));
	}
}

class Thread3 implements Runnable{
	private GetSum getSum;
	private CountDownLatch latch;
	
	public Thread3(GetSum getSum, CountDownLatch latch) {
		this.getSum = getSum;
		this.latch = latch;
	}
	@Override
	public void run() {
		try {
			getSum.getSum3();
		} finally {
			latch.countDown();
		}
	}
}

class Thread2 implements Runnable{
	private GetSum getSum;
	private CountDownLatch latch;
	public Thread2(GetSum getSum, CountDownLatch latch) {
		this.getSum = getSum;
		this.latch = latch;
	}
	@Override
	public void run() {
		try {
			getSum.getSum2();
		} finally {
			latch.countDown();
		}
	}
}

class GetSum{
	private AtomicInteger sum1 = new AtomicInteger();//原子变量，相当于Integer，只是AtomicInteger用了CAS算法保证了线程安全性。
	private AtomicInteger sum2 = new AtomicInteger();
	private AtomicInteger sum3 = new AtomicInteger();
	
	public AtomicInteger getSum1(){
		for(int i = 0; i <= 1000; i++){
			sum1.addAndGet(i);
		}
		return sum1;
	}
	public AtomicInteger getSum2(){
		for(int i = 0; i <= 3000; i++){
			sum2.addAndGet(i);
		}
		return sum2;
	}
	public AtomicInteger getSum3(){
		for(int i = 0; i <= 6000; i++){
			sum3.addAndGet(i);
		}
		return sum3;
	}
	
	public AtomicInteger getSum22(){
		return sum2;
	}
	public AtomicInteger getSum33(){
		return sum3;
	}
}