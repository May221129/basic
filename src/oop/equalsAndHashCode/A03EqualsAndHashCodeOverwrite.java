package oop.equalsAndHashCode;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 为什么要保持equals()和hashCode()的一致性？
 * 如果equals()中用于比较的字段 和 hashCode()中用于计算的字段不一样，会怎样？
 * 
 * @author May
 * 2019年7月25日
 */
public class A03EqualsAndHashCodeOverwrite {
	public static void main(String[] args) {
		
		/**
		 * 按照重写的equals()，两只猫的catId一致，就判断为是同一只猫。所以白猫和黑猫应该是同一只猫。
		 * 	但是，因为重写的hashCode()方法中，是用name来计算哈希值的，所以白猫和黑猫的哈希值不一样。
		 * HashMap集合中，本不允许存在两只相同的猫的，但因为两只猫的哈希值不同，所以同一只猫（catId一致）存在了不同的桶中。
		 * 	这违反了“hashCode()结果不同，则equals()结果一定是false。”，类似于出现了脏数据。
		 * 所以说，equals()和hashCode()方法一定要保持一致性。
		 */
		Cat cat1 = new Cat("白猫", 123);
		Cat cat2 = new Cat("黑猫", 123);
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
	 * 重写的hashCode()中，以 name 字段来计算对象的哈希值。
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	/**
	 * 重写的equals()方法，以 catId 字段作为判断两只猫是否是同一只猫。
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