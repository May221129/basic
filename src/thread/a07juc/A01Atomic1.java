package thread.a07juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ԭ�ӱ�����java.util.concurrent.atomic���µ��ࡣ
 */
public class A01Atomic1 {
	public static void main(String[] args) {
		AtomicTest at = new AtomicTest();
		for(int i = 0; i < 10; i++){
			new Thread(at).start();
		}
	}
}

class AtomicTest implements Runnable{
	
	private AtomicInteger num = new AtomicInteger();//ԭ�ӱ������൱��Integer��ֻ��AtomicInteger����CAS�㷨��֤���̰߳�ȫ�ԡ�
	
	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getNum());
	}
	
	public int getNum(){
		return num.getAndIncrement();//��ȡ������
	}
}