package io.a06test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * ���ԣ�����������ܹ�ͬʱȥдһ���ļ���
 * ���̣����һ���߳����ܣ��������ȥдһ���ļ���������ûд�ꡣ ���ʣ���һ��������Ƿ���ͬʱȥд����ļ���
 * ���ۣ����ԣ��������쳣����Ϊ��д����һ�����ǵĶ������Ǹ��̺߳���д���Ͱ�ǰ��һ���߳�д�����ݸ������ˡ�
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
				System.out.println("�Ұ����ر���");
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
