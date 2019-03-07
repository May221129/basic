package recursion;

import java.io.File;

/**
 * 使用递归：打印文件夹2.0,要看得出文件的层级关系,如果同一级内，又有文件，又有文件夹，则先打印文件夹，再打印文件
 * （如果某一级的文件夹打开里面还有文件夹，则依次打印出来，直至打印到具体文件为止。）
 *
 * “先打印文件夹，再打印文件”的实现方式二：
 * File[]存储的文件是无序的，把File[]中的元素进行排序，文件夹在前，纯文件在后，排序完返回一个新的File[]。然后对这个新的集合进行遍历。
 * 详细代码如下：
 */

public class PrintFolder2_flag {
	public static void printFolder(File file, String i){
		System.out.println(i + file.getName());
		if(file.isDirectory()){
			String j = new String("\t" + i);
			File[] files = file.listFiles();
			File[] reverseFiles = reverse(files);
			for(File f : reverseFiles){
				printFolder(f, j);
			}
		}
	}
	public static File[] reverse(File[] file){//冒泡法：对File[]中的元素进行排序：文件夹在前，纯文件在后
		boolean flag = true;
		for(int j = 0; j < file.length && flag; j++){
			flag = false;
			for(int i = 0; i < file.length-1-j; i++){
				if(file[i].isFile() && file[i+1].isDirectory()){
					File temp = file[i];
					file[i] = file[i+1];
					file[i+1] = temp;
					flag = true;
				}
			}
		}
		return file;
	}

	public static void main(String[] args) {
		File file = new File("Q:\\mystudy\\studynotes");
		Long start = System.currentTimeMillis();
		printFolder(file, "\t");
		Long end = System.currentTimeMillis();
		System.out.println("打印该文件目录共花费： " + (end - start));
	}
}
