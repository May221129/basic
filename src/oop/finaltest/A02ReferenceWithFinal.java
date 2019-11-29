package oop.finaltest;

import java.util.ArrayList;

/**
 * 用final来修饰作为成员变量的引用时：
 * 	1.该引用一旦初始化就不能再该更引用地址；但可以改变引用指向的对象。
 * 
 * @author May
 * 2019年11月27日
 */
public class A02ReferenceWithFinal {
	public static void main(String[] args) {
		D d = new D();
		d.setNumber(10);
		C c = new C(d);//将 d 和 dList 这两个对象的引用赋值给C做C的常量后，就不能再更改、替换掉C中这两个引用了。
		System.out.println(d.getNumber());
		
		//但可以更改作为常量的引用所指向的堆中的实际对象内容：
		d.setNumber(20);
		System.out.println(d.getNumber());
		
		D d1 = new D();
		D d2 = new D();
		c.addD(d1);
		c.addD(d2);
		System.out.println(c.containsD(d1));
		System.out.println(c.containsD(d2));
	}
}
class C{
	private final ArrayList<D> dList = new ArrayList<>();//显式为常量赋值
	private final D d;
	
	public C(D d) {//通过构造函数为常量赋值
		this.d = d;
	}
	
	public void addD(D d) {
		dList.add(d);
	}
	public boolean containsD(D d) {
		return dList.contains(d);
	}
	
	//被final修饰的成员变量都不能再通过方法来进行赋值或修改了：
//	public void setD(D d) {
//		this.d = d;
//	}
	
//	public void setList(ArrayList<D> dList) {
//		this.dList= dList;
//	}
}

class D{
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}