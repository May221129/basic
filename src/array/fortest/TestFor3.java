package array.fortest;
//for循环的练习：
public class TestFor3 {
	public static void main(String[] args){
//		求：3000米长的绳子，每天减一半，问多少天这个绳子会小于5米？不考虑小数
/*
		 方法一：
		int day = 0;
		for(int i = 3000; i >= 5; i /= 2){
			day++;	
		}
		System.out.println(day);
 */
		
		//方法二：
		int day = 0;
		for(int i =3000; i >= 5; day++){
			i /= 2;
		}
		System.out.println(day);
		
		System.out.println();
		
//		找出1000以内所有完数（完数：一个数如果恰好等于他的因子（因子：除去这个数本身后正的约数）之和，这个数就成为 完数。）如：6=1+2+3.
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
