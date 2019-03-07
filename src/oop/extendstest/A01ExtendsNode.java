package oop.extendstest;

import org.junit.Test;

/**
 * 1.论点：子类是否能继承父类的所有属性和方法：
 * 	答：Java官网文档的解释：父类中的公有方法和域(属性)，在类继承中将会被子类继承，但是私有的将不能被继承。
 * 		那么在继承中子类如何才能继承到父类的私有域呢？答案是：在子类的构造方法中通过super()方法调用父类的构造方法。”
 * 	【总共有三种观点，详情见：https://www.zhihu.com/question/51345942/answer/145388196】
 * 
 * 2.在实际编程过程中，我们应该怎么处理子类和父类的属性、方法的继承问题：
 * 	① 对于父类中已有的属性，子类不要再去声明相同的属性了。
 * 		如果子类中也需要访问、修改这个属性，只需在父类中将该属性声明为protected，这样子类也能继承该属性，且可以进行访问和修改。
 * 	② 对于对象的声明方式，下面两种分别是有用泛型的和没使用泛型，选择哪种需要看具体需求。
 * 		Super s = new Subclass();
 * 		Subclass s = new Subclass();
 * 		举例：
 * 		List list = new ArrayList<>();
 * 		这么声明的好处：List是ArrayList的接口，ArrayList中几乎
 * 	③ 如果子类要重写父类中已有的某个属性，那么子类一定要将和该属性相关的方法都重写，
 * 		且在需要修改子类自己的这个属性时，通过方法来操作，否则在使用多态的时候，实际访问、修改的还是父类的属性。
 * 		如：test00()所示。
 */
public class A01ExtendsNode {
	/**
	 * 父类中没有，但子类独有的方法。
	 */
	@Test
	public void test02(){
		/**
		 * 创建一个B对象赋值给父类A的引用ba
		 * 对于子类独有的方法，ba不能访问。
		 */
//		A ba = new B();
//		ba.info();
		
		//父类也不能访问子类独有的方法：
//		A a = new A();
//		a.info();
		
		B b = new B();
		b.info();
	}
	
	/**
	 * 父类中有个private修饰的属性i，通过getJ()获得j的值;
	 * 子类中也有个private修饰的属性i，通过getJ()获得j的值。
	 */
	@Test
	public void test01(){
		A a = new A();
		System.out.println(a.getJ());//11
		
		B b = new B();
		System.out.println(b.getJ());//22
		
		A ba = new B();
		System.out.println(ba.getJ());//22
	}
	
	/**
	 * 父类中有个public修饰的属性i;
	 * 子类中也有个public修饰的属性i。
	 * 当创建一个“对象为子类，但引用为父类”的对象时，访问“子类重写了的属性”，这时访问的是父类的属性，而非子类的这个重写的属性。
	 */
	@Test
	public void test00(){
		B b = new B();
		System.out.println("B b = new B(); ==> " + b.i);//2
		
		A a = new A();
		System.out.println("A a = new A(); ==> " + a.i);//1
		
		A ba = new B();
		System.out.println("B ba = new B(); ==> " + ba.i);//1（父类的i属性）
	}
}

class A{
	public int i = 1;
	
	private int j = 11;
	public void setJ(int newJ){
		this.j = newJ;
	}
	public int getJ(){
		return j;
	}
	
	protected String name = "周六";
	public void setName(String newName){
		this.name = newName;
	}
	public String getName(){
		return name;
	}
}

class B extends A{
	public int i = 2;
	
	private int j = 22;
	public void setJ(int newJ){
		this.j = newJ;
	}
	public int getJ(){
		return j;
	}
	
	public void setName(String newName){
		name = newName;
	}
	public String getName(){
		return name;
	}
	
	public void info(){
		System.out.println("子类独有的info()方法。");
	}
}