package generic.a01helloworld;
//ͨ��� ��  ��ʹ�ù���

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
		l1 = l2;//l1ָ��l2�Ĵ洢����
//		����ͨ��������Iterator��ȡ������Ϊͨ�����l1�еĸ���Ԫ�أ�
		Iterator<?> i = l1.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
//		����������Ϊͨ����ļ�������д��Ԫ�أ�
//		l1.add("C");
//		l1.add(123);
		l1.add(null);//Ψһ���Դ�null������
	}
}
