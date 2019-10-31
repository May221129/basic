package thread.a01extendsThread;

/**
 * synchronized同步方法中，this表示谁的锁：在使用继承Thread实现多线程时，容易出错！
 * 
 * 利用多线程：继承Thread类的方式，模拟火车票窗口售票：3个窗口，总票数100张： 此程序存在线程的安全问题：打印车票时，会出现重票、错票，
 * 这里不能用synchronized同步方法进行处理，因为同步方法默认的锁是this，
 * 而用继承Thread类创建多线程的方式，在使用时是有多个对象的，this表示的锁就不是唯一的，所以还是会有线程安全问题。
 */
public class A03WindowThread3 {
	public static void main(String[] args) {

		Window3 w1 = new Window3();
		Window3 w2 = new Window3();
		Window3 w3 = new Window3();

		w1.setName("窗口1");
		w2.setName("窗口2");
		w3.setName("窗口3");

		w1.start();
		w2.start();
		w3.start();
	}
}

class Window3 extends Thread {
	private static int ticket = 100;// 共享资源==>会有线程安全问题
	// private int ticket = 100;//数据各自对象的资源==>不会有线程安全问题
	static Object obj = new Object();

	public void run() {
		while (true) {
			show();
		}
	}
	/**
	 * 会有线程安全问题
	 */
	public synchronized void show() {// 这里默认的锁是this
		if (ticket > 0) {
			try {
				Thread.currentThread().sleep(100);// 该程序可能存在安全问题，有重票、错票，这里是要放大这个安全问题。
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "售票,票号为:" + ticket--);
		} 
	}
	
	/**
	 * 改变一下synchronized的所属对象和锁的范围，就能有效控制线程安全问题。
	 */
	public void show2() {// 这里默认的锁是this
		synchronized (obj) {
			if (ticket > 0) {
				try {
					Thread.currentThread().sleep(100);// 该程序可能存在安全问题，有重票、错票，这里是要放大这个安全问题。
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "售票,票号为:" + ticket--);
			} 
		}
	}
}