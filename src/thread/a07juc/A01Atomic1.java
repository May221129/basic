package thread.a07juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子变量：java.util.concurrent.atomic包下的类。
 */
public class A01Atomic1 {
	public static void main(String[] args) {
		AtomicTest at = new AtomicTest();
		for(int i = 0; i < 10; i++){
			new Thread(at).start();
		}
	}
}

class AtomicTest implements Runnable{
	
	private AtomicInteger num = new AtomicInteger();//原子变量，相当于Integer，只是AtomicInteger用了CAS算法保证了线程安全性。
	
	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getNum());
	}
	
	public int getNum(){
		return num.getAndIncrement();//获取并自增
	}
}