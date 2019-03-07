package generic.a01helloworld;
import java.util.ArrayList;
//自定义的Order泛型类的使用：
//1.实例化泛型类的对象时，指明泛型的类型；指明以后，对应的类中所有使用泛型的位置，都变成实例化中指明的泛型的类型。
//2.如果自定义了泛型类，但在实例化时没有使用，那么默认类型是Object类
import java.util.List;

import org.junit.Test;

public class TestOrder {
	@Test
	public void testOrder(){

		Order<String> o1 = new Order<String>();
		
		o1.setE("A");
		System.out.println(o1.getE());//输出结果为A
		o1.setE("B");
		System.out.println(o1.getE());//输出结果为B
		
		o1.addE();
		
		List<String> l1 = o1.list;
		System.out.println("l1:" + l1);//输出结果为[B]
		
		System.out.println("=================================");
		
		System.out.println("泛型方法的调用：");
		//当通过对象调泛型方法时，指明泛型方法的类型：
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