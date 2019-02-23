package oop.extendstest;
//类的继承练习题-2
//测试
public class TestCylinder {
	public static void main(String[] args) {
		
//		创建Cylinder的对象：cylinder
		Cylinder cylinder = new Cylinder();
		
		cylinder.setRadius(3);//设置圆柱底部的半径
		
		cylinder.setLength(10);//设置圆柱的长度
		
//		cylinder.findArea();圆柱底部面积,在求圆柱的体积的时候，已经有算圆柱底部面积了，所以这步可以省略。
		
		cylinder.findVolume();//圆柱的体积
		System.out.println("cylinder的表面积为：" + cylinder.findArea());//输出圆柱的表面积
		
//		调用父类中 public Cylinder(double length) 这个构造器
		Cylinder c = new Cylinder(45);
		System.out.println(c.getRadius() + " , " + c.getLength());//通过输出来检查是否调用是否正确
	}
}