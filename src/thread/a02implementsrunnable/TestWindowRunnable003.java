package thread.a02implementsrunnable;

/**
 * �̰߳�ȫ�������
 * ���ö��߳�:ʵ��Runnable�ӿڵķ�ʽ��ģ���Ʊ������Ʊ��3�����ڣ���Ʊ��100�ţ�
 * �˳�������̵߳İ�ȫ���⣺��ӡ��Ʊʱ���������Ʊ����Ʊ����������д�������ͬ�������synchronized��������д���
 */
public class TestWindowRunnable003 {
	public static void main(String[] args) {
		Window2 w1 = new Window2();
		
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
class Window2 implements Runnable{
//	Object obj = new Object();//���������ǳ�Ա�����������Ǿֲ�������
	private int ticket = 100;
	public void run(){
//		Object obj = new Object();//����಻���Ǿֲ����������ܷ������
		for(;;){
//			synchronized(obj){//ͬ���������������κ�һ�������䵱��Ҫ�����е��̱߳��빲��ͬһ������
			synchronized(this){//thisΪ��ǰ����
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
	}
}