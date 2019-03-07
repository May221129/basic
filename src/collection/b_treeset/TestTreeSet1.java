package collection.b_treeset;
//Collection-set-TreeSet֮��������

import java.util.Comparator;
import java.util.TreeSet;
import org.junit.Test;

public class TestTreeSet1 {
	@Test
	public void testTreeSet1(){//������
		TreeSet set = new TreeSet(new Comparator(){
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Man && o2 instanceof Man){
					Man m1 = (Man)o1;
					Man m2 = (Man)o2;
					int i =  m1.getId().compareTo(m2.getId());
					if(i == 0){//����0��˵���Աȵĵ�һ���������
						return m1.getName().compareTo(m2.getName());
					}else{
						return i;
					}
				}
				return 0;
			}
		});
		set.add(new Man("A",1005));
		set.add(new Man("G",1003));
		set.add(new Man("E",1001));
		set.add(new Man("C",1001));
		set.add(new Man("A",1004));
		System.out.println(set);
	}
	
	@Test
	public void testTreeSet(){//������
		//1������һ��ʵ����Comparator�ӿڵ���Ķ���
		Comparator com = new Comparator(){//����ʵ�������
			//��TreeSet�����Man��Ķ����ڴ�Compare()�����У�ָ���ǰ���Man�е��ĸ���������ģ�
			@Override
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Man && o2 instanceof Man){
					Man m1 = (Man)o1;
					Man m2 = (Man)o2;
					int i =  m1.getId().compareTo(m2.getId());
					if(i == 0){//����0��˵���Աȵĵ�һ���������
						return m1.getName().compareTo(m2.getName());
					}else{
						return i;
					}
				}
				return 0;
			}
		};
		//2�����˶�����Ϊ�βδ��ݸ�TreeSet�Ĺ������У�
		TreeSet set = new TreeSet(com);
		//3����TreeSet�����Comparator�ӿ��е�compare���������漰�������
		set.add(new Man("A",1005));
		set.add(new Man("G",1003));
		set.add(new Man("E",1001));
		set.add(new Man("C",1001));
		set.add(new Man("A",1004));
		System.out.println(set);
	}
}
