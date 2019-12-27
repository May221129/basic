package oop.permissionmodifier2;

import oop.permissionmodifier.A01_ModifierInnerClass;

/**
 * 接“oop.permissionmodifier.A01_ModifierInnerClass”
 * 
 * @author May 2019年12月27日
 */
public class A01_ModifierInnerClass3 extends A01_ModifierInnerClass {
	public void test() {
		// 5.1 访问范围：内部类所在类的子类（不是内部类自己的子类）：除了public，其他三种修饰符修饰的都不可以访问到（非静态内部类和静态内部类都一样）；
		// 创建非静态内部类：
		A01_ModifierInnerClass a01 = new A01_ModifierInnerClass();
		a01.new A();
		// a01.new B();// ×
		// a01.new C();// ×
		// a01.new D();// ×

		// 创建静态内部类：
		new A01_ModifierInnerClass.E();
		// new A01_ModifierInnerClass.F();// ×
		// new A01_ModifierInnerClass.G();// ×
		// new A01_ModifierInnerClass.H();// ×
	}
}

// 5.2 访问范围：内部类自己的子类：除了public，其他三种修饰符修饰的都不可以访问到（非静态内部类和静态内部类都一样）；
class Aa extends A01_ModifierInnerClass.A {// public ： √
	public Aa(A01_ModifierInnerClass outer) {
		outer.super();
	}
}

// class Bb extends A01_ModifierInnerClass.B{// protected ： ×
// public Bb(A01_ModifierInnerClass outer) {
// outer.super();
// }
// }

//class Cc extends A01_ModifierInnerClass.C {// 缺省 ： ×
//	public Cc(A01_ModifierInnerClass outer) {
//		outer.super();
//	}
//}

//class Dd extends A01_ModifierInnerClass.D {// Private ： ×
//	public Dd(A01_ModifierInnerClass outer) {
//		outer.super();
//	}
//}
