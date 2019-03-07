package thread.a02implementsrunnable;
/**
 * �����̰߳�ȫ����������
 * 
 * ���ö��߳�:ʵ��Runnable�ӿڵķ�ʽ��ģ���Ʊ������Ʊ��3�����ڣ���Ʊ��100�ţ�
 * �˳�������̵߳İ�ȫ���⣺��ӡ��Ʊʱ���������Ʊ����Ʊ
 */
public class TestWindowRunnable002 {
	public static void main(String[] args) {
		Window1 w1 = new Window1();
		
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
class Window1 implements Runnable{
	private int ticket = 100;
	public void run(){
		for(;;){
			if(ticket > 0){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + ":" + ticket--);
			}else{
				break;
			}
		}
	}
}