package collection.b_treeset;

public class Person implements Comparable{
//	属性：
	private String name;
	private Integer age;
//	构造器：
	public Person(){
		super();
	}
	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
//	方法：
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
	//这个方法是实现属性的排列方式
//	public int compareTo(Object obj){
//		if(obj instanceof Person){
//			Person p = (Person)obj;
//			return this.name.compareTo(p.name);
//		}
//		return 0;
//	}
	public int compareTo(Object obj){
		if(obj instanceof Person){
			Person p = (Person)obj;
//			return this.name.compareTo(p.name);
			int i = this.age.compareTo(p.age);
			if(i == 0){
				return this.name.compareTo(p.name);
			}else{
				return i;
			}
		}
		return 0;//两个属性是一样的时，return0.
	}
}
