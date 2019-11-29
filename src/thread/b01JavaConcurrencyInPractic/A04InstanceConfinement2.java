package thread.b01JavaConcurrencyInPractic;

import java.util.Vector;

/**
 * ��4���У��йء���ȫ���̰߳�ȫ�๹�ɵĳ��򲢲�һ�������̰߳�ȫ�ģ������̰߳�ȫ����Ҳ���԰������̰߳�ȫ���ࡣ���Ĳ���
 * ������̰߳�ȫ������һЩ���ϲ�������ԭ�Ӳ����������п��ܳ����̰߳�ȫ���⡣
 * 
 * @author May
 * 2019��11��27��
 */
public class A04InstanceConfinement2 {
	public static void main(String[] args) {
		SThread sThread = new SThread();
		Thread t1 = new Thread(sThread);
		Thread t2 = new Thread(sThread);
		Thread t3 = new Thread(sThread);
		
		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			Thread.sleep(50);//�����߳���˯һ�£�ȷ�������������̶߳�ִ�����ˡ�
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sThread.printElement();
	}
}

class SThread implements Runnable{
	private Vector<Integer> vector = new Vector<>();//Vector���̰߳�ȫ��ArrayList
	
	//��ԭ���Բ��������̰߳�ȫ���⣺
	@Override
	public void run() {
		if(!vector.contains(10)) {
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			vector.add(10);//vector���̰߳�ȫ�ļ��ϣ����ڸ�run()�����У�ȴ�ǡ��ȼ���ִ�С��ĸ��ϲ������Ƿ�ԭ�Ӳ���������û��ͬ��������»�����̰߳�ȫ���⡣
		}
	}
	
	//����ͬ������֤���̰߳�ȫ�ԣ�
//	@Override
//	public void run() {
//		synchronized(this) {
//			if(!vector.contains(10)) {
//				try {
//					Thread.currentThread().sleep(10);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				vector.add(10);
//			}
//		}
//	}
	
	public void printElement() {
		for(Integer element : vector) {
			System.out.println(element);
		}
	}
}