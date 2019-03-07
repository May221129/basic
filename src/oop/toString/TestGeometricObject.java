package oop.toString;
//equals()方法和toString()方法的练习
public class TestGeometricObject {
	public static void main(String[] args) {
//		创建两个对象，判断其颜色是否相等：
		Circle c1 = new Circle();
		c1.setColor("red");
//		也可以调用其他的有入参的构造器，直接设置好圆的某些属性，如下：
		Circle c2 = new Circle(30,"red",30);//通过构造器，半径、颜色、重量都设置好了
		System.out.println(c1.color.equals(c2.color));
//		利用equals()方法，比较其半径是否相等：
		c1.setRadius(20);
		System.out.println(c1.equals(c2));
//		利用toString()方法，输出其半径。
		System.out.println(c1.toString());
		System.out.println(c2.toString());
	}
}

class GeometricObject{//父类：几何形状
//	属性：
	protected String color;//颜色
	protected double weight;//重量
//	构造器：
	protected GeometricObject(){
		super();
//	初始化对象的color属性为"white"，weight属性为"1.0"
		color = "white";
		weight = 1.0;
	}
	protected GeometricObject(String color, double weight){
		this.color = color;
		this.weight = weight;
	}
//	方法：
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
}

class Circle extends GeometricObject{//子类：圆
//	属性：
	private double radius;//圆的半径
//	构造器：
	public Circle(){
		super();
//		初始化对象的color属性为"white",weight属性为1.0,
//		因为super()构造器中刚好满足这一需求，所以直接调用即可。
		this.radius = this.getRadius();
	}
	public Circle(double radius){
		this.radius = radius;
	}
	public Circle(double radius, String color, double weight){
		super(color,weight);
//		this(1.0);//注意：这行是错误的！
//		一个构造器中，只能调用一个super构造器，或者一个this构造器，而不能同时调用两个！
//		因为super构造器和this构造器，都必须写在首行，同时调用两个，都达不到这个要求。
		this.radius = radius;
	}
//	方法：
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double findArea(){//计算圆的面积
		return Math.PI * radius * radius;
	}
//手动重写Object类的equals()方法：比较两个圆的钣金是否相等，如相等则返回true
	public boolean equals(Circle c){
		if(this == c){
			return true;
		}
		if(c instanceof Circle){
			Circle c1 = (Circle)c;
//			下面这个return是照着系统已有的equals()方法中的return写的，区别在double类型的weight和double类型的radius。
//			return Double.doubleToLongBits(radius) == Double.doubleToLongBits(c1.radius);
			return this.radius == c1.radius;
		}else{
			return false;
		}
	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Circle other = (Circle) obj;
//		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
//			return false;
//		return true;
//		}
//手动重写Object类的toString()方法，返回圆的半径。	
//	public String toString(){
////		return "Circle [radius = " + radius + "]";
////		这里是double类型转成String类型，涉及到包装类，可以用以下形式来写：
//		return String.valueOf(radius);
//	}
	@Override
	public String toString() {
		return "Circle [radius=" + radius + "]";
	}
}