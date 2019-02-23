package thread.a06important;

/**
 * 以下代码表示，interrupt的作用是中断线程，但是如下程序，一直输出优先级，然后执行interrupt方法，但是执行完之后又发现线程还在跑。没错，线程不会停下来。   
 * 	那么interrupt的中断方法到底是怎么用， 可以理解这个方法是通知这个线程一个中断消息到啦，然后线程处理一下这个消息，然后做相应处理。
 * 那么如何中断呢：
 * 	1:首先是如果去主动中断一个线程：线程实例.interrupt()
 *     	Thread.interrupt里面的实现是  Thread.currentThread().interrupt()
 * 	2:中断线程又分成中断正在运行的，和非正在运行的
 * 		2.1 正在运行的线程的中断：就如该类中的代码所写
 * 			线程代码中需要出现Thread.interrupted()返回boolean来判断该线程是否被中断。
 *  		根据boolean那么就做相应的处理
 * 		2.2 非运行状态的线程(线程被挂起的状态, wait,sleep,park,join等都会导致线程被挂起):见E04Interrupt2.java
 *  		thread的某些方法会抛出InterruptedException 异常。  比如sleep wait join
 *  		一旦interrupt，那么这些方法就会抛出InterruptedException，程序就可以捕捉到并处理.
 *  		这时候的interrupt就相当于打断线程的挂起状态.
 *  		注意:synchronized也会导致线程被挂起, 但是synchronized不支持被打断, 这是它的缺点, lock类解决了这个缺点.
 * 	3：Thread.interrupt()就像是一个通知， 如果这个通知并没有被恰好的捕捉到，那么对线程的运行时没有任何影响的。如何捕捉住这个通知，就是第二点中讲的.
 */
public class C01Interrupt1 {//测试2.1
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.interrupted()){//② 执行完①后，这里的判断结果就为“当前Thread=可中断”状态了，不符合判断条件，结束while循环。
                    System.out.println(Thread.currentThread().getPriority());
                }
            }
        });
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        Thread.sleep(3000);
        System.out.println("要中断啦");
        t1.interrupt();//① 这行代码的作用：只是改变了t1线程的interrupt状态。
        System.out.println("中断结束"); 
    }
}
