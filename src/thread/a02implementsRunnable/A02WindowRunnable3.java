package thread.a02implementsRunnable;
/**
 * 线程安全的情况：
 * 
 * 利用多线程:实现Runnable接口的方式，模拟火车票窗口售票：3个窗口，总票数100张：
 * 此程序存在线程的安全问题：打印车票时，会出现重票、错票，在这里进行处理，利用synchronized同步方法在这里进行处理：
 */
public class A02WindowRunnable3 {
	public static void main(String[] args) {
		Window3 w1 = new Window3();
		
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
class Window3 implements Runnable{
	private int ticket = 100;
	public void run(){
		for(;;){
			show();
		}
	}
	public synchronized void show(){
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