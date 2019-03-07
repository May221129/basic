package oop.polymorphism;
//子类对象实例化练习-2
public class TestInstance {
//入口：
	public static void main(String[] args) {
		TestInstance ti = new TestInstance();
		
		ti.method1(new Student());
		System.out.println();
		ti.method1(new Graduate());
	}
//方法：
	public void method1(Person e){//根据e的类型调用相应类的getInfo()方法
//注意：下面这行  e.getInfo();  一开始并没有写！！！请问：写这行的意义在哪里？？？
		System.out.println(e.getInfo());//"e.getInfo()"实际就是一个多态，你给的入参是什么，她就调用下面哪种类的方法。
//注意：下面几个判断的先后顺序！！！
		if(e instanceof Graduate){
//			Graduate g = (Graduate)e;//题目中并没有要求强转
			System.out.println("a graduate");
//			下面这两行代码不要写， 因为如果创建的对象是Graduate类型，它自然也是Student和Person类型。这里有类似继承 、大范围小范围关系在里面
//			System.out.println("a student");
//			System.out.println("a person");
		}
		if(e instanceof Student){
//			Student s = (Student)e;//题目中并没有要求强转
			System.out.println("a student");
		}
		if(e instanceof Person){
			System.out.println("a person");
		}
	}
}
class Person{
//	属性：
	protected String name = "Person";
	protected int age = 50;
//	方法：
	public String getInfo(){
		return "Name: " + name + "\n age: " + age;
	}
}
class Student extends Person{
//	属性：
	protected String school = "pku";
//	方法：
	 public String getInfo(){
		return "Name: " + name + "\n age: "+ age 
				+ "\n schllo: " + school;
	}
}
class Graduate extends Student{
//	属性：
	public String major = "IT";
//	方法：
	public String getInfo(){
		return "Name: " + name + "\n age: " + age 
				+ "\n schllo: " + school 
				+ "\n major: " + major;
	}
}