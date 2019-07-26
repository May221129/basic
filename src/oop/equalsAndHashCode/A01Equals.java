package oop.equalsAndHashCode;

/**
 * 探究：Object.equals()方法（即：==）是比较两个对象的引用地址，还是比较引用地址是否指向堆中的同一个内存？
 * 结论：
 * 		Object.equals（）方法比较的是堆中的内存是否相同。
 * 		重写equals()方法后，比较的是对象的各个字段的内容是否相同。
 */
public class A01Equals {
	public static void main(String[] args) {
	
	/**
	 * 1.用Object.equals()进行比较.
	 * 2.重写了equals()方法后.
	 */
	Person p1 = new Person("光猪",26);
	Person p2 = p1;//指向同一块内存
	Person p3 = new Person("光猪",26);//不同的内存，但各个字段内容都相同
	Person p4 = new Person("light",27);//不同的内存，字段的内容也不同
	
	System.out.println(p1.equals(p2));//1.true；  2.true；
	System.out.println(p1.equals(p3));//1.false  2.true；
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
//		//1.如果两个对象的引用都相同，则说明是同一对象：
//		if (this == obj)
//			return true;
//		//2.this对象肯定不是null(因为如果是null，调用equals()方法会报空指针异常)，所以如果obj是null，则直接不相等：
//		if (obj == null)
//			return false;
//		//3.this和obj是否是同一个类的实例：
//		if (getClass() != obj.getClass())
//			return false;
//		Person other = (Person) obj;
//		//4. 比较第一个属性值是否相等：
//		if (age != other.age)
//			return false;
//		//5.1 name属性都为null吗？
//		if (name == null) {
//			if (other.name != null)
//				return false;
//			//5.2 name属性都不为null的情况下，值是否相同？
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}
}
