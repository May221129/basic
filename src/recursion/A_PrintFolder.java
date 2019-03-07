package recursion;

import java.io.File;

/**
 * ʹ�õݹ飺��ӡ�ļ���2.0,Ҫ���ó��ļ��Ĳ㼶��ϵ�����ĳһ�����ļ��д����滹���ļ��У������δ�ӡ������ֱ����ӡ�������ļ�Ϊֹ����
 *
 * ����1��ʲôʱ�����ʹ�õݹ飿
 * 	==>�ݹ���Ƿ����Լ������Լ�����һ������ֽ�Ϊ������ͬ��С���顣�����ڵİ��ӡ����ļ�������ͷ��
 * ����2���ݹ���Ҫע��ĵ㣺
 * 	==>Ҫ�ҵ��ݹ���յ���ʲô
 * ����3����ôʵ�ֲ㼶��ϵ��
 * 	==> ��һ��String�͵ķ�����Ϊ��Σ������ÿ���ļ���/�ļ���ǰ��Ŀհף����������������ֲ㼶��ϵ
 * ����:4��������StringBuffer����StringBuilder�������
 * 	==>�����ԣ���Ϊ��String����ַ�����ʱ��ԭ�ȵ�String�ַ������ᱣ������StringBuffer��StringBuilder����ԭ�����ַ�����ֱ����ӵġ�
 * 		��һ���ļ���ͨ����ǿforѭ��ȥ����������ļ�ʱ��StringBuffer��StringBuilder�ı�Ƕ���ͬһ�����޷���ʵ�ֱ�ǵ����塣
 * ����5�����������String��
 * ����6����ô��String���ֲ㼶��ϵ��
 */
public class A_PrintFolder {
//	1����һ���ļ�����һ�����ֲ㼶��ϵ��String��ǣ�
	public static void printFolder(File file, String i){
//		2����ӡ����ļ����ļ�����
		System.out.println(i + file.getName());
//		3���ж�����ļ��Ƿ����ļ��У�
		if(file.isDirectory()){
//			4��������ļ��У���˵������չ����һ�㣬���ԣ�String����+String���ţ�����ֵ��String j��
			String j = new String("\t" + i);
//			5��������ļ��У���ȡ������ļ�����һ�����е��ļ���
			File[] files = file.listFiles();
//			6��ͨ����ǿforѭ����һ��һ���Ķ�ȡ��File�����е�Ԫ��(��һ��һ�����ļ�)��
			for(File f : files){
//				7���ظ�ǰ���1��2��3��4��5��6����ֱ����ȡ�����ļ������ļ��У�����һ��������ļ�����ʱ����������ߣ�ȥִ��ÿ���㼶δִ����Ĵ��롣��
				printFolder(f , j);
			}
		}
	}
	public static void main(String[] args) {
		File file = new File("Q:\\һ��ӡ�ļ��в���");
		printFolder(file, "");
//		printFolder(file, "\t");
	}
}
