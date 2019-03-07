package thread.a02implementsrunnable;
/**
 * 存在线程安全问题的情况：
 * 
 * 利用多线程:实现Runnable接口的方式，模拟火车票窗口售票：3个窗口，总票数100张：
 * 此程序存在线程的安全问题：打印车票时，会出现重票、错票
 */
public class TestWindowRunnable002 {
	public static void main(String[] args) {
		Window1 w1 = new Window1();
		
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
class Window1 implements Runnable{
	private int ticket = 100;
	public void run(){
		for(;;){
			if(ticket > 0){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + ":" + ticket--);
			}else{
				break;
			}
		}
	}
}