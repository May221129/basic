package io.a06test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.Date;

import org.junit.Test;

public class B01Io {
	
	/**
	 * �ֽ���
	 */
	@Test
	public void testFileInputStreamAndFileOutputStream(){
		File file = new File("C:/Users/Administrator/Desktop/srcFile.txt");
		File targetFile = new File("C:/Users/Administrator/Desktop/targetFile.txt");
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(file);
			fos = new FileOutputStream(targetFile);
			/**
			 * 1.���srcFile.txt�ļ��е����ݵı����ʽ��ANSI(��GBK)����ΪGBK��һ�������ַ���2���ֽ�����ʾ��
			 * 	�����������������bate[]�Ĵ�СС��2��������������ô��ӡ������̨ʱ���п��������롣
			 * 2.����������ı����ʽ��Ҳ��ͬ��
			 * 	��UTF-8��3���ֽڱ�ʾһ�������ַ�����ô�����Ҫ�ڿ���̨��ӡ�������͵�ÿ�ζ������������ַ����С�
			 * 3.�����ͨ����������һ���ļ��ж�ȡ���ݣ���ͨ�������д�뵽��һ���ļ��У�ֻҪ�����Ĵ��������û�ж����ݽ��мӹ���
			 * 	��ô�����ڳ�����ԣ�������ݾ��ǲ���ġ������ʱ�򿴵��������룬Ҳֻ����Ϊ���ļ��Ĵ򿪷�ʽ�����ݵı����ʽ��һ�¡�
			 */
			byte[] array = new byte[10];
			int len;
			while((len = fis.read(array)) != -1){
				
				fos.write(array, 0, len);
				
				/**
				 * ���ļ��ж�������������ʲô�����ʽ����ɶ����ƣ�����������Ҫ����ͬ�ı����ʽ��
				 */
				System.out.println(new String(array, 0, len, "GBK"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �ַ���
	 * 1.���srcFile.txt����GBK�����ʽ����ǰ��java�ļ�Ҳ����GBK�����ʽ��
	 * 	�����ַ�����srcFile.txt�ļ��ж�ȡ�ַ�����ӡ������̨����û������ġ�
	 * 2.���srcFile.txtʹ��UTF-8�����ʽ���뵱ǰjava�ļ��ı����ʽ��һ��ʱ��
	 * 	������ַ�����srcFile.txt�ļ��ж�ȡ�ַ�����ӡ������̨�Ͼ��п��ܳ����������⡣
	 */
	@Test
	public void testFileReaderAndFileWriter(){
		File srcFile = new File("C:/Users/Administrator/Desktop/srcFile.txt");
		File targetFile = new File("C:/Users/Administrator/Desktop/targetFile2.txt");
		FileReader fr = null;
		FileWriter fw = null;
		try {
			fr = new FileReader(srcFile);
			fw = new FileWriter(targetFile);
			char[] temp = new char[10];
			int len;
			while((len = fr.read(temp)) != -1){
				fw.write(temp, 0, len);
				System.out.println(new String(temp, 0, len));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ������֮������ 2.0
	 */
	@Test
	public void testBufferFileInputStream2(){
		File srcFile = new File("C:/Users/Administrator/Desktop/srcFile.txt");
		File targetFile = new File("C:/Users/Administrator/Desktop/targetFile2.txt");
		
		/**
		 * ʵ����Closeable�ӿڵ��࣬��jdk1.7�Ժ�İ汾�У�����ֱ�ӽ�����������try�������С�
		 * ���ã���try�еĴ���ȫ��ִ�����jvm��Ĭ��ȥ����ص�������try�����������Щ����
		 * 		��Ͳ������Լ���дfinallyȥ�ر���Щ������Դ�ˡ�
		 */
		try (FileReader fr = new FileReader(srcFile); 
				FileWriter fw = new FileWriter(targetFile); 
				BufferedReader br = new BufferedReader(fr);
				BufferedWriter bw = new BufferedWriter(fw);){
			String temp;
			while((temp = br.readLine()) != null){
				bw.write(temp);
				bw.newLine();//���С�
				bw.flush();
				
				System.out.println(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * ������֮������ 1.0
	 */
	@Test
	public void testBufferFileInputStream1(){
		File srcFile = new File("C:/Users/Administrator/Desktop/srcFile.txt");
		File targetFile = new File("C:/Users/Administrator/Desktop/targetFile.txt");
		
		FileReader fr = null;
		FileWriter fw = null;
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			fr = new FileReader(srcFile);
			fw = new FileWriter(targetFile);
			
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);
			
			String temp;
			while((temp = br.readLine()) != null){
				
				bw.write(temp);
				bw.newLine();//���С�
				bw.flush();
				
				System.out.println(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null){
					bw.close();
				}
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ������֮ת����
	 */
	@Test
	public void testInputStreamReader(){
		File srcFile = new File("C:/Users/Administrator/Desktop/srcFile.txt");
		File targetFile = new File("C:/Users/Administrator/Desktop/targetFile.txt");
		try(FileInputStream fis = new FileInputStream(srcFile);
				FileOutputStream fos = new FileOutputStream(targetFile);
				InputStreamReader isr = new InputStreamReader(fis);
				OutputStreamWriter osw = new OutputStreamWriter(fos);) {
			char[] temp = new char[20];
			int len;
			while((len = isr.read(temp)) != -1){
				osw.write(temp, 0, len);
				
				System.out.println(new String(temp, 0, len));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ������
	 */
	@Test
	public void testObjectInputStreamAndObjectOutputStream(){
		TestObjcetIo toi = new TestObjcetIo();
		toi.setName("����");
		toi.setBirthdate(new Date().getTime());
		System.out.println(toi.toString());
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("C:/Users/Administrator/Desktop/TestObjcetIo.txt")));
			oos.writeObject(toi);
			
			ois = new ObjectInputStream(new FileInputStream("C:/Users/Administrator/Desktop/TestObjcetIo.txt"));
			TestObjcetIo targetTOI = (TestObjcetIo)ois.readObject();
			System.out.println(targetTOI.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(oos != null){
					oos.close();
				}
				if(ois != null){
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class TestObjcetIo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private long birthdate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(long birthdate) {
		this.birthdate = birthdate;
	}
	@Override
	public String toString() {
		return "TestObjcetIo [name=" + name + ", birthdate=" + birthdate + "]";
	}
}