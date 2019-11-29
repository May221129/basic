package thread.b01JavaConcurrencyInPractic;

import java.util.Vector;

/**
 * 第4章中，有关“完全由线程安全类构成的程序并不一定就是线程安全的，而在线程安全类中也可以包含非线程安全的类。”的测试
 * 如果对线程安全的类做一些复合操作（非原子操作），就有可能出现线程安全问题。
 * 
 * @author May
 * 2019年11月27日
 */
public class A04InstanceConfinement2 {
	public static void main(String[] args) {
		SThread sThread = new SThread();
		Thread t1 = new Thread(sThread);
		Thread t2 = new Thread(sThread);
		Thread t3 = new Thread(sThread);
		
		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			Thread.sleep(50);//让主线程先睡一下，确保上面三个子线程都执行完了。
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sThread.printElement();
	}
}

class SThread implements Runnable{
	private Vector<Integer> vector = new Vector<>();//Vector是线程安全的ArrayList
	
	//非原子性操作，有线程安全问题：
	@Override
	public void run() {
		if(!vector.contains(10)) {
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			vector.add(10);//vector是线程安全的集合，但在该run()方法中，却是“先检查后执行”的复合操作，是非原子操作，所以没有同步的情况下会出现线程安全问题。
		}
	}
	
	//做了同步，保证了线程安全性：
//	@Override
//	public void run() {
//		synchronized(this) {
//			if(!vector.contains(10)) {
//				try {
//					Thread.currentThread().sleep(10);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				vector.add(10);
//			}
//		}
//	}
	
	public void printElement() {
		for(Integer element : vector) {
			System.out.println(element);
		}
	}
}