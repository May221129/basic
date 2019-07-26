package oop.equalsAndHashCode;

/**
 * ̽����equals() �� hashCode()
 * 	1.equals(Object obj)��hashCode()������ʲô���ã�
 * 		��equals(Object obj)���Ƚ����������Ƿ���ͬ��������������������������ͬ����ʹ�������о�����
 * 				�磺ָ��ͬһ����е��ڴ������ͬ������ĳ���ֶ���ͬ��������������ͬ��
 * 			hashCode()����������ĳЩ�ֶε�hashֵ��
 * 	2.Objcect.hashCode()�������ʲô��
 * 		��һ��intֵ��
 * 	3.equals(Object obj)��hashCode()֮��Ĺ�ϵ��
 * 		����������
 * 			equals()���Ϊtrue����hashCode()���Ҳ��Ȼ��ͬ��
 * 			equals()���Ϊfalse����hashCode()���������ͬ��
 * 			hashCode()�����ͬ����equals()�����һ����true��
 * 			hashCode()�����ͬ����equals()���һ����false��
 * 		hashCode()��equals()һ��Ҫ����һ���ԣ�Ҫ��д����������д������equals()���Ƚϵ��ֶκ����ڼ���hashCode()���ֶ�ҲҪһ�¡�
 * 
 * @author May
 * 2019��7��25��
 */
public class A03EqualsAndHashCode {
	public static void main(String[] args) {
		Student s1 = new Student();
		Student s2 = new Student();
		Student s3 = s2;
		/**
		 * ���ԣ�
		 * 	1.equals()��hashCode()��û��д;
		 * 	2.				   	    ������д;
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