package collection.c_treemap;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import org.junit.Test;

/**
 * 测试：
 * 一、TreeMap的自然排序
 * 二、TreeMap的定制排序
 * 三、如果即非自然排序又非定制排序的自定义对象，put到TreeMap中如果排序的？
 * 
 * TreeMap：按照添加进Map中的元素的key的指定属性进行排序（如：key为Person对象，按照Person对象的name属性值进行排序），要求：key必须是同一个类的对象！
 * 【不论是自然排序，还是定制排序，其compareTo()方法去自定义按照类的属性进行排序时，“对象1.属性.compareTo(对象2.属性)”这里的CompareTo实际上是
 * 	String或基本数据类型的包装类中重写的CompareTo()方法。如下面的定制排序中的代码所示。】
 * 
 * 1.TreeMap是什么：
 * 		TreeMap是一棵红黑树，第一个放进TreeMap中的元素会占一个根节点；第二个元素放进TreeMap前会先和第一个元素进行比较，
 * 	第二个元素若较小则放第一个元素的左下角，若较大则放第一个元素的右下角。后面的每一个元素放进来都重复以上动作，直至元素放
 * 	在了最末端叶子节点的左下方或右下方。整个过程就是一个建树的过程。
 *  	建树过程可能出现一种比较极端的情况——最终建了一个链表。如：TreeMap最先put进去一个5；再put一个6,放在了5这个节点
 * 	的右下角；再put一个7，放在了6节点的右下角；再put进8……；最终结果就是一个5-6-7-8这样一个链表。如果出现这种情况，
 * 	就失去了红黑树快速查找的优势（红黑树的查找速度为log2 N）。为了避免这样情况发生，就有了树的旋转（旋转的目的是让树保持
 * 	红黑树的特性。）
 * 	【详细了解各种树，可看：https://baijiahao.baidu.com/s?id=1561409115312135&wfr=spider&for=pc】
 * 
 * 2.TreeMap怎么进行排序的：
 *   TreeMap做元素的排序，是在put时对元素进行比较的，比较的过程如下：
 *   	2.1  如果put进去的元素是实现了Comparable接口（如Person类作为元素放入TreeMap），并重写了compareTo()方法，
 *   		那么TreeMap在put进元素时，就会调用元素的compareTo()方法，对元素进行排序；（即：自然排序）
 *   	2.2  如果put进去的元素没有实现Comparable接口，但将Comparator匿名类对象作为局部变量，并在Comparator实例中规定了
 *   		put进TreeMap中的元素的属性的比较规则，并将Comparator的实例传给Treeman的构造函数作为入参；此时，往TreeMap
 *   		中put进该类型的元素时，就会自动调用Comparator这个比较器对元素进行比较。（即：定制排序）
 *   	2.3 String或包装类都有实现Comparable接口、并重写CompareTo()方法，所以如果put进去的是这些类型的元素，则会
 *   		按照其重写的CompareTo()方法进行排序。（先将元素转成Unicode字符集的二进制值，再相减，第一个元素的二进制值减
 *   		第二个元素的二进制值，结果为正整数说明第一个元素的二进制值大于第二个元素的，小的二进制值排前面，所以是第二个元素排
 *   		第一个元素前面；结果为负数则是第一个元素排在第二个元素的前面；结果为零则说明两个元素相同。默认是按照Unicode字符
 *   		集的二进制值的从小到大顺序进行排序。）
 *   	2.4  如果put进去的是自定义类类型的元素(如User类)，既没有实现Comparable接口，也没有在TreeMap构造函数的入参中
 *   		传入Comparator比较器，猜想：TreeMap是将User类型的元素算出hashCode，并按照hashCode值进行排序的。
 *   		见testTreeMap3()测试。
 *   
 * 3.String是怎么通过CompareTo()方法进行排序的：通过代码直接说明
 * 	public int compareTo(String anotherString) {
 * 		
 * 		// 3.1 获取两个字符串的长度，取短的那个长度用于做后面的循环条件：
 *       int len1 = value.length;
 *       int len2 = anotherString.value.length;
 *       int lim = Math.min(len1, len2);
 *       
 *       // 3.2 将两个字符串分别赋值给v1、v2这两个char数组：
 *       char v1[] = value;
 *       char v2[] = anotherString.value;
 *
 *		// 3.3 将v1、v2这两个char数组中的元素，逐一进行比对：
 *       int k = 0;
 *       while (k < lim) {
 *           char c1 = v1[k];
 *           char c2 = v2[k];
 *   		// 如取出的数组元素不相同，则将两个元素直接进行相减(实际是将两个元素的Unicode二进制值进行相减的)，
 *   		// 结果若为正数，说明c1的二进制值大于c2的,值小的排前面，所以c2排在c1前面；
 *   		// 结果若为负数，说明c1的二进制值小于c2的，所以c1排在c2的前面；
 *   		// 结果若为0，说明c1=c2，但这里是不会出现等于零的情况，因为是"c1 != c2"才会"return c1 - c2".
 *           if (c1 != c2) {
 *               return c1 - c2;
 *           }
 *           k++;
 *       }
 *       // 3.4 如果通过3.3对于v1、v2字符串数组进行逐一比对过后，每个元素都相等，则直接将两个字符串进行相减“
 *       // 举例："我爱你"和"我爱你啊"进行排序，短的那个字符串会排前面，因为它的二进制值会小于长字符串。
 *       return len1 - len2;
 *   }
 *   
 * TreeMap详解网址：
 * https://www.cnblogs.com/hxsyl/p/3331095.html
 * http://www.jb51.net/article/90537.htm
 */
