package thread.a01extendsthread;
/**
 * 多线程：Thread方法的调用：
 */
public class TestThreadMethod04 {
	public static void main(String[] args) {
		SubThread1 st = new SubThread1();
		st.start();
		st.setPriority(Thread.MAX_PRIORITY);//也可以直接写成st.setPriority(10);
		st.setName("子线程");
		Thread.currentThread().setName("………………主线程");
		for(int i = 1; i <= 100; i++){
			System.out.println( Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + " : " + i);
//			if(i % 10 == 0){
//				Thread.currentThread().yield();//调用此方法的线程释放当前CPU的执行权。
//			}
//			if(i == 50){
//				try {
//					st.join();
////join():在A线程中调用B线程的join()方法，表示：当执行到此方法时，A线程停止执行，直至B线程执行完，A线程才接着执行join()之后未执行的代码。
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
		}
		System.out.println(st.isAlive());//判断当前线程是否存活。
	}
}

class SubThread1 extends Thread{
	public void run(){
		for(int i = 1; i <= 100; i++){
//			try {
//				Thread.currentThread().sleep(50);//显式的让当前线程睡眠L毫秒
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			System.out.println( Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ": " + i);
		}
	}
}
