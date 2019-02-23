package io.a02fileinputstream_fileoutputstream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.Test;

/**
 * �������
 * FileInputStream & FileOutputStream(�ļ�д��Ͷ�ȡ�Ľڵ���/�ļ���)���������ʹ��.
 */
public class A02FileOutputStream {
	
	@Test
	public void test1(){
		//1������һ��File���󣬱���Ҫд����ļ�λ�ã�
		//==>����������ļ����Բ����ڣ�ִ�й������������ڣ����Զ������������ڣ���Ḳ���ļ���ԭ�е����ݡ�
		File f1 = new File("goodgoodstudy.txt");
		//2������һ��FileOutputStream�Ķ��󣬽�file�Ķ�����Ϊ�βδ��ݸ�FileOutputStream�Ĺ������У�
		FileOutputStream fos1 = null;
		try {
			fos1 = new FileOutputStream(f1);
			//3��д�������
			fos1.write(new String("�Ҿ����Լ��ܱ���").getBytes());
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos1 != null){
				try {
					//4���ر��������
					fos1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
