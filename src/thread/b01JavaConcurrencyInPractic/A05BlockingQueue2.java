package thread.b01JavaConcurrencyInPractic;

/**
 * 5.3章节： BlockingQueue阻塞队列：自己实现“生产者-消费者”模式。
 * 
 * 解决A05BlockingQueue类中“在保证线程安全的前提下做到并发”的问题。
 * 
 * @author May 2019年11月30日
 */
public class A05BlockingQueue2 {
	public static void main(String[] args) {
		Work2 work = new Work2();

		PutWork2 putWork = new PutWork2(work);
		Thread p1 = new Thread(putWork);
		Thread p2 = new Thread(putWork);
		Thread p3 = new Thread(putWork);
		p1.setName("p1");
		p2.setName("p2");
		p3.setName("p3");

		TakeWork2 takeWork = new TakeWork2(work);
		Thread t1 = new Thread(takeWork);
		Thread t2 = new Thread(takeWork);
		Thread t3 = new Thread(takeWork);
		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");

		p1.start();
		p2.start();
		p3.start();
		t1.start();
		t2.start();
		t3.start();
	}
}

class PutWork2 implements Runnable {// 工作生产者
	private Work2 work;

	public PutWork2(Work2 work) {
		this.work = work;
	}

	@Override
	public void run() {
		work.putWork();
	}
}

class TakeWork2 implements Runnable {// 工作执行者
	private Work2 work;

	public TakeWork2(Work2 work) {
		this.work = work;
	}

	@Override
	public void run() {
		work.takeWork();
	}
}

class Work2 {// 工作队列
	private int workNumber;

	/**
	 * 产生工作
	 */
	public synchronized void putWork() {
		while(true) {
			notifyAll();
			if (workNumber < 10) {
				System.out.println(Thread.currentThread().getName() + " 产生第 " + (++workNumber) + " 个工作。");
			}
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 执行工作
	 */
	public synchronized void takeWork() {
		while(true) {
			notifyAll();
			if (workNumber >= 1) {
				System.out.println(Thread.currentThread().getName() + " 执行第 " + (workNumber--) + " 个工作。");
			}
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}