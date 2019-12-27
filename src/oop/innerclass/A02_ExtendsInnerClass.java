package oop.innerclass;

/**
 * 一个类该如何继承到一个内部类？【内部类最好不让继承，不然容易乱。】
 * 答：使用专用语法，明确产生该关联性。
 * 	【在《JAVA编程思想》有道关于继承inner classes（内隐类）的例题：
 * 	由于inner class的构造函数必须连接到一个reference指向outer class 对象身上，
 * 	所以当你继承inner class时，事情便稍微复杂些。问题出在“指向outer class对象”的那个神秘reference必须被初始化。
 * 	但derived class之内不存在可连接的缺省对象。】
 * 
 * @author May
 * 2019年12月27日
 */
public class A02_ExtendsInnerClass extends Outer.Inner {//内部类的子类
	
	//让一个类能成功继承一个内部类：使用专用语法，明确产生该关联性。 <----------------------------------------------------------
	A02_ExtendsInnerClass(Outer outer) {
		outer.super();//WithInner类的父类是Object，所以这里为什么要特意写这行代码？
	}
	
	//主动引用之main方法：先加载、初始化main方法所在的InheritInner类，发现该类有父类，所以先加载其父类WithInner.Inner，
	//so：是怎么触发WithInner类的加载、初始化的？没有WithInner的实例，内部类Inner又从何而来？
	public static void main(String[] args) {
		Outer outer = new Outer();
    	new A02_ExtendsInnerClass(outer);
	}
}

class Outer {//内部类所在的类
	class Inner{//内部类
	}
}
