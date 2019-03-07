package designPatterns.a01singleton;
/**
 * 单例设计模式：饿汉式
 */
public class A01TestSingleton {
	public static void main(String[] args) {
		//通过"Singleton.方法"来创建对象：
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		//验证这两个对象，其实是同一个对象：
		System.out.println(s1 == s2);//结果为true，验证成功
	}
}
//只能创建单个实例的Singleton：
class Singleton{
	//1、私有化构造器，使得在类的外部不能再调用到此构造器：
	private Singleton(){
		
	}
	//2、在类的内部创建一个static的实例化：
	//3、私有化此对象，让其只能通过公共的方法来调用：
	private static Singleton instance = new Singleton();
	//4、此公共的方法，只能通过类来调用，因为设置的是static的，同时类的实例也必须为static的声明。
	public static Singleton getInstance(){
		return instance;
	}
}
