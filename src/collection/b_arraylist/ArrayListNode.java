package collection.b_arraylist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 2018.4.17 第一次尝试理解ArrayList的源码。
 * 1.为什么AbstractList抽象类继承了List接口，ArrayList继承了AbstractList抽象类后，还需要再去实现List接口？
 * 	答：因为在对ArrayList进行动态代理的时候，需要知道它实现了什么接口。
 * 	如果ArrayList没有自己去实现List接口，而是通过“AbstractList去实现List接口”这样隔着一层的关系，ArrayList的描述类Class是没法拿到其List接口的。
 * 2.
 */
public class ArrayListNode {
	@Test
	public void test03(){
		Object[] o = new String[2];
		
	}
	
	/**
	 * .getClass() 和 .class 都是拿到运行时类对象（描述类Class）
	 * 
	 * Java的每个类都带有一个运行时类对象，该Class对象中保存了创建对象所需的所有信息。
     * 可以用.class返回此 Object 的运行时类Class对象，也可以用getClass()获得。
     * 获得此对象后可以利用此Class对象的一些反射特性进行操作，例如：
     * 	this.getClass().newInstance(); //用缺省构造函数创建一个该类的对象
     * 	this.getClass().getInterfaces(); //获得此类实现的接口信息
     * 	this.getClass().getMethods();//获得此类实现的所有公有方法
     * 	Class.forName(" ... JDBC driver class name...."); // Class类的静态方法forName, 向DiverManager注册这个JDBC driver类
	 */
	@Test
	public void test02(){
		Object o = new String();
		System.out.println(o.getClass().getName());
		System.out.println(o.getClass());
		
		System.out.println(Person.class);
	}
	
	/**
	 * 构造函数：public ArrayList(Collection<? extends E> c)
	 * 将c集合作为ArrayList构造函数的入参，是将c集合中所有的元素都复制到ArrayList集合中，是深克隆。
	 */
	@Test
	public void test01(){
		/**
		 * 1.集合中放的是八种基本类型的包装类：深克隆
		 * 但八种基本类型的包装类比较特殊，它有缓存机制，所以为了确保测试的准确度，还需用普通的自定义类来做测试，见2.
		 */
		Collection<Integer> collection1 = new ArrayList<>();
		collection1.add(123);
		collection1.add(234);
		List<Integer> list1 = new ArrayList<>(collection1);
		for(Integer element : list1){
			System.out.println(element);
		}
		
		/**
		 * 2.集合中放的是自定义类的引用：深克隆
		 */
		Person p1 = new Person("张三", 23);
		Person p2 = new Person("李四", 24);
		Collection<Person> collection2 = new LinkedList<>();
		collection2.add(p1);
		collection2.add(p2);
		ArrayList<Person> arrayList2 = new ArrayList<>(collection2);
		for(Person person : arrayList2){
			System.out.println(person);
		}
	}
	
	/**
	 * ArrayList的遍历真的比LinkedList的遍历来得快吗？快多少？
	 * 创建一个ArrayList，并向其add进一千万个数字。
	 * 创建一个LinkedList，并向其add进一千万个数字。
	 * 比较这两者之间的用时，并探究ArrayList和LinkedList的扩容机制。
	 */
	@Test
	public void test00(){
		int size = 10000 * 1000;//一千万
		
		ArrayList<Integer> array = new ArrayList<>(size);
		long start = System.currentTimeMillis();
		for(int i = 0; i < size; i++){
			array.add(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("array:add() " + (end - start));//2480
		
		System.out.println("----------------------------------");
		
		LinkedList<Integer> linked = new LinkedList<>();
		long start1 = System.currentTimeMillis();
		for(int i = 0; i < size; i++){//一千万
			linked.add(i);
		}
		long end1 = System.currentTimeMillis();
		System.out.println("linked:add() " + (end1 - start1));//3813
		
		System.out.println("------------- 增强for循环 ---------------------");
		
		long start2 = System.currentTimeMillis();
		for(Integer element : array){

		}
		long end2 = System.currentTimeMillis();
		System.out.println("array:遍历：" + (end2 - start2));//29
		
		System.out.println("------------------------------------");
		
		long start3 = System.currentTimeMillis();
		for(Integer element : linked){
			
		}
		long end3 = System.currentTimeMillis();
		System.out.println("linked:遍历：" + (end3 - start3));//80
		
		System.out.println("------------------- 普通for循环 ----------------------");
		/**
		 * 用普通for循环对ArrayList进行遍历和用增强型for循环对ArrayList进行遍历，两者的用时没有太大区别。
		 * 用增强型for循环对LinkedList进行遍历的效率并不会比ArrayList的遍历效率来得低很多，因为用的是迭代器。
		 * 用普通for循环对LinkedList进行遍历，效率非常慢，这和LinkedList是链表结构有关，LinkedList在get任何一个位置的数据的时候，都会把前面的数据走一遍。
		 */
//		long start4 = System.currentTimeMillis();
//		for(int i = 0; i < array.size(); i++){
//			array.get(i);
//		}
//		long end4 = System.currentTimeMillis();
//		System.out.println(end4 - start4);
//		
//		long start5 = System.currentTimeMillis();
//		for(int i = 0; i < linked.size(); i++){
//			linked.get(i);
//		}
//		long end5 = System.currentTimeMillis();
//		System.out.println("普通for循环遍历两个数组： " + (end5 - start5));
	}
}
