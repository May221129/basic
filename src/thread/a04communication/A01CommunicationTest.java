package thread.a04communication;
/**
 * 线程的通讯：communication的wait(),notify(),notifyAll()方法的使用：
 * 
 * 使用两个线程打印1-100，线程1，线程2，交替打印：
 */
public class A01CommunicationTest {
	public static void main(String[] args) {
		PrintNumber p = new PrintNumber();
		
		Thread t1 = new Thread(p);
		Thread t2 = new Thread(p);
		
		t1.setName("A");
		t2.setName("B");
		
		t1.start();
		t2.start();
	}
}

class PrintNumber implements Runnable{
	private int number = 1;//注意，number1-100是共享资源。
	public void run(){
		for(;;){
			synchronized (this) {
				notify();
				if (number <= 100) {//怎么才能让两个线程一起只打印1-100？这里的if一定 要写！
					try {
						Thread.currentThread().sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ":" + number);
					number++;
				} else {
					break;
				}
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}