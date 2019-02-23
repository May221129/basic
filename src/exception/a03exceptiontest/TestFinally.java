package exception.a03exceptiontest;
//Exception异常的处理之finally的使用：
public class TestFinally {
    public static void main(String[] args) {
	
    }
//    方法�?
    public static int mothod(){
	try{
	    System.out.println(10/0);
	    return 1;
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    System.out.println("我是�?定会执行的程序~");
	    return 2;
	}
    }
}
