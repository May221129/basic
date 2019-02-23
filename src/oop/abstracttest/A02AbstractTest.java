package oop.abstracttest;

/**
 * 抽象类abstract的练习：
 * 	abstract不能用来修饰属性、构造器、被private、final、static这三个关键词修饰的方法：
 */
public class A02AbstractTest {
	public static void main(String[] args) {
		Student s = new Student();
		System.out.println(s.getId());
	}
}
class Student extends Person{
	
}
abstract class Person{
	// 1.abstract不能用来修饰属性
//	protected abstract String name;
	protected static int age;
	protected final int id;
	protected final static int handsNumber = 2;
	
	//代码块：
	{
		id = 1001;
	}
	
	// 2.abstract不能用来修饰构造器，因为构造器不能被重写
//	public abstract Person(){
//		super();
//	}

	// 3.abstract不能用来修饰private的方法，因为子类不能覆盖（或重写）private的方法。
//	private abstract void setAge(int age){
//		this.age = age;
//	}
	
	public int getId() {//获取id的方法
		return id;
	}
	
	// 4.abstract不能用来修饰final，因为final是最终的，不能再被重写
//	public  abstract final int getHandsNumber(){//获取收的数量
//		return handsNumber;
//	}
	
	// 5.abstract不能 用来修饰有static的方法，因为static可以直接通过类来调用，但被abstract修饰之后，就成为没有方法体的一个残缺的方法了，调用时没有了意义
//	public static abstract int getAge(int age){
//		return age;
//	}
}