package oop.equals;

import org.junit.Test;

/**
 * 1. == 和 equals()的详细比较；
 * 2.String类对于 == 和 equals()方法的特殊性；
 * 
 * 详细展开：
 * 1. == 和 equals()的详细比较；
 * 
 * 2.String类对于 == 和 equals()方法的特殊性；
 * 	对比两个String类型的对象是否相等，要用equals()方法进行比较。
 * 
 */
public class EqualsNote {
	
	/**
	 * 1.比较两个直接声明的String
	 * 2.用 == 来比较两个String对象是否相等；
	 * 3.用equals()方法来比较两个String对象是否相等；
	 */
	@Test
	public void testString(){
		String i = "赖";
		String j = "赖";
		System.out.println(i == j);//true
		System.out.println(i.equals(j));//true
		
		String z = new String("赖");
		System.out.println(i == z);//false
		System.out.println(i.equals(z));//true
	}
}
