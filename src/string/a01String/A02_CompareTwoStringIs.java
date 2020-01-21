package string.a01String;

import org.junit.Test;

/**
 * 比较两个字符串是否相等，涉及字符串常量池。
 * 
 * @author May
 * 2020年1月9日
 */
public class A02_CompareTwoStringIs {
	@Test
	public void compare() {
		String s1 = "AB";
		String s2 = "AB";
		String s3 = new String("AB");
		System.out.println(s1 == s2);//true
		System.out.println(s1 == s3);//false
	}
}
