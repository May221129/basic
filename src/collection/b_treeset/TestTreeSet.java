package collection.b_treeset;
//Collection-set-TreeSet之自然排序

import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;

public class TestTreeSet {
	@Test
	public void testTreeSet(){
	TreeSet tree = new TreeSet();
	tree.add(new String("D"));
	tree.add(new String("B"));
	tree.add(new String("我想你"));
	tree.add(new String("C"));
	tree.add(new String("A"));	
	tree.add(new String("A"));//相同的元素，第二次出现时添加不进来。
	tree.add("我爱你");
//	tree.add(123);//ClassCastException
	System.out.println(tree);
//	输出结果是[A, B, C, D, 我想你, 我爱你]，说明String重写了CompareTo()方法
	}
	
	@Test
	public void testTreeSet1(){
		TreeSet s = new TreeSet();
		s.add(new Person("李光荣", 21));
		s.add(new Person("赖丽梅", 21));
		s.add(new Person("袁世凯", 22));
		s.add(new Person("宝宝", 23));
		s.add(new Person("宝宝", 20));
		System.out.println(s);
	}
}
