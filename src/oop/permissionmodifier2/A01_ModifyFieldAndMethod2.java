package oop.permissionmodifier2;

import org.junit.Test;

import oop.permissionmodifier.A01_ModifyFieldAndMethod;

/**
 * �ӡ�oop.accessmodifier.A01_ModifyFieldAndMethod��
 * 
 * @author May
 * 2019��12��26��
 */
public class A01_ModifyFieldAndMethod2 {//��ͬ�� + �����ֻࣺ��public���ԣ�protected��ȱʡ��private�����У�
	@Test
	public void test() {
		A01_ModifyFieldAndMethod a01 = new A01_ModifyFieldAndMethod();
		System.out.println(a01.a);
	}
}

class A01_ModifyField4 extends A01_ModifyFieldAndMethod{//��ͬ�� + ���ࣺpublic �� protected ���ԣ�ȱʡ�� private ���У�
	@Test
	public void test() {
		System.out.println(super.a);
		System.out.println(super.b);
//		System.out.println(super.c);// ��
//		System.out.println(super.d);// ��
	}
}