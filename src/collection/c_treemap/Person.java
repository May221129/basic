package collection.c_treemap;

/**
 * 自然排序，测试见TestTreeMap类
 * 
 * 自然排序的实现：需要改变类结构。
 * 	1.自定义一个类，该类实现Comparable<T>接口；
 * 	2.实现Comparable接口的compareTo()抽象方法,在CompareTo()方法中自定义排序规则(是按什么属性进行排序的);
 * 	3.创建TreeMap对象，将自定义类的对象put进TreeMap中，put每个实现了Comparable接口的这个自定义类的对象时，
 * 		TreeMap都会自动调用该对象的CompareTo()方法，所以TreeMap中元素的存放顺序是按照CompareTo()方法中
 * 		自定义的排序顺序一致的。
 * 提问：
 * 	1.需要做排序的自定义类中，是否需要重写equals()方法、hashCode()方法？为什么？
 * 		TreeMap不是按照hashCode来存放，而是按照实现的Comparable接口的compareTo()方法来存储的，
 * 		只要compareTo的返回结果为0就表示两个对象相等，那么就存不进去两个对象，后put的就把前面的覆盖掉，甚
 * 		至我们都不用重写equasls和hashCode方法，而只需要实现Comparable接口来重写comparareTo方法
 * 		就行了，但是我们不能保证在应用中不会用到HashMap，所以保持良好的习惯，当我们定义了一个对象之后习惯性的
 * 		重写equals和hashCode方法。
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
