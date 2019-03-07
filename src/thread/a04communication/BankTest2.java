package thread.a04communication;

/**
 * 两个线程之间的配合，一种线程做+1，一种线程做-1.money要一直维持在0~1.
 * 两个个线程类，分别创建一个线程。用到了wait()和notify()方法。
 */
public class BankTest2 {
	public static void main(String[] args) throws InterruptedException {
		Bank2 bank = new Bank2();
		
		RichMan2 rm = new RichMan2(bank);
		rm.setName("富人");

		PoorMan2 pm = new PoorMan2(bank);
		pm.setName("穷人");
		
		rm.start();
		pm.start();
	}
}

/**
 * 富人：取钱：-1
 */
class RichMan2 extends Thread {
	private Bank2 bank;
	public RichMan2(Bank2 bank) {
		this.bank = bank;
	}
	@Override
	public void run() {
		try {
			for(int i = 0; i < 10; i++){
				bank.getMoney();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/**
 * 穷人：存钱：+1
 */
class PoorMan2 extends Thread {
	private Bank2 bank;
	public PoorMan2(Bank2 bank) {
		this.bank = bank;
	}
	@Override
	public void run() {
		try {
			for(int i = 0; i < 10; i++){
				bank.addMoney();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Bank2 {
	private int money;
	/**
	 * 加1
	 */
	public synchronized void addMoney() throws InterruptedException {
		while (this.money == 1) {
			System.out.println(" --> 被挂起者：" + Thread.currentThread().getName());
			this.wait();
			System.out.println("被唤醒者 --> " + Thread.currentThread().getName());
		}
		money++;
		System.out.println(Thread.currentThread().getName() + " : " + this.money);
		this.notify();
	}
	/**
	 * 减1
	 */
	public synchronized void getMoney() throws InterruptedException {
		while (this.money == 0) {
			System.out.println(" --> 被挂起者：" + Thread.currentThread().getName());
			this.wait();
			System.out.println("被唤醒者 --> " + Thread.currentThread().getName());
		}
		money--;
		System.out.println(Thread.currentThread().getName() + " : " + this.money);
		this.notify();
	}
}
