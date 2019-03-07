package io.a02fileinputstream_fileoutputstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.Test;

/**
 * FileInputStream & FileOutputStream(�ļ�д��Ͷ�ȡ�Ľڵ���/�ļ���)����������ʹ�ã�
 */
public class A01FileInputStream {
	/**
	 * ��Ӳ�̴��ڵ�һ���ļ��У���ȡ�����ݵ������У�ʹ��FileInputStream����ȡʱ��reads()�������쳣����ʹ��try-catch�쳣����ķ�ʽ��
	 */
	@Test
	public void test3(){
		FileInputStream fis3 = null;
		try {
			File f3 = new File("hello.txt");
			fis3 = new FileInputStream(f3);
			byte[] b = new byte[1024];//���飬����д���ȡ�������ݵġ�
			int len;//��¼ÿ��д��byte�е��ֽڵĳ���
			while((len = fis3.read(b)) != -1){
				
				//��������д���Ǵ���ģ���������"Today is five two zero ze",
				//��Ϊlen�ĳ���Ϊ5�����һ��д��ʱ��ֻʣro������ĸ��ֻ�ܸ��ǵ������ڶ���д��byte��"wo ze"��"wo"������ĸ��" ze"�򸲸ǲ��ˡ�
//				for(int i = 0; i < b.length; i++){
//					System.out.print((char)b[i]);
//				}
				
				//��ȷ��д��һ��
				for(int i = 0; i < len; i++){
					System.out.print((char)b[i]);
				}
				
				//��ȷ��д������
//				System.out.print(new String(b, 0, len));//ע������ĵ���b.length����len
			}
		}  catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fis3 != null){
					try {fis3.close();	
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ��Ӳ�̴��ڵ�һ���ļ��У���ȡ�����ݵ������У�ʹ��FileInputStream��ʹ��try-catch�쳣����ķ�ʽ��
	 */
	@Test
	public void test2(){
		FileInputStream fis2 = null;
		try {
			File f2 = new File("hello.txt");
			fis2 = new FileInputStream(f2);
			int b ;
			while((b = fis2.read()) != -1 ){
				System.out.print((char)b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{//��֤���Ĺرղ���һ����ִ�С�
			try {
				fis2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��Ӳ�̴��ڵ�һ���ļ��У���ȡ�����ݵ������У�ʹ��FileInputStream��ʹ��throws�쳣����ķ�ʽ��
	 */
	@Test
	public void test1() throws Exception{
		//1������һ��File��Ķ���
		File f1 = new File("bin/fileinputandoutputstream/hello.txt");
		//2������һ��FileInputStream��Ķ���
		FileInputStream fis1 = new FileInputStream(f1);
		//3������FileINputStream��ķ�����ʵ��File�ļ��Ķ�ȡ��
//		int b = fis1.read();
//		while(b != -1){
//			System.out.println(b);
//			b = fis1.read();
//		}
		int b;
		while((b = fis1.read()) != -1){
			System.out.print((char)b);
		}
		//4���ر���Ӧ������
		fis1.close();
	}
}
