package io.a06test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 测试：操作系統能够允许多少个流同时存在？
 * 结论：同时开启了一千万个流，都没报错。甚至数字再往上调，依旧不会报错，而是先报内存溢出了。这不正常，所以还没个确切的结论。
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
			 * 如果不把fis保存下来，那么fis在这里就是类似于局部变量，创建完后面就无法拿到它了，
			 * 那么对于GC而言，它就是根不可达的，会被回收，
			 * 那么结果就是：创建很多线程且达到内存的最大限度时，就触发了GC，这些流就被GC回收了。
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
