package io.a06test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * ���ԣ�����ϵ�y�ܹ�������ٸ���ͬʱ���ڣ�
 * ���ۣ�ͬʱ������һǧ���������û�����������������ϵ������ɲ��ᱨ�������ȱ��ڴ�����ˡ��ⲻ���������Ի�û��ȷ�еĽ��ۡ�
 */
public class A01Io3 {

	private static ArrayList<FileInputStream> array = new ArrayList<>(10000000);

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("hello.txt");
		for (int i = 0; i < 10000000; i++) {
			FileInputStream fis = new FileInputStream(file);
			if (i % 10000 == 0) {
				System.out.println(i);
			}
			/**
			 * �������fis������������ôfis��������������ھֲ������������������޷��õ����ˣ�
			 * ��ô����GC���ԣ������Ǹ����ɴ�ģ��ᱻ���գ�
			 * ��ô������ǣ������ܶ��߳��Ҵﵽ�ڴ������޶�ʱ���ʹ�����GC����Щ���ͱ�GC�����ˡ�
			 */
			array.add(fis);
		}
	}
	
//	public static void main(String[] args) throws FileNotFoundException {
//		File file = new File("hello.txt");
//		int i = 0;
//		for (;;) {
//			FileInputStream fis = new FileInputStream(file);
//			i++;
//			if (i % 10000 == 0) {
//				System.out.println(i);
//			}
//		}
//	}

}
