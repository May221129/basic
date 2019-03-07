package oop.extendstest;
//类的继承练习题-2-圆
//父类
public class Circle {
//	属性
	private double radius;
//	构造器
	public Circle(){
		System.out.println("这是父类Circle的空的构造器");
		radius = 1;//将半径初始化为1
	}
	public Circle(double radius){
		this.radius = radius;
	}
//	方法
	public void setRadius(double radius){//设置圆的半径
		this.radius = radius;
	}
	public double getRadius(){//获取圆的半径
		return radius;
	}
	public double findArea(){//求圆的面积
		System.out.println("Circle'area is : " + Math.PI * radius *radius);
		return Math.PI * radius *radius;	
	}
}
