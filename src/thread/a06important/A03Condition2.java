package thread.a06important;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.知识点：condition、await()、signal()的配套使用。
 * 2.任务：让数字在0和1跳转，跳转10次。
 */
public class A03Condition2 {
	public static void main(String[] args) {
		Sum2 sum = new Sum2();
		AddThread2 at = new AddThread2(sum);
		SubThread2 st = new SubThread2(sum);

		at.setName("加线程");
		st.setName("==>减线程");

		at.start();
		st.start();
	}
}

class SubThread2 extends Thread {
	private Sum2 sum;

	public SubThread2(Sum2 sum) {
		this.sum = sum;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " ： 我启动了");
		try {
			for (int i = 0; i < 10; i++) {
				sum.subNumber();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class AddThread2 extends Thread {
	private Sum2 sum;

	public AddThread2(Sum2 sum) {
		this.sum = sum;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " -->  我启动了");
		try {
			for (int i = 0; i < 10; i++) {
				sum.addNumber();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Sum2 {
	private int number;
	private Lock lock = new ReentrantLock();
	private Condition addCondition = lock.newCondition();
	private Condition subCondition = lock.newCondition();

	public void addNumber() throws InterruptedException {
		lock.lock();
		try {
			while (number == 1) {
//				System.out.println(Thread.currentThread().getName() + " ： 我睡了");
				addCondition.await();
//				System.out.println(Thread.currentThread().getName() + " ： 我醒了");
			}
			number++;
			System.out.println("add() --> " + number);
			subCondition.signal();
		} finally {
			lock.unlock();
		}
	}

	public void subNumber() throws InterruptedException {
		lock.lock();
		try {
			while (number == 0) {
//				System.out.println(Thread.currentThread().getName() + " ： 我睡了");
				subCondition.await();
//				System.out.println(Thread.currentThread().getName() + " ： 我醒了");
			}
			number--;
			System.out.println("sub() --> " + number);
			addCondition.signal();
		} finally {
			lock.unlock();
		}
	}
}
