package thread.a04communication;
/**
 * �̵߳�ͨѶ��communication��wait(),notify(),notifyAll()������ʹ�ã�
 * 
 * ʹ�������̴߳�ӡ1-100���߳�1���߳�2�������ӡ��
 */
public class A01CommunicationTest {
	public static void main(String[] args) {
		PrintNumber p = new PrintNumber();
		
		Thread t1 = new Thread(p);
		Thread t2 = new Thread(p);
		
		t1.setName("A");
		t2.setName("B");
		
		t1.start();
		t2.start();
	}
}

class PrintNumber implements Runnable{
	private int number = 1;//ע�⣬number1-100�ǹ�����Դ��
	public void run(){
		for(;;){
			synchronized (this) {
				notify();
				if (number <= 100) {//��ô�����������߳�һ��ֻ��ӡ1-100�������ifһ�� Ҫд��
					try {
						Thread.currentThread().sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ":" + number);
					number++;
				} else {
					break;
				}
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}