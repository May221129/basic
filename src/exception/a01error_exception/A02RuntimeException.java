package exception.a01error_exception;

import org.junit.Test;

/**
 * 异常处理之Exception类型的异常：
 * 认识常见的4种运行时异常。
 */
public class A02RuntimeException {
	
	/** 
	 * 1、数组下标越界的异常：ArrayIndexOutOfBoundsException
	 * 注意：左右两端都有可能 越界
	 */
	@Test
	public void test1(){
		int[] i = new int[10];
		System.out.println(i[10]);//右端越界
		System.out.println(i[-1]);//左端越界
	}
	
	/**
	 * 2、算术异常：ArithmeticException
	 */
	@Test
	public void test2(){
		int i = 5;
		System.out.println(i / 0);
	}
	
	/**
	 * 3、类型转换异常：ClassCastException
	 */
	@Test
	public void test3(){
		Object obj = new String();//向上转型
		int i = (int)obj;//向下转型
//		上面的转型相当于“把男人转化成人，在把人转化成女人”，所以编译时不会报错，运行时才会报错。
//		下面这种转换在编译时就会报错：相当于把男人强转为女人
//		int i = (int)new String();
	}	
	
	/**
	 * 4、空指针异常：NullPointerException
	 */
	@Test
	public void test4(){
		Person p = new Person();
		p = null;
		System.out.println(p.toString());
//		如果上面的有异常的程序没有注释掉，程序从上往下执行，执行的错误的地方就会停止，则下面这个正常的程序也不会执行得到。
		String s = new String("AA");
		System.out.println(s.length());
	}
	
	class Person{}
}
