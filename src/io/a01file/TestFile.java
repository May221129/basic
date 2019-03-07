package io.a01file;

import java.io.File;
import org.junit.Test;

/**
 * java.io.File类的使用：
 */
public class TestFile {
	
	@Test
	public void testFile1(){
		File f1 = new File("helloFile.txt");//相对路径：在当前文件所在的项目的根目录下的路径,详细解释见JavaSE的Word文档。
		
//		绝对路径的第一种写法：
		File f2 = new File("Q:/io/helloFile.txt");//绝对路径：包括盘符在内的完整的文件路径
//		绝对路径的第二种写法。
		File f3 = new File("Q:\\io\\helloFile.txt");
		
		File f4 = new File("Q:/io/呦西");//File类对象也可以对应一个文件目录，haha是个文件夹，并非文件
		
		File f5 = new File("Q:\\mystudy\\Java基础实战_Bank项目_01.pdf");
		System.out.println(f5.getName());
	}
}
