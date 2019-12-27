package oop.permissionmodifier2;

import org.junit.Test;

import oop.permissionmodifier.A01_ModifyFieldAndMethod;

/**
 * 接“oop.accessmodifier.A01_ModifyFieldAndMethod”
 * 
 * @author May
 * 2019年12月26日
 */
public class A01_ModifyFieldAndMethod2 {//不同包 + 非子类：只有public可以，protected、缺省、private都不行；
	@Test
	public void test() {
		A01_ModifyFieldAndMethod a01 = new A01_ModifyFieldAndMethod();
		System.out.println(a01.a);
	}
}

class A01_ModifyField4 extends A01_ModifyFieldAndMethod{//不同包 + 子类：public 和 protected 可以，缺省和 private 不行；
	@Test
	public void test() {
		System.out.println(super.a);
		System.out.println(super.b);
//		System.out.println(super.c);// ×
//		System.out.println(super.d);// ×
	}
}