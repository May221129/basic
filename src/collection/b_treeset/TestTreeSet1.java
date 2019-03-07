package collection.b_treeset;
//Collection-set-TreeSet之定制排序：

import java.util.Comparator;
import java.util.TreeSet;
import org.junit.Test;

public class TestTreeSet1 {
	@Test
	public void testTreeSet1(){//匿名的
		TreeSet set = new TreeSet(new Comparator(){
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Man && o2 instanceof Man){
					Man m1 = (Man)o1;
					Man m2 = (Man)o2;
					int i =  m1.getId().compareTo(m2.getId());
					if(i == 0){//等于0，说明对比的第一个属性相等
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
	public void testTreeSet(){//有名的
		//1、创建一个实现了Comparator接口的类的对象：
		Comparator com = new Comparator(){//匿名实现类对象
			//想TreeSet中添加Man类的对象，在此Compare()方法中，指明是按照Man中的哪个属性排序的：
			@Override
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Man && o2 instanceof Man){
					Man m1 = (Man)o1;
					Man m2 = (Man)o2;
					int i =  m1.getId().compareTo(m2.getId());
					if(i == 0){//等于0，说明对比的第一个属性相等
						return m1.getName().compareTo(m2.getName());
					}else{
						return i;
					}
				}
				return 0;
			}
		};
		//2、将此对象作为形参传递给TreeSet的构造器中：
		TreeSet set = new TreeSet(com);
		//3、向TreeSet中添加Comparator接口中的compare（）方法涉及的类对象：
		set.add(new Man("A",1005));
		set.add(new Man("G",1003));
		set.add(new Man("E",1001));
		set.add(new Man("C",1001));
		set.add(new Man("A",1004));
		System.out.println(set);
	}
}
