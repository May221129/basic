package oop.constructor;
//构造器的练习题
public class TestConstructor {
	public static void main(String[] args) {
//		Person3 p = new Person3();
//		System.out.println("age:" + p.getAge());
		Person3 p1 = new Person3(19);
		System.out.println("age:" + p1.getAge());
		Person3 p2 = new Person3("张三",22);
		System.out.println(p2.getName() + ":" + p2.getAge());
	}
}
class Person3{
//	属性：
	private int age;//私有的，只能在这个类方法里被看到
	private String name;
	
//	构造器：下面这些构造器构成重载
//	public Person3(){
//		age = 18;
//	}
	public Person3(int age){
		this.age = age;
	}
	public Person3(String name){
		name = "张三";
	}
	public Person3(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	//方法：
	public void setName(String neme){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setAge(int age){
		this.age = age;
	}
	public int getAge(){
		return age;
	}
}
