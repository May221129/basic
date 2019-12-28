package oop.object;

/**
 * Object.equals()方法：
 * 1. 没重写比较的是什么？
 * 	答：两个引用是否指向同一个对象；
 * 2. 如何让重写后的equals()方法去比较两个引用所指向的对象的实际内容？
 * 
 * @author May
 * 2019年12月27日
 */
public class A02Method_Equals {
	public static void main(String[] args) {
		//1. 沒有重写对象的类的equals()方法时，比较的两个引用是否指向同一个对象；
		People a = new People("呀哈哈", 1);
		People b = a;
		People c = new People("呀哈哈", 1);
		System.out.println(a.equals(b));//true
		System.out.println(a.equals(c));//false
		
		//2. 重写了equals()方法：
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
