package io.a01file;

import java.io.File;
import org.junit.Test;

/**
 * java.io.File���ʹ�ã�
 */
public class TestFile {
	
	@Test
	public void testFile1(){
		File f1 = new File("helloFile.txt");//���·�����ڵ�ǰ�ļ����ڵ���Ŀ�ĸ�Ŀ¼�µ�·��,��ϸ���ͼ�JavaSE��Word�ĵ���
		
//		����·���ĵ�һ��д����
		File f2 = new File("Q:/io/helloFile.txt");//����·���������̷����ڵ��������ļ�·��
//		����·���ĵڶ���д����
		File f3 = new File("Q:\\io\\helloFile.txt");
		
		File f4 = new File("Q:/io/����");//File�����Ҳ���Զ�Ӧһ���ļ�Ŀ¼��haha�Ǹ��ļ��У������ļ�
		
		File f5 = new File("Q:\\mystudy\\Java����ʵս_Bank��Ŀ_01.pdf");
		System.out.println(f5.getName());
	}
}
