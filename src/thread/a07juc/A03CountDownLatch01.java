package thread.a07juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch ：闭锁，在完成某些运算时，只有其他所有线程的运算全部完成，当前运算才继续执行。
 * CountDownLathe的知识点：①new CountDownLatch(10); ②latch.countDown(); ③latch.await();
 * 
 * 一、需求：t1,t2,t3这三个线程同时开始执行任务（run()方法中的逻辑）。
 * 
 * 二、思路：①让t1,t2,t3一起跑，run()方法中一开始就wait()住这3个线程，②再让主线程去countDown()， ③new CountDownLatch(1);
 * 
 * 三、问题： 
 * 	1.就算让三个线程同时开始跑，其实也很难真的同时开始跑。
 * 	2.怎么让三个线程挂起后，同时开始跑：见探究2.1和2.2
 */
public class A03CountDownLatch01 {
	public static void main(String[] args) {

		// CountDownLatch(int count) ==》 count的作用：每当一个线程执行完，它就递减1，即主线程等待指定数个线程执行完。
		final CountDownLatch latch = new CountDownLatch(1);
		LatchDemo ld = new LatchDemo(latch);

		Thread thread1 = new Thread(ld);
		thread1.setName("thread1");

		Thread thread2 = new Thread(ld);
		thread2.setName("thread2----");

		Thread thread3 = new Thread(ld);
		thread3.setName("----thread3");

		thread1.start();
		thread2.start();
		thread3.start();
		
		latch.countDown();
	}
}

class LatchDemo implements Runnable {

	private CountDownLatch latch;

	public LatchDemo(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		/**
		 * 探究2.2
		 * latch.await(); ==> 当count==0时，当前线程才会被唤醒，否则当前线程将一直处于挂起状态。
		 */
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 50000; i++) {
			if (i % 100 == 0) {
				System.out.println(Thread.currentThread().getName() + " : " + i);
			}
		}
	}
	
//	@Override
//	public void run() {
//		boolean bool = true;
//		/**
//		 * 探究2.1
//		 * 问题：这里用了while来做循环，耗CPU资源。
//		 */
//		while(latch.getCount() == 0 && bool){
//			for (int i = 0; i < 50000; i++) {
//				if (i % 100 == 0) {
//					System.out.println(Thread.currentThread().getName() + " : " + i);
//				}
//			}
//			bool = false;
//		}
//	}
}
