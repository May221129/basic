package string.a01String;

import org.junit.Test;

/**
 * http://silencegg.iteye.com/blog/2069267
 * 
 * 1:拘留字符串是为了省内存
 * 
 * 2:jdk1.6的拘留字符串放在permgen区中(permgen有固定大小,不敢用intern字符串,怕爆了), 
 * 		自从jdk1.7拘留字符串就搬家到了堆中. 就不用怕爆掉了.
 * 
 * 3:jdk实现拘留字符串的数据结构是一个类似hashmap的结构.  只不过桶大小最大大小是-XX:StringTableSize.
 * 		由参数-XX:StringTableSize控制. jdk1.7中的默认大小是1009, jdk1.8中的默认大小是25-50k.
 * 		注意这个值最好为质数, 这个跟hashMap的注意是一样的.
 * 
 * 4:因为最大值被限制死了, 导致如果要用到很多的intern字符串, 效率会一直变慢下去. 每个桶存放的元素越来越多.
 * 		所以就需要实际情况配置-XX:StringTableSize来提升效率
 * 		注意还必须是素数, 如果是合数会导致hash冲突概率暴增, 降低效率.
 * 5:String  intern   StringBuilder  StringBuilder的注意点
 * 		https://www.cnblogs.com/huaxingtianxia/p/6021960.html
 * 		这个博客大体是对的,但是对于一些细节是不对的. 可见下面的测试:testString1 testString2
 * 
 * 6:这个博客就是对第五点中释疑.http://blog.csdn.net/raintungli/article/details/38595573
 * 		对intern方法的总结:
 * 			如果具有相同值的Unicode字符串已经在这个StringTable中，那么该方法返回StringTable中已有字符串的地址，
 * 			如果在StringTable中没有相同值的字符串，则将自己的地址注册到StringTable中
 * 		注意:String a = new String("aaa");
 * 			这一句反编译一下实际上可以算是两句:
 * 				String test = "aaa";
 * 				String a = new String(test);
 * 			因为test已经在StrinTable中了, a又是另一个对象所以:sout(a == a.intern) 得false;
 * 			
 * 			这时候我们换一种方式:
 * 				char[] test = {'a','a','a'};  
 *				String s1 = new String(test);  
 *				System.out.println(s1 == s1.intern());//true
 *				因为test只是一个chat数组, 没有StringTable中, s1只是堆中的一个对象, 所以s1.intern()的时候会发现Stringtable中没值.
 *				所以将自己的引用放到了Stringtable中, 得true.
 *
 *		解释一下testString1和testString2
 *			testString1:
 *				得到a3的时候虽然调用StringBuilder的toString方法,也就是new了一个String. 但是前面没有一个字面量是"ab".
 *				所以StringTable是空的. 当a4="ab"的时候, 这时候StringTable才出来了一个新"ab"的引用.
 *				所以.比一下为false.
 *			testString2:
 *				调用了a3.intern(), 所以这时候StringTable中的"ab"引用就是a3.
 *				这时候再执行String a4 = "ab";  就会在StringTable中找到"ab", 然后将a3赋值给了a4. 所以true.
 * 7：其实应用自己本身也可以实现这样的缓存.
 * 		比如用hashMap.  有博客用WeakHashMap做. 不过说内存消耗是intern的四倍.不知道为什么. 
 * 		应该是因为自己创建的String对象会有多余的引用.
 * 
 * 8:String a = "aaa";
 * 	这样只是简单的从字符串池中拿到一个字符串的引用.
 * 	如果池中没有的话, 那么就会在池中新增一个.
 * @author lgr
 *
 */
public class A03InternString {
	/**
	 * String a3 = a1 + a2;
	 * 底层：
	 * 	会自动声明一个StringBuilder对象，然后依次append()进"a"、"b"，最后调用toString方法得到"ab"字符串，
	 * 	该"ab"字符串不是放在字符串常量池，而是放在堆中。
	 */
	@Test
	public void testString1(){
		String a1 = "a";
		String a2 = "b";
		String a3 = a1 + a2;//!!!
		String a4 = "ab";
		System.out.println(a3 == a4);//false
	}
	
	/**
	 * System.out.println(a3 == a4);//true
	 * 上面这个结论是怎么得出来的？？？
	 * a3.intern()：当字符串常量池中没有"ab"字符串常量，所以此时a3会将通过“String a3 = a1 + a2;”得到的"ab"复制到字符串常量池中。
	 */
	@Test
	public void testString2(){
		String a1 = "a";
		String a2 = "b";
		String a3 = a1 + a2;
		System.out.println(a3 == a3.intern());//true
		
		String a4 = "ab";
		System.out.println(a3 == a4);//true
		System.out.println(a3 == a4.intern());//true
	}
	
	@Test
	public void testString3(){
		char[] test = {'k','v','i','l','l'};  
		String s1 = new String(test); 
		System.out.println(s1 == s1.intern());//true
	}
	
	/**
	 * 注意"aaa"不跟intern方法一样，“aaa”是直接生成到字符串常量池中。
	 * @author lgr
	 */
	@Test
	public void testString4(){
		char[] test = {'a','a','a'};  
		String s1 = new String(test);  
		System.out.println(s1 == "aaa");//false
	}
	
	/**
	   * 实验证明：用未声明的字面量直接相加, 是会生成相加结果(cccddd)的intern字符串。
	   *     同时也会分别生成ccc, ddd的字符串， 由testString7测试得到的结果。
	   *     
	   * 而如果用已声明的字符串相加， 就如上所说， 会有ccc对象，ddd对象， 还有stringBuilder对象， stringBuilder的toString之后的对象。
	   *     并不会产生相加结果(eeefff)的intern字符串。  由testString7测试所得结果。
	   */
	  @Test
	  public void testString5() {
	      String str1 = "ccc" + "ddd";
	      
	      String str2 = new String(str1);
	      
	      System.out.println(str2.intern() == str1);//true
	      
	  }
	  
	  /**
	   * 对比testString5.  结论在testString5
	   */
	  @Test
	  public void testString6() {
	      String s1 = "eee";
	      String s2 = "fff";
	      String s3 = s1 + s2;
	      String s4 = new String(s3);
	      
	      
	      System.out.println(s4.intern() == s3);//false
	  }
	  
	  /**
	     * 对比testString5.  结论在testString5
	     */
	  @Test
	  public void testString7() {
	      String s1 = "ggg" + "hhh";
	      
	      String s2 = new String(new char[] {'g', 'g', 'g'});
	      
	      System.out.println(s2.intern() == s2);//true
	  }

}
