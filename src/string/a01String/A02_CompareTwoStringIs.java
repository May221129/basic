package string.a01String;

import org.junit.Test;

/**
 * �Ƚ������ַ����Ƿ���ȣ��漰�ַ��������ء�
 * 
 * @author May
 * 2020��1��9��
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
