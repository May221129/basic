package oop.polymorphism;
//��̬֮�����ĵ��ã�
public class TestDuoTai {
	public static void main(String[] args) {
		Persons p = new Man();
//		p.info();
//==>ע�⣺����p�ǲ��ܵ������������е�inof()������
		p.show();
//==>ע�⣺����p��ʱ���õ�����������д���show()����
		p.eat();
//==>ע�⣺����p��ʱ���õ��Ǹ�����eat()����	
	}
}
//���ࣺ
class Persons{
	public void eat(){
		System.out.println("���Ǹ���Persons�е�eat()����");
	}
	public void show(){
		System.out.println("���Ǹ���Persons�е�show()����");
	}
}
//���ࣺ
class Man extends Persons{
	public void show(){
		System.out.println("��������Man�е�show()����");
	}
	public void info(){
		System.out.println("��������Man�е�info()����");
	}
}