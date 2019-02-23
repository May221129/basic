package thread.a04communication;

/**
 * 两个线程之间的配合.
 * 一个线程类，创建出两个线程，随机给一个正整数，线程甲：从1加到3（“count++”三次），线程乙：从4加到6，线程甲：从7加到9，……直到count=用户给的那个数。
 */
public class BankTest3 {
	public static void main(String[] args) throws InterruptedException {
		Sum sum = new Sum(13);
		SubThread thread1 = new SubThread(sum);
		SubThread thread2 = new SubThread(sum);

		thread1.setName("甲");
		thread2.setName("乙");

		thread1.start();
		thread2.start();
	}
}

class SubThread extends Thread {
	private Sum sum;

	public SubThread(Sum sum) {
		this.sum = sum;
	}

	@Override
	public void run() {
		try {
			sum.setCount();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Sum {
	private int count;// 和
	private int target;// 用户给的数字
	
	public Sum(int target){
		this.target = target;
	}
	
	public synchronized void setCount() throws InterruptedException {
		while(count < target){
			for (int i = 0; i < 3 && count < target; i++) {
				count++;
				System.out.println(Thread.currentThread().getName() + " : " + count);
			}
			this.notify();
			this.wait();
		}
		this.notify();
	}
}
