package array.helloworld;

public class TestArrayMethod1 {
	public static void main(String[] args) {
		
		int[] arr = new int[]{90,2,8,6,40,23,5};
		
		TestArrayMethod au = new TestArrayMethod();
		
		//�����������ֵ��
		au.getMin(new int[]{1,1,1,1});
		
		int a = 1;
		int b = 3;
		au.add(a,b);
		
		au.add(1, 3);
		
		
		int max = au.getMax(arr);
		System.out.println("���ֵΪ��" + max);
		
		//����������Сֵ��
		int min = au.getMin(arr);
		System.out.println("��СֵΪ��" + min);
		
		//��������ܺͣ�
		int sum = au.getSum(arr);
		System.out.println("����ĺ�Ϊ��" + sum);
		
		//�������ƽ��ֵ��
		int avg = au.getAvg(arr);
		System.out.println("ƽ��ֵΪ��" + avg);
		
		//���������еĸ���Ԫ�أ�
		System.out.println("��������е�����Ԫ�أ�");
		au.print(arr);
		//System.out.println(arr);����������������ã��Ǹ���ַ��
		
		//����ķ�ת��
		System.out.println("����ķ�ת��");
		au.reverse(arr);
		au.print(arr);
		
		//����ĸ��ƣ�
		System.out.println("����ĸ��ƣ�");
		int[] arr1 = au.copy(arr);
		au.print(arr1);
		
		//ʹ��ð�����򷨶�������д�С���������
		System.out.println("�������С��������");
		int[] arr2 = au.sort(arr);
		au.print(arr2);
		
		
		//ʹ��ֱ��ѡ�����򷨶�������дӴ�С������
		System.out.println("������Ӵ�С����");
		int[] arr3 = au.sort1(arr);
		au.print(arr3);
	}
}
