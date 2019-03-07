package oop.polymorphism;
//�������ʵ������ϰ-2
public class TestInstance {
//��ڣ�
	public static void main(String[] args) {
		TestInstance ti = new TestInstance();
		
		ti.method1(new Student());
		System.out.println();
		ti.method1(new Graduate());
	}
//������
	public void method1(Person e){//����e�����͵�����Ӧ���getInfo()����
//ע�⣺��������  e.getInfo();  һ��ʼ��û��д���������ʣ�д���е��������������
		System.out.println(e.getInfo());//"e.getInfo()"ʵ�ʾ���һ����̬������������ʲô�����͵�������������ķ�����
//ע�⣺���漸���жϵ��Ⱥ�˳�򣡣���
		if(e instanceof Graduate){
//			Graduate g = (Graduate)e;//��Ŀ�в�û��Ҫ��ǿת
			System.out.println("a graduate");
//			���������д��벻Ҫд�� ��Ϊ��������Ķ�����Graduate���ͣ�����ȻҲ��Student��Person���͡����������Ƽ̳� ����ΧС��Χ��ϵ������
//			System.out.println("a student");
//			System.out.println("a person");
		}
		if(e instanceof Student){
//			Student s = (Student)e;//��Ŀ�в�û��Ҫ��ǿת
			System.out.println("a student");
		}
		if(e instanceof Person){
			System.out.println("a person");
		}
	}
}
class Person{
//	���ԣ�
	protected String name = "Person";
	protected int age = 50;
//	������
	public String getInfo(){
		return "Name: " + name + "\n age: " + age;
	}
}
class Student extends Person{
//	���ԣ�
	protected String school = "pku";
//	������
	 public String getInfo(){
		return "Name: " + name + "\n age: "+ age 
				+ "\n schllo: " + school;
	}
}
class Graduate extends Student{
//	���ԣ�
	public String major = "IT";
//	������
	public String getInfo(){
		return "Name: " + name + "\n age: " + age 
				+ "\n schllo: " + school 
				+ "\n major: " + major;
	}
}