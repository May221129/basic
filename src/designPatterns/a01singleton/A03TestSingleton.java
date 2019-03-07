package designPatterns.a01singleton;
/**
 * 单例设计模式：懒汉式的线程安全问题，使用同步机制-synchronized代码块的方式来处理：
 * 
 * 为什么懒汉模式用synchronized同步代码时，要做两层判断：
 * 	单例模式在第一次创建对象的时候，才会出现线程安全；对象创建好之后，是不会有线程安全问题的。
 * 	第二层判断：第一次创建对象时用，避免线程安全问题；
 * 	第一层判断：对象创建好后，使用这个对象时做判断用。
 */
public class A03TestSingleton {
	public static void main(String[] args) {
		//通过"Singleton.方法"来创建对象：
		Singleton2 s1 = Singleton2.getInstance();
		Singleton2 s2 = Singleton2.getInstance();
		//验证这两个对象，其实是同一个对象：
		System.out.println(s1 == s2);//结果为true，验证成功
	}
}
class Singleton2{
	private Singleton2(){}
	private static Singleton2 instance = null;
	public static Singleton2 getInstance(){
		if(instance == null){//这行代码很大程度上提高了代码的执行效率
//			对于静态方法而言，使用当前类本身充当锁：
			synchronized(Singleton2.class){
				if(instance == null){
					instance = new Singleton2();
				}
			}
		}
		return instance;
	}
}