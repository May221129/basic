package oop.abstracttest;

/**
 * ������abstract����ϰ��
 * 	abstract���������������ԡ�����������private��final��static�������ؼ������εķ�����
 */
public class A02AbstractTest {
	public static void main(String[] args) {
		Student s = new Student();
		System.out.println(s.getId());
	}
}
class Student extends Person{
	
}
abstract class Person{
	// 1.abstract����������������
//	protected abstract String name;
	protected static int age;
	protected final int id;
	protected final static int handsNumber = 2;
	
	//����飺
	{
		id = 1001;
	}
	
	// 2.abstract�����������ι���������Ϊ���������ܱ���д
//	public abstract Person(){
//		super();
//	}

	// 3.abstract������������private�ķ�������Ϊ���಻�ܸ��ǣ�����д��private�ķ�����
//	private abstract void setAge(int age){
//		this.age = age;
//	}
	
	public int getId() {//��ȡid�ķ���
		return id;
	}
	
	// 4.abstract������������final����Ϊfinal�����յģ������ٱ���д
//	public  abstract final int getHandsNumber(){//��ȡ�յ�����
//		return handsNumber;
//	}
	
	// 5.abstract���� ����������static�ķ�������Ϊstatic����ֱ��ͨ���������ã�����abstract����֮�󣬾ͳ�Ϊû�з������һ����ȱ�ķ����ˣ�����ʱû��������
//	public static abstract int getAge(int age){
//		return age;
//	}
}