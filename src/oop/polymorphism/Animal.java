package oop.polymorphism;
//�����������������������̬��==>�������ࡢ����
class Animal{
//	���ԣ�
	protected String eat;//��
	protected String sleep;//˯
//	������
	public void eat(){
		System.out.println("�ԡ�");
	}
	public void sleep(){
		System.out.println("˯����");
	}
}
class Dog extends Animal{
//	���ԣ�
	private String smile;
//	������
	public void eat(){//�Ը�����eat��������д
		System.out.println("�Թ�ͷ��");
	}
	public void smile(){
		System.out.println("��Ц��");
	}
}
class Cat extends Animal{
//	���ԣ�
	private String cry;
//	������
	public void eat(){//�Ը�����eat��������д
		System.out.println("���㡣");
	}
	public void cry(){
		System.out.println("��ޡ�");
	}
}