package thread.a01extendsThread;
/**
 * û���̰߳�ȫ����������
 * 
 * ���ö��̣߳��̳�Thread��ķ�ʽ��ģ���Ʊ������Ʊ��3�����ڣ���Ʊ��100�ţ�
 * �˳�������̵߳İ�ȫ���⣺��ӡ��Ʊʱ���������Ʊ����Ʊ������ͬ�������synchronized��������д���
 */
public class TestWindowThread006 {
	public static void main(String[] args) {
		
		Window2 w1 = new Window2();
		Window2 w2 = new Window2();
		Window2 w3 = new Window2();
		
		w1.setName("����1");
		w2.setName("����2");
		w3.setName("����3");
		
		w1.start();
		w2.start();
		w3.start();
	}
}
class Window2 extends Thread{
	private static int ticket = 100;
	static Object obj = new Object();
	public void run(){
		while(true){
//			synchronized(this){//�����this��ʾw1,w2,w3,�������ﲻ��дthis��
			synchronized(obj){
				if(ticket > 0){
					try {
						Thread.currentThread().sleep(10);//�ó�����ܴ��ڰ�ȫ���⣬����Ʊ����Ʊ��������Ҫ�Ŵ������ȫ���⡣
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "��Ʊ,Ʊ��Ϊ:" + ticket--);
				}
			}
		}
	}
}