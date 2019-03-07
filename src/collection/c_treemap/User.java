package collection.c_treemap;

/**
 * 如果User类即不是自然排序，也不是定制排序，就是一个普通的类，那么put到TreeMap中是按照什么规则进行排序的？
 * 总结见TestTreeMap-总结2.4
 */
public class User {
	
	private String name;
	private int age;
	
	public User() {
		super();
	}
	public User(String name, int age) {
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
}
