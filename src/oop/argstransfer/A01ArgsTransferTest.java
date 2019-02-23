package oop.argstransfer;
/**
 * 将对象作为参数传递给方法:练习：
 */
public class A01ArgsTransferTest {
	public static void main(String[] args) {
		Circle c = new Circle();
		PassObject2 p = new PassObject2();
		p.PtintArea(c, 5);
		p.PtintArea(new Circle(), 3);
	}
}
class Circle{
	double radius; //属性：半径
	
	public void setRadius(double r){ //方法：设置半径
		this.radius = r;
	}
	public double getRadius(){  //方法：获得半径
		return radius;
	}
	public double findArea(){  //方法：求圆的面积，没有入参，而是采用之前 设置的半径 来进行计算
		return Math.PI * radius * radius;//π的计算，可以采用“Math.PI”的方式来调用，精确度更高
	}
}
class PassObject2{
	public void PtintArea(Circle c, int time){//给你一个圆，一个数值；
		System.out.println("Radius" + "\t\t" + "Area");
		int t = 1;
		while(t <= time){
			c.setRadius(t);
			//c.findArea();程序性思维：这里调用之后没有任何作用，在下一行输出的时候，又重新进行了调用，所以这里可以不写。
			System.out.println(t + "\t\t" + c.findArea());
			t++;
		}
	}
}