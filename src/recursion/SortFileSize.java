package recursion;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * ����һ���ļ�/�ļ��У��õ����С���������С�������������ǰN��
 * 
 * һ��˼·��
 * 1.�ݹ飺���ÿ���ļ��к�ÿ���ļ��еĴ�С��
 * 2.TreeMap��keyΪ�ļ��е�ȫ·������valueΪ�ļ��еĴ�С��
 * 3.���򣺶�TreeMap���ļ��еĴ�С��������
 * 4.չʾ��������ǰN���ļ�������չʾ������
 * 
 * �������⣺
 * 	1.�������ļ����Ƿ�ռ�ڴ棺��ռ�ڴ棬û�д�С��
 * 	2.���File�Ĵ�С��File.length()��
 * 	3.��ʲô������λ���ֱ�ۣ�����long��������о�����ʧ���⣩
 * 	4.��ô���ļ��д�С����������sort()
 * 	5.��Ա��������Ρ������Ƿ���static���Ρ���Ա�����Ƿ���static���Ρ�
 * 		������static���Σ����������д�ķ�����������Ϊ���ߵģ������ࡢ���߷���һ�㶼����״̬�ģ���static���Σ�������ҪΪ��ʹ��ĳ�����߷�����new���������ʵ����
 * 		û�г�Ա���������Ǹ���Σ���Ϊ����Ϊ�����࣬ʹ��ʱֱ�ӵ����侲̬�������ɣ����Ը���Ҳ���ô��ڳ�Ա������
 * 	6.TreeMap��ô������ģ���⣺javase/collection/b02treemap/TestTreeMap
 */
public class SortFileSize {
	public static void main(String[] args) {
		sortFile();
	}
	
	/**
	 * 1.��Ҫ���ڲ��ԵĴ��룬д��һ�������main������ֱ�ӵ�������������ɡ�
	 * 2.д·��ʱ������·����д���ǡ�Q:\mystudy\studynotes�����м�ķָ�������\����ΪʲôFile��ȴд��"\\"�أ�
	 * 	��������·�����ԣ�"\\"��ͨ��ת���ַ�"\",ʹ�ø������������õ��Ķ���"\"��
	 * 	����:javase/path/testpath/TestPath
	 */
	public static void sortFile(){
		File file = new File("Q:/mystudy/studynotes");//"Q:\\mystudy\\studynotes"
		TreeMap<String, Long> fileTreeMap = new TreeMap<>();
		getFileSize(file, fileTreeMap);
		sort(fileTreeMap);
	}
	
	/**
	 * ������������ǲ�����ģ�
	 * 	1.����Ϊ�����࣬����������״̬�ģ�������Ϊ��ʹ�øù���������������ʵ���������Է������Ǿ�̬�ģ�
	 * 		Ϊ���þ�̬�����ܹ�ʹ�ã�������Ա��������static���α�Ϊ������ǲ�����ġ���Ϊ���fileTreeMap
	 * 		����ÿ������sortFile()�������û�����һ���ģ�����ֱ����Ϊ��δ���ȥ���ɡ�
	 * 	2.��Ϊ����Ϊ�����࣬ʹ��ʱֱ�ӵ����侲̬�������ɣ����Ը���Ҳ���ô��ڳ�Ա������
	 */
//	private static TreeMap<String, Long> fileTreeMap = new TreeMap<>();//��Ա����������װ�����ļ����ļ���
	
	/**
	 * �����ÿһ���ļ��еĴ�С������ÿһ����ļ��ж����뵽fileTreeMap����������
	 * (���԰�ʵ����Ҫ�������ļ��Ƿ�Ҫ���뵽fileTreeMap����������)
	 */
	public static long getFileSize(File targetFile, TreeMap<String, Long> fileTreeMap){
		long size = 0;//����ͳ���ļ��еĴ�С
		//������ļ���
		if(targetFile.isFile()){
			size = targetFile.length();
			//���ļ���ȫ·�������С�����뵽fileTreeMap��������
//			fileTreeMap.put(targetFile.getPath(), size);
			return size;
		}
		//������ļ��У�
		File[] files = targetFile.listFiles();
		for(File file : files){
			size += getFileSize(file, fileTreeMap);
		}
		//���ļ��е�ȫ·�������С������fileTreeMap����������
		fileTreeMap.put(targetFile.getPath(), size);
		return size;
	}
	
