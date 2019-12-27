package oop.innerclass;

/**
 * �ڲ����֪ʶ-��Ա�ڲ���
 * 
 * @author May
 * 2019��11��26��
 */
public class A01_MemberInnerClass {
	public static void main(String[] args) {
//		������̬��Hand��Ķ���
		Person.Hand h = new Person.Hand("����", 10.0);
		h.show();
		
		System.out.println();
		
//		�����Ǿ�̬��Foot��Ķ���
		Person p = new Person();
		Person.Foot f = p.new Foot("���~", 20.0);
		f.show();
		f.setName("�ҽ�");
	}
}

//�ⲿ�ࣺPerson��
class Person{
//	���ԣ�
	private String name = "����";
	private int age;
	private String gender;
//	��������
	public Person(){
		super();
	}
	public Person(String name, int age, String gender){
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
//	������
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
		System.out.println("����Person���info()����");
	}
//	��̬�ڲ��ࣺHand��
	static class Hand{
//		���ԣ�
		private String name = "����";//����
		private double size;//��С
//		��������
		public Hand(){
			super();
		}
		public Hand(String name, double size){
			this.name = name;
			this.size = size;
		}
//		������
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
//			info();//���ܵ��ã�ԭ�����£�
/*==>Person���info()�������Ƿ�static�ģ��ڲ���Hand��static�ģ�
 * ���ͨ�� new Person.Hand() ��ʽ��������Hand�Ķ�����ʱ���ǿ��Ե���Hand���show()�����ģ�
 * ������ʱ����δ����Person�Ķ�������Person���info()������δ���ڣ����ܵ��ã�
 */
			System.out.println("����Hand���show()����");
		}
	}
//	�Ǿ�̬�ڲ��ࣺFoot��
	class Foot{
//		���ԣ�
		private String name = "��Ž�";//����
		private double size;//��С
//		��������
		public Foot(){
			super();
		}
		public Foot(String name, double size){
			this.name = name;
			this.size = size;
		}
//		������
		public void show(){
			System.out.println("����Foot���show()����");
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