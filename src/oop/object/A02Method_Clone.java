package oop.object;

import org.junit.Test;

/**
 * Object��clone()������ʵ�ֶ����ǳ���ƣ�ֻ��ʵ����Cloneable�ӿڲſ��Ե��ø÷����������׳�CloneNotSupportedException�쳣
 * 
 * @author May
 * 2019��12��26��
 */
public class A02Method_Clone {
	/**
	 * Aû��ʵ��Cloneable�ӿڣ����Ի��׳���֧�ֿ�¡���쳣
	 */
	@Test
	public void unImplementsCloneable() {
		A a = new A();
		try {
			a.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * AAʵ����Cloneable�ӿڣ����Բ����׳���֧�ֿ�¡���쳣
	 */
	@Test
	public void implementsCloneable() {
		AA aa = new AA();
		try {
			aa.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}

class A{
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

class AA implements Cloneable{
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}