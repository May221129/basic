package thread.a07juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch：闭锁，在完成某些运算时，只有其他所有线程的运算全部完成，当前运算才继续执行。
 * CountDownLathe的知识点：①new CountDownLatch(10); ②latch.countDown(); ③latch.await();
 * 
 * 一、需求：统计t1,t2,t3三个线程执行任务的总时长。
 * 
 * 二、问题：探索1.
 */
public class A03CountDownLatch {
	public static void main(String[] args) {
		
		final CountDownLatch latch = new CountDownLatch(3);
		LatchDemo01 ld = new LatchDemo01(latch);

		long start = System.currentTimeMillis();

		Thread thread1 = new Thread(ld);
		thread1.setName("thread1");
		
		Thread thread2 = new Thread(ld);
		thread2.setName("thread2----");
		
		Thread thread3 = new Thread(ld);
		thread3.setName("----thread3");
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		try {
			/**
			 * 探索1：
			 * 需求：这里要让主线程等所有的子线程都执行完了，再统计所有子线程执行的总执行时长。
			 * 提问：用谁的什么方法来使主线程挂起？主线程被挂起之后又有谁能将其唤醒？
			 * 答：
			 * 	①没法使用Object的wait()方法，因为这是在main()方法中，没有this对象。
			 * 	②没法使用Condition的await()方法，因为这里根本就没用到Condition类。
			 * 	③即使能用到①②中的方法来使主线程挂起，也没人能够将主线程唤醒。
			 * 	④只能用CountDownLatch的await()方法，当count==0时，当前线程才会被唤醒，否则当前线程将一直处于挂起状态。
			 */
			latch.await();
		} catch (InterruptedException e) {
		}

		long end = System.currentTimeMillis();

		System.out.println("耗费时间为 --------------- " + (end - start));
	}
}

class LatchDemo01 implements Runnable {

	private CountDownLatch latch;

	public LatchDemo01(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 50000; i++) {
				if (i % 100 == 0) {
					System.out.println(Thread.currentThread().getName() + " : " + i);
				}
			}
		} finally {
			latch.countDown();//闭锁执行减1.
		}
	}
}
