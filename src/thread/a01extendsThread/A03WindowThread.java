package thread.a01extendsThread;
/**
 * 有线程安全问题的情况：
 * 
 * 利用多线程：继承Thread类的方式，模拟火车票窗口售票：3个窗口，总票数100张：
 * 此程序存在线程的安全问题：打印车票时，会出现重票、错票
 */
public class A03WindowThread {
	public static void main(String[] args) {
		
		Window w1 = new Window();
		Window w2 = new Window();
		Window w3 = new Window();
		
		w1.setName("窗口1");
		w2.setName("窗口2");
		w3.setName("窗口3");
		
		w1.start();
		w2.start();
		w3.start();
	}
}
class Window extends Thread{
	//ticket属性需要注意：总票数为100，所以100需要设置成static，成为类的属性。
	private static int ticket = 100;
	public void run(){
		for(;;){
			if(ticket > 0){
				try {
					Thread.currentThread().sleep(10);//该程序可能存在安全问题，有重票、错票，这里是要放大这个安全问题。
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "售票,票号为:" + ticket--);
			}else{
				break;
			}
		}
	}
}