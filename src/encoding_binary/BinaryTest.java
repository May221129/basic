package encoding_binary;

import org.junit.Test;

/**
 * �й��ֽڡ������Ƶ�����
 */
public class BinaryTest {
	
	@Test
	public void test1(){
		
		byte a = 6;
		int b = 6;
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));
		
		byte c = -6;
		int d = -6;
		System.out.println(Integer.toBinaryString(c));
		System.out.println(Integer.toBinaryString(d));
	}
	
	/**
	 * ����һ��int���͵�������ȡ������������ж��ٸ�"1".
	 * ʵ�ַ�ʽһ��
	 */
	@Test
	public void test2(){
		int x = -5;
		String xStr = Integer.toBinaryString(x);
		System.out.println(xStr);
		int count = 0;
		for(int i = 0; i < xStr.length(); i++){
//			if(xStr.charAt(i) == '1'){
			if(xStr.charAt(i) == 49){//���ַ�ʽ������
				count++;
			}
		}
		System.out.println(count);
	}
	
	/**
	 * ����һ��int���͵�������ȡ������������ж��ٸ�"1".
	 * ʵ�ַ�ʽ����
	 */
	@Test
	public void test3(){
		int x = -5;
		int n = 1;
		int count = 0;
		for(int i = 0; i < 32; i++){
			int y = n << i;
			if((x & y) != 0){
				count++;
			}
		}
		System.out.println(count);
	}
	
	@Test
	public void test4(){
		int count = 0;
		int number = -5;
		while(number != 0){
			number = number & (number - 1);
			count++;
		}
		System.out.println(count);
	}
	
}
