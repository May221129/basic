package exception.a03exceptiontest;
//�쳣�������ϰ�⣺
public class ReturnExceptionDemo {
	public static void main(String[] args) {
		try{
			methodA();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
//		��ʱ����ĳ�����쳣�Ѿ���ȫ������ˣ����Ի�ִ������ĳ���
		System.out.println();
		
		int i = methodB();
		System.out.println(i);
	}
//	������
	static void methodA(){
		try{
			System.out.println("���뷽��A");
			throw new RuntimeException("����һ���쳣����~");
		}finally{
			System.out.println("���Ƿ���A��һ��Ҫִ�еĴ���~");
		}
	}
//	ͨ�����淽��B�е�return�����������ִ��˳��/finally�����ã�
	static int methodB(){
		try{
			System.out.println("���뷽��B");
			return 1;
		}finally{
			System.out.println("���Ƿ���B��һ��Ҫִ�еĴ���~");
			return 2;
		}
	}
}
