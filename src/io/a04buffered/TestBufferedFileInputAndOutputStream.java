package io.a04buffered;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.Test;

/**
 * 使用处理流-缓冲流BufferedInputStream和BufferedOutputStream实现非文本文件的复制
 */
public class TestBufferedFileInputAndOutputStream {
//	调用方法来实现文件复制：
	@Test
	public void test2(){
		String s1 = new String("Q:\\mystudy\\studynotes\\错误处理集.docx");
		String s2 = new String("C:\\Users\\Administrator\\Desktop\\错误处理集(copy).docx");
		long start = System.currentTimeMillis();
		copyFileBuffered(s1, s2);
		long end = System.currentTimeMillis();
		System.out.println("复制该文件花费时间为： " + (end - start));
		
	}
//	运用Buffered实现文件复制的方法：
	public static void copyFileBuffered(String s1, String s2){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
//			1、提供读入、写出的文件：
			File f1 = new File(s1);
			File f2 = new File(s2);
//			2、先创建相应的节点流：FileInputStream、FileOutputStream：
			FileInputStream fis = new FileInputStream(f1);
			FileOutputStream fos = new FileOutputStream(f2);
//			3、将创建好的节点流的对象，作为形参传递给缓冲流的构造器中：
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
//			4、具体的实现文件复制的操作：
			byte[] b = new byte[1024];
			int len;
			while((len = bis.read(b)) != -1){
				bos.write(b, 0, len);
				bos.flush();//最后在传输完成之后刷新，清空一下。
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		5、关闭相应的缓冲输出流、输入流：
		finally{
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Test
	public void test(){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			File f1 = new File("Q:\\My study\\JAVA常用单词.docx");
			File f2 = new File("C:\\Users\\Administrator\\Desktop\\JAVA常用单词.docx");
			
			FileInputStream fis = new FileInputStream(f1);
			FileOutputStream fos = new FileOutputStream(f2);
			
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			
			byte[] b = new byte[10];
			int len;
			while((len = bis.read(b)) != -1){
				bos.write(b, 0, len);
				bos.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
