package designPatterns.a01singleton;
/**
 * �������ģʽ������ʽ���̰߳�ȫ���⣬ʹ��ͬ������-synchronized�����ķ�ʽ������
 * 
 * Ϊʲô����ģʽ��synchronizedͬ������ʱ��Ҫ�������жϣ�
 * 	����ģʽ�ڵ�һ�δ��������ʱ�򣬲Ż�����̰߳�ȫ�����󴴽���֮���ǲ������̰߳�ȫ����ġ�
 * 	�ڶ����жϣ���һ�δ�������ʱ�ã������̰߳�ȫ���⣻
 * 	��һ���жϣ����󴴽��ú�ʹ���������ʱ���ж��á�
 */
public class A03TestSingleton {
	public static void main(String[] args) {
		//ͨ��"Singleton.����"����������
		Singleton2 s1 = Singleton2.getInstance();
		Singleton2 s2 = Singleton2.getInstance();
		//��֤������������ʵ��ͬһ������
		System.out.println(s1 == s2);//���Ϊtrue����֤�ɹ�
	}
}
class Singleton2{
	private Singleton2(){}
	private static Singleton2 instance = null;
	public static Singleton2 getInstance(){
		if(instance == null){//���д���ܴ�̶�������˴����ִ��Ч��
//			���ھ�̬�������ԣ�ʹ�õ�ǰ�౾��䵱����
			synchronized(Singleton2.class){
				if(instance == null){
					instance = new Singleton2();
				}
			}
		}
		return instance;
	}
}