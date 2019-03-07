package exception.a03exceptiontest;
//异常处理的练习题：
public class ReturnExceptionDemo {
	public static void main(String[] args) {
		try{
			methodA();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
//		此时上面的程序的异常已经完全处理好了，所以会执行下面的程序
		System.out.println();
		
		int i = methodB();
		System.out.println(i);
	}
//	方法：
	static void methodA(){
		try{
			System.out.println("进入方法A");
			throw new RuntimeException("制造一个异常给你~");
		}finally{
			System.out.println("我是方法A中一定要执行的代码~");
		}
	}
//	通过下面方法B中的return，来体会程序的执行顺序/finally的作用：
	static int methodB(){
		try{
			System.out.println("进入方法B");
			return 1;
		}finally{
			System.out.println("我是方法B中一定要执行的代码~");
			return 2;
		}
	}
}
