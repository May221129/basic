package thread.a04communication;

/**
 * �����߳�֮�����ϣ�һ���߳���+1��һ���߳���-1.moneyҪһֱά����0~1.
 * �������߳��࣬�ֱ𴴽�һ���̡߳��õ���wait()��notify()������
 */
public class BankTest2 {
	public static void main(String[] args) throws InterruptedException {
		Bank2 bank = new Bank2();
		
		RichMan2 rm = new RichMan2(bank);
		rm.setName("����");

		PoorMan2 pm = new PoorMan2(bank);
		pm.setName("����");
		
		rm.start();
		pm.start();
	}
}

/**
 * ���ˣ�ȡǮ��-1
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
 * ���ˣ���Ǯ��+1
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
		this.notify();
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
		this.notify();
	}
}
