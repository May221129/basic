package recursion;

import java.io.File;

/**
 * ʹ�õݹ飺��ӡ�ļ���1.0�����ĳһ�����ļ��д����滹���ļ��У������δ�ӡ������ֱ����ӡ�������ļ�Ϊֹ����
 */
public class PrintFolder {
	public static void main(String[] args) {
//		���file�Ǹ��ļ�����ֻ���ӡ������ļ�����Ȼ��������ֹ��
		File file = new File("Q:\\һ��ӡ�ļ��в���\\��������ӡ�ļ��в���.docx");
		method(file);
		System.out.println("------------------------------------------");
//		���file�Ǹ��ļ��У�
		File files = new File("Q:\\һ��ӡ�ļ��в���");
		method(files);
	}
	
	/** 
	 * �������У�����ĳ�Ա����û���κ����壺�����method()����������Ǿֲ������������ĵ����ߴ���ȥ���ɣ��ͳ�Ա������û�й�ϵ�ġ�
	 * ���������Ҫ�õ���Ա�������Ͳ�Ҫ��������Σ�ֱ���ڷ��������ó�Ա�������ɡ�
	 */
	public static void method(File file){
		System.out.println(file.getName());
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f :files){
				method(f);
			}
		}
	}
}
