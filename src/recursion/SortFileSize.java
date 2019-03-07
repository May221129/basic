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
 * 给你一个文件/文件夹，得到其大小，并对其大小进行排名，输出前N名
 * 
 * 一、思路：
 * 1.递归：获得每层文件夹和每层文件夹的大小；
 * 2.TreeMap：key为文件夹的全路径名，value为文件夹的大小；
 * 3.排序：对TreeMap中文件夹的大小进行排序；
 * 4.展示：将最大的前N名文件夹依次展示出来；
 * 
 * 二、问题：
 * 	1.单纯的文件夹是否占内存：不占内存，没有大小。
 * 	2.获得File的大小：File.length()。
 * 	3.用什么计量单位会更直观？（用long做运算会有精度损失问题）
 * 	4.怎么做文件夹大小的排名：看sort()
 * 	5.成员变量、入参、方法是否用static修饰、成员变量是否用static修饰、
 * 		方法用static修饰：这个类中所写的方法，都是作为工具的，工具类、工具方法一般都是无状态的，用static修饰，而不需要为了使用某个工具方法而new出工具类的实例。
 * 		没有成员变量，而是给入参：因为该类为工具类，使用时直接调动其静态方法即可，所以该类也不用存在成员变量。
 * 	6.TreeMap怎么做排序的？详解：javase/collection/b02treemap/TestTreeMap
 */
public class SortFileSize {
	public static void main(String[] args) {
		sortFile();
	}
	
	/**
	 * 1.将要用于测试的代码，写在一个方法里，main方法中直接调用这个方法即可。
	 * 2.写路径时，本来路径的写法是“Q:\mystudy\studynotes”（中间的分隔符都是\），为什么File中却写成"\\"呢？
	 * 	下面两种路径都对，"\\"是通过转义字符"\",使得各编译器解析得到的都是"\"。
	 * 	详解见:javase/path/testpath/TestPath
	 */
	public static void sortFile(){
		File file = new File("Q:/mystudy/studynotes");//"Q:\\mystudy\\studynotes"
		TreeMap<String, Long> fileTreeMap = new TreeMap<>();
		getFileSize(file, fileTreeMap);
		sort(fileTreeMap);
	}
	
	/**
	 * 该中有类变量是不合理的：
	 * 	1.该类为工具类，工具类是无状态的（即不用为了使用该工具类而创建该类的实例），所以方法都是静态的；
	 * 		为了让静态方法能够使用，而将成员变量加上static修饰变为类变量是不合理的。因为这个fileTreeMap
	 * 		就是每个调用sortFile()方法的用户各有一个的，所以直接作为入参传进去即可。
	 * 	2.因为该类为工具类，使用时直接调动其静态方法即可，所以该类也不用存在成员变量。
	 */
//	private static TreeMap<String, Long> fileTreeMap = new TreeMap<>();//成员变量，用来装所有文件、文件夹
	
	/**
	 * 计算出每一处文件夹的大小，并将每一层的文件夹都放入到fileTreeMap中用于排序
	 * (可以按实际需要，考虑文件是否要加入到fileTreeMap中用于排序)
	 */
	public static long getFileSize(File targetFile, TreeMap<String, Long> fileTreeMap){
		long size = 0;//用于统计文件夹的大小
		//如果是文件：
		if(targetFile.isFile()){
			size = targetFile.length();
			//将文件的全路径及其大小，放入到fileTreeMap用于排序
//			fileTreeMap.put(targetFile.getPath(), size);
			return size;
		}
		//如果是文件夹：
		File[] files = targetFile.listFiles();
		for(File file : files){
			size += getFileSize(file, fileTreeMap);
		}
		//将文件夹的全路径及其大小，放入fileTreeMap中用于排序
		fileTreeMap.put(targetFile.getPath(), size);
		return size;
	}
	
