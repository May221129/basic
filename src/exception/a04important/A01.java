package exception.a04important;

import java.util.ArrayList;
import org.junit.Test;

public class A01 {
	
	/**
	 * test01()���������飬ͨ��try-catchֹͣѭ�����������ֶ����������߽����⣩��72���롣
	 * test02()��ͨ����������ǿ��forѭ������������:24���롣
	 * ���ۣ���ҪΪ�������Ŀ�������ʹ���쳣����ȻЧ�ʿ��ܻ���ͣ����п���Ӱ��������bug����
	 */
	@Test
	public void test01(){
		long start = System.currentTimeMillis();
		try{
			int i = 0;
			while(true){
				System.out.println(getArray().get(i));
				i++;
			}
		}catch(IndexOutOfBoundsException e){
			
		}finally{
			long end = System.currentTimeMillis();
			System.out.println("end - start = " + (end - start));//72
		}
	}
	@Test
	public void test02(){
		ArrayList<Integer> arrayList = getArray();
		long start = System.currentTimeMillis();
		for(Integer element : arrayList){
			System.out.println(element);
		}
		long end = System.currentTimeMillis();
		System.out.println("end - start = " + (end - start));//24
	}
	
	public ArrayList<Integer> getArray(){
		ArrayList<Integer> array = new ArrayList<>();
		for(int i = 0; i < 1000; i++){
			array.add(i);
		}
		return array;
	}
}
