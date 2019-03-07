package exception.a03exceptiontest;
//Exception异常处理——手动抛出异常：
public class TestCircle {
	public static void main(String[] args) {
		Circle c1 = new Circle(2.0);
		Circle c2 = new Circle(3.0);
		Circle c3 = new Circle(3.0);
		Person p = new Person();
		
		int i1 = c1.compareTo(c2);
		System.out.println(i1);
		
		int i2 = c1.compareTo(p);
		System.out.println(i2);
//==>当上面的c1和p相比，执行了手动抛出 异常之后，下面这段程序就不会再执行了。
		int i3 = c2.compareTo(c3);
		System.out.println(i3);
	}
}
class Circle{
//	属性：
	private double radius;//半径
//	构造器：
	public Circle(){
		super();
	}
	public Circle(double radius){
		this.radius = radius;
	}
//	方法：
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public int compareTo(Object obj){//比较两个圆的大小
//		System.out.println("注：this圆比较大，返回1；this圆比较小，返回-1；两个圆一样大，返回0；传入的对象不是圆，返回-2");
		if(this == obj){
			return 0;
		}else if(obj instanceof Circle){
			Circle c = (Circle)obj;
			if(this.radius == c.radius){
				return 0;
			}else if(this.radius > c.radius){
				return 1;
			}else{
				return -1;
			}
		}else{
//			手动抛出异常：
//			throw new RuntimeException("传入的类型有误！") ;//这里抛的是运行时异常，所以编译时不会报错。
//			手动抛出一个自己创建的异常类：
			throw new MyException("传入的类型有误！");
			
//			try {//手动抛出编译时异常，并采用try-catch的方式处理了
//				throw new Exception("传入的类型 有误！");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
}
class Person{
	
}