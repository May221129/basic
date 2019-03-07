package thread.a04communication;

import java.util.HashMap;

/**
 * 自己实现读写锁_2.0
 * 
 * 一、解决掉 MyReadLockTest1 中存在的问题。
 *  1.写线程优先：如果有写线程被挂起了在等待，那么写线程优先执行。
 * 	2.只记录了正在读或写的线程数量（即reader和writer），当一个没有拿锁的线程，直接调用unLock()，那么reader和writer数量都会出错，就可能造成读、写同时进行。
 * 	3.因为写线程有优先执行权，此时被挂起了很多的读线程，一旦某个写线程拿到了写锁，还没执行到unWriteLock()方法去释放写锁，就被卡死、停止执行了，
 * 		此时所有的读线程会给动不了。
 * 
 * 二、实现：
 * 	1.写线程优先：
 * 		1.1 现象：实际生产中，一般都是读线程多，写线程少。在读写锁问题中，执行了一个读线程之后，就会一直执行读线程，而写线程会被挂起。
 * 			所以看到的结果是————读线程都执行完了再执行写线程。 
 * 		1.2 处理方法： 
 * 			（1）给写线程设置高优先级别：wt1.setPriority(Thread.MAX_PRIORITY);
 * 			（2）用writeRequestMap这个HashMap来记录已经执行过writeLock()方法却没拿到写锁被挂起了的写线程，详见readLock()。
 * 	2.将执行过拿锁方法（即readLock()和writeLock()）的线程，分别放入readRequestMap和writeRequestMap中，
 * 		在释放锁的方法（即unReadLock()和unWriteLock()）中将readRequestMap或writeRequestMap中的记录删除。
 * 		注意：如果是可重入锁（即一个同步方法中调用了另一个同步方法，map中该线程的可以值就会累加），在释放锁时，是将该线程对应的value值减一。
 * 	3.给线程的等待设定时间，读锁被挂起超过10秒钟，就自动被唤醒。未实现。
 */
public class MyReadAndWriteLockTest2 {
	public static void main(String[] args) {
		ReadWriteLock2 lock = new ReadWriteLock2();

		RT2 rt1 = new RT2(lock);
		RT2 rt2 = new RT2(lock);
		RT2 rt3 = new RT2(lock);
		RT2 rt4 = new RT2(lock);
		RT2 rt5 = new RT2(lock);
		RT2 rt6 = new RT2(lock);
		WT2 wt1 = new WT2(lock);
		WT2 wt2 = new WT2(lock);

		rt1.setName("--> 读1");
		rt2.setName("--> 读2");
		rt3.setName("--> 读3");
		rt4.setName("--> 读4");
		rt5.setName("--> 读5");
		rt6.setName("--> 读6");
		wt1.setName("写1");
		wt2.setName("写2");

		wt1.setPriority(Thread.MAX_PRIORITY);
		wt2.setPriority(Thread.MAX_PRIORITY);

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

class RT2 extends Thread {// 读线程类
	private ReadWriteLock2 lock;

	public RT2(ReadWriteLock2 lock) {
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

		// 释放锁，并激活所有被挂起的线程：
		lock.unReadLock();
	}
}

class WT2 extends Thread {// 写线程类
	private ReadWriteLock2 lock;

	public WT2(ReadWriteLock2 lock) {
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

		// 释放锁，并激活所有被挂起的线程：
		lock.unWriteLock();
	}
}

/**
 * 读写锁2.0
 */
class ReadWriteLock2 {
	private int reader;//正在读的读线程个数
	private int writer;//正在写的写线程个数
	private HashMap<Thread, Integer> readRequestMap = new HashMap<>();// 用于记录被挂起的读线程对象，及其数量
	private HashMap<Thread, Integer> writeRequestMap = new HashMap<>();// 用于记录被挂起的写线程对象，及其数量

	public synchronized void readLock() throws InterruptedException {
		//将尝试拿读锁的读线程都放入map中，只有在此map中的读线程，才有资格执行unReadLock()方法去释放读锁。
		if (readRequestMap.containsKey(Thread.currentThread())) {//针对可重入锁
			Integer readerCount = readRequestMap.get(Thread.currentThread());
			readRequestMap.put(Thread.currentThread(), readerCount + 1);
		} else {
			readRequestMap.put(Thread.currentThread(), 1);
		}
		
		//实现了写线程优先：writeRequestMap.size() > 0
		while (writer == 1 || writeRequestMap.size() > 0) {// 有读线程在读ing~
			System.out.println(Thread.currentThread().getName() + " ===> 我睡了");
			this.wait();// 读线程要挂起
			System.out.println(Thread.currentThread().getName() + " =======> 被唤醒");
		}
		reader++;
		System.out.println(Thread.currentThread().getName() + " : 我在读");
	}
	
	public synchronized void unReadLock() {
		if(readRequestMap.containsKey(Thread.currentThread())){
			if(readRequestMap.get(Thread.currentThread()) == 1){
				reader--;
				readRequestMap.remove(Thread.currentThread());
			}else{//readRequestMap.get(Thread.currentThread()) > 1 ==> 可重入锁时，一个线程对象会不止一次拿到的锁
				reader--;
				Integer readerCount = readRequestMap.get(Thread.currentThread());
				readRequestMap.put(Thread.currentThread(), readerCount - 1);
			}
			System.out.println(Thread.currentThread().getName() + " --> 任务完成 《==========");
			this.notifyAll();
		}else{
			System.out.println("警告！当前的‘" + Thread.currentThread() + "'线程未曾执行过readLock()去拿写锁！");
		}
	}

	public synchronized void writeLock() throws InterruptedException {
		if (writeRequestMap.containsKey(Thread.currentThread())) {
			Integer writerCount = writeRequestMap.get(Thread.currentThread());
			writeRequestMap.put(Thread.currentThread(), writerCount + 1);
		} else {
			writeRequestMap.put(Thread.currentThread(), 1);
		}
		while (reader >= 1 || writer == 1) {// 有读线程在读ing~ 或有写线程在写ing~
			System.out.println(Thread.currentThread().getName() + " ===> 我睡了");
			this.wait();// 写线程挂起
			System.out.println(Thread.currentThread().getName() + " =======> 被唤醒");
		}
		writer++;
		System.out.println(Thread.currentThread().getName() + " --> 我在写");
	}

	public synchronized void unWriteLock() {
		if(writeRequestMap.containsKey(Thread.currentThread())){
			if(writeRequestMap.get(Thread.currentThread()) == 1){
				writer--;
				writeRequestMap.remove(Thread.currentThread());
			}else{ //writeRequestMap.get(Thread.currentThread()) > 1 ==》 可重入锁时，一个线程对象会不止一次拿到的锁
				writer--;
				Integer writeCount = writeRequestMap.get(Thread.currentThread());
				writeRequestMap.put(Thread.currentThread(), writeCount - 1);
			}
			System.out.println(Thread.currentThread().getName() + " --> 任务完成 《==========");
			this.notifyAll();
		}else{
			System.out.println("警告！当前的‘" + Thread.currentThread() + "'线程未曾执行过writeLock()去拿写锁！");
		}
	}
}
