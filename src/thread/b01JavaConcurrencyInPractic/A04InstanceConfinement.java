package thread.b01JavaConcurrencyInPractic;

/**
 * 4.2 实例封闭
 * 探究：一个类可以访问到自己依赖的成员变量的方法。
 * 
 * @author May
 * 2019年11月26日
 */
public class A04InstanceConfinement {
	public static void main(String[] args) {
		new BB(new AA()).print();
	}
}

class BB{
	private AA a;
	
	public BB(AA a) {
		this.a = a;
	}
	
	public void print() {
		System.out.println(a.getNumber());
	}
}

class AA{
	private int  number = 10;
	public int getNumber() {
		return number;
	}
	
}