package collection.c_hashmap;
/**
 * 创建一个Map集合，用3种方法遍历出key-value：
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class A02Circulation {
	public static void main(String[] args) {
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("赖丽梅", 26);
		map.put("李光荣", 26);
		map.put("张三", 23);
		map.put("李四", 24);
		map.put("王五", 25);
		
		System.out.println("遍历Map的Entry的方式一：");
		Set<String> key = map.keySet();
		for(String name : key){
			System.out.println(name + "=" + map.get(name));
		}
		
		System.out.println();
		
		System.out.println("遍历Map的Entry的方式二：");
		Set<Map.Entry<String, Integer>> set = map.entrySet();//Entry怎么使用泛型，注意
		for(Map.Entry<String, Integer> entry : set){
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		
		System.out.println();
		
		System.out.println("遍历Map的Entry的方式三：");
		Iterator<Map.Entry<String,Integer>> iterator = map.entrySet().iterator();//Map的迭代器使用方式比较特别，注意区分
		while(iterator.hasNext()){
			Map.Entry<String, Integer> entry = iterator.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		
		System.out.println();
		
		System.out.println("遍历Map集合时，删除某个指定的entry：");
/*
		Set<String> names = map.keySet();
		for(String str : names){
			if(str.equals("李光荣")){
				map.remove(str);
			}else{
				System.out.println(str + "=" + map.get(str));
			}
		}
*/
		Iterator<Map.Entry<String, Integer>> i = map.entrySet().iterator();
		while(i.hasNext()){
			map.remove("李光荣");
			Map.Entry<String, Integer> entry = i.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}
}