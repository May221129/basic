package oop.polymorphism;
//体会面向对象的特征三——多态性==>声明父类、子类
class Animal{
//	属性：
	protected String eat;//吃
	protected String sleep;//睡
//	方法：
	public void eat(){
		System.out.println("吃。");
	}
	public void sleep(){
		System.out.println("睡觉。");
	}
}
class Dog extends Animal{
//	属性：
	private String smile;
//	方法：
	public void eat(){//对父类中eat方法的重写
		System.out.println("吃骨头。");
	}
	public void smile(){
		System.out.println("会笑。");
	}
}
class Cat extends Animal{
//	属性：
	private String cry;
//	方法：
	public void eat(){//对父类中eat方法的重写
		System.out.println("吃鱼。");
	}
	public void cry(){
		System.out.println("会哭。");
	}
}