package thread.b01JavaConcurrencyInPractic;

/**
 * 3.4 不变性。（笔记：G:\mystudy\studynotes\javaSE\thread\《Java并发编程实战》笔记.xmind）
 * 当成员变量用final修饰后，该成员变量的赋值就只能在构造器或静态代码块中完成。
 * 
 * @author May
 * 2019年11月19日
 */
public class A03Final {
	
}

class E{
	private final int number;
	
//	{
//		number = 20;
//	}
	
	public E(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
//	public void setNumber(int number) {
//		this.number = number;
//	}
}