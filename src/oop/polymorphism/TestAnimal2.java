package oop.polymorphism;
//�����������������������̬��==>����2
public class TestAnimal2 {
//	���
	public static void main(String[] args) {
		TestAnimal2 ta2 = new TestAnimal2();//֮������ô����������ΪҪ�������TestAnimal2�е�func������
		ta2.func(new Animal());//�����ôд�����õ��Ǹ����еķ�����
		System.out.println();
		ta2.func(new Dog());//�����ôд�����õ�������Dog�еķ�����
		System.out.println();
		ta2.func(new Cat());//�����ôд�����õ�������Dog�еķ�����
//		����һ����������ͣ�����ʵ���Ϸŵ���һ������Ķ���
	}
//	����
	public void func(Animal p){//ע�⣺���ø÷�����ʱ��"Animal p"�����Σ���ôд������
//		���ø����еķ���������ֱ�ӵ���
		p.eat();
		p.sleep();
//		�������������еķ�������Ҫ����ǿ��ת�������£�		
		if(p instanceof Dog){//ע��ؼ��� instanceof
			Dog d = (Dog)p;
			d.smile();//ǿת֮�󣬾��ܹ����õ�Dog�������еķ�����
		}
		if(p instanceof Cat){
			Cat c = (Cat)p;
			c.cry();
		}
	}
}