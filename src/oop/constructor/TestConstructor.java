package oop.constructor;
//����������ϰ��
public class TestConstructor {
	public static void main(String[] args) {
//		Person3 p = new Person3();
//		System.out.println("age:" + p.getAge());
		Person3 p1 = new Person3(19);
		System.out.println("age:" + p1.getAge());
		Person3 p2 = new Person3("����",22);
		System.out.println(p2.getName() + ":" + p2.getAge());
	}
}
class Person3{
//	���ԣ�
	private int age;//˽�еģ�ֻ��������෽���ﱻ����
	private String name;
	
//	��������������Щ��������������
//	public Person3(){
//		age = 18;
//	}
	public Person3(int age){
		this.age = age;
	}
	public Person3(String name){
		name = "����";
	}
	public Person3(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	//������
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
