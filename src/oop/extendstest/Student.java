package oop.extendstest;
//��ļ̳���ϰ��-3
public class Student extends Person{
	
	long number;//����
	int math;//��ѧ
	int english;//Ӣ��
	int computer;//�����
	
	public Student(String name,char sex, int age ,long k, int m, int e, int c){
//		super(name, sex, age)==>���ø����������������Ǹ�������
		super();//��ʽ���ù���������д�ɲ�д
		this.name = name;
		this.sex = sex;
		this.age = age;
		number = k;
		math = m;
		english = e;
		computer = c;
	}
	public double aver(){//ƽ��ֵ��û����ϸ��˵���飬�����Լ����д
		return number/2;
	}
	public int max(){//���ֵ,û����ϸ��˵���飬�����Լ����д
		return math;
	}
	public int min(){//��Сֵ,û����ϸ��˵���飬�����Լ����д
		return math;
	}
	public void eat(){//����Person��Ҳ���������������д��һ�������ķ�������ʾ��������д��
		System.out.println("��ڴ�ڵĳԷ�");
	}
//	��������������Բ�д����Ϊ�ڸ�����Ҳ�У�ֱ�Ӽ̳�
//	public String toString(){//���һЩ��Ϣ���ǹ��ڱ������Ϣ������Ҫ��˵����
//		return name;
//	}
}
