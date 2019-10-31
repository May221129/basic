package thread.a02implementsRunnable;
/**
 * �̰߳�ȫ�������
 * 
 * ���ö��߳�:ʵ��Runnable�ӿڵķ�ʽ��ģ���Ʊ������Ʊ��3�����ڣ���Ʊ��100�ţ�
 * �˳�������̵߳İ�ȫ���⣺��ӡ��Ʊʱ���������Ʊ����Ʊ����������д�������synchronizedͬ��������������д���
 */
public class A02WindowRunnable3 {
	public static void main(String[] args) {
		Window3 w1 = new Window3();
		
		Thread t1 = new Thread(w1);
		Thread t2 = new Thread(w1);
		Thread t3 = new Thread(w1);
		
		t1.setName("����1");
		t2.setName("����2");
		t3.setName("����3");
		
		t1.start();
		t2.start();
		t3.start();
	}
}
class Window3 implements Runnable{
	private int ticket = 100;
	public void run(){
		for(;;){
			show();
		}
	}
	public synchronized void show(){
		if(ticket > 0){
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + ":" + ticket--);
		}
	}
}