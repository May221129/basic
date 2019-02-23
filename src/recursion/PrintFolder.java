package recursion;

import java.io.File;

/**
 * 使用递归：打印文件夹1.0（如果某一级的文件夹打开里面还有文件夹，则依次打印出来，直至打印到具体文件为止。）
 */
public class PrintFolder {
	public static void main(String[] args) {
//		如果file是个文件，则只会打印出这个文件名，然后程序就终止：
		File file = new File("Q:\\一打印文件夹测试\\（三）打印文件夹测试.docx");
		method(file);
		System.out.println("------------------------------------------");
//		如果file是个文件夹：
		File files = new File("Q:\\一打印文件夹测试");
		method(files);
	}
	
	/** 
	 * 在这题中，这里的成员变量没有任何意义：下面的method()方法的入参是局部变量，方法的调用者传进去即可，和成员变量是没有关系的。
	 * 如果这里想要用到成员变量，就不要给方法入参，直接在方法体里用成员变量即可。
	 */
	public static void method(File file){
		System.out.println(file.getName());
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f :files){
				method(f);
			}
		}
	}
}
