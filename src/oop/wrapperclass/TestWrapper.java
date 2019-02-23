package oop.wrapperclass;
//关于包装类的练习：
import org.junit.Test;

public class TestWrapper {
	@Test
	public void test1(){
//手动转换：基本数据类型转成包装类：
		int i = 10;
		Integer i1 = new Integer(i);
		System.out.println(i1.toString());
		
		boolean b = true;
		Boolean b1 = new Boolean(b);
		System.out.println(b1);
		
		Boolean b2 = new Boolean("false");
		System.out.println(b2);
		
//手动转化：包装类转成基本数据类型：
		Integer i2 = 123;
		int i3 = i2.intValue();
		System.out.println(i3);
		
		Boolean b3 = true;
		boolean b4 = b3.booleanValue();
		
//自动装箱：基本数据类型转成包装类：
		int i4 = 90;
		Integer i5 = i4;
		
		Integer i6 = 100;
		
		Boolean b5 = true;
		
//自动拆箱：包装类转成基本数据类型：
		Integer i7 = 101;
		int i8 = i7;
		System.out.println("i8: " + i8);
		
		int i9 = 101;//和上面的代码一致,只是简写了
		System.out.println("i9: " + i9);
	}
	
	@Test
	public void test2(){
//基本数据类型转成String类：
		int i = 3;
		String str1 = i + "";
		System.out.println("str1: " + str1);
//包装类转成String类：
		Integer i1 = 4;
		String str2 = String.valueOf(i1);
		System.out.println("str2: " + str2);
		
		String str3 = i1.toString();//通过toString()方法来转也可以。
	}
}
