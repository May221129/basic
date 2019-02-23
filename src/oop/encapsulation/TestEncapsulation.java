package oop.encapsulation;
//面向对象的特征之封装性和隐蔽性——练习题
import java.util.Scanner;

public class TestEncapsulation {
	public static void main(String[] args) {
		Person b = new Person();
		Scanner s = new Scanner(System.in);
		System.out.println("请输入您的年龄");
		int age = s.nextInt();
		while(!b.setAge(age)){
			age = s.nextInt();
		}
		
		b.getAge();
		b.info();
		// b.age = 26; ==>Person类中，age这个属性已经变成私有的了，出了Person类，就不能再被看到和调用。
	}
}

class Person {
	private int age;
	public boolean setAge(int i) { 
		if (i > 0 && i < 130) {
			age = i;
			return true;
		} else {
			System.out.println("您输入的数据有误，请重新输入。");
			return false;
		}
	}
	// public void setAge(int i){
	// while(!(i > 0 && i < 130)){
	// Scanner s = new Scanner(System.in);
	// System.out.println("请重新输入");
	// i = s.nextInt();
	// }
	// this.age = i;
	// }
	public int getAge() {
		return age;
	}

	public void info() {
		System.out.println("age:" + age);
	}
}