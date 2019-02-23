package oop.constructor;
//����������ϰ��
public class TestConstructor1 {
	public static void main(String[] args) {
		Person1 p1 = new Person1("����÷", 26);
//		���飺������ֻ����������Խ��г�ʼ��������ֵ���������Ҫ������Ե�ֵ������÷�����ʵ�֡������ֹ������ĵ�������Ҫд�ĸ����ˡ�
		System.out.println(p1.getName() + ":" + p1.getAge());
		Person1 p2 = new Person1("����÷", 26, "Ȫ�ݾ�óְҵ����ѧԺ");
		System.out.println(p2.getName() + ":" + p2.getAge() + ";" + p2.getSchool());
		Person1 p3 = new Person1("����÷",26, "Ȫ�ݾ�óְҵ����ѧԺ", "��������");
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