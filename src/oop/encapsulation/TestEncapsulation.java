package oop.encapsulation;
//������������֮��װ�Ժ������ԡ�����ϰ��
import java.util.Scanner;

public class TestEncapsulation {
	public static void main(String[] args) {
		Person b = new Person();
		Scanner s = new Scanner(System.in);
		System.out.println("��������������");
		int age = s.nextInt();
		while(!b.setAge(age)){
			age = s.nextInt();
		}
		
		b.getAge();
		b.info();
		// b.age = 26; ==>Person���У�age��������Ѿ����˽�е��ˣ�����Person�࣬�Ͳ����ٱ������͵��á�
	}
}

class Person {
	private int age;
	public boolean setAge(int i) { 
		if (i > 0 && i < 130) {
			age = i;
			return true;
		} else {
			System.out.println("������������������������롣");
			return false;
		}
	}
	// public void setAge(int i){
	// while(!(i > 0 && i < 130)){
	// Scanner s = new Scanner(System.in);
	// System.out.println("����������");
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