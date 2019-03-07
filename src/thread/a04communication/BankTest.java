package thread.a04communication;

/**
 * 两个线程之间的配合，一种线程做+1，一种线程做-1.money要一直维持在0~1.
 * 两个线程类，分别创建5个线程。用到了wait()和notifyAll()方法。
 */
public class BankTest {
	public static void main(String[] args) {
		Bank bank = new Bank();

		RichMan rm1 = new RichMan(bank);
		RichMan rm2 = new RichMan(bank);
		RichMan rm3 = new RichMan(bank);
		RichMan rm4 = new RichMan(bank);
		RichMan rm5 = new RichMan(bank);
		rm1.setName("富人1");
		rm2.setName("富人2");
		rm3.setName("富人3");
		rm4.setName("富人4");
		rm5.setName("富人5");

		PoorMan pm1 = new PoorMan(bank);
		PoorMan pm2 = new PoorMan(bank);
		PoorMan pm3 = new PoorMan(bank);
		PoorMan pm4 = new PoorMan(bank);
		PoorMan pm5 = new PoorMan(bank);
		pm1.setName("穷人1");
		pm2.setName("穷人2");
		pm3.setName("穷人3");
		pm4.setName("穷人4");
		pm5.setName("穷人5");
		
		rm1.start();
		rm2.start();
		rm3.start();
		rm4.start();
		rm5.start();
		
		pm1.start();
		pm2.start();
		pm3.start();
		pm4.start();
		pm5.start();
	}
}

/**
 * 富人：取钱
 */
class RichMan extends Thread {
	private Bank bank;
	public RichMan(Bank bank) {
		this.bank = bank;
	}
	@Override
	public void run() {
		try {
			bank.getMoney();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/**
 * 穷人：存钱
 */
class PoorMan extends Thread {
	private Bank bank;

	public PoorMan(Bank bank) {
		this.bank = bank;
	}

	@Override
	public void run() {
		try {
			bank.addMoney();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Bank {
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
		this.notifyAll();
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
		this.notifyAll();
	}
}
