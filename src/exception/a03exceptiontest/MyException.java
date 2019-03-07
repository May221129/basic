package exception.a03exceptiontest;
/*
 * 如何自定义一个异常类：
 */
//1.让自定义的异常类集成现有的异常类：
public class MyException extends RuntimeException{
//2.提供一个序列号，同时提供几个重载的构造器：
	static final long serialVersionUID = -9090862537345766939L;
	//可以通过这个序列号，唯一的确定一个异常类的对象
	public MyException(){
		
	}
	public MyException(String message){
		super(message);
	}
}
