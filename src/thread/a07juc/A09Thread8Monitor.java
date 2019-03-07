package thread.a07juc;

/**
 * 学习这个“线程八锁”的视频见：Q:\mystudy\studyvideo\高级thread\juc == 》12. 尚硅谷_JUC线程高级_线程八锁.avi
 * 
 * 该类主要涉及的知识点是：
 * 	①主线程（main方法中）先后创建两个线程，两个线程拿到锁的概率和创建的先后顺序有关。
 * 	②sleep()不会放掉锁。
 * 	③用synchronized修饰的方法，默认是由谁来充当“monitor（监视器）”？
 * 		静态的同步方法 ==》 猜测：该静态方法所属的类的Class对象。
 * 		非静态的同步方法 ==》 该方法所属的当前实例对象来充当监视器。
 * 	④一个对象只能充当一个monitor（监视器），一个monitor只能有一把锁。
 * 		同一时刻可以存在多个线程都持有锁的情况（有多个monitor），
 * 		但是它们持有的不可能是同一把锁，也就是说，一个锁同一时刻不可能同时被多个线程锁持有。
 * 题目：判断打印的 "one" or "two" ？
 * 	1. 两个普通同步方法，两个线程，标准打印， 打印? //one  two
 * 	2. 新增 Thread.sleep() 给 getOne() ,打印? //one  two
 * 	3. 新增普通方法 getThree() , 打印? //three  one   two
 * 	4. 两个普通同步方法，两个 Number 对象，打印?  //two  one
 * 	5. 修改 getOne() 为静态同步方法，打印?  //two   one
 * 	6. 修改两个方法均为静态同步方法，一个 Number 对象?  //one   two
 * 	7. 一个静态同步方法，一个非静态同步方法，两个 Number 对象?  //two  one
 * 	8. 两个静态同步方法，两个 Number 对象?   //one  two
 */
public class A09Thread8Monitor {
	
	public static void main(String[] args) {
		Number number = new Number();
		Number number2 = new Number();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				number.getOne();
			} 
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
//				number.getTwo();
				number2.getTwo();
			}
		}).start();
		
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				number.getThree();
			}
		}).start();*/
		
	}

}

class Number{
	
	public static synchronized void getOne(){//Number.class
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		
		System.out.println("one");
	}
	
	public synchronized void getTwo(){//this
		System.out.println("two");
	}
	
	public void getThree(){
		System.out.println("three");
	}
	
}