package oop.innerclass;

import oop.innerclass.A.C;

/**
 * ��δ�����̬�ڲ���Ķ���
 * ��δ����ڲ���Ķ���
 * 
 * @author May
 * 2019��11��26��
 */
public class ZhuDongYinYong {
	public static void main(String[] args) {
		//������̬�ڲ���Ķ���
		new A.B();
		
		//�����ڲ���Ķ���
		A a = new A();
		C c = a.new C();//��������д���ע�͵���ֻ��new A������ôC�ǲ��������ĳ�ʼ���ġ�
	}
}



class A{
	public static class B{
		static{
			System.out.println("B����ʼ����");
		}
	}
	
	public class C{
		{
			System.out.println("C����ʼ����");
		}
	}
}