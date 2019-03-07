package io.a02fileinputstream_fileoutputstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.Test;

/**
 * FileInputStream & FileOutputStream(文件写入和读取的节点流/文件流)：输入流的使用：
 */
public class A01FileInputStream {
	/**
	 * 从硬盘存在的一个文件中，读取其内容到程序中，使用FileInputStream（读取时用reads()方法。异常处理使用try-catch异常处理的方式）
	 */
	@Test
	public void test3(){
		FileInputStream fis3 = null;
		try {
			File f3 = new File("hello.txt");
			fis3 = new FileInputStream(f3);
			byte[] b = new byte[1024];//数组，用于写入读取到的数据的。
			int len;//记录每次写入byte中的字节的长度
			while((len = fis3.read(b)) != -1){
				
				//下面这种写法是错误的，输出结果是"Today is five two zero ze",
				//因为len的长度为5，最后一次写入时，只剩ro两个字母，只能覆盖掉倒数第二次写入byte中"wo ze"的"wo"两个字母，" ze"则覆盖不了。
//				for(int i = 0; i < b.length; i++){
//					System.out.print((char)b[i]);
//				}
				
				//正确的写法一：
				for(int i = 0; i < len; i++){
					System.out.print((char)b[i]);
				}
				
				//正确的写法二：
//				System.out.print(new String(b, 0, len));//注意结束的点是b.length还是len
			}
		}  catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fis3 != null){
					try {fis3.close();	
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 从硬盘存在的一个文件中，读取其内容到程序中，使用FileInputStream（使用try-catch异常处理的方式）
	 */
	@Test
	public void test2(){
		FileInputStream fis2 = null;
		try {
			File f2 = new File("hello.txt");
			fis2 = new FileInputStream(f2);
			int b ;
			while((b = fis2.read()) != -1 ){
				System.out.print((char)b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{//保证流的关闭操作一定会执行。
			try {
				fis2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 从硬盘存在的一个文件中，读取其内容到程序中，使用FileInputStream（使用throws异常处理的方式）
	 */
	@Test
	public void test1() throws Exception{
		//1、创建一个File类的对象：
		File f1 = new File("bin/fileinputandoutputstream/hello.txt");
		//2、创建一个FileInputStream类的对象：
		FileInputStream fis1 = new FileInputStream(f1);
		//3、调用FileINputStream类的方法，实现File文件的读取：
//		int b = fis1.read();
//		while(b != -1){
//			System.out.println(b);
//			b = fis1.read();
//		}
		int b;
		while((b = fis1.read()) != -1){
			System.out.print((char)b);
		}
		//4、关闭相应的流：
		fis1.close();
	}
}
