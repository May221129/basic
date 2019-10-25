package thread.a02implementsRunnable;
/**
 * 多线程的练习题：
 * 
 * 银行有一个账户，有两个储户分别向同一个账户存3000元，
 * 每次存1000元，存3次，存完打印账户余额：
 * 拓展：实现二者交替打印：使用线程的通信
 */
public class TestAccount05 {
	public static void main(String[] args) {
		Account a = new Account();
		
		Thread t1 = new Thread(a);
		Thread t2 = new Thread(a);
		
		t1.setName("A");
		t2.setName("B");
		
		t1.start();
		t2.start();
	}
}
class Account implements Runnable{
	private double balance;
	public void run(){
		for(int i = 0; i < 3; i++){
			synchronized(this){
				notify();//线程的通信的方法
				balance += 1000;
				try {
					Thread.currentThread().sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + ":" + balance);
				try {
					wait();//线程的通信的方法
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}