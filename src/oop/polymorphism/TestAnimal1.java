package oop.polymorphism;
//�����������������������̬��==>����1
public class TestAnimal1 {
	public static void main(String[] args) {//���
//		����һ����������ͣ�����ʵ���Ϸŵ���һ������Ķ���
		Animal a1 = new Dog();//newһ��Dog����Animal
		a1.eat();//�Թ�ͷ
//		a1.smile();����д�ᱨ��smile()δ����
//		�����Ҫ����Dog�����еķ�������Ҫ����ǿ��ת�������£�
		if(a1 instanceof Dog){//ע��ؼ��� instanceof
			Dog d = (Dog)a1;
			d.smile();//ǿת֮�󣬾��ܹ����õ�Dog�������еķ�����
		}
		if(a1 instanceof Cat){
			Cat c = (Cat)a1;
			c.cry();
		}
		
		Animal a2 = new Cat();//newһ��Cat����Animal
	}
}