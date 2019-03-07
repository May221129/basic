package io.a06test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 测试：两个输入流能够同时去读一个文件中的内容吗？
 * 过程：如果一个线程在跑，用输入流去读一个文件，在流还没读完。 提问：另一个输入流是否能同时去读这个文件。
 * 结论：可以，不会抛异常。
 * 
 * 一个读流，一个写流，同时针对一个文件，也是不会报错的。但是我们在代码上自己控制了，最好不要这样做。
 */
public class A01Io {
	public static void main(String[] args) {
		SubThread st1 = new SubThread();
		SubThread st2 = new SubThread();

		st1.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		st2.start();
	}
}

class SubThread extends Thread {

	@Override
	public void run() {
		this.io();
	}

	public void io() {
		FileInputStream fis3 = null;
		try {
			File f3 = new File("hello.txt");
			fis3 = new FileInputStream(f3);
			byte[] b = new byte[1024];// 数组，用于写入读取到的数据的。
			int len;// 记录每次写入byte中的字节的长度
			while ((len = fis3.read(b)) != -1) {
				System.out.println("我要睡了");
				Thread.sleep(1000 * 10);
				System.out.print(new String(b, 0, len));// 注意结束的点是b.length还是len
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis3 != null) {
				System.out.println("我把流关闭了");
				try {
					fis3.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
