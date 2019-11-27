package oop.innerclass;

import oop.innerclass.A.C;

/**
 * 如何创建静态内部类的对象？
 * 如何创建内部类的对象？
 * 
 * @author May
 * 2019年11月26日
 */
public class ZhuDongYinYong {
	public static void main(String[] args) {
		//创建静态内部类的对象：
		new A.B();
		
		//创建内部类的对象：
		A a = new A();
		C c = a.new C();//如果把这行代码注释掉，只是new A对象，那么C是不会进行类的初始化的。
	}
}



class A{
	public static class B{
		static{
			System.out.println("B被初始化了");
		}
	}
	
	public class C{
		{
			System.out.println("C被初始化了");
		}
	}
}