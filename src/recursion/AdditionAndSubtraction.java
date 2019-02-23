package recursion;

import java.util.Scanner;
import org.junit.Test;

/**
 * 递归的练习：分别使用for循环和递归来实现 累加、累乘
 */
public class AdditionAndSubtraction {
	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			int x = scanner.nextInt();
			
			System.out.println(getProduct3(x));
			
			System.out.println(add(x));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
	
	/**
	 * 累加：用递归的方式来做
	 * 如：x=5
	 * 	5+4的累加，4的累加=4+3的累加，3的累加=3+2的累加，2的累加=2+1；
	 */
	public static int add(int x){
		return (x > 1) ? (x + add(x-1) ) : ((x < 0) ? 0 : 1 );
	}
	
	/**
	 * 累加：用循环的方式来计算
	 */
	@Test
	public void getAdd(){
		int j = 0;
		for(int i = 0; i <= 3; i++){
			j += i;
		}
		System.out.println(j);
	}
	
	/**
	 * 用递归求：N的阶层，如：客户输入5，求出5*4*3*2*1的积。==>有返回值的递归
	 * 1.递归就是自己调自己；
	 * 2.递归的出口：x>1
	 */
	public static int getProduct3(int x){
		return (x > 1) ? (x * getProduct3(x-1) ) : ((x < 0) ? 0 : 1 );
	}
	
	/**
	 * 这个实现方法设计的不好，每个数都有它自己的意义，x是变量，这里把i代替了x的变量的作用，x又用来记录乘积。容易混乱。
	 */
	@Test
	public void getProduct(){//求：N的阶层，如：客户输入5，求出5*4*3*2*1的积。
		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		for(int i = (x-1); i > 0; i-- ){
			x = x * i;
		}
		System.out.println(x);
	}
	
	@Test
	public void getProduct2(){//求：N的阶层，如：客户输入5，求出5*4*3*2*1的积。
		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		int sum = 1;
		while(x > 0){
			sum *= x;
			x--;
		}
		System.out.println(sum);
	}
}
