package thread.a01extendsThread;

/**
 * synchronizedͬ�������У�this��ʾ˭��������ʹ�ü̳�Threadʵ�ֶ��߳�ʱ�����׳���
 * 
 * ���ö��̣߳��̳�Thread��ķ�ʽ��ģ���Ʊ������Ʊ��3�����ڣ���Ʊ��100�ţ� �˳�������̵߳İ�ȫ���⣺��ӡ��Ʊʱ���������Ʊ����Ʊ��
 * ���ﲻ����synchronizedͬ���������д�����Ϊͬ������Ĭ�ϵ�����this��
 * ���ü̳�Thread�ഴ�����̵߳ķ�ʽ����ʹ��ʱ���ж������ģ�this��ʾ�����Ͳ���Ψһ�ģ����Ի��ǻ����̰߳�ȫ���⡣
 */
public class A03WindowThread3 {
	public static void main(String[] args) {

		Window3 w1 = new Window3();
		Window3 w2 = new Window3();
		Window3 w3 = new Window3();

		w1.setName("����1");
		w2.setName("����2");
		w3.setName("����3");

		w1.start();
		w2.start();
		w3.start();
	}
}

class Window3 extends Thread {
	private static int ticket = 100;// ������Դ==>�����̰߳�ȫ����
	// private int ticket = 100;//���ݸ��Զ������Դ==>�������̰߳�ȫ����
	static Object obj = new Object();

	public void run() {
		while (true) {
			show();
		}
	}
	/**
	 * �����̰߳�ȫ����
	 */
	public synchronized void show() {// ����Ĭ�ϵ�����this
		if (ticket > 0) {
			try {
				Thread.currentThread().sleep(100);// �ó�����ܴ��ڰ�ȫ���⣬����Ʊ����Ʊ��������Ҫ�Ŵ������ȫ���⡣
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "��Ʊ,Ʊ��Ϊ:" + ticket--);
		} 
	}
	
	/**
	 * �ı�һ��synchronized��������������ķ�Χ��������Ч�����̰߳�ȫ���⡣
	 */
	public void show2() {// ����Ĭ�ϵ�����this
		synchronized (obj) {
			if (ticket > 0) {
				try {
					Thread.currentThread().sleep(100);// �ó�����ܴ��ڰ�ȫ���⣬����Ʊ����Ʊ��������Ҫ�Ŵ������ȫ���⡣
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "��Ʊ,Ʊ��Ϊ:" + ticket--);
			} 
		}
	}
}