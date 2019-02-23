package io.a02fileinputstream_fileoutputstream;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

/**
 * �ֽ������ֽ�������ϰ��
 */
public class A04Exercise {
//	ʹ���ַ���ʵ���ļ��ĸ��ƣ�
	@Test
	public void test4(){
		BufferedReader br = null;
		BufferedWriter bw = null;
		try{
			br = new BufferedReader(new FileReader(new File("C:\\Users\\Administrator\\Desktop\\test.txt")));
			bw = new BufferedWriter(new FileWriter(new File("C:\\Users\\Administrator\\Desktop\\test4.txt")));
			char[] c = new char[10];
			int len;
			while((len = br.read(c)) != -1){
				bw.write(c,0, len);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(br != null){
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
/**
 * ʹ���ַ���ʵ�����ݵ�����-��ȡ������̨��
 * �ַ�����Reader��Writer
 * FileReader��FileWriter�ֱ���Reader��Writer�ַ��������ࡣ
 */
	@Test
	public void test3(){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("Q:\\mystudy\\studynotes\\������.docx")));
			String str;
			while((str = br.readLine()) != null){
				System.out.println(str);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
//	ʹ���ַ���ʵ�����ݵ������
	@Test
	public void test2(){
		BufferedWriter bw2 = null;
		try {
			bw2 = new BufferedWriter(new FileWriter(new File("C:\\Users\\Administrator\\Desktop\\test2.txt")));
			bw2.write(new String("���߲�����������������ҹ��������������֪���١�"));
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bw2 != null){
				try {
					bw2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
//	ʹ���ֽ���ʵ�����ݵ������
	@Test
	public void test(){
		BufferedOutputStream bos = null;
		try {
			File f1 = new File("C:\\Users\\Administrator\\Desktop\\test.txt");
			FileOutputStream fos = new FileOutputStream(f1);
			bos = new BufferedOutputStream(fos);
			bos.write(new String("Java��һ�ֿ���׫д��ƽ̨Ӧ��������������ĳ���������ԣ����ɡ���").getBytes());
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
