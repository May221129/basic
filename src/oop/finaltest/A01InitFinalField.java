package oop.finaltest;

/**
 * final�����գ�����ϰ���ֱ����������ࡢ���ԡ���������������ᷢ��ʲô����
 * 1. ��final���������ԣ������Ծ���һ��������һ����ʼ������ʾ��ֵ��ͨ������顢��������ֵ���󣬲����ٱ���ֵ��������ͨ������Ϊ�丳ֵ����
 * 2. ��final�����εķ����������ٱ���д��
 * 3. ��final���ε��࣬�����ٱ��̳У�
 * 4. final�����������ι�������������ֻ����public��protected��private�����Σ�
 * @author May
 * 2019��11��27��
 */
public class A01InitFinalField {

}
class A{
//1.��final���������ԣ������Ծ���һ��������һ����ʼ���󣬲����ٱ���ֵ
	final int I = 10;//���Ը�����ʽ�ĸ�ֵ
	final String NAME;//����ͨ������������ֵ
	final int ID;//����ͨ��������������ֵ
	final int AGE;//����ͨ��������������ֵ
	private int count;
//	����飺
	{
		NAME = "AA";
	}
//	��������
	public A(){
		ID = 10001;
		AGE = 6;
	}
	//4. final�����������ι�������
//	public final A(int count) {
//		this.count = count;
//	}
	
//	public void setAge(){
//		AGE = 5;
//	}
	public void show(){
		System.out.println("���ǹ���final֪ʶ�����ϰ��");
	}
//2. ��final�����εķ����������ٱ���д��
	public final void info(){
		System.out.println("��Ҫ�ú�ѧϰ��");
	}
}

//3. ��final���ε��࣬�����ٱ��̳У�
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