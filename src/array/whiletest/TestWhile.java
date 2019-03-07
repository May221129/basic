package array.whiletest;
//while练习：100以内的偶数的输出。
public class TestWhile {
	public static void main(String[] args){
		int i = 0;
		int sum = 0;
		while(i < 100){
			if(i % 2 == 0){
				System.out.println(i);
				sum +=i;
			}
			i++;
		}
		System.out.println("100以内的偶数的总和为" + sum);
	}
}
