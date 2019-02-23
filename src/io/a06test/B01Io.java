package io.a06test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.Date;

import org.junit.Test;

public class B01Io {
	
	/**
	 * 字节流
	 */
	@Test
	public void testFileInputStreamAndFileOutputStream(){
		File file = new File("C:/Users/Administrator/Desktop/srcFile.txt");
		File targetFile = new File("C:/Users/Administrator/Desktop/targetFile.txt");
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(file);
			fos = new FileOutputStream(targetFile);
			/**
			 * 1.如果srcFile.txt文件中的内容的编码格式是ANSI(即GBK)，因为GBK的一个中文字符是2个字节来表示，
			 * 	所以如果这里声明的bate[]的大小小于2，或是奇数，那么打印到控制台时就有可能是乱码。
			 * 2.如果是其他的编码格式，也是同理。
			 * 	如UTF-8是3个字节表示一个中文字符，那么如果想要在控制台打印出来，就得每次都读到完整的字符才行。
			 * 3.如果是通过输入流从一个文件中读取内容，再通过输出流写入到另一个文件中，只要在流的传输过程中没有对内容进行加工，
			 * 	那么，对于程序而言，你的内容就是不变的。如果打开时候看到的是乱码，也只是因为你文件的打开方式和内容的编码格式不一致。
			 */
			byte[] array = new byte[10];
			int len;
			while((len = fis.read(array)) != -1){
				
				fos.write(array, 0, len);
				
				/**
				 * 从文件中读到的内容是用什么编码格式编码成二进制，这里解码就需要用相同的编码格式。
				 */
				System.out.println(new String(array, 0, len, "GBK"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 字符流
	 * 1.如果srcFile.txt是用GBK编码格式，当前的java文件也是用GBK编码格式，
	 * 	那用字符流从srcFile.txt文件中读取字符并打印到控制台上是没有问题的。
	 * 2.如果srcFile.txt使用UTF-8编码格式，与当前java文件的编码格式不一致时，
	 * 	如果用字符流从srcFile.txt文件中读取字符并打印到控制台上就有可能出现乱码问题。
	 */
	@Test
	public void testFileReaderAndFileWriter(){
		File srcFile = new File("C:/Users/Administrator/Desktop/srcFile.txt");
		File targetFile = new File("C:/Users/Administrator/Desktop/targetFile2.txt");
		FileReader fr = null;
		FileWriter fw = null;
		try {
			fr = new FileReader(srcFile);
			fw = new FileWriter(targetFile);
			char[] temp = new char[10];
			int len;
			while((len = fr.read(temp)) != -1){
				fw.write(temp, 0, len);
				System.out.println(new String(temp, 0, len));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 处理流之缓冲流 2.0
	 */
	@Test
	public void testBufferFileInputStream2(){
		File srcFile = new File("C:/Users/Administrator/Desktop/srcFile.txt");
		File targetFile = new File("C:/Users/Administrator/Desktop/targetFile2.txt");
		
		/**
		 * 实现了Closeable接口的类，在jdk1.7以后的版本中，可以直接将对象声明在try的括号中。
		 * 作用：在try中的代码全部执行完后，jvm会默认去帮你关掉声明在try的括号里的那些对象。
		 * 		你就不用再自己再写finally去关闭这些对象、资源了。
		 */
		try (FileReader fr = new FileReader(srcFile); 
				FileWriter fw = new FileWriter(targetFile); 
				BufferedReader br = new BufferedReader(fr);
				BufferedWriter bw = new BufferedWriter(fw);){
			String temp;
			while((temp = br.readLine()) != null){
				bw.write(temp);
				bw.newLine();//换行。
				bw.flush();
				
				System.out.println(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 处理流之缓冲流 1.0
	 */
	@Test
	public void testBufferFileInputStream1(){
		File srcFile = new File("C:/Users/Administrator/Desktop/srcFile.txt");
		File targetFile = new File("C:/Users/Administrator/Desktop/targetFile.txt");
		
		FileReader fr = null;
		FileWriter fw = null;
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			fr = new FileReader(srcFile);
			fw = new FileWriter(targetFile);
			
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);
			
			String temp;
			while((temp = br.readLine()) != null){
				
				bw.write(temp);
				bw.newLine();//换行。
				bw.flush();
				
				System.out.println(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null){
					bw.close();
				}
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 处理流之转换流
	 */
	@Test
	public void testInputStreamReader(){
		File srcFile = new File("C:/Users/Administrator/Desktop/srcFile.txt");
		File targetFile = new File("C:/Users/Administrator/Desktop/targetFile.txt");
		try(FileInputStream fis = new FileInputStream(srcFile);
				FileOutputStream fos = new FileOutputStream(targetFile);
				InputStreamReader isr = new InputStreamReader(fis);
				OutputStreamWriter osw = new OutputStreamWriter(fos);) {
			char[] temp = new char[20];
			int len;
			while((len = isr.read(temp)) != -1){
				osw.write(temp, 0, len);
				
				System.out.println(new String(temp, 0, len));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 对象流
	 */
	@Test
	public void testObjectInputStreamAndObjectOutputStream(){
		TestObjcetIo toi = new TestObjcetIo();
		toi.setName("张三");
		toi.setBirthdate(new Date().getTime());
		System.out.println(toi.toString());
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("C:/Users/Administrator/Desktop/TestObjcetIo.txt")));
			oos.writeObject(toi);
			
			ois = new ObjectInputStream(new FileInputStream("C:/Users/Administrator/Desktop/TestObjcetIo.txt"));
			TestObjcetIo targetTOI = (TestObjcetIo)ois.readObject();
			System.out.println(targetTOI.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(oos != null){
					oos.close();
				}
				if(ois != null){
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class TestObjcetIo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private long birthdate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(long birthdate) {
		this.birthdate = birthdate;
	}
	@Override
	public String toString() {
		return "TestObjcetIo [name=" + name + ", birthdate=" + birthdate + "]";
	}
}