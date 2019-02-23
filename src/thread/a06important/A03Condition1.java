package thread.a06important;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.知识点：
 * 		1.1 通过ReentrantLock的tryLock(long timeout, TimeUnitunit)方法，去判断线程是否拿到了锁，没拿到锁可以执行其他的逻辑。 
 * 		1.2 condition、await()、signal()的配套使用。
 * 2.测试： 在加线程要执行的
 * 		addNumber()方法中完成number++后，睡眠4秒。 
 * 		减线程要执行的subNumber()方法中，获取锁的最长等待时间是3秒。
 * 3.结果：
 * 		3.1 如果是加线程先执行了run()方法，则减线程等了3秒之后会执行else中的逻辑。
 * 		3.2 如果是减线程值执行了run()方法，则：
 * 			因为number的值为0，所以减线程先被挂起；
 * 			加线程执行完number++后睡眠4秒，再唤醒减线程；
 * 			减线程被唤醒后执行number--。
 */
public class A03Condition1 {
	public static void main(String[] args) {
		Sum sum = new Sum();
		SubThread st = new SubThread(sum);
		st.setName("..........减线程");
		st.start();
		
		AddThread at = new AddThread(sum);
		at.setName("加线程");
		at.start();
	}
}

class SubThread extends Thread {
	private Sum sum;

	public SubThread(Sum sum) {
		this.sum = sum;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " ： 我启动了");
		try {
			sum.subNumber();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class AddThread extends Thread {
	private Sum sum;

	public AddThread(Sum sum) {
		this.sum = sum;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " -->  我启动了");
		try {
			sum.addNumber();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Sum {
	private int number;
	private Lock lock = new ReentrantLock();
	private Condition addCondition = lock.newCondition();
	private Condition subCondition = lock.newCondition();

	public void addNumber() throws InterruptedException {
		lock.lock();
		try {
			while (number == 1) {
				System.out.println(Thread.currentThread().getName() + " ： 我睡了");
				addCondition.await();
				System.out.println(Thread.currentThread().getName() + " ： 我醒了");
			}
			number++;
			System.out.println("add() --> " + number);

			System.out.println(Thread.currentThread().getName() + " : 我先睡4秒,hahaha~~~");
			Thread.sleep(4000);

			subCondition.signal();
			System.out.println(Thread.currentThread().getName() + " : 我已经叫了减线程");
		} finally {
			lock.unlock();
		}
	}

	public void subNumber() throws InterruptedException {
		if (lock.tryLock(3, TimeUnit.SECONDS)) {
			try {
				while (number == 0) {
					System.out.println(Thread.currentThread().getName() + " ： 我睡了");
					subCondition.await();
					System.out.println(Thread.currentThread().getName() + " ： 我醒了");
				}
				number--;
				System.out.println("sub() --> " + number);
				addCondition.signal();
				System.out.println(Thread.currentThread().getName() + " : 我已经叫了加线程");
			} finally {
				lock.unlock();
			}
		} else {// 等待超时没拿到锁
			System.out.println(Thread.currentThread().getName() + " ： 已经等了3秒了还没拿到锁，我走了");
		}
	}
}
