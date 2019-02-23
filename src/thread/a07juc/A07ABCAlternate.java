package thread.a07juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *	1.�����̰߳������ӡ������дһ�����򣬿��� 3���̣߳��������̵߳� ID�ֱ�Ϊ A��B��C��
 *		ÿ���߳̽��Լ��� ID����Ļ�ϴ�ӡ 10�飬Ҫ������Ľ�����밴˳����ʾ��
 *		�磺ABCABCABC���� ���εݹ顣
 *	2.���⣺
 *		2.1 ��� A��B��C��awaitס�ˣ�û�˻��ѣ�����û��ͣ����printA(int printNumb)������
 *		2.2 ���Ҫ�����ӡ10�Σ����������������ƱȽϺã�run�����С�
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
		
		//���ｨ���������ڲ��࣬��ȻҪ��������Runnable��ʵ����̫�鷳�ˡ�
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
	private int number = 1;//�Á�����ĸ��߳̿���ִ�д�ӡ����
	private Lock lock = new ReentrantLock();
	private Condition _a = lock.newCondition();
	private Condition _b = lock.newCondition();
	private Condition _c = lock.newCondition();
	
	/**
	 * printNumb���ڼ��ֵĴ�ӡ
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
	//�Ա�һ����һ��ʼ��ʵ�ַ�ʽ��
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