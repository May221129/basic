package thread.a_note;

/**
 * 探讨：Thread的实例可以充当监视器这个角色吗？
 * 
 * 结果：
 * 	Thread的实例是可以充当监视器的。问题出在：这个实例自己是否执行了start方法:
 * 	如果有执行，则在实例的run()方法没跑完时，监视器不起作用;
 * 	run方法一旦跑完了则监视器会立刻起作用，锁死同步代码块中的代码。
 * 
 * 也就是说，用Thread的实例来充当监视器是有条件的，所以——没事别作死用thread的实例来充当监视器。
 * 
 * @author May
 * 2019年10月30日
 */
public class A02UseThreadInstanceAsMonitor {
	public static void main(String[] args) {
		A a = new A();
		
		B b1 = new B(a);
		B b2 = new B(a);
		
		b1.setName("b1");
		b2.setName("b2");
		
		b1.start();
		b2.start();
		a.start();//问题出在这里《=====
	}
}

class B extends Thread{
	private A a;
	
	public B(A a) {
		this.a = a;
	}
	
	@Override
	public void run() {
		synchronized(a) {//用Thread的实例来充当监视器
			for(int i = 0; i < 3; i++) {
				System.out.println(currentThread().getName() + " : " + i + " ,我要睡了。");
				try {
					a.wait();
					System.out.println(currentThread().getName() + " : 我醒了。");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class A extends Thread{
	
	@Override
	public void run() {
		synchronized(this) {
			System.out.println("我是A，我抢到了锁 —— " + this);
		}
	}
}