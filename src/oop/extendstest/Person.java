package oop.extendstest;
//��ļ̳���ϰ��-3
public class Person {
	protected String name;//����
	protected char sex;//�Ա�
	protected int age;//����
//������	
	public Person(){
		
	}
	public Person(String name, char sex, int age){
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
//����	
	public String toString(){//����ֵת�����ַ���;������ʱ��ֵת����;ת������Ϊ�ַ���;����
		return name;
	}
	public void eat(){
		System.out.println("�Է�");
	}
}
