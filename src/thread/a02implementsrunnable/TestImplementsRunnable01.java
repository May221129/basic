package thread.a02implementsrunnable;
/**
 * 创建多线程的第二种方式：实现Runnable接口：
 */
public class TestImplementsRunnable01 {
	public static void main(String[] args) {
//3、创建一个Runnable接口实现类的对象：
		SubThread1 st = new SubThread1();
//4、将此对象作为形参传递给Thread类的构造器中，创建Thread类的对象，此对象即为一个线程：
		Thread t = new Thread(st);	
//5、调用start()方法:启动线程并执行run():
		t.start();
//		再创建一个线程（区别于继承Thread类的第一种创建多线程的方法）：
		Thread t1 = new Thread(st);
		t1.start();
	}
}
//1、创建一个实现了Runnable接口的类：
class SubThread1 implements Runnable{
//2、实现Runnable接口的抽象方法：
	public void run(){
		for(int i = 1; i <= 100; i++){
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
	}
}