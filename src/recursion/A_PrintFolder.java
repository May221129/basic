package recursion;

import java.io.File;

/**
 * 使用递归：打印文件夹2.0,要看得出文件的层级关系（如果某一级的文件夹打开里面还有文件夹，则依次打印出来，直至打印到具体文件为止。）
 *
 * 问题1：什么时候可以使用递归？
 * 	==>递归就是方法自己调用自己。把一个事情分解为许多个相同的小事情。包子馅的包子。它的极限是馒头。
 * 问题2：递归需要注意的点：
 * 	==>要找到递归的终点是什么
 * 问题3：怎么实现层级关系？
 * 	==> 用一个String型的符号作为入参，来标记每级文件夹/文件名前面的空白，整个来看就能体现层级关系
 * 问题:4：可以用StringBuffer或者StringBuilder来标记吗？
 * 	==>不可以，因为往String里加字符串的时候，原先的String字符串还会保留；而StringBuffer和StringBuilder是在原来的字符串上直接添加的。
 * 		当一个文件夹通过增强for循环去遍历里面的文件时，StringBuffer或StringBuilder的标记都是同一个，无法再实现标记的意义。
 * 问题5：在哪里加入String？
 * 问题6：怎么让String体现层级关系？
 */
public class A_PrintFolder {
//	1、给一个文件，给一个体现层级关系的String标记：
	public static void printFolder(File file, String i){
//		2、打印这个文件的文件名：
		System.out.println(i + file.getName());
//		3、判断这个文件是否是文件夹：
		if(file.isDirectory()){
//			4、如果是文件夹，则说明还能展开下一层，所以：String符号+String符号，并赋值给String j：
			String j = new String("\t" + i);
//			5、如果是文件夹，读取出这个文件夹下一层所有的文件：
			File[] files = file.listFiles();
//			6、通过增强for循环，一个一个的读取出File数组中的元素(即一个一个的文件)：
			for(File f : files){
//				7、重复前面的1、2、3、4、5、6步，直至读取到的文件不是文件夹，而是一个具体的文件，这时候程序往回走，去执行每个层级未执行完的代码。：
				printFolder(f , j);
			}
		}
	}
	public static void main(String[] args) {
		File file = new File("Q:\\一打印文件夹测试");
		printFolder(file, "");
//		printFolder(file, "\t");
	}
}
