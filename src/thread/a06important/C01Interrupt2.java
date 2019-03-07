package thread.a06important;

/**
 * 测试2.2
 */
public class C01Interrupt2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	System.out.println("我开始执行任务了");
					Thread.sleep(100000);
					System.out.println("我自己醒的！");
				} catch (InterruptedException e) {
					System.out.println("我被叫醒了，开始工作吧！");
					for(int i = 0; i < 10; i++){
						System.out.println(Thread.currentThread().getName() + " ==> i");
					}
				}
            }
        });
        t1.start();
        Thread.sleep(3000);
        System.out.println("要中断啦");
        t1.interrupt();//① 这行代码的作用：只是改变了t1线程的interrupt状态。
        System.out.println("中断结束"); 
    }
}
