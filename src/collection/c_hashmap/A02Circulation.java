package collection.c_hashmap;
/**
 * ����һ��Map���ϣ���3�ַ���������key-value��
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class A02Circulation {
	public static void main(String[] args) {
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("����÷", 26);
		map.put("�����", 26);
		map.put("����", 23);
		map.put("����", 24);
		map.put("����", 25);
		
		System.out.println("����Map��Entry�ķ�ʽһ��");
		Set<String> key = map.keySet();
		for(String name : key){
			System.out.println(name + "=" + map.get(name));
		}
		
		System.out.println();
		
		System.out.println("����Map��Entry�ķ�ʽ����");
		Set<Map.Entry<String, Integer>> set = map.entrySet();//Entry��ôʹ�÷��ͣ�ע��
		for(Map.Entry<String, Integer> entry : set){
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		
		System.out.println();
		
		System.out.println("����Map��Entry�ķ�ʽ����");
		Iterator<Map.Entry<String,Integer>> iterator = map.entrySet().iterator();//Map�ĵ�����ʹ�÷�ʽ�Ƚ��ر�ע������
		while(iterator.hasNext()){
			Map.Entry<String, Integer> entry = iterator.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		
		System.out.println();
		
		System.out.println("����Map����ʱ��ɾ��ĳ��ָ����entry��");
/*
		Set<String> names = map.keySet();
		for(String str : names){
			if(str.equals("�����")){
				map.remove(str);
			}else{
				System.out.println(str + "=" + map.get(str));
			}
		}
*/
		Iterator<Map.Entry<String, Integer>> i = map.entrySet().iterator();
		while(i.hasNext()){
			map.remove("�����");
			Map.Entry<String, Integer> entry = i.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}
}