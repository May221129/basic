package collection.b_collectiontest;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class a01test {
	/**
	 * ������Arrays��asList()������
	 * 	public static <T> List<T> asList(T... a) {
     * 		return new ArrayList<>(a);
     * 	}
     * 	1.�÷�����ע��㣺
     * 		�� �����ķ����� <T> ��
     * 		�� ����������� "N��<T>���͵�Ԫ����ɵ�����a���磺asList(1,2,3,4)�������4��int���͵�Ԫ����ɵ����顣
     * 	 	  ==�����ԣ�test01()�У�Arrays.asList(demo)�����Ϊ��1��int[]������ɵ����顱��
     * 		�� �����ķ���ֵ�� <T>���͵�List��
     * 	 	  ==�����ԣ�test01()�У�Arrays.asList(demo)������ֵ������int[]���͵�List����List<int[]>.
     * 	2.�÷�����ʹ�ó�����
     * 		����һ��������ͬ��Ԫ�أ���װ��һ�����ϡ��磺asList("����","����","����");����List<String>.
	 */
	@Test
	public void test01(){
		int[] demo = new int[]{1,2,3,4,5};
		for(int i = 0; i < demo.length; i++){
			System.out.println(demo[i]);
		}
		List<int[]> ll = Arrays.asList(demo);//�����"1��int[]������ɵ�����"
		for(int i = 0; i < ll.size(); i++){
			for(int j = 0; j < ll.get(i).length; j++){
				System.out.println("==> " + ll.get(i)[j]);
			}
		}
	}
}
