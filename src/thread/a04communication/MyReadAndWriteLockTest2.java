package thread.a04communication;

import java.util.HashMap;

/**
 * �Լ�ʵ�ֶ�д��_2.0
 * 
 * һ������� MyReadLockTest1 �д��ڵ����⡣
 *  1.д�߳����ȣ������д�̱߳��������ڵȴ�����ôд�߳�����ִ�С�
 * 	2.ֻ��¼�����ڶ���д���߳���������reader��writer������һ��û���������̣߳�ֱ�ӵ���unLock()����ôreader��writer������������Ϳ�����ɶ���дͬʱ���С�
 * 	3.��Ϊд�߳�������ִ��Ȩ����ʱ�������˺ܶ�Ķ��̣߳�һ��ĳ��д�߳��õ���д������ûִ�е�unWriteLock()����ȥ�ͷ�д�����ͱ�������ִֹͣ���ˣ�
 * 		��ʱ���еĶ��̻߳�������ˡ�
 * 
 * ����ʵ�֣�
 * 	1.д�߳����ȣ�
 * 		1.1 ����ʵ�������У�һ�㶼�Ƕ��̶߳࣬д�߳��١��ڶ�д�������У�ִ����һ�����߳�֮�󣬾ͻ�һֱִ�ж��̣߳���д�̻߳ᱻ����
 * 			���Կ����Ľ���ǡ����������̶߳�ִ��������ִ��д�̡߳� 
 * 		1.2 �������� 
 * 			��1����д�߳����ø����ȼ���wt1.setPriority(Thread.MAX_PRIORITY);
 * 			��2����writeRequestMap���HashMap����¼�Ѿ�ִ�й�writeLock()����ȴû�õ�д���������˵�д�̣߳����readLock()��
 * 	2.��ִ�й�������������readLock()��writeLock()�����̣߳��ֱ����readRequestMap��writeRequestMap�У�
 * 		���ͷ����ķ�������unReadLock()��unWriteLock()���н�readRequestMap��writeRequestMap�еļ�¼ɾ����
 * 		ע�⣺����ǿ�����������һ��ͬ�������е�������һ��ͬ��������map�и��̵߳Ŀ���ֵ�ͻ��ۼӣ������ͷ���ʱ���ǽ����̶߳�Ӧ��valueֵ��һ��
 * 	3.���̵߳ĵȴ��趨ʱ�䣬���������𳬹�10���ӣ����Զ������ѡ�δʵ�֡�
 */
