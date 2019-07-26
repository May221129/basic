package oop.equalsAndHashCode;

/**
 * 探究：equals() 和 hashCode()
 * 	1.equals(Object obj)和hashCode()各自有什么作用？
 * 		答：equals(Object obj)：比较两个对象是否相同，至于两个对象怎样才算是相同，由使用者自行决定。
 * 				如：指向同一块堆中的内存就是相同，或者某个字段相同就是两个对象相同。
 * 			hashCode()：计算对象的某些字段的hash值。
 * 	2.Objcect.hashCode()输出的是什么？
 * 		答：一个int值。
 * 	3.equals(Object obj)和hashCode()之间的关系：
 * 		答：两个对象：
 * 			equals()结果为true，则hashCode()结果也必然相同；
 * 			equals()结果为false，则hashCode()结果可能相同；
 * 			hashCode()结果相同，则equals()结果不一定是true；
 * 			hashCode()结果不同，则equals()结果一定是false。
 * 		hashCode()和equals()一定要保持一致性，要重写就两个都重写；用于equals()做比较的字段和用于计算hashCode()的字段也要一致。
 * 
 * @author May
 * 2019年7月25日
 */
public class A03EqualsAndHashCode {
	public static void main(String[] args) {
		Student s1 = new Student();
		Student s2 = new Student();
		Student s3 = s2;
		/**
		 * 测试：
		 * 	1.equals()和hashCode()都没重写;
		 * 	2.				   	    都有重写;
		 */
		System.out.println(s1.hashCode() == s2.hashCode());//1.false  2.true
		System.out.println(s1.equals(s2));//1.false;  2.true;
		
		System.out.println(s2.hashCode() == s3.hashCode());//1.true   2.true
		System.out.println(s2.equals(s3));//1.true;  2.true;
	}
}

class Student{
	private String name;
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Student other = (Student) obj;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}
}