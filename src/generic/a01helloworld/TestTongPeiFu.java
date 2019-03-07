package generic.a01helloworld;
//通配符 ？  的使用规则：

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

public class TestTongPeiFu {
	@Test
	public void test1(){
		List<?> l1 = null;
		List<String> l2 = new ArrayList<String>();
		l2.add("A");
		l2.add("B");
		l1 = l2;//l1指向l2的存储内容
//		可以通过迭代器Iterator读取到声明为通配符的l1中的各个元素：
		Iterator<?> i = l1.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
//		不能向声明为通配符的集合类中写入元素：
//		l1.add("C");
//		l1.add(123);
		l1.add(null);//唯一可以存null！！！
	}
}
