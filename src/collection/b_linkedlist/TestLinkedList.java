package collection.b_linkedlist;
import java.util.ArrayList;
//Collection-List-LinkedList:
//�ʺ�����Ƶ����������ɾ����
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class TestLinkedList {
	@Test
	public void testLinkedLinked(){
		List l = new LinkedList();
		l.add("AA");
		l.add(123);
		l.add("������ã�");
		l.add("������ã�");
		System.out.println(l.size());//�鿴���ϵ�Ԫ�ظ���
		System.out.println(l);
		System.out.println(l.get(3));//����ĳ��λ�õ�Ԫ��
		l.set(3, "Hello Word!");//�޸�ĳ��λ�õ�Ԫ��
		System.out.println(l);
	}
	@Test
	public void testList(){
		List l = new ArrayList();
		l.add("AA");
		l.add(123);
		l.add("������ã�");
		l.add("������ã�");
		l.get(2);
	}
}
