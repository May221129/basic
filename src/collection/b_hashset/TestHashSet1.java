package collection.b_hashset;
//体会HashSet实现类存储元素的特点：
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TestHashSet1 {
	@Test
	public void testHashSet1(){
		Set s1 = new HashSet();
		s1.add("我");
		s1.add("要");
		s1.add("好");
		s1.add("好");
		s1.add("学");
		s1.add("习");
		s1.add(null);//集合元素可以是 null
		System.out.println("s1's size: " + s1.size());
//结果为6==>证明：①HashSet中的元素不可重复;②HashSet实现类已经重写了equals（）方法和hashCode（）方法。
		System.out.println(s1.toString());
//输出结果为：[习, null, 我, 要, 学, 好]==>证明：①HashSet的无序性;②HashSet重写了toString（）方法。
	}
}
