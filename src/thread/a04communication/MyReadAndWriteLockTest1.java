package thread.a04communication;

/**
 * 自己实现读写锁_1.0
 * 
 * 一、要求： 
 *  1.读锁时共享锁。加了读锁后，一个线程在读的时候，其他的读线程也能进入一起读，但写线程执行到读锁时会被挂起；
 * 	2.写锁是排它锁。加了写锁后，当一个写线程在写时，其他线程都执行到写锁时都会被挂起； 
 * 
 * 二、实现：
 * 	1.关键点：synchronized,wait(),notify()/notifyAll(),while。
 * 	2.锁被抽象出来了，读锁和写锁之所以能锁住线程，靠的是判断reader和writer这两个变量，见readLock()和writeLock()。
 * 	3.被唤醒的线程需要通过while重新做判断，因为：被挂起的线程，被唤醒是后，从当初被挂起的wait()方法之后的代码继续执行下去的。 
 * 	4.某个线程执行过程中去用它并未获得的锁，报异常：java.lang.IllegalMonitorStateException。
 * 
 * 三、问题：
 * 	1.写线程优先：如果有写线程被挂起了在等待，那么写线程优先执行。
 * 	2.只记录了正在读或写的线程数量（即reader和writer），当一个没有拿锁的线程，直接调用unLock()，那么reader和writer数量都会出错，就可能造成读、写同时进行。
 * 	3.因为写线程有优先执行权，此时被挂起了很多的读线程，一旦某个写线程拿到了写锁，还没执行到unWriteLock()方法去释放写锁，就被卡死、停止执行了，
 * 		此时所有的读线程会给动不了。
 */
public class MyReadAndWriteLockTest1 {
	public static void main(String[] args) {
		ReadWriteLock1 lock = new ReadWriteLock1();
		RT1 rt1 = new RT1(lock);
		RT1 rt2 = new RT1(lock);
		RT1 rt3 = new RT1(lock);
		RT1 rt4 = new RT1(lock);
		RT1 rt5 = new RT1(lock);
		RT1 rt6 = new RT1(lock);
		
		WT1 wt1 = new WT1(lock);
		WT1 wt2 = new WT1(lock);

		rt1.setName("--> 读1");
		rt2.setName("--> 读2");
		rt3.setName("--> 读3");
		rt4.setName("--> 读4");
		rt5.setName("--> 读5");
		rt6.setName("--> 读6");
		
		wt1.setName("写1");
		wt2.setName("写2");

		rt1.start();
		rt2.start();
		rt3.start();
		rt4.start();
		rt5.start();
		rt6.start();
		wt1.start();
		wt2.start();

	}
}

class RT1 extends Thread {// 读线程类
	private ReadWriteLock1 lock;

	public RT1(ReadWriteLock1 lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " : 开启");

		try {
			lock.readLock();// 加读锁
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 激活所有被挂起的线程：
		lock.unReadLock();
	}
}

class WT1 extends Thread {// 写线程类
	private ReadWriteLock1 lock;

	public WT1(ReadWriteLock1 lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " --> 开启");

		try {
			lock.writeLock();// 加写锁
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 激活所有被挂起的线程：
		lock.unWriteLock();
	}
}

/**
 * 读写锁1.0
 */
class ReadWriteLock1 {
	private int reader;//记录正在读的读线程数量
	private int writer;//记录正在写的写线程数量

	public synchronized void readLock() throws InterruptedException {
		while (writer == 1) {// 有读线程在读ing~
			System.out.println(Thread.currentThread().getName() + " ===> 我睡了");
			this.wait();// 读线程要挂起
			System.out.println(Thread.currentThread().getName() + " =======> 被唤醒");
		}
		reader++;
		System.out.println(Thread.currentThread().getName() + " : 我在读");
	}

	public synchronized void unReadLock() {
		reader--;
		System.out.println(Thread.currentThread().getName() + " --> 任务完成 《==========");
		this.notifyAll();
	}

	public synchronized void writeLock() throws InterruptedException {
		while (reader >= 1 || writer == 1) {// 有读线程在读ing~ 或有写线程在写ing~
			System.out.println(Thread.currentThread().getName() + " ===> 我睡了");
			this.wait();// 写线程挂起
			System.out.println(Thread.currentThread().getName() + " =======> 被唤醒");
		}
		writer++;
		System.out.println(Thread.currentThread().getName() + " --> 我在写");
	}

	public synchronized void unWriteLock() {
		writer--;
		System.out.println(Thread.currentThread().getName() + " --> 任务完成 《==========");
		this.notifyAll();
	}
}
