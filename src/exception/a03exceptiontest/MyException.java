package exception.a03exceptiontest;
/*
 * ����Զ���һ���쳣�ࣺ
 */
//1.���Զ�����쳣�༯�����е��쳣�ࣺ
public class MyException extends RuntimeException{
//2.�ṩһ�����кţ�ͬʱ�ṩ�������صĹ�������
	static final long serialVersionUID = -9090862537345766939L;
	//����ͨ��������кţ�Ψһ��ȷ��һ���쳣��Ķ���
	public MyException(){
		
	}
	public MyException(String message){
		super(message);
	}
}
