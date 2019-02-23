package io.a04buffered;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

/**
 * ������֮��������BufferedReader��BufferedWriter��ʹ�ã�
 */
public class TestBufferedFileReaderAndFileWriter {
	@Test
	public void test(){
		
		BufferedReader br1 = null;
		BufferedWriter bw1 = null;
		
		try {
			File f1 = new File("hello.txt");
			File f2 = new File("hello2.txt");
			
			FileReader fr1 = new FileReader(f1);
			FileWriter fw1 = new FileWriter(f2);
			
			br1 = new BufferedReader(fr1);
			bw1 = new BufferedWriter(fw1);
			
			char[] c = new char[1024];
			int len;
//			��ȡ�ķ���һ��
//			while((len = br1.read(c)) != -1){
//				bw1.write(c, 0, len);
//				bw1.newLine();
//				bw1.flush();
//			}
			
//			��ȡ�ķ�������
			String s;
		while((s = br1.readLine()) != null){//һ��һ�еĶ�ȡ��������s
				bw1.write(s + "\n");//���з�ʽ��
//				bw1.newLine();//���з�ʽ��
				bw1.flush();//������������Ҫˢ�£�����
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(bw1 != null){
				try {
					bw1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(br1 != null){
				try {
					br1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
