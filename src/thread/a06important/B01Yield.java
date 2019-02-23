package thread.a06important;

/**
 * 所谓得到执行的机会并不是把放弃掉锁，而是放出cpu的执行权力，让给其他的线程用cpu。
 * 	1:Thread.sleep(long millis),必须带有一个时间参数。
 * 		sleep(long)使当前线程进入停滞状态，所以执行sleep()的线程在指定的时间内肯定不会被执行；
 * 		sleep(long)放出CPU的执行权；
 * 		sleep(long)是不会释放锁标志的。
 * 	
 * 	2:yield()没有参数。
 * 		sleep 方法使当前运行中的线程睡眼一段时间，进入不可运行状态，这段时间的长短是由程序设定的，
 * 		yield 方法使当前线程让出CPU占有权，但让出的时间是不可设定的。
 * 		yield()也不会释放锁标志。
 * 		实际上，yield()方法对应了如下操作：
 * 			先检测当前是否有相同优先级的线程处于同可运行状态，如有，则把 CPU 的占有权交给此线程，否则继续运行原来的线程。
 * 			所以yield()方法称为“退让”，它把运行机会让给了同等优先级的其他线程。
 * 		sleep方法允许较低优先级的线程获得运行机会，但yield()方法执行时，当前线程仍处在可运行状态，
 * 			所以不可能让出较低优先级的线程些时获得CPU占有权。 在一个运行系统中，如果较高优先级的线程没有调用 sleep 方法，
 * 			又没有受到 I/O阻塞，那么较低优先级线程只能等待所有较高优先级的线程运行结束，才有机会运行。
 * 		yield()只是使当前线程重新回到可执行状态，所以执行yield()的线程有可能在进入到可执行状态后马上又被执行。
 * 			所以yield()只能使同优先级的线程有执行的机会。
 * 	
 * 	3:Thread.yield()方法作用是：暂停当前正在执行的线程对象，并执行其他线程。
 * 		yield()应该做的是让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。
 * 			因此，使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。但是，实际中无法保证
 * 			yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。
 * 	结论：yield()从未导致线程转到等待/睡眠/阻塞状态。在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果
 */
public class B01Yield {

    public static void main(String[] args) {
        MyThread03 t1 = new MyThread03("t1");

        MyThread03 t2 = new MyThread03("t2");

        t1.start();

        t2.start();
    }

}

class MyThread03 extends Thread {

    MyThread03(String s) {
        super(s);
    }

    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(getName() + ":" + i);
            if (i == 0)
                yield();
        }
    }
}
