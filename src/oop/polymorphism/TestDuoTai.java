package oop.polymorphism;
//多态之方法的调用：
public class TestDuoTai {
	public static void main(String[] args) {
		Persons p = new Man();
//		p.info();
//==>注意：对象p是不能调用子类中特有的inof()方法的
		p.show();
//==>注意：对象p此时调用的是子类中重写后的show()方法
		p.eat();
//==>注意：对象p此时调用的是父类中eat()方法	
	}
}
//父类：
class Persons{
	public void eat(){
		System.out.println("我是父类Persons中的eat()方法");
	}
	public void show(){
		System.out.println("我是父类Persons中的show()方法");
	}
}
//子类：
class Man extends Persons{
	public void show(){
		System.out.println("我是子类Man中的show()方法");
	}
	public void info(){
		System.out.println("我是子类Man中的info()方法");
	}
}