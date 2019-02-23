package thread.a04communication;

/**
 * �����߳�֮������.
 * һ���߳��࣬�����������̣߳������һ�����������̼߳ף���1�ӵ�3����count++�����Σ����߳��ң���4�ӵ�6���̼߳ף���7�ӵ�9������ֱ��count=�û������Ǹ�����
 */
public class BankTest3 {
	public static void main(String[] args) throws InterruptedException {
		Sum sum = new Sum(13);
		SubThread thread1 = new SubThread(sum);
		SubThread thread2 = new SubThread(sum);

		thread1.setName("��");
		thread2.setName("��");

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
	private int count;// ��
	private int target;// �û���������
	
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
