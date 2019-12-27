package oop.permissionmodifier;

/**
 * 一、提问：四个权限修饰符，哪些可以用来修饰内部类？
 * 答：通过1、2可以看到，不管是修饰非静态内部类，还是修饰静态内部类，四种权限修饰符都可以用。
 * 
 * 二、四种修饰符修饰内部类后的可访问范围。
 * 答：
 * 	3、类内部：四种权限修饰符修饰的都可以访问到（非静态内部类和静态内部类都一样）；
 * 	4、同一个包：除了private，其他三种权限修饰符修饰的都可以访问到（（非静态内部类和静态内部类都一样）；
 * 	5、不同包+子类：除了public，其他三种修饰符修饰的都不可以访问到（非静态内部类和静态内部类都一样）；
 * 		▲问：用protected修饰内部类，其对子类可见是对哪个子类？内部类所在类的子类？还是内部类自己的子类？
 * 		答：不管是“内部类所在类的子类”还是“内部类自己的子类”，在“不同包+子类”的情况下，都是只有public修饰的可以访问到。
 * 
 * @author May
 * 2019年12月27日
 */
public class A01_ModifierInnerClass {
	//1.普通内部类：四种权限修饰符都可以；
	public class A{}
	protected class B{}
	class C{}
	private class D{}
	
	//2.静态内部类：四种权限修饰符都可以；
	public static class E{}
	protected static class F{}
	static class G{}
	private class H{}
	
	//3.访问范围：在类内部，四种权限修饰的都可以访问到（非静态内部类和静态内部类都一样）
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

class A01_ModifierInnerClass2{//4.访问范围：同一个包，除了private，其他三种权限修饰符都可以（非静态内部类和静态内部类都一样）
	public void test() {
		//创建非静态内部类：
		A01_ModifierInnerClass a01 = new A01_ModifierInnerClass();
		a01.new A();
		a01.new B();
		a01.new C();
//		a01.new D();// ×
		
		//创建静态内部类：
		new A01_ModifierInnerClass.E();
		new A01_ModifierInnerClass.F();
		new A01_ModifierInnerClass.G();
//		new A01_ModifierInnerClass.H();// ×
	}
}