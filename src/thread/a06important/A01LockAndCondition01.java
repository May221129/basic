package thread.a06important;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ����һ���˻���֧���û���ȡ�
 * ʵ�֣�synchronized + wait() + notifyAll()
 * ���⣺
 * 	1.wait()������notifyAll/notify()��������˭��ִ�У�
 * 		���г䵱��ǰ�������Ķ�����ִ�С������̽��1
 */
public class A01LockAndCondition01 {
	public static void main(String[] args) {
        // �����������ʵ��˻�
        MyCount01 myCount = new MyCount01("95599200901215522", 10000);
        // ����һ���̳߳�
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Thread t1 = new SaveThread01("����", myCount, 1000);
        Thread t2 = new SaveThread01("����", myCount, 1000);
        Thread t3 = new DrawThread01("����", myCount, 12600);
        Thread t4 = new SaveThread01("����", myCount, 600);
        Thread t5 = new DrawThread01("��ţ", myCount, 1300);
        Thread t6 = new DrawThread01("����", myCount, 800);
        Thread t7 = new SaveThread01("����", myCount, 2100);
        // ִ�и����߳�
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        pool.execute(t7);
        // �ر��̳߳�
        pool.shutdown();
    }
}

/**
 * ����߳���
 */
class SaveThread01 extends Thread {
    private String name; // ������
    private MyCount01 myCount; // �˻�
    private int x; // �����

    SaveThread01(String name, MyCount01 myCount, int x) {
        this.name = name;
        this.myCount = myCount;
        this.x = x;
    }

    public void run() {
        myCount.saving(x, name);
    }
}

/**
 * ȡ���߳���
 */
class DrawThread01 extends Thread {
    private String name; // ������
    private MyCount01 myCount; // �˻�
    private int x; // �����

    DrawThread01(String name, MyCount01 myCount, int x) {
        this.name = name;
        this.myCount = myCount;
        this.x = x;
    }

    public void run() {
        myCount.drawing(x, name);
    }
}

/**
 * ��ͨ�����˻�������͸֧
 */
class MyCount01 {
    private String oid; // �˺�
    private int cash; // �˻����

    MyCount01(String oid, int cash) {
        this.oid = oid;
        this.cash = cash;
    }

    /**
     * ���
     * @param x �������
     * @param name ������
     */
    public synchronized void saving(int x, String name) {
    	try {
    		if (x > 0) {
            	cash += x; // ���
            	System.out.println(name + "���" + x + "����ǰ���Ϊ" + cash);
            }
		} finally {
			this.notifyAll();//�ɵ�ǰMyCount01��ʵ������ȥ�������б�������̡߳�
		}
        
    }

    /**
     * ȡ��
     * @param x ���������
     * @param name ��������
     */
    public synchronized void drawing(int x, String name) {
    	while (cash - x < 0) { 
    		System.out.println("Ǯ������" + name + "������");
    		try {
				/**
				 * ̽��1
				 * ��wait()��notify()��˭�ķ�����
				 * 	����Object�ķ�����������final���εġ�������д�ķ�����
				 * ��this��˭��
				 * 	�𣺵�ǰ�������ڵ�ʵ�����󣬼�"MyCount01 myCount"����
				 * �������"Thread.currentThread().wait();"��������ʲô��
				 * 	��ԭ����ʵ�֡���ǰ�߳�������������ӵ�е����������ŵ���ǰ��������wait������ȥ������һ���ܣ�
				 * 		���ǣ�������ôд�ᱨ��java.lang.IllegalMonitorStateException����
				 * 		��Ϊ����ļ�����Ĭ����this��
				 * ��"this.wait();"��������ʲô��
				 * 	�𣺵�ǰ����ļ������õ�ǰ�̶߳�����е����ſ����������̶߳�����𡢷ŵ�wait�����С�
				 */
//				Thread.currentThread().wait();//java.lang.IllegalMonitorStateException
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		System.out.println(name + "������");
    	}
    	cash -= x; // ȡ��
    	System.out.println(name + "ȡ��" + x + "����ǰ���Ϊ" + cash);
    }
}