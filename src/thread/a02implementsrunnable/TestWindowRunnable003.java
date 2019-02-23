package thread.a02implementsrunnable;

/**
 * 线程安全的情况：
 * 利用多线程:实现Runnable接口的方式，模拟火车票窗口售票：3个窗口，总票数100张：
 * 此程序存在线程的安全问题：打印车票时，会出现重票、错票，在这里进行处理，利用同步代码块synchronized在这里进行处理：
 */
public class TestWindowRunnable003 {
	public static void main(String[] args) {
		Window2 w1 = new Window2();
		
		Thread t1 = new Thread(w1);
		Thread t2 = new Thread(w1);
		Thread t3 = new Thread(w1);
		
		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");
		
		t1.start();
		t2.start();
		t3.start();
	}
}
class Window2 implements Runnable{
//	Object obj = new Object();//这个类必须是成员变量，不能是局部变量。
	private int ticket = 100;
	public void run(){
//		Object obj = new Object();//这个类不能是局部变量，不能放这儿。
		for(;;){
//			synchronized(obj){//同步监视器可以是任何一个类来充当；要求：所有的线程必须共用同一把锁！
			synchronized(this){//this为当前对象
				if(ticket > 0){
					try {
						Thread.currentThread().sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ":" + ticket--);
				}
			}
		}
	}
}