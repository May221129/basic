package collection.b_linkedhashset;
//体会LinkedHashSet实现类存储元素的特点：
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

public class TestLinkedHashSet {
	@Test
	public void testLinkedHashSet(){
		Set s1 = new LinkedHashSet();
		s1.add("我");
		s1.add("要");
		s1.add("好");
		s1.add("好");
		s1.add("学");
		s1.add("习");
		s1.add(null);
		System.out.println("s1's size: " +s1.size());
//结果为6==>证明：①LinkedHashSet中的元素不可重复;②LinkedHashSet实现类已经重写了equals（）方法和hashCode（）方法。
		System.out.println(s1);
/*输出结果为：[我, 要, 好, 学, 习, null]==>证明：①HashSet使用链表维护了一个添加进集合中的顺序，
所以当我们遍历LinkedHashSet集合元素时，是按照添加进去的顺序遍历的;②HashSet重写了toString（）方法。 */
		s1.remove(null);
		System.out.println(s1);
	}
}
