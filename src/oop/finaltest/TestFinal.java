package oop.finaltest;
//final（最终）的练习：
//分别用来修饰属性、构造器、方法的作用：
public class TestFinal {

}
class A{
//用final来修饰属性，此属性就是一个常量，一旦初始化后，不可再被赋值
	final int I = 10;//可以给它显式的赋值
	final String NAME;//可以通过代码块给它赋值
	final int ID;//可以通过构造器给它赋值
	final int AGE;//不能通过方法来给它赋值
//	代码块：
	{
		NAME = "AA";
	}
//	构造器：
	public A(){
		ID = 10001;
		AGE = 6;
	}
//	public void setAge(){
//		AGE = 5;
//	}
	public void show(){
		System.out.println("我是关于final知识点的练习。");
	}
//用final来修饰的方法，不能再被重写：
	public final void info(){
		System.out.println("我要好好学习！");
	}
}

//被final修饰的类，不能再被继承：
final class B extends A{
	public void show(){
		System.out.println("我也是关于final知识点的练习~");
	}
//	public final void info(){
//		System.out.println("我也要好好学习~");
//	}
}

//C类继承不到B类：
//class C extends B{
//	
//}