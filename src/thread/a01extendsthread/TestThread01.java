package thread.a01extendsthread;
/**
 * ���ʲô�Ƕ��̣߳�
 * 
 * ����һ�����̣߳����1-100֮����Ȼ���������ͬ���ģ����߳�ִ����ͬ�Ĳ�����
 * �������̵߳ĵ�һ�ַ�ʽ���̳�java.lang.Thread��
 */
public class TestThread01 {
	public static void main(String[] args) {
//3�������̶߳���
//�����ͬһ�����̵߳�run()������Ҫ�������Σ�����Ҫ������������Ķ��������ã�
		SubThread st = new SubThread();
		SubThread st1 = new SubThread();
//4�������̵߳�start()����;ע�⣺start()�������������ܣ����������̣߳��ڵ�����Ӧ��run()������
//5��һ���߳�ֻ�ܹ�ִ��һ��start():
		st.start();
//		st.start();
		st1.start();
//����ͨ��Thread��ʵ��������run()ȥ����һ���̣߳��൱���õ�ǰ�����߳�ִ��st�����run()����������st�߳��Լ�ִ��run()��ע��������ߵ�����~~~
//		st.run();
//6����д���߳�Ҫ��ɵĹ��ܣ�
		for(int i = 1; i <= 100; i++){
			System.out.println( Thread.currentThread().getName() + ": " + i);
		}
	}
}
//1������һ���̳���Thread������ࣺ
class SubThread extends Thread{
//2����дThread���е�run()������������ʵ�ָ������߳�Ҫ��ɵĹ��ܣ�
	public void run(){
		for(int i = 1; i <= 100; i++){
			System.out.println(  Thread.currentThread().getName() + ": " + i);
		}
	}
}