package oop.equalsAndHashCode;

/**
 * ̽����Object.equals()����������==���ǱȽ�������������õ�ַ�����ǱȽ����õ�ַ�Ƿ�ָ����е�ͬһ���ڴ棿
 * ���ۣ�
 * 		Object.equals���������Ƚϵ��Ƕ��е��ڴ��Ƿ���ͬ��
 * 		��дequals()�����󣬱Ƚϵ��Ƕ���ĸ����ֶε������Ƿ���ͬ��
 */
public class A01Equals {
	public static void main(String[] args) {
	
	/**
	 * 1.��Object.equals()���бȽ�.
	 * 2.��д��equals()������.
	 */
	Person p1 = new Person("����",26);
	Person p2 = p1;//ָ��ͬһ���ڴ�
	Person p3 = new Person("����",26);//��ͬ���ڴ棬�������ֶ����ݶ���ͬ
	Person p4 = new Person("light",27);//��ͬ���ڴ棬�ֶε�����Ҳ��ͬ
	
	System.out.println(p1.equals(p2));//1.true��  2.true��
	System.out.println(p1.equals(p3));//1.false  2.true��
	System.out.println(p3.equals(p4));//1.false  2.false;
	}
}
class Person{
	protected String name;
	protected int age;
	
	public Person() {
		super();
	}
	public Person(String name, int age){
		this.name = name;
		this.age = age;
	}

//	@Override
//	public boolean equals(Object obj) {
//		//1.���������������ö���ͬ����˵����ͬһ����
//		if (this == obj)
//			return true;
//		//2.this����϶�����null(��Ϊ�����null������equals()�����ᱨ��ָ���쳣)���������obj��null����ֱ�Ӳ���ȣ�
//		if (obj == null)
//			return false;
//		//3.this��obj�Ƿ���ͬһ�����ʵ����
//		if (getClass() != obj.getClass())
//			return false;
//		Person other = (Person) obj;
//		//4. �Ƚϵ�һ������ֵ�Ƿ���ȣ�
//		if (age != other.age)
//			return false;
//		//5.1 name���Զ�Ϊnull��
//		if (name == null) {
//			if (other.name != null)
//				return false;
//			//5.2 name���Զ���Ϊnull������£�ֵ�Ƿ���ͬ��
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}
}
