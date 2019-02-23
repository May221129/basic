package exception.a03exceptiontest;
//Exception异常处理——手动抛出异常：
public class TestThrows {
	public static void main(String[] args) {
		method1();
		
		Circle1 s1 = new Circle1(2.0);
		Circle1 s2 = new Circle1(2.0);
		try {
			System.out.println(s1.compareTo(s2));
			System.out.println(s1.compareTo(new String("加油！")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			System.out.println("我不管我不管，我就是要执行~");
		}
	}
	public static void method1(){
		try {
			int i = 10;
			System.out.println(10/0);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
class Circle1{
//	属性：
	private double radius;//半径
//	构造器：
	public Circle1(double radius){
		this.radius = radius;
	}
//	方法：比较两个圆的大小
	public int compareTo(Object obj) throws Exception{//手动抛出的编译时异常，到了这里
//		System.out.println("注：this圆比较大，返回1；this圆比较小，返回-1；两个圆一样大，返回0；传入的对象不是圆，返回-2");
		if(this == obj){
			return 0;
		}else if(obj instanceof Circle1){
			Circle1 c = (Circle1)obj;
			if(this.radius == c.radius){
				return 0;
			}else if(this.radius > c.radius){
				return 1;
			}else{
				return -1;
			}
		}else{
//			手动抛出异常-编译时异常：
			throw new Exception("传入的类型 有误！");
		}
	}
}