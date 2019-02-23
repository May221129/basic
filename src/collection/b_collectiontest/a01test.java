package collection.b_collectiontest;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class a01test {
	/**
	 * 工具类Arrays的asList()方法：
	 * 	public static <T> List<T> asList(T... a) {
     * 		return new ArrayList<>(a);
     * 	}
     * 	1.该方法的注意点：
     * 		① 方法的泛型是 <T> ；
     * 		② 方法的入参是 "N个<T>类型的元素组成的数组a，如：asList(1,2,3,4)，入参是4个int类型的元素组成的数组。
     * 	 	  ==》所以，test01()中，Arrays.asList(demo)，入参为“1个int[]类型组成的数组”。
     * 		③ 方法的返回值是 <T>类型的List。
     * 	 	  ==》所以，test01()中，Arrays.asList(demo)，返回值类型是int[]类型的List，即List<int[]>.
     * 	2.该方法的使用场景：
     * 		给你一组类型相同的元素，封装成一个集合。如：asList("张三","李四","王五");返回List<String>.
	 */
	@Test
	public void test01(){
		int[] demo = new int[]{1,2,3,4,5};
		for(int i = 0; i < demo.length; i++){
			System.out.println(demo[i]);
		}
		List<int[]> ll = Arrays.asList(demo);//入参是"1个int[]类型组成的数组"
		for(int i = 0; i < ll.size(); i++){
			for(int j = 0; j < ll.get(i).length; j++){
				System.out.println("==> " + ll.get(i)[j]);
			}
		}
	}
}
