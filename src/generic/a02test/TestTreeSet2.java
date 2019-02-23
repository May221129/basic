package generic.a02test;
//TreeSet�ж����������Ȼ�������ϰ��

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import org.junit.Test;

public class TestTreeSet2 {
	@Test
	public void testTreeSet1(){
//		��Ȼ����
		TreeSet<Employee> t = new TreeSet<Employee>();
//==>���ĵĲ��ǰ���ABCD˳�����ŵģ����ǰ��������ֵ�hashֵ���ţ�
		t.add(new Employee("���»�", 1, new MyDate(2017, 5, 12)));
		t.add(new Employee("������", 2, new MyDate(2016, 4, 11)));
		t.add(new Employee("��   ��", 5, new MyDate(2012, 1, 30)));
		t.add(new Employee("��ѧ��", 6, new MyDate(2011, 5, 23)));
		t.add(new Employee("����Ѹ", 1, new MyDate(2017, 5, 23)));
//		���������е�����Ԫ�أ�
//		for(Object obj : t){
//			System.out.println(obj);
//		}
		Iterator<Employee> i = t.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
	}
	@Test
	public void testTreeSet2(){
//		��������
		Comparator c = new Comparator(){
			
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Employee && o2 instanceof Employee){
					Employee e1 = (Employee)o1;
					Employee e2 = (Employee)o2;
					MyDate birth1 = e1.getBirthday();
					MyDate birth2 = e2.getBirthday();
					if(birth1.getYear() != birth2.getYear()){
						return birth1.getYear() - birth2.getYear();
					}else{
						if(birth1.getMonth() != birth2.getMonth()){
							return birth1.getMonth() - birth2.getMonth();
						}else{
							return birth1.getDay() - birth2.getDay();
						}
					}
				}
				return 0;
			}
		};	
		
		TreeSet<Employee> set = new TreeSet<>(c);
		
		set.add(new Employee("���»�", 1, new MyDate(2017, 5, 12)));
		set.add(new Employee("������", 2, new MyDate(2017, 4, 11)));
		set.add(new Employee("����", 5, new MyDate(2012, 1, 30)));
		set.add(new Employee("��ѧ��", 6, new MyDate(2011, 5, 23)));
		set.add(new Employee("����Ѹ", 1, new MyDate(2017, 5, 23)));
		
//		Iterator<Employee> i = set.iterator();
//		while(i.hasNext()){
//			System.out.println(i.next());
//		}
		for(Object o : set){
			System.out.println(o);
		}
		}
	}
