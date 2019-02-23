package collection.b_arraylist;
//Collection集合-List-ArrayList的知识整理：

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

public class TestArrayList {
	@Test
	public void testCollection4(){
		
		Collection c2 = new ArrayList();	
		c2.add(123);
		c2.add("今天天气真好！");
		c2.add("I Love You");
		
//		14.iterator():返回一个Iterator接口实现类的对象，从而实现集合的遍历！
		Iterator iterator = c2.iterator();//这里要对Iterator进行导包！
//		方式一(推荐使用）：
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
//		方法二（并不推荐使用，因为上面已经有现成的方法可以调用了）：
		Object[] obj = c2.toArray();
		for(int i = 0; i < obj.length; i++){
			System.out.println("*" + obj[i]);//iterator.next()
		}
		
//		方式三（不具有通用性，因为你不会每次都知道集合中有几个元素）：
//		System.out.println(iterator.next());
//		System.out.println(iterator.next());
//		System.out.println(iterator.next());
	}
	
	@Test
	public void TestCollection3(){
		
		Collection c2 = new ArrayList();	
		c2.add(123);
		c2.add("今天天气真好！");
		c2.add("I Love You");
		
		Collection c3 = new ArrayList();
		c3.add(123);
		c3.add(new String("今天天气真好！"));
		c3.add(5566);
		
		Collection c4 = new ArrayList();	
		c4.add("I Love You");
		
//		10.removeAll(Collection coll):从当前集合中删除包含在coll中的元素（相当于删除交集，保留差集）
		c2.removeAll(c3);//删除集合c2和c3中的交集
		System.out.println("10: " + c2);
		
//		11.equals(Object obj)判断两个集合中的所有元素是否完全相同
		boolean b3 = c2.equals(c4);
		System.out.println("11: " + b3);
		
//		12.hashCode():
		System.out.println(c3.hashCode());	
		
		System.out.println();
		
//		13.toArray():将集合转化为数组
		Object[] obj = c3.toArray();
//==>这里为什么要转成Object类型的数组？因为c3这个集合中什么类型的数据都有。
		for(int i = 0; i < obj.length; i++){
			System.out.println(obj[i]);
		}	
	}
	
	@Test
	public void testCollection2(){
		
		Collection c2 = new ArrayList();	
		c2.add(1234);
		c2.add("今天天气真好！");
		c2.add("I Love You");
		
//		6.contains(Object obj)：判断集合中是否包含指定的obj元素，若包含，返回turn，反之返回false。
//		判断的依据：根据元素所在类的equals()方法进行判断
//		明确：如果存入集合中的元素是自定义的类对象，要求：自定义类要重写equals()方法！
		System.out.println("6_1: " + c2.contains(123));
		c2.add(new Person("李光渣渣",26));
		boolean b1 = c2.contains(new Person("李光渣渣", 26));
		//在Person类中，equals()方法没有重写的时候，这里返回的是false，重写之后返回turn。
		System.out.println("6_2: " + b1);
		
//		7.containsAll(Collection coll):判断当前集合中是否包含coll中所有元素
		Collection c3 = new ArrayList();
		c3.add(12345);
		c3.add(new String("今天天气真好！"));
		c3.add(5566);
		System.out.println("7: " + c2.containsAll(c3));
		
//		8.retainAll(Collection coll):求当前集合与coll中共有的元素（交集），并返回给当前集合。
//		注意：如果当前集合与coll没有交集，当前集合会被修改为 空的集合[]
		c2.retainAll(c3);
		System.out.println("8: " + c2);
		
//		9.remove(Object obj):删除集合中的obj元素，若删除成功，返回turn；删除失败，返回false。
		boolean b2 = c2.remove("今天天气真好！");
		System.out.println("9_1: " + b2);
		System.out.println("9_2: c2" + c2);
	}
	
	@Test
	public void testCollection1(){
		
		Collection c1 = new ArrayList();//这里要导入它们的包
		
//		1.size():返回集合中元素的个数
		System.out.println(c1.size());
		
//		2.add(Object obj):向集合中添加一个元素
		c1.add(123);//因为add()方法所需的形参是一个对象，所以这里的123是进过自动装箱后的
		c1.add("我爱你");
		c1.add(90.34);
		c1.add(12+90);
		System.out.println(c1.size());
		
//		3.addAll(Collection coll):将形参coll中包含的所有元素都添加到当前集合中
		Collection c2 = Arrays.asList(1,2,3,4,5);
		c1.addAll(c2);
		System.out.println(c1.size());
		
//		4.isEmpty():判断这个集合是否为空
		System.out.println(c1.isEmpty());
//		在这里查看集合中的元素：
		System.out.println(c1.toString());//ArrayList对象重写了toString()方法
		
//		5.clear():清空集合中的所有元素
		c1.clear();
		System.out.println("5: " + c1.isEmpty());
		System.out.println("5: " + c1.toString());
		
		
	}
}
