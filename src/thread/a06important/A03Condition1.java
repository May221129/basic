package thread.a06important;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.֪ʶ�㣺
 * 		1.1 ͨ��ReentrantLock��tryLock(long timeout, TimeUnitunit)������ȥ�ж��߳��Ƿ��õ�������û�õ�������ִ���������߼��� 
 * 		1.2 condition��await()��signal()������ʹ�á�
 * 2.���ԣ� �ڼ��߳�Ҫִ�е�
 * 		addNumber()���������number++��˯��4�롣 
 * 		���߳�Ҫִ�е�subNumber()�����У���ȡ������ȴ�ʱ����3�롣
 * 3.�����
 * 		3.1 ����Ǽ��߳���ִ����run()����������̵߳���3��֮���ִ��else�е��߼���
 * 		3.2 ����Ǽ��߳�ִֵ����run()��������
 * 			��Ϊnumber��ֵΪ0�����Լ��߳��ȱ�����
 * 			���߳�ִ����number++��˯��4�룬�ٻ��Ѽ��̣߳�
 * 			���̱߳����Ѻ�ִ��number--��
 */
public class A03Condition1 {
	public static void main(String[] args) {
		Sum sum = new Sum();
		SubThread st = new SubThread(sum);
		st.setName("..........���߳�");
		st.start();
		
		AddThread at = new AddThread(sum);
		at.setName("���߳�");
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
		System.out.println(Thread.currentThread().getName() + " �� ��������");
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
		System.out.println(Thread.currentThread().getName() + " -->  ��������");
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
				System.out.println(Thread.currentThread().getName() + " �� ��˯��");
				addCondition.await();
				System.out.println(Thread.currentThread().getName() + " �� ������");
			}
			number++;
			System.out.println("add() --> " + number);

			System.out.println(Thread.currentThread().getName() + " : ����˯4��,hahaha~~~");
			Thread.sleep(4000);

			subCondition.signal();
			System.out.println(Thread.currentThread().getName() + " : ���Ѿ����˼��߳�");
		} finally {
			lock.unlock();
		}
	}

	public void subNumber() throws InterruptedException {
		if (lock.tryLock(3, TimeUnit.SECONDS)) {
			try {
				while (number == 0) {
					System.out.println(Thread.currentThread().getName() + " �� ��˯��");
					subCondition.await();
					System.out.println(Thread.currentThread().getName() + " �� ������");
				}
				number--;
				System.out.println("sub() --> " + number);
				addCondition.signal();
				System.out.println(Thread.currentThread().getName() + " : ���Ѿ����˼��߳�");
			} finally {
				lock.unlock();
			}
		} else {// �ȴ���ʱû�õ���
			System.out.println(Thread.currentThread().getName() + " �� �Ѿ�����3���˻�û�õ�����������");
		}
	}
}
