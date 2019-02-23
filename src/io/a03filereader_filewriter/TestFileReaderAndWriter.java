package io.a03filereader_filewriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

/**
 * 字符流：FileReader和FileWriter实现文件的复制： 使用FileReader、FileWriter 可以实现文本文件的复制。
 * 对于非文本文件（视频文件、音频文件、图片），只能使用字节流！
 */
public class TestFileReaderAndWriter {
	@Test
	public void testFileReaderAndWriter() {
		FileReader fr = null;
		FileWriter fw = null;
		try {
			// 输入流对应的文件f1一定要存在，否则抛异常。输出流对应的文件f2可以不存在，执行过程中会自动创建:
			// 不能实现非文本文件的复制!!!
			File f1 = new File("Q:\\My study\\Java设计模式.docx");
			File f2 = new File("C:\\Users\\Administrator\\Desktop\\Java设计模式.docx");
			fr = new FileReader(f1);
			fw = new FileWriter(f2);
			char[] c = new char[10];
			int len;
			while ((len = fr.read(c)) != -1) {
				fw.write(c, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
