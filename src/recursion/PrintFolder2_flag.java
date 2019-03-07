package recursion;

import java.io.File;

/**
 * ʹ�õݹ飺��ӡ�ļ���2.0,Ҫ���ó��ļ��Ĳ㼶��ϵ,���ͬһ���ڣ������ļ��������ļ��У����ȴ�ӡ�ļ��У��ٴ�ӡ�ļ�
 * �����ĳһ�����ļ��д����滹���ļ��У������δ�ӡ������ֱ����ӡ�������ļ�Ϊֹ����
 *
 * ���ȴ�ӡ�ļ��У��ٴ�ӡ�ļ�����ʵ�ַ�ʽ����
 * File[]�洢���ļ�������ģ���File[]�е�Ԫ�ؽ��������ļ�����ǰ�����ļ��ں������귵��һ���µ�File[]��Ȼ�������µļ��Ͻ��б�����
 * ��ϸ�������£�
 */

public class PrintFolder2_flag {
	public static void printFolder(File file, String i){
		System.out.println(i + file.getName());
		if(file.isDirectory()){
			String j = new String("\t" + i);
			File[] files = file.listFiles();
			File[] reverseFiles = reverse(files);
			for(File f : reverseFiles){
				printFolder(f, j);
			}
		}
	}
	public static File[] reverse(File[] file){//ð�ݷ�����File[]�е�Ԫ�ؽ��������ļ�����ǰ�����ļ��ں�
		boolean flag = true;
		for(int j = 0; j < file.length && flag; j++){
			flag = false;
			for(int i = 0; i < file.length-1-j; i++){
				if(file[i].isFile() && file[i+1].isDirectory()){
					File temp = file[i];
					file[i] = file[i+1];
					file[i+1] = temp;
					flag = true;
				}
			}
		}
		return file;
	}

	public static void main(String[] args) {
		File file = new File("Q:\\mystudy\\studynotes");
		Long start = System.currentTimeMillis();
		printFolder(file, "\t");
		Long end = System.currentTimeMillis();
		System.out.println("��ӡ���ļ�Ŀ¼�����ѣ� " + (end - start));
	}
}
