package oop.constructor;
//构造器的练习题
public class TestConstructor2 {
	public static void main(String[] args) {	
//		方法一、使用构造器来计算三角形的面积
		TriAngle ta = new TriAngle(2,9);
		System.out.println(ta.getArea());;	
//		方法二、直接调用方法来计算三角形的面积
//		TriAngle ta = new TriAngle();//没有设置显式的构造器，所以是默认构造器
//		ta.setBase(4);
//		ta.setHeight(3);
//		System.out.println("该三角形的面积为：" + ta.getArea());
		
	}
}
class TriAngle{
//	属性：
	private double base;//底边长
	private double height;//高
//	构造器：
	public TriAngle(double base,double height){
		this.base = base;//this.表示当前对象，后面的base表示的是形参
		this.height = height;
	} 
//	方法：
	public void setBase(double base){
		this.base = base;
	}
	public double getBase(){
		return base;
	}
	public void setHeight(double height){
		this.height = height;
	}
	public double getHeight(){
		return height;
	}
//	计算三角形的面积，前面已经写了设置高、底边的方法了，所以可以直接用前面设置的高和底边来计算面积！！！
	public double getArea(){
		return (this.base * this.height) / 2;
	}
}