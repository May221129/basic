package string.a01String;

import java.util.Random;

import org.junit.Test;

/**
 * ̽����String.intern()������
 * 
 * @author May 2020��1��9��
 */
public class A02Method_Intern {
	@Test
	public void testIntern() {
		String s1 = new String();
		System.out.println("s1.intern() : " + s1.intern());

		String s2 = new String("hello");
		System.out.println("s2.intern() : " + s2.intern());

		// ------------------------------------------------

		String s3 = new String("hello");
		System.out.println("s2 == s3 ? " + (s2 == s3));// false

		String s4 = "hello";
		System.out.println("s2 == s4 ? " + (s2 == s4));// false

		String s5 = s2.intern();
		System.out.println("s2 == s5 ? " + (s2 == s5));// false
		System.out.println("s4 == s5 ? " + (s4 == s5));// true
	}

	@Test
	public void test() {

		// ����û����Ӧ���ַ������ݣ�
		String s1 = new String("hello");// �ڶ�������"hello"�ַ�������
		String s2 = s1.intern();// ִ�С�s1.intern()��ʱ��String Pool��û��"hello"����ֱ���Ƚ�s1�����Ƶ�����
		System.out.println(s1 == s2); // false
		System.out.println(s1.intern()); // hello

		// �����У�
		String s3 = "world"; // ֱ����String Pool������"world"�ַ���
		String s4 = s3.intern(); // �ӳ�����"world"�ַ������ظ�s4
		System.out.println(s3 == s4); // true
	}
}
