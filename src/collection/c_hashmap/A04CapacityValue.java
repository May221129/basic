package collection.c_hashmap;

/**
 * ̽��2��ΪʲôHahsMap������һ��Ϊ2���ݣ�
 * 	��һ��Ԫ�ط��õ��ĸ�Ͱ�У���ͨ�����㡰Ԫ�ص�key�Ĺ�ϣֵ % Ͱ�����õ���������ȷ���ġ�
 * 		������һ����2^nʱ��h & (length - 1) == h % length�������ǵȼ۲���Ч�ģ�
 * 		��Ϊ�������̫�ó���ȡģ���㣬��ȴ�ǳ��ó���λ���㣬��Ч�ʼ��ߣ����Լ���/important/src/Algorithm/BitAndModulus.java
 * 	
 * 	���䣺�������㣺a / b = c ���� R;  R = a - bc;  �磺1 % 10 == 1 ������Ϊ 1 / 10 = 0,   ���� = 1 - 10*0 = 1
 * 	���ͣ�https://blog.csdn.net/a_long_/article/details/51594159
 * 
 * @author May
 * 2019��7��26��
 */
public class A04CapacityValue {
	public static void main(String[] args) {
		int a = 1, b = 16;//2^4
		yu(a, b);
		wei(a, b);
		System.out.println("---------------------------");
		
		b = 5;//��2�Ĵη�
		yu(a, b);
		wei(a, b);
	}
	//ȡģ���㣺
	public static void yu(int hashCode, int capacity) {
		System.out.println(hashCode % capacity);
	}
	//λ����
	private static void wei(int hashCode, int capacity) {
		System.out.println(hashCode & (capacity-1));
	}
}
