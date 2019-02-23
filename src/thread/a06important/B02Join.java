/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread.a06important;

/**
 * 在A线程的执行的时候,执行到了B.join(), 那么A线程就会交出CPU执行权,直至B线程执行结束,A线程才能继续执行 。 注意这跟其他线程没关系。
 * 解释下面程序: 主线程、t1、t2启动. 主线程执行t1.join,  主线程让出CPU执行权,  只有t1, t2在跑. 等到t1跑完, 主线程才能继续.
 * 	如果把t1.join放到t2.start的前面,  那么就是t1执行完之后,  主线程和t2一起执行.
 * @author ligr
 */
public class B02Join {
    public static void main(String[] args) throws InterruptedException {
        Demo t1 = new Demo();
        t1.setName("t1");
        Demo t2 = new Demo();
        t2.setName("t2");
        Thread.currentThread().setName("主线程");
        
        t1.start();
        t2.start();
        t1.join();
        
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " .... " + i);
        }
    }

    private static class Demo extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " .... " + i);
            }
        }
    }
}
