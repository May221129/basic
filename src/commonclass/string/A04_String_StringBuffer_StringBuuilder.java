package commonclass.string;

import org.junit.Test;

/**
 * 
 * 一、String、StringBuffer、StringBuilder这三者之间的比较：
 * 		这篇博客说得很详细了：https://www.cnblogs.com/huaxingtianxia/p/6021960.html
 * 
 * 二、有关String：【第4点和第5点中，所涉及的String Pool中的HashMap表来存放字符串，这里并未深入了解。】
 * 	1.做String对象的相等性判断，使用equals()方法，而不是用 == 。
 * 	2.String是常量，其对象一旦创建完毕就无法改变；当使用 + 拼接字符串时，会生成新的String对象，而不是向原有的String对象中追加内容；
 * 	3.String Pool(字符串池)；
 * 	4.String s = "aaa"; (采用字面值方式赋值)
 * 		（1）查找String Pool中的HashMap表中是否存在"aaa"字符串的记录。
 * 			如果不存在：
 * 				（2）则在堆中创建一个"aaa"对象
 *				（3）再在HashMap表中创建一个"aaa"记录，key为通过"aaa"字符串的Unicode值算出的唯一结果(具体怎么通过Unicode字符
 *					串对应的Unicode值来获得这个唯一值，并没深入了解)，value为堆中的"aaa"对象的引用；
 *				（4）然后将堆中这个"aaa"对象的地址返回来，并赋给引用变量s，导致s会指向堆中的"aaa"字符串对象；
 * 			如果存在：
 * 				（2）则直接将HashMap表中"aaa"字符串记录的value值所指向的那个堆中的"aaa"对象赋给s引用。(因为HashMap中既然存在"aaa"
 * 					记录，那“aaa"记录的value值就一定会指向堆中的"aaa"对象，所以既不用再在HashMap表中创建"aaa"记录，也不用在堆
 * 					中创建"aaa"对象了)
 * 	5.String s = new String("aaa");
 * 		（1）查找String Pool中的HashMap表中是否存在"aaa"字符串的记录。
 *			如果存在：
 *				（2）则在堆中创建一个"aaa"字符串对象，然后将这个对象的地址赋给s引用，这样s就指向了堆中创建的这个"aaa"字符串对象了。
 * 			如果不存在：
 * 				（2）则在堆中创建一个"aaa"对象；
 *				（3）再在HashMap表中创建一个"aaa"记录，key为通过"aaa"字符串的Unicode值算出的唯一结果，value为通过（2）创建的
 *					堆中的"aaa"对象的引用；
 *				（4）再在堆中创建一个"aaa"字符串对象，然后将这个对象的地址赋给s引用，这样s就指向了堆中第二次创建的这个"aaa"字符串对象了。
 * 
 * 三、有关String的拘留字符串：
 * 	1 String Pool其实就是运行时常量池中存在着一批拘留字符串；
 * 	2 String s = "haha"; s就是拘留字符串的引用，即会把字符串池中的同名引用赋值给s;
 * 	3   当 new String("haha"); 会在堆中创建一个String对象，还会再方法去的运行时常量池中创建一个拘留字符串；
 * 	4 String s = new String ("haha"); s.intern(); 用intern()方法得到该字符串的拘留字符串的对象的引用；
 * 		如果运行时常量池中没有该字符串，那就新建一个；如果已经有了，就直接吧引用传给s.
 */
public class A04_String_StringBuffer_StringBuuilder {
	
	/**
	 * 1.比较两个直接声明的String
	 * 2.用 == 来比较两个String对象是否相等；
	 * 3.用equals()方法来比较两个String对象是否相等；
	 */
	@Test
	public void testString(){
		String i = "赖";
		String j = "赖";
		System.out.println(i == j);//true
		System.out.println(i.equals(j));//true
		
		String z = new String("赖");
		System.out.println(i == z);//false
		System.out.println(i.equals(z));//true
	}
	
	/**
	 * 这里涉及到光荣说的有关String更深的知识：具体我没参透
	 * 	说“字符串是放在String Pool中的”这一说法不够准确，String Pool是一个HashMap数据结构的表，
	 * 		1. "String a = "赖";"这一行代码的底层：
	 * 			a是拘留字符串的引用，会把字符串常量池中"赖"的同名引用赋值给a;
	 * 			如果运行时字符串常量池中没有“赖”这一拘留字符时，就新建一个，再将引用赋值给a;
	 * 			【"赖"字符根据其Unicode值，存放到String Pool的桶中的某个位置。】
	 * 		2. "String b = "皮";"也和 1 相同；
	 * 		3. "String c = a + b;":
	 * 			① StringBuilder temp = new StringBuilder(a)，
     *			② temp.append(b);
     *			③ c = temp.toString();
	 */
	@Test
	public void testAddString(){
		String a = "赖";
		String b = "皮";
		
		String c = a + b;
		System.out.println(c);
		
		String d = b + a;
		System.out.println(d);
		System.out.println(c == d);//false
		
		String e = new String("赖皮");
		System.out.println(c == e);//false
		System.out.println(c.equals(e));//true
	}
	
	/**
	 * 累加
	 */
	@Test
	public void testStringBuffered(){
		
		/**
		 *  String常量与String变量的"+"操作比较 ：
		 * 	局部变量s和ss存放的是两个不同的拘留字符串对象的地址。然后会通过下面三个步骤完成“+连接”：
         *		1、StringBuilder temp = new StringBuilder(s)，
         *		2、temp.append(s);
         *		3、ss = temp.toString();
		 */
		String s = "hello";
		String ss = "world";
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			ss = s + ss;
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);//298
		
		System.out.println("----------------------------------");
		
		String s1 = "hello";
		StringBuffer sb1 = new StringBuffer(100000);
		startTime = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			sb1.append(s1);
		}
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);//0
		
		System.out.println("----------------------------------");
		
		String s2 = "hello";
		StringBuffer sb2 = new StringBuffer();
		startTime = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			sb2.append(s2);
		}
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);//1
	}
	
	@Test
	public void testAddString2(){
		
		String a = "hello";
		String b = "world";
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			String c = a + b;
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);//4
		
		System.out.println("-------------------------------");
		
		/**
		 * 字符串相加
		 * "hello"+"world"在编译阶段就已经连接起来，形成了一个字符串常量"helloworld"，并指向堆中的拘留字符串对象。
		 * 	运行时只需要将"helloworld"指向的拘留字符串对象地址取出1W次，存放在局部变量str中，所以不需要什么时间。
		 */
		String str = "";
		startTime = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			str = "hello" + "world";
		}
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);//0
	}
}
