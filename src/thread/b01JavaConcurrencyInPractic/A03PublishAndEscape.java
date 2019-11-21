package thread.b01JavaConcurrencyInPractic;

/**
 * 3.2 发布与逸出。（笔记：G:\mystudy\studynotes\javaSE\thread\《Java并发编程实战》笔记.xmind）
 * 
 * 探讨：发布一个尚未构造完成的对象，这会导致该对象的逸出。
 * 
 * 问：
 * 	1. 怎样才是构造完成了呢？
 * 		答：对象完成了初始化（实例变量初始化完成 + 执行完动态代码块 + 执行完构造函数）
 * 
 * 理解下面的代码时，需要先理顺代码的执行顺序。
 * 
 * @author May
 * 2019年11月16日
 */
public class A03PublishAndEscape {
	public static void main(String[] args) {
		new B(new A());//先完成B的初始化，再做A的初始化。
	}
}

class B{
	//声明这两个成员变量是为了验证“B的实例还未完成初始化就发布给了A，这会导致B的实例的逸出。”
	public int number;
	public int number2;
	
	public B(A a) {
		System.out.println("B的构造函数");
		number = 10;//number会赋值成功。
		a.escape(this);//this是B的实例，此时B的构造方法还未执行完，this是不完整的，将this发布给a对象时this逸出了。
		number2 = 20;//B的实例逸出后，number2应该会赋值失败。
		System.out.println("==> b.number2 = " + number2);//==> b.number2 = 20
	}
}

class A{
	public  void escape(B b) {
		System.out.println("A的构造函数");
		System.out.println("b.number = " + b.number);
		System.out.println("b.number2 = " + b.number2);
	}
}
