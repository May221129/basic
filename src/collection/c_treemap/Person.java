package collection.c_treemap;

/**
 * ��Ȼ���򣬲��Լ�TestTreeMap��
 * 
 * ��Ȼ�����ʵ�֣���Ҫ�ı���ṹ��
 * 	1.�Զ���һ���࣬����ʵ��Comparable<T>�ӿڣ�
 * 	2.ʵ��Comparable�ӿڵ�compareTo()���󷽷�,��CompareTo()�������Զ����������(�ǰ�ʲô���Խ��������);
 * 	3.����TreeMap���󣬽��Զ�����Ķ���put��TreeMap�У�putÿ��ʵ����Comparable�ӿڵ�����Զ�����Ķ���ʱ��
 * 		TreeMap�����Զ����øö����CompareTo()����������TreeMap��Ԫ�صĴ��˳���ǰ���CompareTo()������
 * 		�Զ��������˳��һ�µġ�
 * ���ʣ�
 * 	1.��Ҫ��������Զ������У��Ƿ���Ҫ��дequals()������hashCode()������Ϊʲô��
 * 		TreeMap���ǰ���hashCode����ţ����ǰ���ʵ�ֵ�Comparable�ӿڵ�compareTo()�������洢�ģ�
 * 		ֻҪcompareTo�ķ��ؽ��Ϊ0�ͱ�ʾ����������ȣ���ô�ʹ治��ȥ�������󣬺�put�ľͰ�ǰ��ĸ��ǵ�����
 * 		�����Ƕ�������дequasls��hashCode��������ֻ��Ҫʵ��Comparable�ӿ�����дcomparareTo����
 * 		�����ˣ��������ǲ��ܱ�֤��Ӧ���в����õ�HashMap�����Ա������õ�ϰ�ߣ������Ƕ�����һ������֮��ϰ���Ե�
 * 		��дequals��hashCode������
 * 		
 */
public class Person implements Comparable<Object>{
	private String name;
	private Integer age;
	public Person() {
		super();
	}
	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Person other = (Person) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public int compareTo(Object o){
		if(o instanceof Person){
			Person p = (Person)o;
			int i = this.name.compareTo(p.name);
			if(i == 0){
				return this.age.compareTo(p.age);
			}else{
				return i;
			}
		}
		return 0;
	}
}
