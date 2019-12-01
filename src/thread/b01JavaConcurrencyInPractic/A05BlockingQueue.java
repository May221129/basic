package thread.b01JavaConcurrencyInPractic;

/**
 * 5.3章节： BlockingQueue阻塞队列：自己实现“生产者-消费者”模式。
 * 
 * 本类的实现存在一个问题：
 * 	在“work阻塞队列”还未满时，PutWork线程并非并发生产Work的；
 * 	在“work阻塞队列”中有Work时，TakeWork线程也并非并发地去执行Work。
 * 
 * 所以：如何在保证线程安全的前提下做到并发的呢？可以看BlockingQueue接口及其实现类是怎么做的。
 * 自己的解决方案：A05BlockingQueue2
 * 
 * @author May 2019年11月30日
 */
public class A05BlockingQueue {
	public static void main(String[] args) {
		Work work = new Work();

		PutWork putWork = new PutWork(work);
		Thread p1 = new Thread(putWork);
		Thread p2 = new Thread(putWork);
		Thread p3 = new Thread(putWork);
		p1.setName("p1");
		p2.setName("p2");
		p3.setName("p3");

		TakeWork takeWork = new TakeWork(work);
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

class PutWork implements Runnable {// 工作生产者
	private Work work;

	public PutWork(Work work) {
		this.work = work;
	}

	@Override
	public void run() {
		work.putWork();
	}
}

class TakeWork implements Runnable {// 工作执行者
	private Work work;

	public TakeWork(Work work) {
		this.work = work;
	}

	@Override
	public void run() {
		work.takeWork();
	}
}

class Work {// 工作队列
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