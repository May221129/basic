package collection.b_hashset;
//���HashSetʵ����洢Ԫ�ص��ص㣺
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TestHashSet1 {
	@Test
	public void testHashSet1(){
		Set s1 = new HashSet();
		s1.add("��");
		s1.add("Ҫ");
		s1.add("��");
		s1.add("��");
		s1.add("ѧ");
		s1.add("ϰ");
		s1.add(null);//����Ԫ�ؿ����� null
		System.out.println("s1's size: " + s1.size());
//���Ϊ6==>֤������HashSet�е�Ԫ�ز����ظ�;��HashSetʵ�����Ѿ���д��equals����������hashCode����������
		System.out.println(s1.toString());
//������Ϊ��[ϰ, null, ��, Ҫ, ѧ, ��]==>֤������HashSet��������;��HashSet��д��toString����������
	}
}
