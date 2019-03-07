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
 * ʹ�ô�����-������BufferedInputStream��BufferedOutputStreamʵ�ַ��ı��ļ��ĸ���
 */
public class TestBufferedFileInputAndOutputStream {
//	���÷�����ʵ���ļ����ƣ�
	@Test
	public void test2(){
		String s1 = new String("Q:\\mystudy\\studynotes\\������.docx");
		String s2 = new String("C:\\Users\\Administrator\\Desktop\\������(copy).docx");
		long start = System.currentTimeMillis();
		copyFileBuffered(s1, s2);
		long end = System.currentTimeMillis();
		System.out.println("���Ƹ��ļ�����ʱ��Ϊ�� " + (end - start));
		
	}
//	����Bufferedʵ���ļ����Ƶķ�����
	public static void copyFileBuffered(String s1, String s2){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
//			1���ṩ���롢д�����ļ���
			File f1 = new File(s1);
			File f2 = new File(s2);
//			2���ȴ�����Ӧ�Ľڵ�����FileInputStream��FileOutputStream��
			FileInputStream fis = new FileInputStream(f1);
			FileOutputStream fos = new FileOutputStream(f2);
//			3���������õĽڵ����Ķ�����Ϊ�βδ��ݸ��������Ĺ������У�
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
//			4�������ʵ���ļ����ƵĲ�����
			byte[] b = new byte[1024];
			int len;
			while((len = bis.read(b)) != -1){
				bos.write(b, 0, len);
				bos.flush();//����ڴ������֮��ˢ�£����һ�¡�
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		5���ر���Ӧ�Ļ������������������
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
			File f1 = new File("Q:\\My study\\JAVA���õ���.docx");
			File f2 = new File("C:\\Users\\Administrator\\Desktop\\JAVA���õ���.docx");
			
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
