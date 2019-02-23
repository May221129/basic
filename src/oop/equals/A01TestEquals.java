package oop.equals;

/**
 * equals()������
 * 1.�Ƚ��Լ���д��equals()���� �� ϵͳ�ṩ��equals()��������д��
 * 2.�����˽�"=="��equals()����������
 */
public class A01TestEquals {
	public static void main(String[] args) {
	
	Person p1 = new Person("����",26);
	Person p2 = new Person("����",26);
	
	//"=="�Ƚϵ������õĵ�ַ:
	System.out.println(p1 == p2);//false
	
	//���Person��û����дequals()����������õ���Object�е�equals()�������ȽϵĻ�������������õ�ַ�������Ҫ�Ƚϵ�������ʵ��ָ������ݣ�����Ҫ����������д������
	System.out.println(p1.equals(p2));//false
	
	System.out.println(p1.getClass());//class equals.Person
	System.out.println(p2.getClass());//class equals.Person
	}
}
class Person{
//	���ԣ�
	protected String name;
	protected int age;
//	��������
	public Person() {
		super();
	}
	public Person(String name, int age){
		this.name = name;
		this.age = age;
	}
//	������	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * �Լ���дObject���equals�����������Ͻ������ǲ�����ȫ
	 */
/*  
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}
		if(obj instanceof Person){
			Person p = (Person)obj;
//			д��һ��
//			if(this.name == p.name && this.age == p.age){
//				return true;
//			}else{
//				return false;
//			}
//			д������
			return this.name == p.name && this.age == p.age;
		}else{
			return false;
		}
	}
*/
	/**
	 * ϵͳд�õ�equals()�������ӡ���������һ����ĳ����������ȣ���������Ͳ���ȡ���˼·չ������Χ�������㣬С�ľ�û��Ҫ���ˣ�ֱ��false��
	 */
	@Override
	public boolean equals(Object obj) {
		//1.���������������ö���ͬ����˵����ͬһ����
		if (this == obj)
			return true;
		//2.this����϶�����null(��Ϊ�����null������equals()�����ᱨ��ָ���쳣)���������obj��null����ֱ�Ӳ���ȣ�
		if (obj == null)
			return false;
		//3.this��obj�Ƿ���ͬһ�����ʵ����
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		//4. �Ƚϵ�һ������ֵ�Ƿ���ȣ�
		if (age != other.age)
			return false;
		//5.1 name���Զ�Ϊnull��
		if (name == null) {
			if (other.name != null)
				return false;
			//5.2 name���Զ���Ϊnull������£�ֵ�Ƿ���ͬ��
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
