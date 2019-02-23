package exception.a01error_exception;

import org.junit.Test;

/**
 * �쳣����֮Exception���͵��쳣��
 * ��ʶ������4������ʱ�쳣��
 */
public class A02RuntimeException {
	
	/** 
	 * 1�������±�Խ����쳣��ArrayIndexOutOfBoundsException
	 * ע�⣺�������˶��п��� Խ��
	 */
	@Test
	public void test1(){
		int[] i = new int[10];
		System.out.println(i[10]);//�Ҷ�Խ��
		System.out.println(i[-1]);//���Խ��
	}
	
	/**
	 * 2�������쳣��ArithmeticException
	 */
	@Test
	public void test2(){
		int i = 5;
		System.out.println(i / 0);
	}
	
	/**
	 * 3������ת���쳣��ClassCastException
	 */
	@Test
	public void test3(){
		Object obj = new String();//����ת��
		int i = (int)obj;//����ת��
//		�����ת���൱�ڡ�������ת�����ˣ��ڰ���ת����Ů�ˡ������Ա���ʱ���ᱨ������ʱ�Żᱨ��
//		��������ת���ڱ���ʱ�ͻᱨ���൱�ڰ�����ǿתΪŮ��
//		int i = (int)new String();
	}	
	
	/**
	 * 4����ָ���쳣��NullPointerException
	 */
	@Test
	public void test4(){
		Person p = new Person();
		p = null;
		System.out.println(p.toString());
//		�����������쳣�ĳ���û��ע�͵��������������ִ�У�ִ�еĴ���ĵط��ͻ�ֹͣ����������������ĳ���Ҳ����ִ�еõ���
		String s = new String("AA");
		System.out.println(s.length());
	}
	
	class Person{}
}
