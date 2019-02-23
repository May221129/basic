package io.a06test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 测试：两个输出流能够同时去写一个文件吗？
 * 过程：如果一个线程在跑，用输出流去写一个文件，在流还没写完。 提问：另一个输出流是否能同时去写这个文件。
 * 结论：可以，不会抛异常。因为“写”是一个覆盖的动作，那个线程后面写，就把前面一个线程写的内容给覆盖了。
 */
public class A01Io2 {
	public static void main(String[] args) {
		WriterThread wr1 = new WriterThread();
		WriterThread wr2 = new WriterThread();
		
		wr1.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wr2.start();
	}
}

class WriterThread extends Thread {

	@Override
	public void run() {
		this.io();
	}

	public void io() {
		FileOutputStream fos = null;
		try {
			File f = new File("hello.txt");
			fos = new FileOutputStream(f);
			System.out.println(Thread.currentThread().getName());
			fos.write(Thread.currentThread().getName().getBytes());
			System.out.println(Thread.currentThread().getName());
			TimeUnit.SECONDS.sleep(10);
			fos.write(Thread.currentThread().getName().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				System.out.println("我把流关闭了");
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
