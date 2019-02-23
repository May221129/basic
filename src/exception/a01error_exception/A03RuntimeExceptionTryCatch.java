package exception.a01error_exception;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.Test;

/**
 * �쳣����֮Exception���͵��쳣�Ĵ������:try-catch
 */
public class A03RuntimeExceptionTryCatch {
	
	/**
	 * 1.��ָ���쳣��NullPointerException
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
	class person4{//Person��
		
	}
	
	/**
	 * 2.�����쳣��ArithmeticException
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
	 * 3.�����±�Խ����쳣��ArrayIndexOutOfBoundsException
	 */
	@Test
	public void test2(){
		try{
			int[] i2 = new int[2];
			System.out.println(i2[3]);
		}catch(Exception e2){
			System.out.println("�����쳣�ˣ�");
			System.out.println(10 / 0);
		}finally{
		    System.out.println("������ǰ�ĳ��������ִ���ˣ���ҲҪִ�У�");
		}
	}
	
	/**
	 * 4.����ĺ�����Ĳ�ƥ��:InputMismatchException
	 */
	@Test
	public void test1(){
		Scanner s1 = new Scanner(System.in);
		try{
		    int i1 = s1.nextInt();
		    System.out.println(i1);
		}catch(ClassCastException e){
		    System.out.println("��������ת���쳣��!");
		}catch(InputMismatchException e){
		    System.out.println("����ĺ�����Ĳ�ƥ�䣡");
		    e.getMessage();
		}
		System.out.println("�ú�ѧϰ���������ϣ�");
		//�����catch�Ѿ����쳣��������ˣ�����������������ִ�С�
	}
}
