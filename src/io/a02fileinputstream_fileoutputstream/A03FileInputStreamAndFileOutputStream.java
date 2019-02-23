package io.a02fileinputstream_fileoutputstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Test;

/**
 * FileInputStream & FileOutputStream(文件写入和读取的节点流/文件流)：
 * 	输入流+输出流一起使用——从硬盘读取一个文件，并写入到另一个位置（相当于文件的复制）
 */
public class A03FileInputStreamAndFileOutputStream {
	/**
	 * 调用方法来实现文件复制：
	 */
	@Test
	public void test2(){
		long start = System.currentTimeMillis();
		String src = new String("Q:\\mystudy\\studynotes\\设计模式之禅.pdf");
		String dest = new String("C:\\Users\\Administrator\\Desktop\\ceshi设计模式之禅.pdf");
		copyFile(src, dest);
		long end = System.currentTimeMillis();
		System.out.println("复制该文件花费的时间为： " + (end - start));
	}
	
	/**
	 * 实现文件复制的方法：
	 */
	public void copyFile(String src, String dest){
		//1、提供读入、写出的文件：
		File f1 = new File(src);
		File f2 = new File(dest);
		//2、提供相应的输入、输出流：
		FileInputStream fis1 = null;
		FileOutputStream fos1 = null;
		
		try{
			fis1 = new FileInputStream(f1);
			fos1 = new FileOutputStream(f2);
			//3、实现文件的复制：
			byte[] b = new byte[1024];//这里的数组长度，会直接影响传输速度，所以设计时要考虑实际情况来设置。
			int len;
			while((len = fis1.read(b)) != -1){//总是会忘记里面这对括号！！！
				fos1.write(b, 0, len);
				//另外两种错误的写法一定要避免：1.fos1.writer(b); 2.fos1.weiter(b,0,b.length);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(fos1 != null){
					//4、关闭输出流
					fos1.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		try{
			if(fis1 != null){
				//5、关闭输入流：
				fis1.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void test1(){
		//1、提供读入、写出的文件：
		File f1 = new File("C:\\Users\\Administrator\\Desktop\\壁纸1.jpg");
		File f2 = new File("C:\\Users\\Administrator\\Desktop\\壁纸2.jpg");
		//2、提供相应的输入、输出流：
		FileInputStream fis1 = null;
		FileOutputStream fos1 = null;
		
		try{
			fis1 = new FileInputStream(f1);
			fos1 = new FileOutputStream(f2);
			//3、实现文件的复制：
			byte[] b = new byte[1024];
			int len;
			while((len = fis1.read(b)) != -1){//总是会忘记里面这对括号！！！
				fos1.write(b, 0, len);
				//另外两种错误的写法一定要避免：1.fos1.writer(b); 2.fos1.weiter(b,0,b.length);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(fos1 != null){
					//4、关闭输出流“
					fos1.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		try{
			if(fis1 != null){
				//5、关闭输入流：
				fis1.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
