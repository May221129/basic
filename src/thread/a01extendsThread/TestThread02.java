package thread.a01extendsThread;

/**
 * 继承于Thread类的匿名类对象：创建两个子线程，让其中一个输出1-100之间的偶数，另一个输出1-100之间的奇数：
 */
public class TestThread02 {
	public static void main(String[] args) {
//		SubThread2 st2 = new SubThread2();
//		SubThread3 st3 = new SubThread3();
//		st2.start();
//		st3.start();
//继承于Thread类的匿名类对象：
		new Thread(){
			public void run(){
				for(int i = 1; i <= 100; i++){
					if(i % 2 == 0){
						System.out.println(Thread.currentThread().getName() + "--》" + i);
					}
				}
			}
		}.start();
		
		new Thread(){
			public void run(){
				for(int i = 1; i <= 100; i++){
					if(i % 2 != 0){
						System.out.println(Thread.currentThread().getName() + ":" + i);
					}
				}
			}
		}.start();
	}
}
class SubThread2 extends Thread{
	public void run(){
		for(int i = 1; i <= 100; i++){
			if(i % 2 == 0){
				System.out.println(Thread.currentThread().getName() + "--》" + i);
			}
		}
	}
}
class SubThread3 extends Thread{
	public void run(){
		for(int i = 1; i <= 100; i++){
			if(i % 2 != 0){
				System.out.println(Thread.currentThread().getName() + ":" + i);
			}
		}
	}
}