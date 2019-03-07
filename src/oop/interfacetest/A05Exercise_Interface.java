package oop.interfacetest;
/**
 * 接口interface的练习：
 * 接口与具体的实现类之间，也存在多态性：
 */
public class A05Exercise_Interface {
	public static void main(String[] args) {
		
		Car1 c1 = new Car1();
		A05Exercise_Interface.test1(c1);
		
		System.out.println(".............");
		
		A05Exercise_Interface.test2(c1);
		
		System.out.println(".............");
		
		A05Exercise_Interface.test1(new Car1());
	}
//	方法：下面的两个方法验证了“接口与具体的实现类之间，也存在多态性”
//==>因为下面的方法都是static，所以不依赖于对象而被调用
	public static void test1(Runner1 r){//Runner1 r = new Car1();
		r.start();//虚拟方法调用，实际调用这个方法的，还是后面造出来的对象
		r.run();
		r.stop();
	}
	public static void test2(Swimmer s){//Swimmer s = new Car1();
		s.swim();
	}
}
interface Runner1{//接口：跑步的功能
	public abstract void start();
	public abstract void run();
	public abstract  void stop();
}

interface Swimmer{//接口：游泳的功能
	public abstract void swim();
}

class Car1 implements Runner1,Swimmer{//Car类，实现Runner1和Swimmer接口

	public void start() {
		System.out.println("踩油门就开始跑");	
	}
	public void run() {
		System.out.println("可快可慢的跑");	
	}
	public void stop() {
		System.out.println("踩刹车就停了");
	}
	public void swim() {
		System.out.println("卡车不会游泳，掉水里就会坏掉的");
	}
}