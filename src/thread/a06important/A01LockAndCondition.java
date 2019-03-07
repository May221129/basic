package thread.a06important;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock��jdkʵ�ֵģ����ã���synchronizedһ��
 * condition��������
 * 
 *  1:��Condition�У���await()�滻wait()����signal()�滻notify()����signalAll()�滻notifyAll()��
 *  	��ͳ�̵߳�ͨ�ŷ�ʽ��Condition������ʵ�֣�����ע�⣬Condition�Ǳ��󶨵�Lock�ϵģ�Ҫ����һ��Lock��Condition������newCondition()������
 *  
 *  2:���������ĳ�����Ϊ�˸���ϸ�����̵߳ȴ��뻽�ѣ���Java5֮ǰ���̵߳ĵȴ��뻽����������Object�����wait()��notify()/notifyAll()������
 *  	�����Ĵ�������ϸ��ͨ���׶���˵������������/�����ߵĳ����У���ԭ���Ļ����ϣ������˶�����ʱ��ʱ֪ͨ�����ߣ����п�ʱ��ʱ֪ͨ�����ߵ��Ż���ͨ����
 *  	������������һ����֣�һ������ֵ�������������������Ժ��޹�ϵ���չ���˵������Lock�ķ�Χ�ڡ����ԣ��ӱ�������˵���Ƕ�Object�������ĳ������Ż���
 *  	������ȫ�»��Ƶ����롣
 *
 *	3:������wait()�� notify()���������ȡǮ, notify���������һ���߳�, ���º�����߳���������, �����ĳ�notifyAll. 
 *		�����condition�Ļ�, �Ϳ��Զ���Ļ���һ���߳�. ���¾���_save �� _draw
 *	
 *	4.��ƪ��������Դ�����:http://ifeve.com/understand-condition/
 *
 *	���⣺
 *	��Lock�ǰѻ���������t1ִ��Lock.lock()�������̻߳ᱻ������
 *	��Condition�Ǻ�Lock�󶨵ģ�һ��Lock�����ж��Condition��Condition�Ǹ������������ŷ���ĳ��������һ����̡߳�
 */
public class A01LockAndCondition {
	public static void main(String[] args) {
        // �����������ʵ��˻�
        MyCount myCount = new MyCount("95599200901215522", 10000);
        // ����һ���̳߳�
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Thread t1 = new SaveThread("����", myCount, 1000);
        Thread t2 = new SaveThread("����", myCount, 1000);
        Thread t3 = new DrawThread("����", myCount, 12600);
        Thread t4 = new SaveThread("����", myCount, 600);
        Thread t5 = new DrawThread("��ţ", myCount, 1300);
        Thread t6 = new DrawThread("����", myCount, 800);
        Thread t7 = new SaveThread("����", myCount, 2100);
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
class SaveThread extends Thread {
    private String name; // ������
    private MyCount myCount; // �˻�
    private int x; // �����

    SaveThread(String name, MyCount myCount, int x) {
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
class DrawThread extends Thread {
    private String name; // ������
    private MyCount myCount; // �˻�
    private int x; // �����

    DrawThread(String name, MyCount myCount, int x) {
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
class MyCount {
    private String oid; // �˺�
    private int cash; // �˻����
    private Lock lock = new ReentrantLock(); // �˻���������������
    /**
     * ����������ò���_save��������������Ϊ_save�ڴ�Ǯ�����оͲ���ִ��await()���������𣬼�Ȼû�б����������̸�λ����أ�
     * �����������ǮȡǮ�ĳ����У����Բ���Lock��
     */
//    private Condition _save = lock.newCondition(); // �������
    private Condition _draw = lock.newCondition(); // ȡ������

    MyCount(String oid, int cash) {
        this.oid = oid;
        this.cash = cash;
    }

    /**
     * ���
     * @param x �������
     * @param name ������
     */
    public void saving(int x, String name) {
        lock.lock(); // ��ȡ��
        try {
        	if (x > 0) {
                cash += x; // ���
                System.out.println(name + "���" + x + "����ǰ���Ϊ" + cash);
            }
            _draw.signalAll(); // ��������ȡǮ�ĵȴ��̡߳�
		} finally{
			lock.unlock(); // �ͷ���
		}
    }

    /**
     * ȡ��
     * @param x ���������
     * @param name ��������
     */
    public void drawing(int x, String name) {
        lock.lock(); // ��ȡ��
        try {
            while (cash - x < 0) { 
            	System.out.println("Ǯ������" + name + "������");
                _draw.await(); // ����ȡ�����, await֮�����ʾ�Զ��ͷ���lock��ֱ���������Զ���ȡ
                System.out.println(name + "������");
            }
            cash -= x; // ȡ��
            System.out.println(name + "ȡ��" + x + "����ǰ���Ϊ" + cash);
            
//            _save.signalAll(); // �������д�����
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // �ͷ���
        }
    }
}