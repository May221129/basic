package io.a03filereader_filewriter;

import java.io.File;
import java.io.FileReader;

import org.junit.Test;

/**
 * 字符流：FileReader的使用：
 */
public class TestFileReader {
	@Test
	public void test1(){
		FileReader fr1 = null;
		try{
		File f1 = new File("hello.txt");
		fr1 = new FileReader(f1);
		char[] c = new char[10];
		int len;
		while((len = fr1.read(c)) != -1){
			String s = new String(c, 0, len);
			System.out.print(s);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(fr1 != null){
				try{
					fr1.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

}
