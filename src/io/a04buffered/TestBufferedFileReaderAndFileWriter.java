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
 * 处理流之缓冲流：BufferedReader、BufferedWriter的使用：
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
//			读取的方法一：
//			while((len = br1.read(c)) != -1){
//				bw1.write(c, 0, len);
//				bw1.newLine();
//				bw1.flush();
//			}
			
//			读取的方法二：
			String s;
		while((s = br1.readLine()) != null){//一行一行的读取，并赋给s
				bw1.write(s + "\n");//换行方式①
//				bw1.newLine();//换行方式②
				bw1.flush();//保险起见，最后要刷新！！！
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
