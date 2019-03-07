package oop.interfacetest;
/**
 * �ӿ�interface����ϰ��
 * �ӿ�������ʵ����֮�䣬Ҳ���ڶ�̬�ԣ�
 */
public class A05Exercise_Interface {
	public static void main(String[] args) {
		
		Car1 c1 = new Car1();
		A05Exercise_Interface.test1(c1);
		
		System.out.println(".............");
		
		A05Exercise_Interface.test2(c1);
		
		System.out.println(".............");
		
		A05Exercise_Interface.test1(new Car1());
	}
//	���������������������֤�ˡ��ӿ�������ʵ����֮�䣬Ҳ���ڶ�̬�ԡ�
//==>��Ϊ����ķ�������static�����Բ������ڶ����������
	public static void test1(Runner1 r){//Runner1 r = new Car1();
		r.start();//���ⷽ�����ã�ʵ�ʵ�����������ģ����Ǻ���������Ķ���
		r.run();
		r.stop();
	}
	public static void test2(Swimmer s){//Swimmer s = new Car1();
		s.swim();
	}
}
interface Runner1{//�ӿڣ��ܲ��Ĺ���
	public abstract void start();
	public abstract void run();
	public abstract  void stop();
}

interface Swimmer{//�ӿڣ���Ӿ�Ĺ���
	public abstract void swim();
}

class Car1 implements Runner1,Swimmer{//Car�࣬ʵ��Runner1��Swimmer�ӿ�

	public void start() {
		System.out.println("�����žͿ�ʼ��");	
	}
	public void run() {
		System.out.println("�ɿ��������");	
	}
	public void stop() {
		System.out.println("��ɲ����ͣ��");
	}
	public void swim() {
		System.out.println("����������Ӿ����ˮ��ͻỵ����");
	}
}