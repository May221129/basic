package oop.permissionmodifier;

import org.junit.Test;

/**
 * һ����ЩȨ�����η��������������ֶμ��������ֶεķ��ʷ�Χ��
 * �𣺣����ֶ����ԣ���ͬ��Ȩ�����η������ֶκ󣬸��ֶοɱ����ʵķ�Χ��Ȩ�����η��ķ�Χһ�¡�
 * 
 * �������η�����һ��һģһ���ġ�
 * 
 * @author May
 * 2019��12��26��
 */
public class A01_ModifyFieldAndMethod {
	public String a;
	protected String b;
	String c;
	private String d;
	
	public void test() {//1.���ڲ�������Ȩ��Ȩ�����η������ֶκ����ڲ����Է��ʵ��������ֶ�
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
	}
}

class A01_ModifyField2{//2. ͬһ������public��protected��ȱʡ�����ԣ���private���У�
	@Test
	public void test() {
		A01_ModifyFieldAndMethod a01 = new A01_ModifyFieldAndMethod();
		System.out.println(a01.a);
		System.out.println(a01.b);
		System.out.println(a01.c);
//		System.out.println(a01.d);// ��
	}
//	3. ��ͬ�� + �����ࣺoop.accessmodifier2.A01_ModifyField3��
//	4. ��ͬ�� + ���ࣺoop.accessmodifier2.A01_ModifyField3�ļ��е�A01_ModifyField4��
}