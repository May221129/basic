package oop.equalsAndHashCode;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * ΪʲôҪ����equals()��hashCode()��һ���ԣ�
 * ���equals()�����ڱȽϵ��ֶ� �� hashCode()�����ڼ�����ֶβ�һ������������
 * 
 * @author May
 * 2019��7��25��
 */
public class A03EqualsAndHashCodeOverwrite {
	public static void main(String[] args) {
		
		/**
		 * ������д��equals()����ֻè��catIdһ�£����ж�Ϊ��ͬһֻè�����԰�è�ͺ�èӦ����ͬһֻè��
		 * 	���ǣ���Ϊ��д��hashCode()�����У�����name�������ϣֵ�ģ����԰�è�ͺ�è�Ĺ�ϣֵ��һ����
		 * HashMap�����У��������������ֻ��ͬ��è�ģ�����Ϊ��ֻè�Ĺ�ϣֵ��ͬ������ͬһֻè��catIdһ�£������˲�ͬ��Ͱ�С�
		 * 	��Υ���ˡ�hashCode()�����ͬ����equals()���һ����false�����������ڳ����������ݡ�
		 * ����˵��equals()��hashCode()����һ��Ҫ����һ���ԡ�
		 */
		Cat cat1 = new Cat("��è", 123);
		Cat cat2 = new Cat("��è", 123);
		System.out.println(cat1.hashCode() == cat2.hashCode());//false
		System.out.println(cat1.equals(cat2));//true
		
		HashMap<String, Integer> hashMap = new HashMap<>();
		hashMap.put(cat1.getName(), cat1.getCatId());
		hashMap.put(cat2.getName(), cat2.getCatId());
		for(Entry<String, Integer> entry : hashMap.entrySet()) {
			System.out.println(entry.getKey() + " : "  + entry.getValue());
		}
	}
}
class Cat{
	private String name;
	private Integer catId;
	
	public Cat(String name, Integer catId) {
		this.name = name;
		this.catId = catId;
	}
	public String getName() {
		return this.name;
	}
	public Integer getCatId() {
		return this.catId;
	}
	
	/**
	 * ��д��hashCode()�У��� name �ֶ����������Ĺ�ϣֵ��
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	/**
	 * ��д��equals()�������� catId �ֶ���Ϊ�ж���ֻè�Ƿ���ͬһֻè��
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cat other = (Cat) obj;
		if (catId != other.catId)
			return false;
		return true;
	}
}