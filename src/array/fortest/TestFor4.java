package array.fortest;
//forѭ������ϰ��
public class TestFor4 {
	public static void main(String[] args){
		
		int m = 12;
		int n = 28;
		
		//�Ƚ�m,n�Ĵ�С��ȡ�ϴ�ֵ��
		int max = (m > n )? m : n;
		System.out.println(max);
		
		System.out.println("------------------------------");
		
		//�Ƚ�m,n�Ĵ�С��ȡ��Сֵ��
		int min = (m < n)? m : n;
		System.out.println(min);
		
		System.out.println("------------------------------");
		
		//��m��n�� ���Լ����
		/*
		 int a = 0;
		
		for(int i = 1; i <= m; i++){
			if(m % i ==0 && n % i == 0){
				a = i;
			}
		}
		System.out.println(a);
		 */
		
		for(int i = min; i >=1; i--){
			if(m % i == 0 && n % i == 0){
				System.out.println("m=12,n=28ʱ�����ǵ����Լ��Ϊ" + i);
				break;
			}
		}
		
		System.out.println("------------------------------");
		
		//��m��n�� ��С��������
		for(int i = max; i >= max; i++){
			if(i % n == 0 && i % m == 0){
				System.out.println("m=12,n=28ʱ�����ǵ���С������Ϊ" + i);
				break;
			}
		}
	}
}
