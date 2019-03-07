package array.helloworld;
/*

 数组（由数据类型和元素长度来确定）
  
一、如何定义一个数组：
	1、数组的声明；
	2、初始化：静态初始化(初始化数组与给数组元素赋值 同时进行)、动态初始化(初始化数组与给数组元素赋值 分开进行)；

二、如何调用相应的数组元素:
	通过数组元素的下角标的方式来调用。下角标从0开始，到 n-1 结束，其中 n 表示数组的长度。

三、数组的长度:通过数组的length属性。

四、如何遍历数组元素。

五、数组一旦初始化，其长度是不可变的。程序只能改变数组元素的值。

六、数组变量&数组对象：
	数组变量：只是一个引用变量，通常存放在栈内存中（也可被放入堆内存中）；
	数组对象：是保存在堆内存中的连续的内存空间。
	数组初始化，并不知道对数组变量的初始化，而是对数组对象的初始化————也就是为该数组对象分配一个连续的内存空间，这块连续的内存空间的长度，就是数组的长度。
	对于数组变量来说，它不需要进行所谓的初始化，只要让数组变量指向一个有效的数组对象，程序即可正常使用该数组变量。
	*同理：对于Java程序中的所有的引用变量，都不需要经过所谓的初始化操作，需要进行初始化操作的是该引用变量所引用的对象。
	
七、成员变量是存放在堆空间；局部变量（声明在方法内的变量）是存放在栈空间的。
	如果一个引用数据类型的数组是声明为局部变量，则这个数组的引用是存放在栈里，引用指向的实体则放在对空间里。
	如果一个引用数据类型的数组是成员变量，则这个数组是存放在对空间里的。
 */
public class A001Array_Flag {
	public static void main(String[] args){
		
		//声明一个数组：
		String[] names;//加了[]，表明它是一个数组
		int[] scores;
		
		//静态初始化；
		names = new String[]{"张三","李四","王五"};
		
		//动态初始化；
		scores = new int[3];
		
		//如何调用相应的数组元素：通过数组元素的下角标的方式来调用：
		scores[0] = 90;
		scores[1] = 99;
		scores[2] = 67;
		
		//数组的长度，通过length属性。
		System.out.println(names.length);//3
		System.out.println(scores.length);//3
		
		//如何遍历数组元素：
		for(int i = 0 ;i < names.length; i++){
			System.out.println(names[i]);
		}
		
		int[] ages;
		ages = new int[]{1,2,3,4,5};
		/*ages = new int[5];
		ages[0] = 1;
		ages[1] = 2;
		ages[2] = 3;
		ages[3] = 4;
		ages[4] = 5;
		*/
		System.out.println("___________________");
		System.out.println(ages.length);
		for(int i = 0; i < ages.length; i++){
			System.out.println(ages[i]);
		}
		
		char[] aaa = new char[3];
		for(int i = 0; i < aaa.length; i++){
			System.out.println(aaa[i] + 0);
		}
	}
}
