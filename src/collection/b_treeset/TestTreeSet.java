package collection.b_treeset;
//Collection-set-TreeSet֮��Ȼ����

import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;

public class TestTreeSet {
	@Test
	public void testTreeSet(){
	TreeSet tree = new TreeSet();
	tree.add(new String("D"));
	tree.add(new String("B"));
	tree.add(new String("������"));
	tree.add(new String("C"));
	tree.add(new String("A"));	
	tree.add(new String("A"));//��ͬ��Ԫ�أ��ڶ��γ���ʱ��Ӳ�������
	tree.add("�Ұ���");
//	tree.add(123);//ClassCastException
	System.out.println(tree);
//	��������[A, B, C, D, ������, �Ұ���]��˵��String��д��CompareTo()����
	}
	
	@Test
	public void testTreeSet1(){
		TreeSet s = new TreeSet();
		s.add(new Person("�����", 21));
		s.add(new Person("����÷", 21));
		s.add(new Person("Ԭ����", 22));
		s.add(new Person("����", 23));
		s.add(new Person("����", 20));
		System.out.println(s);
	}
}
