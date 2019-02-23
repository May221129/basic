package thread.a07juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch�������������ĳЩ����ʱ��ֻ�����������̵߳�����ȫ����ɣ���ǰ����ż���ִ�С�
 * CountDownLathe��֪ʶ�㣺��new CountDownLatch(10); ��latch.countDown(); ��latch.await();
 * 
 * һ������ͳ��t1,t2,t3�����߳�ִ���������ʱ����
 * 
 * �������⣺̽��1.
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
			 * ̽��1��
			 * ��������Ҫ�����̵߳����е����̶߳�ִ�����ˣ���ͳ���������߳�ִ�е���ִ��ʱ����
			 * ���ʣ���˭��ʲô������ʹ���̹߳������̱߳�����֮������˭�ܽ��份�ѣ�
			 * ��
			 * 	��û��ʹ��Object��wait()��������Ϊ������main()�����У�û��this����
			 * 	��û��ʹ��Condition��await()��������Ϊ���������û�õ�Condition�ࡣ
			 * 	�ۼ�ʹ���õ��٢��еķ�����ʹ���̹߳���Ҳû���ܹ������̻߳��ѡ�
			 * 	��ֻ����CountDownLatch��await()��������count==0ʱ����ǰ�̲߳Żᱻ���ѣ�����ǰ�߳̽�һֱ���ڹ���״̬��
			 */
			latch.await();
		} catch (InterruptedException e) {
		}

		long end = System.currentTimeMillis();

		System.out.println("�ķ�ʱ��Ϊ --------------- " + (end - start));
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
			latch.countDown();//����ִ�м�1.
		}
	}
}
