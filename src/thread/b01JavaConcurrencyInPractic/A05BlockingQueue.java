package thread.b01JavaConcurrencyInPractic;

/**
 * 5.3�½ڣ� BlockingQueue�������У��Լ�ʵ�֡�������-�����ߡ�ģʽ��
 * 
 * �����ʵ�ִ���һ�����⣺
 * 	�ڡ�work�������С���δ��ʱ��PutWork�̲߳��ǲ�������Work�ģ�
 * 	�ڡ�work�������С�����Workʱ��TakeWork�߳�Ҳ���ǲ�����ȥִ��Work��
 * 
 * ���ԣ�����ڱ�֤�̰߳�ȫ��ǰ���������������أ����Կ�BlockingQueue�ӿڼ���ʵ��������ô���ġ�
 * �Լ��Ľ��������A05BlockingQueue2
 * 
 * @author May 2019��11��30��
 */
public class A05BlockingQueue {
	public static void main(String[] args) {
		Work work = new Work();

		PutWork putWork = new PutWork(work);
		Thread p1 = new Thread(putWork);
		Thread p2 = new Thread(putWork);
		Thread p3 = new Thread(putWork);
		p1.setName("p1");
		p2.setName("p2");
		p3.setName("p3");

		TakeWork takeWork = new TakeWork(work);
		Thread t1 = new Thread(takeWork);
		Thread t2 = new Thread(takeWork);
		Thread t3 = new Thread(takeWork);
		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");

		p1.start();
		p2.start();
		p3.start();
		t1.start();
		t2.start();
		t3.start();
	}
}

class PutWork implements Runnable {// ����������
	private Work work;

	public PutWork(Work work) {
		this.work = work;
	}

	@Override
	public void run() {
		work.putWork();
	}
}

class TakeWork implements Runnable {// ����ִ����
	private Work work;

	public TakeWork(Work work) {
		this.work = work;
	}

	@Override
	public void run() {
		work.takeWork();
	}
}

class Work {// ��������
	private int workNumber;

	/**
	 * ��������
	 */
	public synchronized void putWork() {
		while(true) {
			notifyAll();
			if (workNumber < 10) {
				System.out.println(Thread.currentThread().getName() + " ������ " + (++workNumber) + " ��������");
			}
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ִ�й���
	 */
	public synchronized void takeWork() {
		while(true) {
			notifyAll();
			if (workNumber >= 1) {
				System.out.println(Thread.currentThread().getName() + " ִ�е� " + (workNumber--) + " ��������");
			}
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}