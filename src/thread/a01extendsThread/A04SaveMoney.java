package thread.a01extendsThread;

/**
 * 银行有一个账户，有两个储户分别向同一个账户存3000元， 每次存1000元，存3次，存完打印账户余额.
 *  拓展：实现二者交替打印：使用线程的通信
 * 
 * @author May 2019年10月29日
 */
public class A04SaveMoney {
	public static void main(String[] args) {
		Man zhang = new Man();
		Man li = new Man();

		zhang.setName("张");
		li.setName("李");

		zhang.start();
		li.start();
	}
}

class Man extends Thread {
	private static int money = 0;
	
	@Override
	public void run() {
		saveMoney();
	}
	
	/**
	 * 用哪个对象来充当锁，就需要哪个对象来唤醒在等待该对象的锁的线程：
	 * synchronized(Man.class) {
	 * 	Man.class.notifyAll();
	 * 	Man.class.wait();
	 * }
	 */
	public void saveMoney() {
		synchronized(Man.class) {
			for(int i = 0; i < 3; i++) {
				// 唤醒方法到底由谁来调用？==》要唤醒哪个对象上等待锁的线程，就由哪个对象来调用。
				//notifyAll()方法的放置位置也很重要，线程被唤醒后，从wait()代码后继续往下执行。
				Man.class.notifyAll();
				money += 1000;
				System.out.println(currentThread().getName() + " 成功存款 1000 元，当前账户余额为 " + money + " 元。");
				
				try {
					Man.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}