package oop.polymorphism;
//多态性练习
public class TestGeometricObject {//测试类
//	入口
	public static void main(String[] args) {
		TestGeometricObject t = new TestGeometricObject();//调用的是当前TestGeometricObject对象的方法

		Circle c1 = new Circle(3.0, "蓝色", 6.0);//创建一个圆
		Circle c2 = new Circle(3.0, "红色", 6.0);//创建一个圆		
		
		MyRectangle m1 = new MyRectangle(5, 6, "粉色", 10);//创建一个矩形
		
		t.displayGeometricObject(m1);//怎么给它入参？？？
//==>前面要先创造一个圆对象.这个就是多态的运用——GeometricObject g = new Circle(3.0, "蓝色", 6) ，
//	   赋值过程：new Circle(3.0, "蓝色", 6)赋值给g, c再赋值给方法里的入参o
		
		boolean b = t.equalsArea(c1, c2);//调用GeometricObject判断两个对象的面积是否相等，并赋值给Boolean类型的b
		System.out.println(b);//通过赋值给Boolean类型的b，就能打印出来作为结果的检查！！！
	}
//	方法：
	public boolean equalsArea(GeometricObject g1, GeometricObject g2){//判断两个对象的面积是否相等
//注意==>1、既然是判断，那就用booblean类型的返回类型；
//注意==>2、这里的入参，写两个父类GeometricObject的对象，能打乱第一个和第二个的入参对象来比较两个子类的面积！体现多态性的应用。
//		public boolean GeometricObject(Circle c, MyRectangle m)也是可以的，只是规定死了入参第一个写什么，第二个写什么。
//		写法一：
//		if(g1.findArae() == g2.findArae()){
//			return true;
//		}else{
//			return false;
//		}
//		写法二：
		return g1.findArae() == g2.findArae();
	}
	public void displayGeometricObject(GeometricObject g){//显示对象的面积
		System.out.println("该对象的面积为： " + g.findArae());
//注意：==>下面这些都可以不用写！！！因为调用的是子类对父类findArea方法的重写，而不是调用子类中特有的方法！！！
//		if(g instanceof Circle){
//			Circle c = (Circle)g;
//			System.out.println("该圆形的面积为： " + c.findArea());
//		}
//		if(g instanceof MyRectangle){
//			MyRectangle m = (MyRectangle)g;
//			System.out.println("该矩形的面积为： " + m.fidArae());
//		}
	}
}
class GeometricObject{//父类：几何形状
//	属性：
	protected String color;//颜色
	protected double weight;//重量
//	构造器：
	protected GeometricObject(){//注意：每个父类中，最好都要声明一个空参的构造器
		
	}
	protected GeometricObject(String color, double weight){
		this.color = color;
		this.weight = weight;
	}
//	方法：
	public String getColor(){//获取颜色
		return color;
	}
	public void setColor(String color){//设置颜色
		this.color = color;
	}
	public double getWeight(){//获取重量
		return weight;
	}
	public void setWeight(){//设置重量
		this.weight = weight;
	}
	public double findArae(){//求几何形状的面积
//==>注意：题目中并没有提供可以求几何形状的面积的方法，所以可以先默认返回0！！！
		return 0;
	}
}
class Circle extends GeometricObject{//子类：圆形
//	属性：
	private double radius;//半径
//	构造器：
	public Circle(double radius, String color, double weight){
		super(color,weight);
		this.radius = radius;
	}
//	方法：
	public double getRadius() {//获取圆的半径
		return radius;
	}
	public void setRadius(double radius) {//设置圆的半径
		this.radius = radius;
	}
	public double findArae(){//计算圆的面积==>注意：这里要不要给入参？？？
		return Math.PI * radius * radius;
	}
}
class MyRectangle extends GeometricObject{//子类：矩形
//	属性：
	private double width;//宽
	private double height;//高
//	构造器：
	public MyRectangle(double width, double height, String color, double weight){
		super(color, weight);
		this.width = width;
		this.height = height;
	}
//	方法：
	public double getWidth() {//获取矩形的宽
		return width;
	}
	public void setWidth(double width) {//设置矩形的宽
		this.width = width;
	}
	public double getHeight() {//获取矩形的高
		return height;
	}
	public void setHeight(double height) {//设置矩形的高
		this.height = height;
	}
	public double findArae(){//计算矩形的面积
		return width * height;
	}
}