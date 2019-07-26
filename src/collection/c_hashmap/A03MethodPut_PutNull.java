package collection.c_hashmap;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 测试：HashMap中可以存在null，但可以同时存在多个null吗？
 * 结果：可以存在null，但不能同时存在多个null。这和HashMap中的元素具有唯一性是一致的。
 * 
 * @author May
 * 2019年7月25日
 */
public class A03MethodPut_PutNull {
	public static void main(String[] args) {
		HashMap<String, Integer> hashMap = new HashMap<>();
		hashMap.put(null, 123);
		hashMap.put(null, 456);
		for(Entry<String, Integer> entry : hashMap.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}
