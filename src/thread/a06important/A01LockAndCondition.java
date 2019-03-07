package thread.a06important;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock：jdk实现的，作用：和synchronized一样
 * condition：条件。
 * 
 *  1:在Condition中，用await()替换wait()，用signal()替换notify()，用signalAll()替换notifyAll()，
 *  	传统线程的通信方式，Condition都可以实现，这里注意，Condition是被绑定到Lock上的，要创建一个Lock的Condition必须用newCondition()方法。
 *  
 *  2:条件变量的出现是为了更精细控制线程等待与唤醒，在Java5之前，线程的等待与唤醒依靠的是Object对象的wait()和notify()/notifyAll()方法，
 *  	这样的处理不够精细。通熟易懂的说，就是消费者/生产者的场景中，在原来的基础上，增加了队列满时及时通知消费者，队列空时及时通知生产者的优化，通常是
 *  	两个条件变量一起出现，一个控制值，但两个条件变量可以毫无关系，终归来说还是在Lock的范围内。所以，从本质上来说，是对Object监视器的场景性优化，
 *  	而不是全新机制的引入。
 *
 *	3:回忆用wait()和 notify()来做这个存取钱, notify是随机唤醒一个线程, 导致后面的线程死锁问题, 后来改成notifyAll. 
 *		如果用condition的话, 就可以定向的唤醒一类线程. 如下就有_save 和 _draw
 *	
 *	4.这篇文章中有源码解析:http://ifeve.com/understand-condition/
 *
 *	问题：
 *	①Lock是把互斥锁，当t1执行Lock.lock()后，其他线程会被阻塞。
 *	②Condition是和Lock绑定的，一个Lock可以有多个Condition。Condition是个容器，用来放符合某种条件的一类的线程。
 */
public class A01LockAndCondition {
	public static void main(String[] args) {
        // 创建并发访问的账户
        MyCount myCount = new MyCount("95599200901215522", 10000);
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Thread t1 = new SaveThread("张三", myCount, 1000);
        Thread t2 = new SaveThread("李四", myCount, 1000);
        Thread t3 = new DrawThread("王五", myCount, 12600);
        Thread t4 = new SaveThread("老张", myCount, 600);
        Thread t5 = new DrawThread("老牛", myCount, 1300);
        Thread t6 = new DrawThread("胖子", myCount, 800);
        Thread t7 = new SaveThread("测试", myCount, 2100);
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
class SaveThread extends Thread {
    private String name; // 操作人
    private MyCount myCount; // 账户
    private int x; // 存款金额

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
 * 取款线程类
 */
class DrawThread extends Thread {
    private String name; // 操作人
    private MyCount myCount; // 账户
    private int x; // 存款金额

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
 * 普通银行账户，不可透支
 */
class MyCount {
    private String oid; // 账号
    private int cash; // 账户余额
    private Lock lock = new ReentrantLock(); // 账户锁（可重入锁）
    /**
     * 这里根本就用不到_save这个存款条件，因为_save在存钱过程中就不会执行await()方法被挂起，既然没有被挂起过，那谈何唤醒呢？
     * 所以在这个存钱取钱的场景中，可以不用Lock。
     */
//    private Condition _save = lock.newCondition(); // 存款条件
    private Condition _draw = lock.newCondition(); // 取款条件

    MyCount(String oid, int cash) {
        this.oid = oid;
        this.cash = cash;
    }

    /**
     * 存款
     * @param x 操作金额
     * @param name 操作人
     */
    public void saving(int x, String name) {
        lock.lock(); // 获取锁
        try {
        	if (x > 0) {
                cash += x; // 存款
                System.out.println(name + "存款" + x + "，当前余额为" + cash);
            }
            _draw.signalAll(); // 唤醒所有取钱的等待线程。
		} finally{
			lock.unlock(); // 释放锁
		}
    }

    /**
     * 取款
     * @param x ：操作金额
     * @param name ：操作人
     */
    public void drawing(int x, String name) {
        lock.lock(); // 获取锁
        try {
            while (cash - x < 0) { 
            	System.out.println("钱不够，" + name + "阻塞中");
                _draw.await(); // 阻塞取款操作, await之后就隐示自动释放了lock，直到被唤醒自动获取
                System.out.println(name + "被唤醒");
            }
            cash -= x; // 取款
            System.out.println(name + "取款" + x + "，当前余额为" + cash);
            
//            _save.signalAll(); // 唤醒所有存款操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // 释放锁
        }
    }
}