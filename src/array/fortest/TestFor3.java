package array.fortest;
//forѭ������ϰ��
public class TestFor3 {
	public static void main(String[] args){
//		��3000�׳������ӣ�ÿ���һ�룬�ʶ�����������ӻ�С��5�ף�������С��
/*
		 ����һ��
		int day = 0;
		for(int i = 3000; i >= 5; i /= 2){
			day++;	
		}
		System.out.println(day);
 */
		
		//��������
		int day = 0;
		for(int i =3000; i >= 5; day++){
			i /= 2;
		}
		System.out.println(day);
		
		System.out.println();
		
//		�ҳ�1000��������������������һ�������ǡ�õ����������ӣ����ӣ���ȥ��������������Լ����֮�ͣ�������ͳ�Ϊ ���������磺6=1+2+3.
		for(int i = 1; i < 1000; i++){
			int sum = 0;
			for(int j = 1; j < i; j++){
				if( i % j == 0){
					sum += j;
				}
			}
			if(i == sum){
				System.out.println(i);
			}
		}
	}
}
