package collection.b_linkedhashset;
//���LinkedHashSetʵ����洢Ԫ�ص��ص㣺
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

public class TestLinkedHashSet {
	@Test
	public void testLinkedHashSet(){
		Set s1 = new LinkedHashSet();
		s1.add("��");
		s1.add("Ҫ");
		s1.add("��");
		s1.add("��");
		s1.add("ѧ");
		s1.add("ϰ");
		s1.add(null);
		System.out.println("s1's size: " +s1.size());
//���Ϊ6==>֤������LinkedHashSet�е�Ԫ�ز����ظ�;��LinkedHashSetʵ�����Ѿ���д��equals����������hashCode����������
		System.out.println(s1);
/*������Ϊ��[��, Ҫ, ��, ѧ, ϰ, null]==>֤������HashSetʹ������ά����һ����ӽ������е�˳��
���Ե����Ǳ���LinkedHashSet����Ԫ��ʱ���ǰ�����ӽ�ȥ��˳�������;��HashSet��д��toString���������� */
		s1.remove(null);
		System.out.println(s1);
	}
}
