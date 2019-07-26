package oop.equalsAndHashCode;

import org.junit.Test;

/**
 * String类对于 == 和 equals()方法的特殊性；
 */
public class A02StringEqualsMethod {
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
