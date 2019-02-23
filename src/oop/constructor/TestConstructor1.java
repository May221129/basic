package oop.constructor;
//构造器的练习题
public class TestConstructor1 {
	public static void main(String[] args) {
		Person1 p1 = new Person1("赖丽梅", 26);
//		建议：构造器只给对象的属性进行初始化（即赋值），如果需要输出属性的值，则调用方法来实现。【保持构造器的单纯，不要写的复杂了】
		System.out.println(p1.getName() + ":" + p1.getAge());
		Person1 p2 = new Person1("赖丽梅", 26, "泉州经贸职业技术学院");
		System.out.println(p2.getName() + ":" + p2.getAge() + ";" + p2.getSchool());
		Person1 p3 = new Person1("赖丽梅",26, "泉州经贸职业技术学院", "物流管理");
		System.out.println(p3.getName() + ":" + p3.getAge() + ";" + p3.getSchool() + ";" + p3.getMajor());
	}
}
class Person1{
	String name;
	int age;
	String school;
	String major;
	public Person1(String n, int a){
		name = n;
		age = a;
	}
	public Person1(String n, int a, String s){
		name = n;
		age = a;
		school = s;
	}
	public Person1(String n, int a, String s, String m){
		name = n;
		age = a;
		school = s;
		major = m;
	}
	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}
	public String getSchool(){
		return school;
	}
	public String getMajor(){
		return major;
	}
}