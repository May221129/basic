package oop.object;

/**
 * Object.equals()������
 * 1. û��д�Ƚϵ���ʲô��
 * 	�����������Ƿ�ָ��ͬһ������
 * 2. �������д���equals()����ȥ�Ƚ�����������ָ��Ķ����ʵ�����ݣ�
 * 
 * @author May
 * 2019��12��27��
 */
public class A02Method_Equals {
	public static void main(String[] args) {
		//1. �]����д��������equals()����ʱ���Ƚϵ����������Ƿ�ָ��ͬһ������
		People a = new People("ѽ����", 1);
		People b = a;
		People c = new People("ѽ����", 1);
		System.out.println(a.equals(b));//true
		System.out.println(a.equals(c));//false
		
		//2. ��д��equals()������
		Apple d = new Apple(1);
		Apple e = new Apple(1);
		System.out.println(e.equals(e));//true
	}
}

class People{
	private String name;
	private int age;
	public People(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

class Apple{
	private int weight;
	public Apple(int weight) {
		this.weight = weight;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + weight;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apple other = (Apple) obj;
		if (weight != other.weight)
			return false;
		return true;
	}
}
