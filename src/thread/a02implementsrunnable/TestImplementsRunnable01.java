package thread.a02implementsrunnable;
/**
 * �������̵߳ĵڶ��ַ�ʽ��ʵ��Runnable�ӿڣ�
 */
public class TestImplementsRunnable01 {
	public static void main(String[] args) {
//3������һ��Runnable�ӿ�ʵ����Ķ���
		SubThread1 st = new SubThread1();
//4�����˶�����Ϊ�βδ��ݸ�Thread��Ĺ������У�����Thread��Ķ��󣬴˶���Ϊһ���̣߳�
		Thread t = new Thread(st);	
//5������start()����:�����̲߳�ִ��run():
		t.start();
//		�ٴ���һ���̣߳������ڼ̳�Thread��ĵ�һ�ִ������̵߳ķ�������
		Thread t1 = new Thread(st);
		t1.start();
	}
}
//1������һ��ʵ����Runnable�ӿڵ��ࣺ
class SubThread1 implements Runnable{
//2��ʵ��Runnable�ӿڵĳ��󷽷���
	public void run(){
		for(int i = 1; i <= 100; i++){
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
	}
}