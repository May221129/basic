package oop.equalsAndHashCode;

import org.junit.Test;

/**
 * String����� == �� equals()�����������ԣ�
 */
public class A02StringEqualsMethod {
	@Test
	public void testString(){
		String i = "��";
		String j = "��";
		System.out.println(i == j);//true
		System.out.println(i.equals(j));//true
		
		String z = new String("��");
		System.out.println(i == z);//false
		System.out.println(i.equals(z));//true
	}
}
