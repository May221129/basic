package collection.b_linkedlist;
import java.util.ArrayList;
//Collection-List-LinkedList:
//适合用于频繁进行增、删操作
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class TestLinkedList {
	@Test
	public void testLinkedLinked(){
		List l = new LinkedList();
		l.add("AA");
		l.add(123);
		l.add("明天你好！");
		l.add("明天你好！");
		System.out.println(l.size());//查看集合的元素个数
		System.out.println(l);
		System.out.println(l.get(3));//查找某个位置的元素
		l.set(3, "Hello Word!");//修改某个位置的元素
		System.out.println(l);
	}
	@Test
	public void testList(){
		List l = new ArrayList();
		l.add("AA");
		l.add(123);
		l.add("明天你好！");
		l.add("明天你好！");
		l.get(2);
	}
}
