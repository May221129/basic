package oop.extendstest;
//类的继承练习题-3
public class Person {
	protected String name;//名字
	protected char sex;//性别
	protected int age;//年龄
//构造器	
	public Person(){
		
	}
	public Person(String name, char sex, int age){
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
//方法	
	public String toString(){//将数值转换成字符串;将日期时间值转换成;转换对象为字符串;方法
		return name;
	}
	public void eat(){
		System.out.println("吃饭");
	}
}
