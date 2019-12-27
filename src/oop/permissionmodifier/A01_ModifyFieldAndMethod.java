package oop.permissionmodifier;

import org.junit.Test;

/**
 * 一、哪些权限修饰符可以用来修饰字段及被修饰字段的访问范围。
 * 答：：四种都可以，不同的权限修饰符修饰字段后，该字段可被访问的范围和权限修饰符的范围一致。
 * 
 * 二、修饰方法和一是一模一样的。
 * 
 * @author May
 * 2019年12月26日
 */
public class A01_ModifyFieldAndMethod {
	public String a;
	protected String b;
	String c;
	private String d;
	
	public void test() {//1.类内部：四种权限权限修饰符修饰字段后，类内部可以访问到被修饰字段
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
	}
}

class A01_ModifyField2{//2. 同一个包：public、protected、缺省都可以，就private不行；
	@Test
	public void test() {
		A01_ModifyFieldAndMethod a01 = new A01_ModifyFieldAndMethod();
		System.out.println(a01.a);
		System.out.println(a01.b);
		System.out.println(a01.c);
//		System.out.println(a01.d);// ×
	}
//	3. 不同包 + 非子类：oop.accessmodifier2.A01_ModifyField3；
//	4. 不同包 + 子类：oop.accessmodifier2.A01_ModifyField3文件中的A01_ModifyField4；
}