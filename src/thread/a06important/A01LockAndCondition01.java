package thread.a06important;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 需求：一个账户，支持用户存取款。
 * 实现：synchronized + wait() + notifyAll()
 * 问题：
 * 	1.wait()方法和notifyAll/notify()方法是由谁来执行？
 * 		答：有充当当前监视器的对象来执行。详见：探索1
 */
public class A01LockAndCondition01 {
	public static void main(String[] args) {
        // 创建并发访问的账户
        MyCount01 myCount = new MyCount01("95599200901215522", 10000);
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Thread t1 = new SaveThread01("张三", myCount, 1000);
        Thread t2 = new SaveThread01("李四", myCount, 1000);
        Thread t3 = new DrawThread01("王五", myCount, 12600);
        Thread t4 = new SaveThread01("老张", myCount, 600);
        Thread t5 = new DrawThread01("老牛", myCount, 1300);
        Thread t6 = new DrawThread01("胖子", myCount, 800);
        Thread t7 = new SaveThread01("测试", myCount, 2100);
        // 执行各个线程
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        pool.execute(t7);
        // 关闭线程池
        pool.shutdown();
    }
}

/**
 * 存款线程类
 */
class SaveThread01 extends Thread {
    private String name; // 操作人
    private MyCount01 myCount; // 账户
    private int x; // 存款金额

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
 * 取款线程类
 */
class DrawThread01 extends Thread {
    private String name; // 操作人
    private MyCount01 myCount; // 账户
    private int x; // 存款金额

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
 * 普通银行账户，不可透支
 */
class MyCount01 {
    private String oid; // 账号
    private int cash; // 账户余额

    MyCount01(String oid, int cash) {
        this.oid = oid;
        this.cash = cash;
    }

    /**
     * 存款
     * @param x 操作金额
     * @param name 操作人
     */
    public synchronized void saving(int x, String name) {
    	try {
    		if (x > 0) {
            	cash += x; // 存款
            	System.out.println(name + "存款" + x + "，当前余额为" + cash);
            }
		} finally {
			this.notifyAll();//由当前MyCount01的实例对象去唤醒所有被挂起的线程。
		}
        
    }

    /**
     * 取款
     * @param x ：操作金额
     * @param name ：操作人
     */
    public synchronized void drawing(int x, String name) {
    	while (cash - x < 0) { 
    		System.out.println("钱不够，" + name + "阻塞中");
    		try {
				/**
				 * 探究1
				 * ①wait()、notify()是谁的方法？
				 * 	答：是Object的方法。且是用final修饰的、不可重写的方法。
				 * ②this是谁？
				 * 	答：当前方法所在的实例对象，即"MyCount01 myCount"对象。
				 * ③这里的"Thread.currentThread().wait();"的作用是什么？
				 * 	答：原想它实现“当前线程主动放弃手上拥有的锁，并被放到当前监视器的wait容器中去挂起”这一功能，
				 * 		但是，这里这么写会报错“java.lang.IllegalMonitorStateException”。
				 * 		因为这里的监视器默认是this。
				 * ④"this.wait();"的作用是什么？
				 * 	答：当前对象的监视器让当前线程对象持有的锁放开，并将该线程对象挂起、放到wait容器中。
				 */
//				Thread.currentThread().wait();//java.lang.IllegalMonitorStateException
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		System.out.println(name + "被唤醒");
    	}
    	cash -= x; // 取款
    	System.out.println(name + "取款" + x + "，当前余额为" + cash);
    }
}