package generic.a01helloworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 * ��᷺���ڼ����е�ʹ�ã�
 */
public class TestGeneric {
//	ʹ���˷��ͺ��Map���ϣ�
	@Test
	public void testGeneric3(){
		Map<String, Integer> m1 = new HashMap<String, Integer>();
		m1.put("A", 1);
		m1.put("B", 2);
		m1.put("C", 3);
		 Set<Map.Entry<String, Integer>> s3 = m1.entrySet();
		for(Map.Entry<String, Integer> me : s3){
//			System.out.println(me);//������������д�������ԡ�
			System.out.println(me.getKey() + "-->" + me.getValue());
		}
	}
	
//	ʹ���˷��ͺ��Lisst���ϣ�
	@Test
	public void testGeneric2(){
		List<String> l2 = new ArrayList<String>();
		l2.add("A");
		l2.add("B");
		l2.add("C");
//		l2.add(123);//ʹ�÷���֮����ӽ������е�Ԫ�ؾ�ֻ����ͬһ�����͵ġ�
		for(String s : l2){
			System.out.println(s);
		}
	}
	
//	û��ʹ�÷���ʱ�ļ��ϣ�
	@Test
	public void testGeneric1(){
		List l1 = new ArrayList();
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add("Hello!");
//==>��ûʹ�÷��͵�����£���ӽ������е�Ԫ��û�����ƣ��������Ϳ��ܲ�һ�¡�
		for(Object o :l1){
			Integer i = (Integer)o;
			System.out.println(i);
//==>��Ϊ�����е�Ԫ�����Ͳ�һ�£�ǿת�ǿ��ܻᵼ�¡�ClassCastException���쳣��
		}
	}
}
