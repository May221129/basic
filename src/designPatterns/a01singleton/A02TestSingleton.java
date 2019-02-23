package designPatterns.a01singleton;
/**
 * �������ģʽ������ʽ
 */
public class A02TestSingleton {
	public static void main(String[] args) {
		//ͨ��"Singleton.����"����������
		Singleton1 s1 = Singleton1.getInstance();
		Singleton1 s2 = Singleton1.getInstance();
		//��֤������������ʵ��ͬһ������
		System.out.println(s1 == s2);//���Ϊtrue����֤�ɹ�
	}
}
class Singleton1{
	//1��˽�л���������ʹ��������ⲿ�����ٵ��õ��˹�������	
	private Singleton1(){
	
	}
	//2��������ڲ�����һ��static��ʵ������
	//3��˽�л��˶�������ֻ��ͨ�������ķ��������ã�	
	private static Singleton1 instance = null;
	//4���˹����ķ�����ֻ��ͨ���������ã���Ϊ���õ���static�ģ�ͬʱ���ʵ��Ҳ����Ϊstatic��������
	public static Singleton1 getInstance(){
		if(instance == null){
			instance = new Singleton1();
		}
		return instance;
	}
}