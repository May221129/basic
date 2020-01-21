package string.a01String;

import java.util.Random;

import org.junit.Test;

/**
 * 探究：String.intern()方法。
 * 
 * @author May 2020年1月9日
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

		// 池中没有相应的字符串内容：
		String s1 = new String("hello");// 在堆中生成"hello"字符串对象
		String s2 = s1.intern();// 执行“s1.intern()”时，String Pool中没有"hello"，会直接先将s1对象复制到池中
		System.out.println(s1 == s2); // false
		System.out.println(s1.intern()); // hello

		// 池中有：
		String s3 = "world"; // 直接在String Pool中生成"world"字符串
		String s4 = s3.intern(); // 从池中拿"world"字符串返回给s4
		System.out.println(s3 == s4); // true
	}
}
