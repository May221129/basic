package oop.innerclass;

/**
 * 内部类的知识-成员内部类
 * 
 * @author May
 * 2019年11月26日
 */
public class A01_MemberInnerClass {
	public static void main(String[] args) {
//		创建静态的Hand类的对象：
		Person.Hand h = new Person.Hand("左手", 10.0);
		h.show();
		
		System.out.println();
		
//		创建非静态的Foot类的对象：
		Person p = new Person();
		Person.Foot f = p.new Foot("左脚~", 20.0);
		f.show();
		f.setName("右脚");
	}
}

//外部类：Person：
class Person{
//	属性：
	private String name = "张三";
	private int age;
	private String gender;
//	构造器：
	public Person(){
		super();
	}
	public Person(String name, int age, String gender){
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
//	方法：
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void info(){
		System.out.println("我是Person类的info()方法");
	}
//	静态内部类：Hand：
	static class Hand{
//		属性：
		private String name = "右手";//名字
		private double size;//大小
//		构造器：
		public Hand(){
			super();
		}
		public Hand(String name, double size){
			this.name = name;
			this.size = size;
		}
//		方法：
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getSize() {
			return size;
		}
		public void setSize(double size) {
			this.size = size;
		}
		public void show(){
//			info();//不能调用，原因如下：
/*==>Person类的info()方法，是非static的，内部类Hand是static的，
 * 如果通过 new Person.Hand() 方式，创建了Hand的对象，这时候是可以调用Hand类的show()方法的，
 * 但是这时或许并未创建Person的对象，所以Person类的info()方法还未存在，不能调用！
 */
			System.out.println("我是Hand类的show()方法");
		}
	}
//	非静态内部类：Foot：
	class Foot{
//		属性：
		private String name = "左脚脚";//名字
		private double size;//大小
//		构造器：
		public Foot(){
			super();
		}
		public Foot(String name, double size){
			this.name = name;
			this.size = size;
		}
//		方法：
		public void show(){
			System.out.println("我是Foot类的show()方法");
			info();
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			System.out.println(name);
			System.out.println(this.name);
			System.out.println(Person.this.name);
		}
		public double getSize() {
			return size;
		}
		public void setSize(double size) {
			this.size = size;
		}
	}
}