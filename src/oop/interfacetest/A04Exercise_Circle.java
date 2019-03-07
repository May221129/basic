package oop.interfacetest;
/**
 * 接口的练习题
 */
public class A04Exercise_Circle {
	public static void main(String[] args) {
		ComparableCircle c1 = new ComparableCircle(2.1);
		ComparableCircle c2 = new ComparableCircle(3.0);
		ComparableCircle c3 = new ComparableCircle(3.0);
		
		int i1 = c1.compaereTo(c2);
		System.out.println(i1);
		int i2 = c2.compaereTo(c3);
		System.out.println(i2);
	}
}

/**
 * 接口：写一个抽象的方法，由"子类"去实现
 */
interface CompareObject{
	int compaereTo(Object obj);//比较两个对象的大小
}

/**
 * 父类：圆
 */
class Circle{
	protected double radius;//半径
//	构造器：
	public Circle(){
		super();
	}
	public Circle(double radius){
		super();
		this.radius = radius;
	}
//	方法：
	public void setRadius(double radius){
		this.radius = radius;
	}
	public double getRadius(){
		return radius;
	}
}

/**
 * 声明一个ComparableCircle子类，该类继承Circle类：
 */
class ComparableCircle extends Circle implements CompareObject {
	public ComparableCircle(double radius){
		super(radius);
	}
	public int compaereTo(Object obj){//比较两个圆的大小:若返回值是0，代表相等；若为正数，代表当前对象大；负数调拨当前对象小。
		System.out.println("注意：若返回值是0，代表相等；若为正数，代表当前对象大；负数调拨当前对象小。");
		if(this == obj){
			return 0;
		}else if(obj instanceof ComparableCircle){
			ComparableCircle c = (ComparableCircle)obj;
			if(this.radius > c.radius){
				return 1;
			}else if(this.radius < c.radius){
				return -1;
			}else{
				return 0;
			}
		}else{
			throw new RuntimeException("传入的不是ComparableCircle的对象，不能进行比较！");
		}
	}
}