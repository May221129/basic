package oop.equals;

/**
 * equals()方法：
 * 1.比较自己重写的equals()方法 和 系统提供的equals()方法的重写；
 * 2.初步了解"=="和equals()方法的区别；
 */
public class A01TestEquals {
	public static void main(String[] args) {
	
	Person p1 = new Person("光猪",26);
	Person p2 = new Person("光猪",26);
	
	//"=="比较的是引用的地址:
	System.out.println(p1 == p2);//false
	
	//如果Person类没有重写equals()方法，则调用的是Object中的equals()方法，比较的还是两对象的引用地址。如果想要比较的是引用实际指向的内容，就需要在子类中重写方法。
	System.out.println(p1.equals(p2));//false
	
	System.out.println(p1.getClass());//class equals.Person
	System.out.println(p2.getClass());//class equals.Person
	}
}
class Person{
//	属性：
	protected String name;
	protected int age;
//	构造器：
	public Person() {
		super();
	}
	public Person(String name, int age){
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * 自己重写Object类的equals方法：不够严谨，考虑不够周全
	 */
/*  
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}
		if(obj instanceof Person){
			Person p = (Person)obj;
//			写法一：
//			if(this.name == p.name && this.age == p.age){
//				return true;
//			}else{
//				return false;
//			}
//			写法二：
			return this.name == p.name && this.age == p.age;
		}else{
			return false;
		}
	}
*/
	/**
	 * 系统写好的equals()方法：从“两个对象一旦有某个条件不相等，两个对象就不相等”的思路展开。大范围都不满足，小的就没必要比了，直接false。
	 */
	@Override
	public boolean equals(Object obj) {
		//1.如果两个对象的引用都相同，则说明是同一对象：
		if (this == obj)
			return true;
		//2.this对象肯定不是null(因为如果是null，调用equals()方法会报空指针异常)，所以如果obj是null，则直接不相等：
		if (obj == null)
			return false;
		//3.this和obj是否是同一个类的实例：
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		//4. 比较第一个属性值是否相等：
		if (age != other.age)
			return false;
		//5.1 name属性都为null吗？
		if (name == null) {
			if (other.name != null)
				return false;
			//5.2 name属性都不为null的情况下，值是否相同？
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
