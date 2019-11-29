package oop.finaltest;

import java.util.ArrayList;

/**
 * ��final��������Ϊ��Ա����������ʱ��
 * 	1.������һ����ʼ���Ͳ����ٸø����õ�ַ�������Ըı�����ָ��Ķ���
 * 
 * @author May
 * 2019��11��27��
 */
public class A02ReferenceWithFinal {
	public static void main(String[] args) {
		D d = new D();
		d.setNumber(10);
		C c = new C(d);//�� d �� dList ��������������ø�ֵ��C��C�ĳ����󣬾Ͳ����ٸ��ġ��滻��C�������������ˡ�
		System.out.println(d.getNumber());
		
		//�����Ը�����Ϊ������������ָ��Ķ��е�ʵ�ʶ������ݣ�
		d.setNumber(20);
		System.out.println(d.getNumber());
		
		D d1 = new D();
		D d2 = new D();
		c.addD(d1);
		c.addD(d2);
		System.out.println(c.containsD(d1));
		System.out.println(c.containsD(d2));
	}
}
class C{
	private final ArrayList<D> dList = new ArrayList<>();//��ʽΪ������ֵ
	private final D d;
	
	public C(D d) {//ͨ�����캯��Ϊ������ֵ
		this.d = d;
	}
	
	public void addD(D d) {
		dList.add(d);
	}
	public boolean containsD(D d) {
		return dList.contains(d);
	}
	
	//��final���εĳ�Ա������������ͨ�����������и�ֵ���޸��ˣ�
//	public void setD(D d) {
//		this.d = d;
//	}
	
//	public void setList(ArrayList<D> dList) {
//		this.dList= dList;
//	}
}

class D{
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}