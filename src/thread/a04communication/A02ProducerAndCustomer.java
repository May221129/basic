package thread.a04communication;
/**
 * ���̣߳�
 * ������/����������:
 * ������(Productor)����Ʒ������Ա(Clerk)����������(Customer)�ӵ�Ա��ȡ�߲�Ʒ��
 * ��Աһ��ֻ�ܳ��й̶������Ĳ�Ʒ(����:20���������������ͼ��������Ĳ�Ʒ����Ա���������ͣһ�£���������п�λ�Ų�Ʒ����֪ͨ�����߼���������
 * �������û�в�Ʒ�ˣ���Ա����������ߵ�һ�£���������в�Ʒ����֪ͨ��������ȡ�߲�Ʒ��
 * ������
 * 1.�Ƿ��漰�����̵߳����⣿�ǣ������ߡ�������
 * 2.�Ƿ��漰���������ݣ��У������̵߳İ�ȫ
 * 3.�˹���������˭����Ϊ��Ʒ������
 * 4.�Ƿ��漰���̵߳�ͨ���أ������������������ߵ�ͨ��
 * 5.��ٻ��ѣ��̱߳�wait()ס���ٴα�����ʱ���Ǽ���ִ��wait()֮��Ĵ���ġ�����wait()һ��Ҫ��ѭ����ȥʹ�á������̽��5.
 */
public class A02ProducerAndCustomer {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		
		Producer p1 = new Producer(clerk);
		Producer p2 = new Producer(clerk);
		Customer c1 = new Customer(clerk);
		Customer c2 = new Customer(clerk);
		
		new Thread(p1, "������1").start();
		new Thread(p2, "������2").start();
		new Thread(c1, "������1").start();
		new Thread(c2, "������2").start();
	}
}

class Producer implements Runnable{//������
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
class Customer implements Runnable{//������
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

class Clerk{//��Ա
	private int product;//��Ʒ
	
	/**
	 * ������Ʒ
	 */
	public synchronized void addProduct(){
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(product >= 1){
			try {
				System.out.println(Thread.currentThread().getName() + " �� ֹͣ������");
				wait();//̽��5��Ϊ�˱�����ٻ������⣬Ӧ������ʹ����ѭ����
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " �� " + ++product);
		notifyAll();
	}
	
	/**
	 * ���Ѳ�Ʒ
	 */
	public synchronized void consumptionProduct(){
		while(product <= 0){
			try {
				System.out.println(Thread.currentThread().getName() + " �� ������");
				wait();//̽��5��Ϊ�˱�����ٻ������⣬Ӧ������ʹ����ѭ����
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " ��" + --product);
		notifyAll();
	}
}