package thread.a04communication;

/**
 * �����߳�֮�����ϣ�һ���߳���+1��һ���߳���-1.moneyҪһֱά����0~1.
 * �����߳��࣬�ֱ𴴽�5���̡߳��õ���wait()��notifyAll()������
 */
public class BankTest {
	public static void main(String[] args) {
		Bank bank = new Bank();

		RichMan rm1 = new RichMan(bank);
		RichMan rm2 = new RichMan(bank);
		RichMan rm3 = new RichMan(bank);
		RichMan rm4 = new RichMan(bank);
		RichMan rm5 = new RichMan(bank);
		rm1.setName("����1");
		rm2.setName("����2");
		rm3.setName("����3");
		rm4.setName("����4");
		rm5.setName("����5");

		PoorMan pm1 = new PoorMan(bank);
		PoorMan pm2 = new PoorMan(bank);
		PoorMan pm3 = new PoorMan(bank);
		PoorMan pm4 = new PoorMan(bank);
		PoorMan pm5 = new PoorMan(bank);
		pm1.setName("����1");
		pm2.setName("����2");
		pm3.setName("����3");
		pm4.setName("����4");
		pm5.setName("����5");
		
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
 * ���ˣ�ȡǮ
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
 * ���ˣ���Ǯ
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
	 * ��1
	 */
	public synchronized void addMoney() throws InterruptedException {
		while (this.money == 1) {
			System.out.println(" --> �������ߣ�" + Thread.currentThread().getName());
			this.wait();
			System.out.println("�������� --> " + Thread.currentThread().getName());
		}
		money++;
		System.out.println(Thread.currentThread().getName() + " : " + this.money);
		this.notifyAll();
	}
	
	/**
	 * ��1
	 */
	public synchronized void getMoney() throws InterruptedException {
		while (this.money == 0) {
			System.out.println(" --> �������ߣ�" + Thread.currentThread().getName());
			this.wait();
			System.out.println("�������� --> " + Thread.currentThread().getName());
		}
		money--;
		System.out.println(Thread.currentThread().getName() + " : " + this.money);
		this.notifyAll();
	}
}
