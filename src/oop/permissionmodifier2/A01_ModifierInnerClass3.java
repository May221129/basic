package oop.permissionmodifier2;

import oop.permissionmodifier.A01_ModifierInnerClass;

/**
 * �ӡ�oop.permissionmodifier.A01_ModifierInnerClass��
 * 
 * @author May 2019��12��27��
 */
public class A01_ModifierInnerClass3 extends A01_ModifierInnerClass {
	public void test() {
		// 5.1 ���ʷ�Χ���ڲ�������������ࣨ�����ڲ����Լ������ࣩ������public�������������η����εĶ������Է��ʵ����Ǿ�̬�ڲ���;�̬�ڲ��඼һ������
		// �����Ǿ�̬�ڲ��ࣺ
		A01_ModifierInnerClass a01 = new A01_ModifierInnerClass();
		a01.new A();
		// a01.new B();// ��
		// a01.new C();// ��
		// a01.new D();// ��

		// ������̬�ڲ��ࣺ
		new A01_ModifierInnerClass.E();
		// new A01_ModifierInnerClass.F();// ��
		// new A01_ModifierInnerClass.G();// ��
		// new A01_ModifierInnerClass.H();// ��
	}
}

// 5.2 ���ʷ�Χ���ڲ����Լ������ࣺ����public�������������η����εĶ������Է��ʵ����Ǿ�̬�ڲ���;�̬�ڲ��඼һ������
class Aa extends A01_ModifierInnerClass.A {// public �� ��
	public Aa(A01_ModifierInnerClass outer) {
		outer.super();
	}
}

// class Bb extends A01_ModifierInnerClass.B{// protected �� ��
// public Bb(A01_ModifierInnerClass outer) {
// outer.super();
// }
// }

//class Cc extends A01_ModifierInnerClass.C {// ȱʡ �� ��
//	public Cc(A01_ModifierInnerClass outer) {
//		outer.super();
//	}
//}

//class Dd extends A01_ModifierInnerClass.D {// Private �� ��
//	public Dd(A01_ModifierInnerClass outer) {
//		outer.super();
//	}
//}