	/**
	 * 按阶层打印文件夹，且显示文件/文件夹的大小
	 * (先：如果是文件，后：如果是文件夹，返回值为文件/文件夹的size)
	 * 该方法的优点：1.能够体现文件夹的层级； 2.文件的大小也可知
	 * 该方法的缺点：1.如果文件夹层级很多，最后展示出来的效果会比较长、乱；
	 */
	public static long printFileAndSize2(File targetFile, String i){//调用方法时String i写“\t”,用于体现阶级的层次
		long size = 0;//用于统计文件夹的大小
		//如果是文件：
		if(targetFile.isFile()){
			size = targetFile.length();
			//打印文件大小：
//			System.out.println(i + targetFile.getName() + "(" + getFileSizeDescription(size) + ")");
			return size;
		}
		//如果是文件夹：
		System.out.println(i + targetFile.getName());
		String j = new String("\t" + i);
		File[] files = targetFile.listFiles();
		File[] sortedFiles = sort(files);//文件夹先，文件后
		for(File file : sortedFiles){
			size += printFileAndSize2(file,j);
		}
		//打印文件夹的大小：
		System.out.println(i + targetFile.getName() + "(" + getFileSizeDescription(size) + ")" );
		return size;
	}
	
	/**
	 * 按阶层打印文件夹，且显示文件/文件夹的大小-和printFolderAndSize2()方法时一样的，只是方法体写法有点差异。
	 * (先：如果是文件夹，后：如果是文件，返回值为文件/文件夹的size)
	 */
	public static long printFileAndSize1(File targetFile,String i){//调用方法时String i写“\t”,用于体现阶级的层次
		long size = 0;//用于统计文件夹的大小
		//如果是文件夹
		if(targetFile.isDirectory()){
			System.out.println(i + targetFile.getName());
			String j = new String("\t" + i);
			File[] files = targetFile.listFiles();
			File[] sortedFiles = sort(files);//文件夹先，文件后
			for(File file : sortedFiles){
				size += printFileAndSize1(file,j);
			}
			System.out.println(i + targetFile.getName() + "(" + getFileSizeDescription(size) + ")");
			return size;
		}
		//如果是文件：
		size = targetFile.length();//File.length()仅仅用于获得某个文件的大小，单位为bytes
		return size;
	}
	
	/**
	 * 排序：需要用到TreeMap、Collections的sort(List<T> list, Comparator<? super T> c)方法
	 * 同一级中：文件夹大的在前，小的在后
	 * 	根据TreeMap的value排序我们就需要借助于Collections的sort(List<T> list, Comparator<? super T> c)方法，
	 * 	该方法根据指定比较器产生的顺序对指定列表进行排序。但是有一个前提条件，那就是所有的元素都必须能够根据所提供的比较器来进行比较
	 */
	public static void sort(TreeMap<String, Long> fileTreeMap){
		//进行降序排序：
		List<Map.Entry<String, Long>> fileList = new ArrayList<Map.Entry<String, Long>>(fileTreeMap.entrySet());
		Collections.sort(fileList, new Comparator<Map.Entry<String, Long>>() {
			@Override
			public int compare(Entry<String, Long> o1, Entry<String, Long> o2) {
				return o2.getValue().compareTo(o1.getValue());//降序。o1.getValue().compareTo(o2.getValue())：升序
			}
		});
		//自定义输出文件夹大小排名在前几的，如：只输出文件大小排在前10的：
		for(int i = 0; i < 10 && i < fileList.size(); i++){
			System.out.println(fileList.get(i).getKey() + "(" + getFileSizeDescription(fileList.get(i).getValue()) + ")");
		}
		//遍历所有文件夹：
//		for(Map.Entry<String, Double> fileEntry : fileList){
//			System.out.println(fileEntry.getKey() + "(" + getFileSizeDescription(fileEntry.getValue()) + ")");
//		}
	}
	
	/**
	 * 文件夹大小单位的确定：B、KB、MB、GB
	 */
	public static String getFileSizeDescription(long targetSize) {
		//用long数据类型直接做运算，会有精度损失问题，所有要先将long转为double/float：
		double size = (double)targetSize;
        StringBuffer bytes = new StringBuffer();
        //用DecimalFormat类进行数字格式化：自定义小数点后保留几位数
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
	 * 排序：入参为File数组，返回值为File数组。
	 * 冒泡法：对File[]中的元素进行排序：同一级中，文件夹在前，纯文件在后
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
