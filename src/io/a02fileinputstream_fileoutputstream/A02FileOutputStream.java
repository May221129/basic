package io.a02fileinputstream_fileoutputstream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.Test;

/**
 * 输出流：
 * FileInputStream & FileOutputStream(文件写入和读取的节点流/文件流)：输出流的使用.
 */
public class A02FileOutputStream {
	
	@Test
	public void test1(){
		//1、创建一个File对象，表明要写入的文件位置：
		//==>输出的物理文件可以不存在，执行过程中若不存在，会自动创建，若存在，则会覆盖文件里原有的内容。
		File f1 = new File("goodgoodstudy.txt");
		//2、创建一个FileOutputStream的对象，将file的对象作为形参传递给FileOutputStream的构造器中：
		FileOutputStream fos1 = null;
		try {
			fos1 = new FileOutputStream(f1);
			//3、写入操作：
			fos1.write(new String("我觉得自己很笨。").getBytes());
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos1 != null){
				try {
					//4、关闭输出流：
					fos1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
