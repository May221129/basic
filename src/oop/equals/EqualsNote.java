package oop.equals;

import org.junit.Test;

/**
 * 1. == �� equals()����ϸ�Ƚϣ�
 * 2.String����� == �� equals()�����������ԣ�
 * 
 * ��ϸչ����
 * 1. == �� equals()����ϸ�Ƚϣ�
 * 
 * 2.String����� == �� equals()�����������ԣ�
 * 	�Ա�����String���͵Ķ����Ƿ���ȣ�Ҫ��equals()�������бȽϡ�
 * 
 */
public class EqualsNote {
	
	/**
	 * 1.�Ƚ�����ֱ��������String
	 * 2.�� == ���Ƚ�����String�����Ƿ���ȣ�
	 * 3.��equals()�������Ƚ�����String�����Ƿ���ȣ�
	 */
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
