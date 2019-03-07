package generic.a01helloworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 * 体会泛型在集合中的使用：
 */
public class TestGeneric {
//	使用了泛型后的Map集合：
	@Test
	public void testGeneric3(){
		Map<String, Integer> m1 = new HashMap<String, Integer>();
		m1.put("A", 1);
		m1.put("B", 2);
		m1.put("C", 3);
		 Set<Map.Entry<String, Integer>> s3 = m1.entrySet();
		for(Map.Entry<String, Integer> me : s3){
//			System.out.println(me);//这里的两种输出写法都可以。
			System.out.println(me.getKey() + "-->" + me.getValue());
		}
	}
	
//	使用了泛型后的Lisst集合：
	@Test
	public void testGeneric2(){
		List<String> l2 = new ArrayList<String>();
		l2.add("A");
		l2.add("B");
		l2.add("C");
//		l2.add(123);//使用泛型之后，添加进集合中的元素就只能是同一种类型的。
		for(String s : l2){
			System.out.println(s);
		}
	}
	
//	没有使用泛型时的集合：
	@Test
	public void testGeneric1(){
		List l1 = new ArrayList();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add("Hello!");
//==>在没使用泛型的情况下，添加进集合中的元素没有限制，导致类型可能不一致。
		for(Object o :l1){
			Integer i = (Integer)o;
			System.out.println(i);
//==>因为集合中的元素类型不一致，强转是可能会导致“ClassCastException”异常。
		}
	}
}
