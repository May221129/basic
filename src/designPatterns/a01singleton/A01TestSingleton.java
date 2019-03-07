package designPatterns.a01singleton;
/**
 * �������ģʽ������ʽ
 */
public class A01TestSingleton {
	public static void main(String[] args) {
		//ͨ��"Singleton.����"����������
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		//��֤������������ʵ��ͬһ������
		System.out.println(s1 == s2);//���Ϊtrue����֤�ɹ�
	}
}
//ֻ�ܴ�������ʵ����Singleton��
class Singleton{
	//1��˽�л���������ʹ��������ⲿ�����ٵ��õ��˹�������
	private Singleton(){
		
	}
	//2��������ڲ�����һ��static��ʵ������
	//3��˽�л��˶�������ֻ��ͨ�������ķ��������ã�
	private static Singleton instance = new Singleton();
	//4���˹����ķ�����ֻ��ͨ���������ã���Ϊ���õ���static�ģ�ͬʱ���ʵ��Ҳ����Ϊstatic��������
	public static Singleton getInstance(){
		return instance;
	}
}
