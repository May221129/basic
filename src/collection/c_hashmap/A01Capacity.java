package collection.c_hashmap;

import java.util.HashMap;

/**
 * ̽��1��HashMap�����ݻ��ơ�
 * 	��ֵ = Ͱ�� * �������ӡ���Ĭ�ϳ�ʼ��ֵ = 16 * 0.75 = 12.
 * 	��ֵ������HashMap�ܹ����ɶ��ٸ�Ԫ�أ��������е�Ԫ�ظ���==��ֵ����ʱ������putԪ�أ��ͻᴥ�����ݡ�
 * 
 * @author May
 * 2019��7��23��
 */
public class A01Capacity {
	public static void main(String[] args) {
		HashMap<Integer, String> hashMap = new HashMap<>();//ʹ��Ĭ�ϳ�ʼ��ֵ��16*0.75��
		for(int i = 0; i < 15; i++) {
			hashMap.put(i, "hello");//i==11ʱ�����ˣ�12ʱ���ݡ��ϵ����HashMap.resize()�ϡ�
		}
	}
}
