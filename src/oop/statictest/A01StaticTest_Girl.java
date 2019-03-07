package oop.statictest;
/**
 * static关键字的练习：
 */
public class A01StaticTest_Girl {
	public static void main(String[] args) {
	Girl g1 = new Girl("小红",23);
	Girl g2 = new Girl("小蓝",24);
//用下面两组来验证，一方调用类变量之后，直接影响另一方的该类变量的输出：
	g1.beautiful = "美丽";//通过对象来调用的是类变量
	System.out.println(g1.toString());
	System.out.println(g2.toString());
	
	Girl.beautiful = "我要变漂亮！";//通过类类调用的是类变量
//上面这行代码证明：类变量是先于对象出现的，所以可以通过类来直接调用类变量，当然，也可以通过对象来调用类变量
	System.out.println(g1.toString());
	System.out.println(g2.toString());
	
	g1.beautiful();//通过对象来调用的是类方法
	Girl.beautiful();//通过类来调用类方法
	}
}
class Girl{
//	属性：
//	实例变量：随着对象的创建而被加载的
	private String name;
	private int age;
//	类变量：随着类的创建而被加载的
	static String beautiful;
//	构造器：
	public Girl(){
		super();
	}
	public Girl(String name, int age){
		this.name = name;
		this.age = age;
	}
//	方法：
//	实例方法：
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
@Override
	public String toString() {
		return "Girl [name=" + name + ", age=" + age + 
				", beautiful=" + beautiful + "]";
	}
	//	类方法：
	public static void beautiful(){
		System.out.println(beautiful);
//		System.out.println(name + beautiful);
		System.out.println("女孩，因自信而美丽！");
	}
	public static String getBeautiful() {
		return beautiful;
	}
	public static void setBeautiful(String beautiful) {
		Girl.beautiful = beautiful;
	}
}