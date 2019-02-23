package recursion;

import java.io.File;
import java.util.ArrayList;

/**
 * ʹ�õݹ飺��ӡ�ļ���2.0,Ҫ���ó��ļ��Ĳ㼶��ϵ,���ͬһ���ڣ������ļ��������ļ��У����ȴ�ӡ�ļ��У��ٴ�ӡ�ļ�
 * �����ĳһ�����ļ��д����滹���ļ��У������δ�ӡ������ֱ����ӡ�������ļ�Ϊֹ����
 *
 * ���ȴ�ӡ�ļ��У��ٴ�ӡ�ļ�����ʵ�ַ�ʽһ��
 * 1���õ�һ���ļ������������ļ��Ĳ㼶���ļ�����System.out.println(i + file.getName());
 * 2������㼶�ķ���+1��String j = new String("\t" + i);
 * 3���ж�����ļ��Ǵ��ļ������ļ��У�����Ǵ��ļ���return��������ļ��У��������ļ����е���һ��Ŀ¼����ΪFile[]��
 * 4���½�һ��ArrayList���ϣ���������File[]����ļ���
 * 5������File[]�е������ļ�������ÿһ���ļ����жϣ�������ļ��У����õݹ�ķ��������õ�ǰ�������������Ǵ��ļ����򱣴浽ArrayList�����У�
 * 6����������File[]�е������ļ����ٰ�ArrayList�е����д��ļ������������ע��:Ҫ����ļ�������
 * ��ϸ�������£�
 */

public class PrintFolder1_flag {
	public static void main(String[] args) {	
		File file = new File("Q:\\mystudy\\studynotes");
		Long start = System.currentTimeMillis();
		printFolder(file, "\t");
		Long end = System.currentTimeMillis();
		System.out.println("��ӡ���ļ�Ŀ¼�����ѣ� " + (end - start));
	}
	
	public static void printFolder(File file, String i){
		System.out.println(i + file.getName());
		if(file.isDirectory()){
			String j = new String("\t" + i);
			ArrayList<File> la = new ArrayList<File>();
			File[] files = file.listFiles();
			for(File f : files){
				if(f.isDirectory()){
					printFolder(f, j);
				}else{
					la.add(f);
				}
			}
			for(File newFile : la){
				System.out.println(j + newFile.getName());
			}
		}
	}
}
