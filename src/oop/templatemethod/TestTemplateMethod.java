package oop.templatemethod;
//ģ�巽�����ģʽ��
public class TestTemplateMethod {
	public static void main(String[] args) {
		SubTemplate s = new SubTemplate();
		s.spendTime();
	}
}
abstract class Template{//���ࣺģ��
	
	public abstract void code();
//==>�Ѳ�ȷ���ķ�����д��abstract���ͣ�������̳�ʱ����ʵ����������д����
	
	public void spendTime(){//��ȷ���ķ���д��
		long start = System.currentTimeMillis();//��ʼʱ��
		this.code();//���ó���
		long end = System.currentTimeMillis();//����ʱ��
		System.out.println("ִ��������򻨷�ʱ���� " + (end - start));
	}
}

class SubTemplate extends Template{//����
	public void code(){
		int i1 = 10;
		int i2 = 100;
		int i3 = i1 * i2;
		System.out.println(i3);
	}
}