package oop.overload;
//方法重载overload：练习
public class TestOverLoad {
	public static void main(String[] args) {
		
		//第一步：创建类的对象：
		TestOverLoad a = new TestOverLoad();
		
		//第二步：通过类的对象来调用方法：
		a.mOL(2,3);
		
		a.mOL(12);
		
		a.mOL("傻逼");
		
		a.getMax(2,3);
		System.out.println(a.getMax(2,3));
		
		a.getMax(2.3, 4.5);
		System.out.println(a.getMax(2.3, 4.5));
		
		a.getMax(2.3, 3.4,4.5);
		System.out.println(a.getMax(2.3, 3.4,4.5));
	}
	
	//方法重载的创建：
	private void mOL(int a) {
		System.out.println(a*a);
	}
	
	public void mOL(int a, int b) {
		System.out.println(a*b);
	}

	public void mOL(String a) {
		System.out.println(a);
	}
	//以上三个方法构成重载
	
	//以下三个方法构成重载
	public int getMax(int a, int b){
		int max = (a > b)? a : b;
		return max;
	}
	public double getMax(double a, double b){
		double max = (a > b)? a : b;
		return max;
	}
	public double getMax(double a, double b, double c){
		//写法一：
//		double temp = (a > b)? a : b;
//		double max = (temp > c)? temp : c;
//		return max;
		//写法二：
		return (getMax(a,b) > c)? getMax(a,b) : c;//调用上一个写好的方法，来和c作比较
	}
}