package oop.wrapperclass;
//���ڰ�װ�����ϰ��
import org.junit.Test;

public class TestWrapper {
	@Test
	public void test1(){
//�ֶ�ת����������������ת�ɰ�װ�ࣺ
		int i = 10;
		Integer i1 = new Integer(i);
		System.out.println(i1.toString());
		
		boolean b = true;
		Boolean b1 = new Boolean(b);
		System.out.println(b1);
		
		Boolean b2 = new Boolean("false");
		System.out.println(b2);
		
//�ֶ�ת������װ��ת�ɻ����������ͣ�
		Integer i2 = 123;
		int i3 = i2.intValue();
		System.out.println(i3);
		
		Boolean b3 = true;
		boolean b4 = b3.booleanValue();
		
//�Զ�װ�䣺������������ת�ɰ�װ�ࣺ
		int i4 = 90;
		Integer i5 = i4;
		
		Integer i6 = 100;
		
		Boolean b5 = true;
		
//�Զ����䣺��װ��ת�ɻ����������ͣ�
		Integer i7 = 101;
		int i8 = i7;
		System.out.println("i8: " + i8);
		
		int i9 = 101;//������Ĵ���һ��,ֻ�Ǽ�д��
		System.out.println("i9: " + i9);
	}
	
	@Test
	public void test2(){
//������������ת��String�ࣺ
		int i = 3;
		String str1 = i + "";
		System.out.println("str1: " + str1);
//��װ��ת��String�ࣺ
		Integer i1 = 4;
		String str2 = String.valueOf(i1);
		System.out.println("str2: " + str2);
		
		String str3 = i1.toString();//ͨ��toString()������תҲ���ԡ�
	}
}
