package generic.a02test;
//TreeSet中定制排序和自然排序的练习：

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import org.junit.Test;

public class TestTreeSet2 {
	@Test
	public void testTreeSet1(){
//		自然排序：
		TreeSet<Employee> t = new TreeSet<Employee>();
//==>中文的不是按照ABCD顺序来排的，而是按中文文字的hash值来排：
		t.add(new Employee("刘德华", 1, new MyDate(2017, 5, 12)));
		t.add(new Employee("郭富城", 2, new MyDate(2016, 4, 11)));
		t.add(new Employee("黎   明", 5, new MyDate(2012, 1, 30)));
		t.add(new Employee("张学友", 6, new MyDate(2011, 5, 23)));
		t.add(new Employee("陈奕迅", 1, new MyDate(2017, 5, 23)));
//		遍历集合中的所有元素：
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
//		定制排序：
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
		
		set.add(new Employee("刘德华", 1, new MyDate(2017, 5, 12)));
		set.add(new Employee("郭富城", 2, new MyDate(2017, 4, 11)));
		set.add(new Employee("黎明", 5, new MyDate(2012, 1, 30)));
		set.add(new Employee("张学友", 6, new MyDate(2011, 5, 23)));
		set.add(new Employee("陈奕迅", 1, new MyDate(2017, 5, 23)));
		
//		Iterator<Employee> i = set.iterator();
//		while(i.hasNext()){
//			System.out.println(i.next());
//		}
		for(Object o : set){
			System.out.println(o);
		}
		}
	}
