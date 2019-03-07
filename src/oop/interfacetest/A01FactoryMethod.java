package oop.interfacetest;
/**
 * �ӿڵ�Ӧ�á����������������ģʽ��FactoryMethod��
 * ����һ�����������������Ƚϸ��ӵ�һ������ģ����Ƕ��⣬���ܹ�����һ�����յĽ�������������ߡ����磺��Ҫ��һ�����ԵĶ��󣬵��ǵ��Ե��������̷ǳ����ӣ���ʱ����ù���ȥ������ԣ����������������ߡ�
 */
public class A01FactoryMethod {
	public static void main(String[] args) {
		
		IWorkFactory i1 = new StudentWorkFactory();
		i1.getWork().doWork();
		
		IWorkFactory i2 = new TeacherWorkFactory();
		i2.getWork().doWork();
		
		System.out.println("-------------");
		
		Work w1 = new StudentWork();
		w1.doWork();
		
		Work w2 = new TeacherWork();
		w2.doWork();
	}
}
interface IWorkFactory{//�ӿڣ�IWorkFactory
	public abstract Work getWork();
}
//���������и��������ֱ�����ѧ����������ʦ������
class StudentWorkFactory implements IWorkFactory{
	@Override
	public Work getWork() {
		return new StudentWork();
	}
}
class TeacherWorkFactory implements IWorkFactory{
	@Override
	public Work getWork() {
		return new TeacherWork();
	}
}

interface Work {//�ӿڣ�Work
	public abstract void doWork();
}
class StudentWork implements Work{
	@Override
	public void doWork() {
		System.out.println("ѧ���Ĺ�����ѧϰ");
	}
}
class TeacherWork implements Work{
	@Override
	public void doWork() {
		System.out.println("��ʦ�Ĺ����ǽ�������");
	}
}