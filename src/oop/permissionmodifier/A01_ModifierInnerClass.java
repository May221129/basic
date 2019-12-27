package oop.permissionmodifier;

/**
 * һ�����ʣ��ĸ�Ȩ�����η�����Щ�������������ڲ��ࣿ
 * ��ͨ��1��2���Կ��������������ηǾ�̬�ڲ��࣬�������ξ�̬�ڲ��࣬����Ȩ�����η��������á�
 * 
 * �����������η������ڲ����Ŀɷ��ʷ�Χ��
 * ��
 * 	3�����ڲ�������Ȩ�����η����εĶ����Է��ʵ����Ǿ�̬�ڲ���;�̬�ڲ��඼һ������
 * 	4��ͬһ����������private����������Ȩ�����η����εĶ����Է��ʵ������Ǿ�̬�ڲ���;�̬�ڲ��඼һ������
 * 	5����ͬ��+���ࣺ����public�������������η����εĶ������Է��ʵ����Ǿ�̬�ڲ���;�̬�ڲ��඼һ������
 * 		���ʣ���protected�����ڲ��࣬�������ɼ��Ƕ��ĸ����ࣿ�ڲ�������������ࣿ�����ڲ����Լ������ࣿ
 * 		�𣺲����ǡ��ڲ�������������ࡱ���ǡ��ڲ����Լ������ࡱ���ڡ���ͬ��+���ࡱ������£�����ֻ��public���εĿ��Է��ʵ���
 * 
 * @author May
 * 2019��12��27��
 */
public class A01_ModifierInnerClass {
	//1.��ͨ�ڲ��ࣺ����Ȩ�����η������ԣ�
	public class A{}
	protected class B{}
	class C{}
	private class D{}
	
	//2.��̬�ڲ��ࣺ����Ȩ�����η������ԣ�
	public static class E{}
	protected static class F{}
	static class G{}
	private class H{}
	
	//3.���ʷ�Χ�������ڲ�������Ȩ�����εĶ����Է��ʵ����Ǿ�̬�ڲ���;�̬�ڲ��඼һ����
	public void test() {
		new A();
		new B();
		new C();
		new D();
		
		new E();
		new F();
		new G();
		new H();
	}
}

class A01_ModifierInnerClass2{//4.���ʷ�Χ��ͬһ����������private����������Ȩ�����η������ԣ��Ǿ�̬�ڲ���;�̬�ڲ��඼һ����
	public void test() {
		//�����Ǿ�̬�ڲ��ࣺ
		A01_ModifierInnerClass a01 = new A01_ModifierInnerClass();
		a01.new A();
		a01.new B();
		a01.new C();
//		a01.new D();// ��
		
		//������̬�ڲ��ࣺ
		new A01_ModifierInnerClass.E();
		new A01_ModifierInnerClass.F();
		new A01_ModifierInnerClass.G();
//		new A01_ModifierInnerClass.H();// ��
	}
}