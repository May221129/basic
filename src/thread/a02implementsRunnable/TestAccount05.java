package thread.a02implementsRunnable;
/**
 * ���̵߳���ϰ�⣺
 * 
 * ������һ���˻��������������ֱ���ͬһ���˻���3000Ԫ��
 * ÿ�δ�1000Ԫ����3�Σ������ӡ�˻���
 * ��չ��ʵ�ֶ��߽����ӡ��ʹ���̵߳�ͨ��
 */
public class TestAccount05 {
	public static void main(String[] args) {
		Account a = new Account();
		
		Thread t1 = new Thread(a);
		Thread t2 = new Thread(a);
		
		t1.setName("A");
		t2.setName("B");
		
		t1.start();
		t2.start();
	}
}
class Account implements Runnable{
	private double balance;
	public void run(){
		for(int i = 0; i < 3; i++){
			synchronized(this){
				notify();//�̵߳�ͨ�ŵķ���
				balance += 1000;
				try {
					Thread.currentThread().sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + ":" + balance);
				try {
					wait();//�̵߳�ͨ�ŵķ���
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}