public class MyReadAndWriteLockTest2 {
	public static void main(String[] args) {
		ReadWriteLock2 lock = new ReadWriteLock2();

		RT2 rt1 = new RT2(lock);
		RT2 rt2 = new RT2(lock);
		RT2 rt3 = new RT2(lock);
		RT2 rt4 = new RT2(lock);
		RT2 rt5 = new RT2(lock);
		RT2 rt6 = new RT2(lock);
		WT2 wt1 = new WT2(lock);
		WT2 wt2 = new WT2(lock);

		rt1.setName("--> ��1");
		rt2.setName("--> ��2");
		rt3.setName("--> ��3");
		rt4.setName("--> ��4");
		rt5.setName("--> ��5");
		rt6.setName("--> ��6");
		wt1.setName("д1");
		wt2.setName("д2");

		wt1.setPriority(Thread.MAX_PRIORITY);
		wt2.setPriority(Thread.MAX_PRIORITY);

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

class RT2 extends Thread {// ���߳���
	private ReadWriteLock2 lock;

	public RT2(ReadWriteLock2 lock) {
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

		// �ͷ��������������б�������̣߳�
		lock.unReadLock();
	}
}

class WT2 extends Thread {// д�߳���
	private ReadWriteLock2 lock;

	public WT2(ReadWriteLock2 lock) {
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

		// �ͷ��������������б�������̣߳�
		lock.unWriteLock();
	}
}

/**
 * ��д��2.0
 */
class ReadWriteLock2 {
	private int reader;//���ڶ��Ķ��̸߳���
	private int writer;//����д��д�̸߳���
	private HashMap<Thread, Integer> readRequestMap = new HashMap<>();// ���ڼ�¼������Ķ��̶߳��󣬼�������
	private HashMap<Thread, Integer> writeRequestMap = new HashMap<>();// ���ڼ�¼�������д�̶߳��󣬼�������

	public synchronized void readLock() throws InterruptedException {
		//�������ö����Ķ��̶߳�����map�У�ֻ���ڴ�map�еĶ��̣߳������ʸ�ִ��unReadLock()����ȥ�ͷŶ�����
		if (readRequestMap.containsKey(Thread.currentThread())) {//��Կ�������
			Integer readerCount = readRequestMap.get(Thread.currentThread());
			readRequestMap.put(Thread.currentThread(), readerCount + 1);
		} else {
			readRequestMap.put(Thread.currentThread(), 1);
		}
		
		//ʵ����д�߳����ȣ�writeRequestMap.size() > 0
		while (writer == 1 || writeRequestMap.size() > 0) {// �ж��߳��ڶ�ing~
			System.out.println(Thread.currentThread().getName() + " ===> ��˯��");
			this.wait();// ���߳�Ҫ����
			System.out.println(Thread.currentThread().getName() + " =======> ������");
		}
		reader++;
		System.out.println(Thread.currentThread().getName() + " : ���ڶ�");
	}
	
	public synchronized void unReadLock() {
		if(readRequestMap.containsKey(Thread.currentThread())){
			if(readRequestMap.get(Thread.currentThread()) == 1){
				reader--;
				readRequestMap.remove(Thread.currentThread());
			}else{//readRequestMap.get(Thread.currentThread()) > 1 ==> ��������ʱ��һ���̶߳���᲻ֹһ���õ�����
				reader--;
				Integer readerCount = readRequestMap.get(Thread.currentThread());
				readRequestMap.put(Thread.currentThread(), readerCount - 1);
			}
			System.out.println(Thread.currentThread().getName() + " --> ������� ��==========");
			this.notifyAll();
		}else{
			System.out.println("���棡��ǰ�ġ�" + Thread.currentThread() + "'�߳�δ��ִ�й�readLock()ȥ��д����");
		}
	}

	public synchronized void writeLock() throws InterruptedException {
		if (writeRequestMap.containsKey(Thread.currentThread())) {
			Integer writerCount = writeRequestMap.get(Thread.currentThread());
			writeRequestMap.put(Thread.currentThread(), writerCount + 1);
		} else {
			writeRequestMap.put(Thread.currentThread(), 1);
		}
		while (reader >= 1 || writer == 1) {// �ж��߳��ڶ�ing~ ����д�߳���дing~
			System.out.println(Thread.currentThread().getName() + " ===> ��˯��");
			this.wait();// д�̹߳���
			System.out.println(Thread.currentThread().getName() + " =======> ������");
		}
		writer++;
		System.out.println(Thread.currentThread().getName() + " --> ����д");
	}

	public synchronized void unWriteLock() {
		if(writeRequestMap.containsKey(Thread.currentThread())){
			if(writeRequestMap.get(Thread.currentThread()) == 1){
				writer--;
				writeRequestMap.remove(Thread.currentThread());
			}else{ //writeRequestMap.get(Thread.currentThread()) > 1 ==�� ��������ʱ��һ���̶߳���᲻ֹһ���õ�����
				writer--;
				Integer writeCount = writeRequestMap.get(Thread.currentThread());
				writeRequestMap.put(Thread.currentThread(), writeCount - 1);
			}
			System.out.println(Thread.currentThread().getName() + " --> ������� ��==========");
			this.notifyAll();
		}else{
			System.out.println("���棡��ǰ�ġ�" + Thread.currentThread() + "'�߳�δ��ִ�й�writeLock()ȥ��д����");
		}
	}
}
