package generic.a01helloworld;
import java.util.ArrayList;
//�Զ����Order�������ʹ�ã�
//1.ʵ����������Ķ���ʱ��ָ�����͵����ͣ�ָ���Ժ󣬶�Ӧ����������ʹ�÷��͵�λ�ã������ʵ������ָ���ķ��͵����͡�
//2.����Զ����˷����࣬����ʵ����ʱû��ʹ�ã���ôĬ��������Object��
import java.util.List;

import org.junit.Test;

public class TestOrder {
	@Test
	public void testOrder(){

		Order<String> o1 = new Order<String>();
		
		o1.setE("A");
		System.out.println(o1.getE());//������ΪA
		o1.setE("B");
		System.out.println(o1.getE());//������ΪB
		
		o1.addE();
		
		List<String> l1 = o1.list;
		System.out.println("l1:" + l1);//������Ϊ[B]
		
		System.out.println("=================================");
		
		System.out.println("���ͷ����ĵ��ã�");
		//��ͨ����������ͷ���ʱ��ָ�����ͷ��������ͣ�
		Integer l2 = o1.getT(123);
		Double l3 = o1.getT(123.456);
		System.out.println("l2: " + l2 + "; " + " l3: " + l3);
		
		System.out.println("=================================");
		
		Integer[] i = new Integer[]{1,2,3,4,5};
		List<Integer> l4 = new ArrayList<Integer>();
		o1.fromArrayToList(i, l4);
		System.out.println(l4);
	}
}