package thread.a05volatile;

/**
 * 《Java并发编程实战》第27页的代码——字段不可见。
 * 主线程和ReaderThread线程并发。
 * 
 * @author May
 * 2019年11月14日
 */
public class A02Novisible2 {
	private static boolean ready;
	private static int number;
	
	private static class ReaderThread extends Thread {
		@Override
		public void run() {
			while(!ready) {
				Thread.yield();//如果不把这行代码注释掉，则难以测试到字段不可见现象。
			}
			System.out.println("number == " + number);
		}
	}

	public static void main(String[] args) {
		new ReaderThread().start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		number = 42;
		ready = true;
	}
}
