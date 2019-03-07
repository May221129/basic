package oop.polymorphism;
//体会面向对象的特征三——多态性==>测试2
public class TestAnimal2 {
//	入口
	public static void main(String[] args) {
		TestAnimal2 ta2 = new TestAnimal2();//之所有这么声明，是因为要调用这个TestAnimal2中的func方法。
		ta2.func(new Animal());//如果这么写，调用的是父类中的方法。
		System.out.println();
		ta2.func(new Dog());//如果这么写，调用的是子类Dog中的方法。
		System.out.println();
		ta2.func(new Cat());//如果这么写，调用的是子类Dog中的方法。
//		声明一个父类的类型，但是实际上放的是一个子类的对象
	}
//	方法
	public void func(Animal p){//注意：调用该方法的时候，"Animal p"这个入参，怎么写？？？
//		调用父类中的方法，可以直接调用
		p.eat();
		p.sleep();
//		调用子类里特有的方法，需要进行强制转换，如下：		
		if(p instanceof Dog){//注意关键字 instanceof
			Dog d = (Dog)p;
			d.smile();//强转之后，就能够调用到Dog类里特有的方法了
		}
		if(p instanceof Cat){
			Cat c = (Cat)p;
			c.cry();
		}
	}
}