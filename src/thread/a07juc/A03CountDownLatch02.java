package thread.a07juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * �������̸߳������0~1000���ܺͣ�t1�������0~3000���ܺͣ�t2�������0~5000���ܺͣ�Ȼ����������������͡�
 * ʵ�֣�1.��CountDownLatch��ʵ�֣������õ������ַ�ʽ���� 2.��Callable��ʵ�֣���A04Callable01����
 * ����ʵ�ַ�ʽ�ıȽϣ�
 * 	��CountDownLatch�Ƚ��鷳������Ҫ�������sum�����ո����̼߳���ĺͣ��һ�Ҫ����sum���̰߳�ȫ����(ԭ�ӱ���)��
 * 	��Callable�Ƚϼ򵥣���Ϊ������ֱ�ӷ��ؽ����
 */
public class A03CountDownLatch02 {
	public static void main(String[] args) {
		GetSum getSum = new GetSum();
		CountDownLatch latch = new CountDownLatch(2);
		
		Thread2 thread2 = new Thread2(getSum, latch);
		new Thread(thread2).start();
		
		Thread3 thread3 = new Thread3(getSum, latch);
		new Thread(thread3).start();
		
		//�����������̹߳���ȥ�ȴ��������̼߳����ꣿ������ڻ�ȡsumֵ֮�󣬻�������
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		AtomicInteger sum1 = getSum.getSum1();
		AtomicInteger sum2 = getSum.getSum22();
		AtomicInteger sum3 = getSum.getSum33();	
		//��������������̹߳��𣬾�ʧȥ�˱��������壬��Ϊ�п������̻߳�û�����꣬Ȼ�����߳��õ��Ĳ������ս����sum������ˡ�
//		try {
//			latch.await();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		System.out.println("sum1 = " + sum1);
		System.out.println("sum2 = " + sum2);
		System.out.println("sum3 = " + sum3);
		System.out.println("�ܺ� = " + (sum1.get() + sum2.get() + sum3.get()));
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
	private AtomicInteger sum1 = new AtomicInteger();//ԭ�ӱ������൱��Integer��ֻ��AtomicInteger����CAS�㷨��֤���̰߳�ȫ�ԡ�
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