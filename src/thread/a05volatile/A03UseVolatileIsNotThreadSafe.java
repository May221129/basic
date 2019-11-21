package thread.a05volatile;

/**
 * 测试——用volatile修饰的字段，能确保线程安全性吗？
 * 
 * 回答这个问题前，我们要先明确什么是线程安全性——“多个线程同时访问同一对象的共享数据，最终数据的结果是正确的”。
 * 
 * 测试结果：（1）出现了大量的重复数字； （2）最后还输出了 “-1”；
 * 	出现问题（1）是因为：线程存在“先检查后执行”的竞态条件。
 * 		可能有两个线程同时拥有CPU的执行权（机器是双核的），它们判断到做“if (ticket > 0)”，并同时做“ticket--”操作。
 * 	出现问题（2）是因为：
 * 		①当ticket==1时，两个或多个线程同时通过了“if (ticket > 0)”的判断，并进入了判断框中去执行代码；
 * 		②然后它们执行到“Thread.sleep(100);”就睡了；
 * 		③睡醒后总有一个线程会先抢到cup的执行权，然后执行“ticket--”操作，并将最新的ticket数值推送告知到每个线程；
 * 		④此时那些在判断框中的其他的线程并不会再次做“if (ticket > 0)”的判断，而是直接拿最新的ticket并做“ticket--”操作。
 * 		就算线程在“ticket--”之前每次都做“if (ticket > 0)”的判断，也依旧会有线程安全问题，因为又可能出现①那种同时通过判断的状态。
 * 
 * 结论：volatile不能保证线程安全性，因为要保证线程安全性就得保证是以串行形式来访问操作共享资源的，而volatile做不到这点。
 * 
 * @author May 2019年11月14日
 */
public class A03UseVolatileIsNotThreadSafe {
	public static void main(String[] args) {
		Window w = new Window();

		Thread t1 = new Thread(w);
		Thread t2 = new Thread(w);
		Thread t3 = new Thread(w);

		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");

		t1.start();
		t2.start();
		t3.start();
	}
}

class Window implements Runnable {
	private volatile int ticket = 100;

	public void run() {
		for (;;) {
			//通过下面的①②两个步骤我们可以发现：对一个共享资源可以多个线程同时进行修改，自然就会有线程安全问题。
			if (ticket > 0) {
				try {
					Thread.sleep(100);//①多个线程同时判断到“ticket>0”，然后挂起了
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//②多个线程同时醒来，同时进行“ticket--”操作：
				System.out.println(Thread.currentThread().getName() + ":" + ticket--);
			} else {
				break;
			}
		}
	}
}