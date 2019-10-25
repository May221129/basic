package thread.a01extendsThread;
/**
 * 没有线程安全问题的情况：
 * 
 * 利用多线程：继承Thread类的方式，模拟火车票窗口售票：3个窗口，总票数100张：
 * 此程序存在线程的安全问题：打印车票时，会出现重票、错票，利用同步代码块synchronized在这里进行处理：
 */
public class TestWindowThread006 {
	public static void main(String[] args) {
		
		Window2 w1 = new Window2();
		Window2 w2 = new Window2();
		Window2 w3 = new Window2();
		
		w1.setName("窗口1");
		w2.setName("窗口2");
		w3.setName("窗口3");
		
		w1.start();
		w2.start();
		w3.start();
	}
}
class Window2 extends Thread{
	private static int ticket = 100;
	static Object obj = new Object();
	public void run(){
		while(true){
//			synchronized(this){//在这里，this表示w1,w2,w3,所以这里不能写this！
			synchronized(obj){
				if(ticket > 0){
					try {
						Thread.currentThread().sleep(10);//该程序可能存在安全问题，有重票、错票，这里是要放大这个安全问题。
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "售票,票号为:" + ticket--);
				}
			}
		}
	}
}