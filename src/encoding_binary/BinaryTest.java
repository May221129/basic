package encoding_binary;

import org.junit.Test;

/**
 * 有关字节、二进制的讨论
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
	 * 给你一个int类型的数，获取到其二进制中有多少个"1".
	 * 实现方式一：
	 */
	@Test
	public void test2(){
		int x = -5;
		String xStr = Integer.toBinaryString(x);
		System.out.println(xStr);
		int count = 0;
		for(int i = 0; i < xStr.length(); i++){
//			if(xStr.charAt(i) == '1'){
			if(xStr.charAt(i) == 49){//两种方式都可以
				count++;
			}
		}
		System.out.println(count);
	}
	
	/**
	 * 给你一个int类型的数，获取到其二进制中有多少个"1".
	 * 实现方式二：
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
