package collection.c_hashmap;
/**
 * Map���õķ�����
 * 
 * 1.HashMapĬ�ϵ�Ͱ������16��λ�ã�ÿ��λ���ϷŵĶ���һ������(Ԫ�ص�hashֵ  % Ͱ����  = �������������������Ԫ�ط����ĸ�λ���ϣ�������ͬ�ķ�ͬһλ�õ������ϣ�JDK1.7��1.7��ǰ��������)
 * 	������û�����ݻ��Ƶ�ǰ���£�����Ϊ16��HashMap�ǿ��Դ��������Ԫ�صġ�
 * 	����ͬһλ���ϵ���������������ͻ�ʧȥHashMap��O1�Ĳ����ٶȵ����壬���Ե�Ԫ�ص������ﵽ��������0.75Lʱ��HashMap�ͻ�������ݡ�
 * 2.TreeMap��һ�ú������HashMap��Ͱ��ÿ��λ�ú��涼��һ�ú������JDK1.8��1.8�Ժ��Ժ��Ǻ��������ѡ��ʲô���ϣ�Ҫ�������������Ҫ��ʲô���ܣ�Ƶ������ɾ�Ĳ��ŵ�Ԫ�غ��٣�
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class A01HashMap {
	@Test
	public void testMap(){
		Map<String,Integer> m1 = new HashMap<String,Integer>();
		m1.put("�����", 26);//��Map�����һ��Ԫ��
		m1.put("����÷", 26);
		m1.put("������", 12);
		m1.put("�����", 18);
		m1.put("����", 2);	
		m1.put(null, null);
		System.out.println(m1);
		m1.remove("����÷");//ɾ��ָ����valueֵ
		System.out.println(m1);
		
		System.out.println("����������������key��������������");
		
//		����key����
		Set<String> s = m1.keySet();
		for(Object o : s){
			System.out.println(o);
		}
		
		System.out.println("����������������value��������������");
		
//		����value����
		Collection<Integer> c = m1.values();
		Iterator<Integer> i = c.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
		
		System.out.println("����������������key-value��,��ʽһ������������");
		
//		����key-value�ԣ�
//		����һ��
		Set<String> set = m1.keySet();
		for(Object o : set){
			System.out.println(o + "=" + m1.get(o));
		}
		
		System.out.println("����������������key-value��,��ʽ��������������");
		
//		��������
		Set<Map.Entry<String, Integer>> set2 = m1.entrySet();
		for(Map.Entry<String, Integer> entry: set2){
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}
}
