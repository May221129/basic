package io.a03filereader_filewriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

/**
 * �ַ�����FileReader��FileWriterʵ���ļ��ĸ��ƣ� ʹ��FileReader��FileWriter ����ʵ���ı��ļ��ĸ��ơ�
 * ���ڷ��ı��ļ�����Ƶ�ļ�����Ƶ�ļ���ͼƬ����ֻ��ʹ���ֽ�����
 */
public class TestFileReaderAndWriter {
	@Test
	public void testFileReaderAndWriter() {
		FileReader fr = null;
		FileWriter fw = null;
		try {
			// ��������Ӧ���ļ�f1һ��Ҫ���ڣ��������쳣���������Ӧ���ļ�f2���Բ����ڣ�ִ�й����л��Զ�����:
			// ����ʵ�ַ��ı��ļ��ĸ���!!!
			File f1 = new File("Q:\\My study\\Java���ģʽ.docx");
			File f2 = new File("C:\\Users\\Administrator\\Desktop\\Java���ģʽ.docx");
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
