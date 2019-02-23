package array.fortest;
//for循环的练习题:
import org.junit.Test;

public class TestFor {
	@Test
	public void test1(){//求：0~100之间所有奇数的和
		int sum = 0;
		for(int i = 1; i <= 100; i += 2){
			//sum = i;
			sum += i;
		}
		System.out.println("0~100之间所有奇数的和为" + sum);
	}
	
	@Test
	public void test2(){//求：0~100之间所有奇数的和
		int sum = 0;
		for(int i = 0; i <= 100; i++){
			if(i % 2 == 1){
				sum += i;	
			}	
		}
		System.out.println("0~100之间所有奇数的和为" + sum);
	}
	
	@Test
	public void test3(){//求：100以内偶数的总和及偶数的总个数
		int sum = 0;
		int count = 0;
		for(int i = 0; i < 100; i++){
			if(i % 2 == 0){
				sum += i;
				count++; 
			}
		}
		System.out.println("100以内偶数的总和为" + sum);
		System.out.println("100以内的偶数总共有" + count + "个");
	}
	
	@Test
	public void test4(){//求：1~100之间所有是7倍数的整数的个数、1~100之间所有是7倍数的整数的总和
		int count = 0;
		int sum = 0;
		for(int i = 1; i <= 100; i++){
			if(i % 7 == 0){
				count ++;
				sum += i;
			}
		}
		System.out.println("1~100之间所有是7倍数的整数的个数有" + count + "个");
		System.out.println("1~100之间所有是7倍数的整数的总和为" + sum );
	}
}
