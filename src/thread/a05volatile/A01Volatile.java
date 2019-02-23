package thread.a05volatile;

/**
 * volatile是用于修饰属性的，保证该字段的可见性，以及防止该字段重排序；它不保证该字段的线程安全问题。
 * 1.什么是字段的可见性？ ==》 见测试。
 * 2.什么是字段重排序？ ==> 字段重排序是操作系统决定的，大致是因为局部性原理（时间局部性或空间局部性）。
 * 		但是具体哪些字段会被重排序，进行重排序的时机等我们都不得而知，所以很难进行测试。
 * 3.理解volatile能够保证字段的可见性，以及防止被修饰的字段重排序，就看A01Volatile即可。
 * 		A02VolatileTest 和 A03VolatileTest这两个类都是老公做的测试。
 * 
 * 下面这个测试的就是字段的可见性：
 * 	1.准备：
 * 		线程sa：只要flag = true	它就一直执行while中的代码（注意：为了测试结果能够更大概率出现“字段不可见问题，”所以while中没写代码）.
 * 		线程setA：改变flag的值：flag = false。
 * 	2.测试：
 * 		线程sa先启动并执行并执行任务；
 * 		让线程setA正常启动并执行任务。在执行修改flag值前先睡1秒的原因：
 * 			确认线程sa已经成功启动并正常在执行其run()方法后setA线程才去修改flag值的。
 * 			否则可能线程sa和线程setA同时启动，这时候setA先修改了flag的值，sa在执行run()方法时判断flag就为false了。
 * 	3.结果：
 * 		线程sa会一直执行while不会停下，即使后面setA线程修改了flag的值为false，sa线程也读不到 ==》 这就是字段不可见。
 * 		如果flag变量加了volatile关键字来修改，就可以避免上面所述情况。
 * 	
 * 	4.结论：
 * 		（1）如果只是单线程是不存在字段的可见性问题的。
 * 		（2）用volatile关键字来修饰变量，就可以避免该变量在多线程环境下出现字段的不可见问题。
 * 		（3）volatile只是负责解决字段的可见性和字段的重排序问题，并不意味着被volatile修饰的变量就是线程安全的。
 * 
 *  5.sychronized是能够保证字段的可见性。jdk提供的Lock也是如此。
 *  
 *  6.字段可见性和“每个线程都有自己的缓存(或叫线程的工作内存)”有关系：
 *  	①操作没有用volatile来修饰字段时，各个线程都是先从主内存(堆)中复制一份数据到自己的工作内存中，然后操作自己工作内存中的数据，最后再更新到主内存中。
 *  	②当字段被volatile修饰后，各个线程操作该字段时，都是直接在主内存中进行操作的。
 */
public class A01Volatile {
	public static void main(String[] args) {
		A a = new A();
		ShowA sa = new ShowA(a);
		SetA setA = new SetA(a);
		sa.start();
		
//		try {
//			TimeUnit.SECONDS.sleep(3);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		setA.start();
	}
}
class SetA extends Thread{
	private A a;
	public SetA(A a) {
		this.a = a;
	}
	@Override
	public void run() {
		try {
			a.setFlag();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class ShowA extends Thread{
	private A a;
	public ShowA(A a) {
		this.a = a;
	}
	@Override
	public void run() {
		a.show();
	}
}
class A{
//	private volatile boolean flag = true;
	private Boolean flag = true;
	public void show(){
		while(flag){
//			System.out.println("嘻嘻嘻，我是小星星。");
		}
		System.out.println("flag = " + flag);
	}
	public void setFlag() throws InterruptedException {
		Thread.sleep(1000);
		flag = false;
		System.out.println("我改了flag。");
	}
}