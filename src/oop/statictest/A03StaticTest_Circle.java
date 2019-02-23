package oop.statictest;
/**
 * static����ϰ��
 */
public class A03StaticTest_Circle {
	public static void main(String[] args) {
		
	Circle c1 = new Circle(2.0);
	Circle c2 = new Circle(3.0);
	Circle c3 = new Circle();
	System.out.println(Circle.getTotal());//3
	
	c3.setInfo("����һ���ɰ���Բ");
	System.out.println(c1.getInfo());//��Ϊc3�Ѿ���ֵ��info()����
	}
}
class Circle{
	private double radius;//�뾶
	private static String info = "����һ��Բ";
	private static int total = 0;//�ƴ����˼�������
	
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