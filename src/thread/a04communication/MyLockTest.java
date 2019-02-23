package thread.a04communication;

/**
 * 1.某个线程执行过程中去用它并未获得的锁，报异常：java.lang.IllegalMonitorStateException
 * 2.将锁抽象成Lock，执行到lock()方法时，只有一个线程会继续执行，其余线程都被挂起，直到那个线程执行notifyAll()，那些被挂起的线程才会被唤醒。
 */
public class MyLockTest {
	public static void main(String[] args) {
		Lock lock = new Lock();

		SubT st1 = new SubT(lock);
		SubT st2 = new SubT(lock);
		SubT st3 = new SubT(lock);
		SubT st4 = new SubT(lock);

		st1.setName("st1");
		st2.setName("st2");
		st3.setName("st3");
		st4.setName("st4");

		st1.start();
		st2.start();
		st3.start();
		st4.start();
	}
}

class SubT extends Thread {
	private Lock lock;

	public SubT(Lock lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " : hi~");
		
		lock.lock();

		System.out.println(Thread.currentThread().getName() + " : 我最厉害！");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		lock.unLock();
	}
}

class Lock {
	private int count;

	public synchronized void lock() {
		while (count >= 1) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count++;
	}

	public synchronized void unLock() {
		count--;
		this.notifyAll();
	}
}
