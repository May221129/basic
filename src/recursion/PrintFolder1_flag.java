package recursion;

import java.io.File;
import java.util.ArrayList;

/**
 * 使用递归：打印文件夹2.0,要看得出文件的层级关系,如果同一级内，又有文件，又有文件夹，则先打印文件夹，再打印文件
 * （如果某一级的文件夹打开里面还有文件夹，则依次打印出来，直至打印到具体文件为止。）
 *
 * “先打印文件夹，再打印文件”的实现方式一：
 * 1、拿到一个文件，先输出这个文件的层级和文件名：System.out.println(i + file.getName());
 * 2、代表层级的符号+1：String j = new String("\t" + i);
 * 3、判断这个文件是纯文件还是文件夹：如果是纯文件则return，如果是文件夹，则把这个文件夹中的下一层目录保存为File[]；
 * 4、新建一个ArrayList集合，用来保存File[]里的文件；
 * 5、遍历File[]中的所有文件，并对每一个文件做判断：如果是文件夹，则用递归的方法，调用当前这个方法；如果是纯文件，则保存到ArrayList集合中；
 * 6、遍历完了File[]中的所有文件，再把ArrayList中的所有纯文件的名字输出（注意:要输出文件名）。
 * 详细代码如下：
 */

public class PrintFolder1_flag {
	public static void main(String[] args) {	
		File file = new File("Q:\\mystudy\\studynotes");
		Long start = System.currentTimeMillis();
		printFolder(file, "\t");
		Long end = System.currentTimeMillis();
		System.out.println("打印该文件目录共花费： " + (end - start));
	}
	
	public static void printFolder(File file, String i){
		System.out.println(i + file.getName());
		if(file.isDirectory()){
			String j = new String("\t" + i);
			ArrayList<File> la = new ArrayList<File>();
			File[] files = file.listFiles();
			for(File f : files){
				if(f.isDirectory()){
					printFolder(f, j);
				}else{
					la.add(f);
				}
			}
			for(File newFile : la){
				System.out.println(j + newFile.getName());
			}
		}
	}
}