	/**
	 * ���ײ��ӡ�ļ��У�����ʾ�ļ�/�ļ��еĴ�С
	 * (�ȣ�������ļ�����������ļ��У�����ֵΪ�ļ�/�ļ��е�size)
	 * �÷������ŵ㣺1.�ܹ������ļ��еĲ㼶�� 2.�ļ��Ĵ�СҲ��֪
	 * �÷�����ȱ�㣺1.����ļ��в㼶�ܶ࣬���չʾ������Ч����Ƚϳ����ң�
	 */
	public static long printFileAndSize2(File targetFile, String i){//���÷���ʱString iд��\t��,�������ֽ׼��Ĳ��
		long size = 0;//����ͳ���ļ��еĴ�С
		//������ļ���
		if(targetFile.isFile()){
			size = targetFile.length();
			//��ӡ�ļ���С��
//			System.out.println(i + targetFile.getName() + "(" + getFileSizeDescription(size) + ")");
			return size;
		}
		//������ļ��У�
		System.out.println(i + targetFile.getName());
		String j = new String("\t" + i);
		File[] files = targetFile.listFiles();
		File[] sortedFiles = sort(files);//�ļ����ȣ��ļ���
		for(File file : sortedFiles){
			size += printFileAndSize2(file,j);
		}
		//��ӡ�ļ��еĴ�С��
		System.out.println(i + targetFile.getName() + "(" + getFileSizeDescription(size) + ")" );
		return size;
	}
	
	/**
	 * ���ײ��ӡ�ļ��У�����ʾ�ļ�/�ļ��еĴ�С-��printFolderAndSize2()����ʱһ���ģ�ֻ�Ƿ�����д���е���졣
	 * (�ȣ�������ļ��У���������ļ�������ֵΪ�ļ�/�ļ��е�size)
	 */
	public static long printFileAndSize1(File targetFile,String i){//���÷���ʱString iд��\t��,�������ֽ׼��Ĳ��
		long size = 0;//����ͳ���ļ��еĴ�С
		//������ļ���
		if(targetFile.isDirectory()){
			System.out.println(i + targetFile.getName());
			String j = new String("\t" + i);
			File[] files = targetFile.listFiles();
			File[] sortedFiles = sort(files);//�ļ����ȣ��ļ���
			for(File file : sortedFiles){
				size += printFileAndSize1(file,j);
			}
			System.out.println(i + targetFile.getName() + "(" + getFileSizeDescription(size) + ")");
			return size;
		}
		//������ļ���
		size = targetFile.length();//File.length()�������ڻ��ĳ���ļ��Ĵ�С����λΪbytes
		return size;
	}
	
	/**
	 * ������Ҫ�õ�TreeMap��Collections��sort(List<T> list, Comparator<? super T> c)����
	 * ͬһ���У��ļ��д����ǰ��С���ں�
	 * 	����TreeMap��value�������Ǿ���Ҫ������Collections��sort(List<T> list, Comparator<? super T> c)������
	 * 	�÷�������ָ���Ƚ���������˳���ָ���б�������򡣵�����һ��ǰ���������Ǿ������е�Ԫ�ض������ܹ��������ṩ�ıȽ��������бȽ�
	 */
	public static void sort(TreeMap<String, Long> fileTreeMap){
		//���н�������
		List<Map.Entry<String, Long>> fileList = new ArrayList<Map.Entry<String, Long>>(fileTreeMap.entrySet());
		Collections.sort(fileList, new Comparator<Map.Entry<String, Long>>() {
			@Override
			public int compare(Entry<String, Long> o1, Entry<String, Long> o2) {
				return o2.getValue().compareTo(o1.getValue());//����o1.getValue().compareTo(o2.getValue())������
			}
		});
		//�Զ�������ļ��д�С������ǰ���ģ��磺ֻ����ļ���С����ǰ10�ģ�
		for(int i = 0; i < 10 && i < fileList.size(); i++){
			System.out.println(fileList.get(i).getKey() + "(" + getFileSizeDescription(fileList.get(i).getValue()) + ")");
		}
		//���������ļ��У�
//		for(Map.Entry<String, Double> fileEntry : fileList){
//			System.out.println(fileEntry.getKey() + "(" + getFileSizeDescription(fileEntry.getValue()) + ")");
//		}
	}
	
	/**
	 * �ļ��д�С��λ��ȷ����B��KB��MB��GB
	 */
	public static String getFileSizeDescription(long targetSize) {
		//��long��������ֱ�������㣬���о�����ʧ���⣬����Ҫ�Ƚ�longתΪdouble/float��
		double size = (double)targetSize;
        StringBuffer bytes = new StringBuffer();
        //��DecimalFormat��������ָ�ʽ�����Զ���С���������λ��
        DecimalFormat format = new DecimalFormat("###.00"); 
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB");
        }
        else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB");
        }
        else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB");
        }
        else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            }
            else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
    }
	
	/**
	 * �������ΪFile���飬����ֵΪFile���顣
	 * ð�ݷ�����File[]�е�Ԫ�ؽ�������ͬһ���У��ļ�����ǰ�����ļ��ں�
	 */
	public static File[] sort(File[] file){
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
}
