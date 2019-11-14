package thread.a05volatile;

/**
 * 测试——字段不可见
 * 
 * 1.准备：
 * 		线程PrintBean：只要flag = true	它就一直执行while中的代码（注意：为了测试结果能够更大概率出现“字段不可见问题，”所以while中没写代码）.
 * 		线程SetBean：改变flag的值：flag = false。
 * 	2.测试：
 * 		线程PrintBean先启动并执行并执行任务；
 * 		让线程SetBean正常启动并执行任务。在执行修改flag值前先睡1秒的原因：
 * 			确认线程PrintBean已经成功启动并正常在执行其run()方法后SetBean线程才去修改flag值的。
 * 			否则可能线程PrintBean和线程SetBean同时启动，这时候SetBean先修改了flag的值，PrintBean在执行run()方法时判断flag就为false了。
 * 	3.结果：
 * 		线程PrintBean会一直执行while不会停下，即使后面SetBean线程修改了flag的值为false，PrintBean线程也读不到 ==》 这就是字段不可见。
 * 		如果flag变量加了volatile关键字来修改，就可以避免上面所述情况，测试见A02Visible。
 * 	
 * 	4.结论：
 * 		（1）如果只是单线程是不存在字段的可见性问题的。
 * 		（2）用volatile关键字来修饰变量，就可以避免该变量在多线程环境下出现字段的不可见问题。
 * 		（3）volatile只是负责解决字段的可见性和字段的重排序问题，并不意味着被volatile修饰的变量就是线程安全的，测试见：A03UseVolatileIsThreadSafe
 * 
 * @author May
 * 2019年11月14日
 */
public class A02NoVisible {
	public static void main(String[] args) {
		Bean bean = new Bean();
		PrintBean printBean = new PrintBean(bean);
		SetBean setBean = new SetBean(bean);
		printBean.start();
		setBean.start();
	}
}

class SetBean extends Thread{
	private Bean bean;
	
	public SetBean(Bean bean) {
		this.bean = bean;
	}
	
	public void run() {
		try {
			currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		bean.setFlag(false);
		System.out.println("flag == false.");
	}
}

class PrintBean extends Thread{
	private Bean bean;
	public PrintBean(Bean bean) {
		this.bean = bean;
	}
	@Override
	public void run() {
		while(bean.getFlag()) {
			//注意：为了测试结果能够更大概率出现“字段不可见问题，”所以while中没写代码。
		}
		System.out.println("PrintBean结束while循环了。");
	}
}

class Bean{
	private boolean flag = true;
	
	public void setFlag(boolean bool) {
		flag = bool;
	}
	
	public boolean getFlag() {
		return this.flag;
	}
}