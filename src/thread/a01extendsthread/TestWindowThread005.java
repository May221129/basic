package thread.a01extendsthread;
/**
 * ���̰߳�ȫ����������
 * 
 * ���ö��̣߳��̳�Thread��ķ�ʽ��ģ���Ʊ������Ʊ��3�����ڣ���Ʊ��100�ţ�
 * �˳�������̵߳İ�ȫ���⣺��ӡ��Ʊʱ���������Ʊ����Ʊ
 */
public class TestWindowThread005 {
	public static void main(String[] args) {
		
		Window w1 = new Window();
		Window w2 = new Window();
		Window w3 = new Window();
		
		w1.setName("����1");
		w2.setName("����2");
		w3.setName("����3");
		
		w1.start();
		w2.start();
		w3.start();
	}
}
class Window extends Thread{
	private static int ticket = 100;
//==>ticket������Ҫע�⣺��Ʊ��Ϊ100������100��Ҫ���ó�static����Ϊ������ԡ�
	public void run(){
		for(;;){
			if(ticket > 0){
				try {
					Thread.currentThread().sleep(10);//�ó�����ܴ��ڰ�ȫ���⣬����Ʊ����Ʊ��������Ҫ�Ŵ������ȫ���⡣
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "��Ʊ,Ʊ��Ϊ:" + ticket--);
			}else{
				break;
			}
		}
	}
}