public class TestTreeMap {
	/**
	 * 一、定制排序：
	 * 	1. 定制排序的实现：不改变需要用来排序的类的类结构。
	 * 		1.1 创建匿名类Comparator作为局部变量，在Comparator实例中实现compare()方法；
	 * 		1.2 在compare()方法中，自定义方法入参的属性的排序规则；
	 * 		1.3 创建TreeMap对象，并在TreeMap的构造函数中传入自定义了排序规则的Comparator匿名类对象；
	 * 		1.4 每次put进TreeMap中一个对象(该对象即为在Comparator对象中自定义了排序规则类型的对象)，
	 * 			TreeMap都会自动调用到Comparator对象的compare()方法，保证put进去的对象是有序的。
	 * 	2. 提问： 需要做排序的自定义类中，是否需要重写equals()方法、hashCode()方法，为什么？
	 * 		TreeMap不是按照hashCode来存放，而是按照实现的Comparable接口的compareTo()方法来存储的，
	 * 		只要compareTo的返回结果为0就表示两个对象相等，那么就存不进去两个对象，后put的就把前面的覆盖掉，甚
	 * 		至我们都不用重写equasls和hashCode方法，而只需要实现Comparable接口来重写comparareTo方法
	 * 		就行了，但是我们不能保证在应用中不会用到HashMap，所以保持良好的习惯，当我们定义了一个对象之后习惯性的
	 * 		重写equals和hashCode方法。
	 */
	@Test
	public void testTreeMap1(){
		Comparator<Object> com = new Comparator<Object>(){
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Student && o2 instanceof Student){
					Student s1 = (Student)o1;
					Student s2 = (Student)o2;
					//下面这行代码的compareTo()方法，其实是String的CompareTo()方法
					int i = s1.getName().compareTo(s2.getName());
					if(i == 0){
						//下面这行代码的compareTo()方法，其实是Integer包装类的CompareTo()方法
						return s1.getAge().compareTo(s2.getAge());
					}else{
						return i;
					}
				}
				return 0;
			}
		};
		TreeMap<Student, Integer> t = new TreeMap<Student, Integer>(com);
		t.put(new Student("A", 26), 66);
		t.put(new Student("A", 21), 90);
		t.put(new Student("C", 26), 80);
		t.put(new Student("C", 23), 70);
		t.put(new Student("E", 23), 91);
		Set<Entry<Student, Integer>> s = t.entrySet();
		for(Object obj : s){
			Entry<Student, Integer> entry = (Entry)obj;
			System.out.println(entry);
		}
	}
	
	/**
	 * 二、自然排序：
	 * 【步骤见Person类】
	 * 注意：
	 * 	中文字符的排序，也是按照每个中文字符对应的Unicode二进制值的大小进行排序，而非按照每个中文字符的首字母从a~z排序。
	 * 	因为Unicode是万国码，录入中文字符进去时，并没有按照新华字典类型的顺序把每个中文字符进行排序录入。
	 */
	@Test
	public void testTreeMap2(){
		TreeMap<Person, Integer> tm = new TreeMap<>();
		tm.put(new Person("D", 23), 70);
		tm.put(new Person("E", 26), 66);
		tm.put(new Person("A", 21), 90);
		tm.put(new Person("巴", 23), 70);
		tm.put(new Person("阿", 26), 66);
		tm.put(new Person("德", 21), 90);
	//	遍历集合中的所有entry对：
		Set<Entry<Person, Integer>> s = tm.entrySet();
		for(Object o : s){
			Entry<Person, Integer> e = (Entry)o;
			System.out.println(e);
		}
	}
	
	/**
	 * 三、如果即非自然排序又非定制排序的自定义对象，put到TreeMap中如果排序的？
	 * 	放入到TreeMap中的元素的类，必须是实现了Comparable接口，或者TreeMap的构造方法中，必须传入Comparator比较器，
	 * 	否则会报“ClassCastException”异常。
	 * 	String和包装类都有实现Comparable接口并实现CompareTo()方法。
	 */
	@Test
	public void testTreeMap3(){
		TreeMap<User,Integer> userTreeMap = new TreeMap<>();
		userTreeMap.put(new User("A",11), 1);
		userTreeMap.put(new User("C",33), 3);
		userTreeMap.put(new User("B",22), 2);
		
		Set<Entry<User,Integer>> userSet = userTreeMap.entrySet();
		for(Entry<User,Integer> userEntry: userSet){
			System.out.println(userEntry.getKey() + "==>" + userEntry.getValue());
		}
	}
	
	/**
	 * char类型的字符，可以为int类型，因为char类型可以根据Unicode字符集转为int类型。
	 */
	@Test
	public void testChar(){
		/**
		 * char字符型的a,可以转为int类型，试着输出看看
		 */
		char a = 'A';
		int i = a;
		System.out.println(i);//65
		//下面这个是非正规写法：
		System.out.println(a + 0);//65
		//下面这个是正规的写法：
		System.out.println(a & 0xffff);//65
	}
}
