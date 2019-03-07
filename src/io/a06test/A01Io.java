package io.a06test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * ���ԣ������������ܹ�ͬʱȥ��һ���ļ��е�������
 * ���̣����һ���߳����ܣ���������ȥ��һ���ļ���������û���ꡣ ���ʣ���һ���������Ƿ���ͬʱȥ������ļ���
 * ���ۣ����ԣ��������쳣��
 * 
 * һ��������һ��д����ͬʱ���һ���ļ���Ҳ�ǲ��ᱨ��ġ����������ڴ������Լ������ˣ���ò�Ҫ��������
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
			byte[] b = new byte[1024];// ���飬����д���ȡ�������ݵġ�
			int len;// ��¼ÿ��д��byte�е��ֽڵĳ���
			while ((len = fis3.read(b)) != -1) {
				System.out.println("��Ҫ˯��");
				Thread.sleep(1000 * 10);
				System.out.print(new String(b, 0, len));// ע������ĵ���b.length����len
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis3 != null) {
				System.out.println("�Ұ����ر���");
				try {
					fis3.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
