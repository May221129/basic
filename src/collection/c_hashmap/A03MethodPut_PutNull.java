package collection.c_hashmap;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * ���ԣ�HashMap�п��Դ���null��������ͬʱ���ڶ��null��
 * ��������Դ���null��������ͬʱ���ڶ��null�����HashMap�е�Ԫ�ؾ���Ψһ����һ�µġ�
 * 
 * @author May
 * 2019��7��25��
 */
public class A03MethodPut_PutNull {
	public static void main(String[] args) {
		HashMap<String, Integer> hashMap = new HashMap<>();
		hashMap.put(null, 123);
		hashMap.put(null, 456);
		for(Entry<String, Integer> entry : hashMap.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}
