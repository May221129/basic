package io.a05otherstream;
//处理流：InputStreamReader和OutputStreamWriter：

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class TestInputStreamReaderAndOutputStreamWriter {
	@Test
	public void test(){
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
//			解码：
			File f1 = new File("hello.txt");
			FileInputStream fr = new FileInputStream(f1);
			InputStreamReader isr = new InputStreamReader(fr,"GBK");
			br = new BufferedReader(isr);
//		编码：
			File f2 = new File("hello3.txt");
			FileOutputStream fw = new FileOutputStream(f2);
			OutputStreamWriter osw = new OutputStreamWriter(fw,"GBK");
			bw = new BufferedWriter(osw);
			String str;
			while((str = br.readLine()) != null){
				bw.write(str);
				bw.newLine();//复制出来的文件，最后对了一行空白的？？？
				bw.flush();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
}
