package exception.a01error_exception;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.Test;

/**
 * 异常处理之Exception类型的异常的处理机制:try-catch
 */
public class A03RuntimeExceptionTryCatch {
	
	/**
	 * 1.空指针异常：NullPointerException
	 */
	@Test
	public void test4(){
		person4 p4 = new person4();
		try {
			p4 = null;
			System.out.println(p4.toString());
		} catch (NullPointerException e4) {
//			e4.printStackTrace();
			System.out.println(e4.getMessage());
		}
	}
	class person4{//Person类
		
	}
	
	/**
	 * 2.算术异常：ArithmeticException
	 */
	@Test
	public void test3(){
		try {
			int i3 = 10;
			System.out.println(i3 / 0);
		} catch (ArithmeticException e3) {
			e3.printStackTrace();
		}
	}
	
	/**
	 * 3.数组下标越界的异常：ArrayIndexOutOfBoundsException
	 */
	@Test
	public void test2(){
		try{
			int[] i2 = new int[2];
			System.out.println(i2[3]);
		}catch(Exception e2){
			System.out.println("出现异常了！");
			System.out.println(10 / 0);
		}finally{
		    System.out.println("就算以前的程序出错不再执行了，我也要执行！");
		}
	}
	
	/**
	 * 4.输入的和所需的不匹配:InputMismatchException
	 */
	@Test
	public void test1(){
		Scanner s1 = new Scanner(System.in);
		try{
		    int i1 = s1.nextInt();
		    System.out.println(i1);
		}catch(ClassCastException e){
		    System.out.println("出现类型转换异常了!");
		}catch(InputMismatchException e){
		    System.out.println("输入的和所需的不匹配！");
		    e.getMessage();
		}
		System.out.println("好好学习，天天向上！");
		//上面的catch已经将异常处理完毕了，所以这个代码会正常执行。
	}
}
