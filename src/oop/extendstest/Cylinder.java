package oop.extendstest;
//类的继承练习题-2-圆柱
//子类
public class Cylinder extends Circle{
	private double length;//属性
//	构造器
	public Cylinder(){
//		super();没有进行显式调用父类中某一构造器时，子类中的空参的构造器，也依旧会隐式的去调用父类中的空参的构造器。
//		super(123);有显式的调用父类中的某一构造器
		length = 1;//将长度初始化为1
	}
	public Cylinder(double length){
		super(33);
		this.length = length;
	}
	public void setLength(double length){//设置圆柱的长度
		this.length = length;
	}
	public double getLength(){//获取圆柱的长度
		return length;
	}
	
//	求圆柱的表面积：方法一：
//	public double findArea(){//重写父类Circle中的求圆面积的方法，改为求圆柱的表面积：兀rr * 2 + 2兀rh
//		return Math.PI * this.getRadius() * this.getRadius() * 2
//				+ 2 * Math.PI * this.getRadius() * length;
	
//	求圆柱的表面积：方法二：
	public double findArea(){//重写父类Circle中的求圆面积的方法，改为求圆柱的表面积：兀rr * 2 + 2兀rh
		return super.findArea() * 2 + 2 * Math.PI * this.getRadius() * length;
		
	}
	public double findVolume(){//计算圆柱的体积
		System.out.println("Cylinder'volnme is : " + Math.PI * this.getRadius() * this.getRadius() * length);
//		return Math.PI * radius *radius * length;//父类中的radius是protected时，可以直接调用
		return Math.PI * this.getRadius() * this.getRadius() * length;//这边也可以写成：return super.findArae * length;
	}
}
