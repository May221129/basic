package io.a02fileinputstream_fileoutputstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Test;

/**
 * FileInputStream & FileOutputStream(�ļ�д��Ͷ�ȡ�Ľڵ���/�ļ���)��
 * 	������+�����һ��ʹ�á�����Ӳ�̶�ȡһ���ļ�����д�뵽��һ��λ�ã��൱���ļ��ĸ��ƣ�
 */
public class A03FileInputStreamAndFileOutputStream {
	/**
	 * ���÷�����ʵ���ļ����ƣ�
	 */
	@Test
	public void test2(){
		long start = System.currentTimeMillis();
		String src = new String("Q:\\mystudy\\studynotes\\���ģʽ֮��.pdf");
		String dest = new String("C:\\Users\\Administrator\\Desktop\\ceshi���ģʽ֮��.pdf");
		copyFile(src, dest);
		long end = System.currentTimeMillis();
		System.out.println("���Ƹ��ļ����ѵ�ʱ��Ϊ�� " + (end - start));
	}
	
	/**
	 * ʵ���ļ����Ƶķ�����
	 */
	public void copyFile(String src, String dest){
		//1���ṩ���롢д�����ļ���
		File f1 = new File(src);
		File f2 = new File(dest);
		//2���ṩ��Ӧ�����롢�������
		FileInputStream fis1 = null;
		FileOutputStream fos1 = null;
		
		try{
			fis1 = new FileInputStream(f1);
			fos1 = new FileOutputStream(f2);
			//3��ʵ���ļ��ĸ��ƣ�
			byte[] b = new byte[1024];//��������鳤�ȣ���ֱ��Ӱ�촫���ٶȣ��������ʱҪ����ʵ����������á�
			int len;
			while((len = fis1.read(b)) != -1){//���ǻ���������������ţ�����
				fos1.write(b, 0, len);
				//�������ִ����д��һ��Ҫ���⣺1.fos1.writer(b); 2.fos1.weiter(b,0,b.length);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(fos1 != null){
					//4���ر������
					fos1.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		try{
			if(fis1 != null){
				//5���ر���������
				fis1.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void test1(){
		//1���ṩ���롢д�����ļ���
		File f1 = new File("C:\\Users\\Administrator\\Desktop\\��ֽ1.jpg");
		File f2 = new File("C:\\Users\\Administrator\\Desktop\\��ֽ2.jpg");
		//2���ṩ��Ӧ�����롢�������
		FileInputStream fis1 = null;
		FileOutputStream fos1 = null;
		
		try{
			fis1 = new FileInputStream(f1);
			fos1 = new FileOutputStream(f2);
			//3��ʵ���ļ��ĸ��ƣ�
			byte[] b = new byte[1024];
			int len;
			while((len = fis1.read(b)) != -1){//���ǻ���������������ţ�����
				fos1.write(b, 0, len);
				//�������ִ����д��һ��Ҫ���⣺1.fos1.writer(b); 2.fos1.weiter(b,0,b.length);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(fos1 != null){
					//4���ر��������
					fos1.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		try{
			if(fis1 != null){
				//5���ر���������
				fis1.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
