package collection.c_hashmap;

/**
 * 探究1：HashMap的keySet、valueSet和EntrySet的简单遍历。
 */
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class A01Iterable {
	@Test
	public void testMap() {
		Map<String, Integer> m1 = new HashMap<String, Integer>();
		m1.put("李光荣", 26);// 向Map中添加一个元素
		m1.put("赖丽梅", 26);
		m1.put("李天昱", 12);
		m1.put("李光荣", 18);
		m1.put("拉拉", 2);
		m1.put(null, null);
		System.out.println(m1);
		m1.remove("赖丽梅");// 删除指定的value值
		System.out.println(m1);

		System.out.println("………………遍历key集………………");

		// 遍历key集：
		Set<String> s = m1.keySet();
		for (Object o : s) {
			System.out.println(o);
		}

		System.out.println("………………遍历value集………………");

		// 遍历value集：
		Collection<Integer> c = m1.values();
		Iterator<Integer> i = c.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}

		System.out.println("………………遍历key-value对,方式一………………");

		// 遍历key-value对：
		// 方法一：
		Set<String> set = m1.keySet();
		for (Object o : set) {
			System.out.println(o + "=" + m1.get(o));
		}

		System.out.println("………………遍历key-value对,方式二………………");

		// 方法二：
		Set<Map.Entry<String, Integer>> set2 = m1.entrySet();
		for (Map.Entry<String, Integer> entry : set2) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}
}
