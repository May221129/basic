package generic.a01helloworld;
//泛型与继承的关系:若类A是类B的子类，那么List<A>就不是List<B>的子类/子接口.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class TestGenericAndExtends {
	@Test
	public void testGenericAndExtends(){
//		在没有使用泛型时，String类是Object类的子类。
		Object obj = null;
		String str = "AA";
		obj = str;
		System.out.println(obj);//输出结果为AA
		
		List<Object> l1 = null;
		List<String> l2 = new ArrayList<String>();
//		l1 = l2;//这里无法将l2赋值给l1;
		
		method1(l1);
//		method1(l2);
		
		method2(l1);
		method2(l2);
	}
	
	public void method1(List<Object> list){
		
	}
	public void method2(List<?> list){//使用了通配符后，该方法就具有通用性。
		
	}
	
//	使用通配符后：
	@Test
	public void testGenericAndExtends1(){
		List<?> l1 = new ArrayList();
		List<String> l2 = new ArrayList<String>();
		l2.add("赖丽梅");
		l1 = l2;
		System.out.println("*" + l1);//输出：[赖丽梅]
		
		List<Integer> l3 = new ArrayList<Integer>();
		l3.add(123);
		l1 = l3;
		System.out.println("*" + l1);//输出：[123]
		
		List<String> l4 = new LinkedList<String>();
		l4.add("李光荣");
		l1 = l4;
		System.out.println("*" + l1);//输出：[李光荣]
		
		List<Object> l5 = null;
		
		List<? extends Number> l6 = null;//Number下面的子类有int,long,float,double;
		l6 = l3;//可以存放Number及其子类
//		l6 = l5;//l5是Object类型的，这里存放不了，报错！
		
		List<? super Number> l7 = null;
		l7 = l5;//可以存放Number及其父类
//		l7 = l3;//l3是Integer类型的，这里存放不了！
//		l7 = l4;//l4是String类型的，非父类，也非子类，这里存放不了！
		
	}
}
