package thread.a07juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch �������������ĳЩ����ʱ��ֻ�����������̵߳�����ȫ����ɣ���ǰ����ż���ִ�С�
 * CountDownLathe��֪ʶ�㣺��new CountDownLatch(10); ��latch.countDown(); ��latch.await();
 * 
 * һ������t1,t2,t3�������߳�ͬʱ��ʼִ������run()�����е��߼�����
 * 
 * ����˼·������t1,t2,t3һ���ܣ�run()������һ��ʼ��wait()ס��3���̣߳����������߳�ȥcountDown()�� ��new CountDownLatch(1);
 * 
 * �������⣺ 
 * 	1.�����������߳�ͬʱ��ʼ�ܣ���ʵҲ�������ͬʱ��ʼ�ܡ�
 * 	2.��ô�������̹߳����ͬʱ��ʼ�ܣ���̽��2.1��2.2
 */
public class A03CountDownLatch01 {
	public static void main(String[] args) {

		// CountDownLatch(int count) ==�� count�����ã�ÿ��һ���߳�ִ���꣬���͵ݼ�1�������̵߳ȴ�ָ�������߳�ִ���ꡣ
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
		 * ̽��2.2
		 * latch.await(); ==> ��count==0ʱ����ǰ�̲߳Żᱻ���ѣ�����ǰ�߳̽�һֱ���ڹ���״̬��
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
//		 * ̽��2.1
//		 * ���⣺��������while����ѭ������CPU��Դ��
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
