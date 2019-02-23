package array.fortest;
//for循环的练习：
public class TestFor4 {
	public static void main(String[] args){
		
		int m = 12;
		int n = 28;
		
		//比较m,n的大小，取较大值：
		int max = (m > n )? m : n;
		System.out.println(max);
		
		System.out.println("------------------------------");
		
		//比较m,n的大小，取较小值：
		int min = (m < n)? m : n;
		System.out.println(min);
		
		System.out.println("------------------------------");
		
		//求m和n的 最大公约数：
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
				System.out.println("m=12,n=28时，它们的最大公约数为" + i);
				break;
			}
		}
		
		System.out.println("------------------------------");
		
		//求m和n的 最小公倍数：
		for(int i = max; i >= max; i++){
			if(i % n == 0 && i % m == 0){
				System.out.println("m=12,n=28时，它们的最小公倍数为" + i);
				break;
			}
		}
	}
}
