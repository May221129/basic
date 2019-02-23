package oop.statictest;
/**
 * static的练习：
 */
public class A03StaticTest_Circle {
	public static void main(String[] args) {
		
	Circle c1 = new Circle(2.0);
	Circle c2 = new Circle(3.0);
	Circle c3 = new Circle();
	System.out.println(Circle.getTotal());//3
	
	c3.setInfo("我是一个可爱的圆");
	System.out.println(c1.getInfo());//因为c3已经充值了info()方法
	}
}
class Circle{
	private double radius;//半径
	private static String info = "我是一个圆";
	private static int total = 0;//计创建了几个对象
	
	public Circle(){
		total++;
	}
	public Circle(double radius){
		this.radius = radius;
		total++;
	}
	
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public static String getInfo() {
		return info;
	}
	public static void setInfo(String info) {
		Circle.info = info;
	}
	public static int getTotal() {
		return total;
	}
}