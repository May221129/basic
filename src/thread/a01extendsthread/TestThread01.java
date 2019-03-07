package thread.a01extendsthread;
/**
 * 体会什么是多线程：
 * 
 * 创建一个子线程，完成1-100之间自然数的输出，同样的，主线程执行相同的操作：
 * 创建多线程的第一种方式：继承java.lang.Thread类
 */
public class TestThread01 {
	public static void main(String[] args) {
//3、创建线程对象：
//如果对同一个子线程的run()方法需要调用两次，则需要创建两个子类的对象来调用：
		SubThread st = new SubThread();
		SubThread st1 = new SubThread();
//4、调用线程的start()方法;注意：start()方法有两个功能：①启动此线程；②调用相应的run()方法。
//5、一个线程只能够执行一次start():
		st.start();
//		st.start();
		st1.start();
//不能通过Thread的实现类对象的run()去启动一个线程：相当于用当前的主线程执行st对象的run()方法，而且st线程自己执行run()。注意体会两者的区别~~~
//		st.run();
//6、编写主线程要完成的功能：
		for(int i = 1; i <= 100; i++){
			System.out.println( Thread.currentThread().getName() + ": " + i);
		}
	}
}
//1、创建一个继承了Thread类的子类：
class SubThread extends Thread{
//2、重写Thread类中的run()方法，方法内实现该子类线程要完成的功能：
	public void run(){
		for(int i = 1; i <= 100; i++){
			System.out.println(  Thread.currentThread().getName() + ": " + i);
		}
	}
}