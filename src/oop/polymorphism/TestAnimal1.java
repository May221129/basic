package oop.polymorphism;
//体会面向对象的特征三——多态性==>测试1
public class TestAnimal1 {
	public static void main(String[] args) {//入口
//		声明一个父类的类型，但是实际上放的是一个子类的对象
		Animal a1 = new Dog();//new一个Dog赋给Animal
		a1.eat();//吃骨头
//		a1.smile();这样写会报错：smile()未定义
//		如果想要调用Dog里特有的方法，需要进行强制转换，如下：
		if(a1 instanceof Dog){//注意关键字 instanceof
			Dog d = (Dog)a1;
			d.smile();//强转之后，就能够调用到Dog类里特有的方法了
		}
		if(a1 instanceof Cat){
			Cat c = (Cat)a1;
			c.cry();
		}
		
		Animal a2 = new Cat();//new一个Cat赋给Animal
	}
}