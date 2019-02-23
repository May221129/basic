package io.a05otherstream;
//打印流：PrintStreamWriter

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class TestPrintStreamWriter {
	@Test
	public void printStreamWriter() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File("print.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 创建打印输出流,设置为自动刷新模式(写入换行符或字节 '\n' 时都会刷新输出缓冲区)
		PrintStream ps = new PrintStream(fos, true);
		if (ps != null) { // 把标准输出流(控制台输出)改成文件
			System.setOut(ps);
		}
		for (int i = 0; i <= 255; i++) { // 输出ASCII字符
			System.out.print((char) i);
			if (i % 50 == 0) { // 每50个数据一行
				System.out.println(); // 换行
			}
		}
		ps.close();
	}
}
