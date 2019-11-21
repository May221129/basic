package thread.b01JavaConcurrencyInPractic;

/**
 * 3.2 发布与逸出（笔记：G:\mystudy\studynotes\javaSE\thread\《Java并发编程实战》笔记.xmind）
 * 探讨：在构造函数中调用一个可改写的实例方法时（既不是私有方法，也不是终结方法），同样会导致this引用在构造过程中逸出。
 * 
 * @author May
 * 2019年11月16日
 */
public class A03PublishAndEscape2 {
	public static void main(String[] args) {
		new C();
	}
}

class C{
	private int number;
	private int number2;
	
	public C() {
		number = 10;
		//匿名内部类D是可以访问到C的成员变量的，这是匿名内部类的特性。
		new D() {
			@Override
			public void test(C c) {
				//C的实例在这里逸出了：这里即使什么也没写，也代表了C的成员变量已经暴露给了D
			}
		};
		number2 = 20;
	}
}

class D{
	public void test(C c) {}
}
