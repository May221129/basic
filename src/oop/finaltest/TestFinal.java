package oop.finaltest;
//final�����գ�����ϰ��
//�ֱ������������ԡ������������������ã�
public class TestFinal {

}
class A{
//��final���������ԣ������Ծ���һ��������һ����ʼ���󣬲����ٱ���ֵ
	final int I = 10;//���Ը�����ʽ�ĸ�ֵ
	final String NAME;//����ͨ������������ֵ
	final int ID;//����ͨ��������������ֵ
	final int AGE;//����ͨ��������������ֵ
//	����飺
	{
		NAME = "AA";
	}
//	��������
	public A(){
		ID = 10001;
		AGE = 6;
	}
//	public void setAge(){
//		AGE = 5;
//	}
	public void show(){
		System.out.println("���ǹ���final֪ʶ�����ϰ��");
	}
//��final�����εķ����������ٱ���д��
	public final void info(){
		System.out.println("��Ҫ�ú�ѧϰ��");
	}
}

//��final���ε��࣬�����ٱ��̳У�
final class B extends A{
	public void show(){
		System.out.println("��Ҳ�ǹ���final֪ʶ�����ϰ~");
	}
//	public final void info(){
//		System.out.println("��ҲҪ�ú�ѧϰ~");
//	}
}

//C��̳в���B�ࣺ
//class C extends B{
//	
//}