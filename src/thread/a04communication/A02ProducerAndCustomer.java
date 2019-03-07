package thread.a04communication;
/**
 * 多线程：
 * 生产者/消费者问题:
 * 生产者(Productor)将产品交给店员(Clerk)，而消费者(Customer)从店员处取走产品，
 * 店员一次只能持有固定数量的产品(比如:20），如果生产者试图生产更多的产品，店员会叫生产者停一下，如果店中有空位放产品了再通知生产者继续生产；
 * 如果店中没有产品了，店员会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。
 * 分析：
 * 1.是否涉及到多线程的问题？是！生产者、消费者
 * 2.是否涉及到共享数据？有！考虑线程的安全
 * 3.此共享数据是谁？即为产品的数量
 * 4.是否涉及到线程的通信呢？存在生产者与消费者的通信
 * 5.虚假唤醒：线程被wait()住后，再次被唤醒时，是继续执行wait()之后的代码的。所以wait()一定要在循环中去使用。详见：探究5.
 */
public class A02ProducerAndCustomer {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		
		Producer p1 = new Producer(clerk);
		Producer p2 = new Producer(clerk);
		Customer c1 = new Customer(clerk);
		Customer c2 = new Customer(clerk);
		
		new Thread(p1, "生产者1").start();
		new Thread(p2, "生产者2").start();
		new Thread(c1, "消费者1").start();
		new Thread(c2, "消费者2").start();
	}
}

class Producer implements Runnable{//生产者
	Clerk clerk;
	public Producer(Clerk clerk){
		this.clerk = clerk;
	}
	public void run(){
		for(int i = 0; i < 10; i++){
			clerk.addProduct();
		}
	}
}
class Customer implements Runnable{//消费者
	Clerk clerk;
	public Customer(Clerk clerk){
		this.clerk = clerk;
	}
	public void run(){
		for(int i = 0; i < 10; i++){
			clerk.consumptionProduct();
		}
	}
}

class Clerk{//店员
	private int product;//产品
	
	/**
	 * 生产产品
	 */
	public synchronized void addProduct(){
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(product >= 1){
			try {
				System.out.println(Thread.currentThread().getName() + " ： 停止生产！");
				wait();//探究5：为了避免虚假唤醒问题，应该总是使用在循环中
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " ： " + ++product);
		notifyAll();
	}
	
	/**
	 * 消费产品
	 */
	public synchronized void consumptionProduct(){
		while(product <= 0){
			try {
				System.out.println(Thread.currentThread().getName() + " ： 售罄！");
				wait();//探究5：为了避免虚假唤醒问题，应该总是使用在循环中
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " ：" + --product);
		notifyAll();
	}
}