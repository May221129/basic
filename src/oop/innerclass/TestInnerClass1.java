package oop.innerclass;
//内部类的知识-局部内部类
public class TestInnerClass1 {

}
class OutClass{
//	下面这种使用方式比较不常见：
	public void method1(){
		class InnerClass{
			
		}
	}
//	下面是局部内部类常见的使用方式：
//	常常使用一个方法，使其返回值为某个类或接口的对象，而这个类或接口在方法内部创建：
//	使用方式一：有名类的对象
	public Comparable getComparable(){
		//1.创建一个实现Comparable接口的类
		class MyComparable implements Comparable{
			@Override
			public int compareTo(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
		}
		//2.返回一个实现类的对象
		return new MyComparable();
	}
//	使用方式二：匿名类的对象
	public Comparable getComparable1(){
		return new Comparable(){//这个是实现接口的一个匿名类，而非接口的构造器，接口是没有构造器的！
			@Override
			public int compareTo(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}
}