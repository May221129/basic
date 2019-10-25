package thread.a04communication;

/**
 * �Լ�ʵ�ֶ�д��_1.0
 * 
 * һ��Ҫ�� 
 *  1.����ʱ�����������˶�����һ���߳��ڶ���ʱ�������Ķ��߳�Ҳ�ܽ���һ�������д�߳�ִ�е�����ʱ�ᱻ����
 * 	2.д����������������д���󣬵�һ��д�߳���дʱ�������̶߳�ִ�е�д��ʱ���ᱻ���� 
 * 
 * ����ʵ�֣�
 * 	1.�ؼ��㣺synchronized,wait(),notify()/notifyAll(),while��
 * 	2.������������ˣ�������д��֮��������ס�̣߳��������ж�reader��writer��������������readLock()��writeLock()��
 * 	3.�����ѵ��߳���Ҫͨ��while�������жϣ���Ϊ����������̣߳��������Ǻ󣬴ӵ����������wait()����֮��Ĵ������ִ����ȥ�ġ� 
 * 	4.ĳ���߳�ִ�й�����ȥ������δ��õ��������쳣��java.lang.IllegalMonitorStateException��
 * 
 * �������⣺
 * 	1.д�߳����ȣ������д�̱߳��������ڵȴ�����ôд�߳�����ִ�С�
 * 	2.ֻ��¼�����ڶ���д���߳���������reader��writer������һ��û���������̣߳�ֱ�ӵ���unLock()����ôreader��writer������������Ϳ�����ɶ���дͬʱ���С�
 * 	3.��Ϊд�߳�������ִ��Ȩ����ʱ�������˺ܶ�Ķ��̣߳�һ��ĳ��д�߳��õ���д������ûִ�е�unWriteLock()����ȥ�ͷ�д�����ͱ�������ִֹͣ���ˣ�
 * 		��ʱ���еĶ��̻߳�������ˡ�
 */
public class MyReadAndWriteLockTest1 {
	public static void main(String[] args) {
		ReadWriteLock1 lock = new ReadWriteLock1();
		RT1 rt1 = new RT1(lock);
		RT1 rt2 = new RT1(lock);
		RT1 rt3 = new RT1(lock);
		RT1 rt4 = new RT1(lock);
		RT1 rt5 = new RT1(lock);
		RT1 rt6 = new RT1(lock);
		
		WT1 wt1 = new WT1(lock);
		WT1 wt2 = new WT1(lock);

		rt1.setName("--> ��1");
		rt2.setName("--> ��2");
		rt3.setName("--> ��3");
		rt4.setName("--> ��4");
		rt5.setName("--> ��5");
		rt6.setName("--> ��6");
		
		wt1.setName("д1");
		wt2.setName("д2");

		rt1.start();
		rt2.start();
		rt3.start();
		rt4.start();
		rt5.start();
		rt6.start();
		wt1.start();
		wt2.start();

	}
}

class RT1 extends Thread {// ���߳���
	private ReadWriteLock1 lock;

	public RT1(ReadWriteLock1 lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " : ����");

		try {
			lock.readLock();// �Ӷ���
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// �������б�������̣߳�
		lock.unReadLock();
	}
}

class WT1 extends Thread {// д�߳���
	private ReadWriteLock1 lock;

	public WT1(ReadWriteLock1 lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " --> ����");

		try {
			lock.writeLock();// ��д��
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// �������б�������̣߳�
		lock.unWriteLock();
	}
}

/**
 * ��д��1.0
 */
class ReadWriteLock1 {
	private int reader;//��¼���ڶ��Ķ��߳�����
	private int writer;//��¼����д��д�߳�����

	public synchronized void readLock() throws InterruptedException {
		while (writer == 1) {// �ж��߳��ڶ�ing~
			System.out.println(Thread.currentThread().getName() + " ===> ��˯��");
			this.wait();// ���߳�Ҫ����
			System.out.println(Thread.currentThread().getName() + " =======> ������");
		}
		reader++;
		System.out.println(Thread.currentThread().getName() + " : ���ڶ�");
	}

	public synchronized void unReadLock() {
		reader--;
		System.out.println(Thread.currentThread().getName() + " --> ������� ��==========");
		this.notifyAll();
	}

	public synchronized void writeLock() throws InterruptedException {
		while (reader >= 1 || writer == 1) {// �ж��߳��ڶ�ing~ ����д�߳���дing~
			System.out.println(Thread.currentThread().getName() + " ===> ��˯��");
			this.wait();// д�̹߳���
			System.out.println(Thread.currentThread().getName() + " =======> ������");
		}
		writer++;
		System.out.println(Thread.currentThread().getName() + " --> ����д");
	}

	public synchronized void unWriteLock() {
		writer--;
		System.out.println(Thread.currentThread().getName() + " --> ������� ��==========");
		this.notifyAll();
	}
}
