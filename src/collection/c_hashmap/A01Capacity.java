package collection.c_hashmap;

import java.util.HashMap;

/**
 * 探究1：HashMap的扩容机制。
 * 	阈值 = 桶数 * 加载因子。其默认初始阈值 = 16 * 0.75 = 12.
 * 	阈值代表了HashMap能够容纳多少个元素，当集合中的元素个数==阈值，这时再往里put元素，就会触发扩容。
 * 
 * @author May
 * 2019年7月23日
 */
public class A01Capacity {
	public static void main(String[] args) {
		HashMap<Integer, String> hashMap = new HashMap<>();//使用默认初始阈值（16*0.75）
		for(int i = 0; i < 15; i++) {
			hashMap.put(i, "hello");//i==11时就满了，12时扩容。断点打在HashMap.resize()上。
		}
	}
}
