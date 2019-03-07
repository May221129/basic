package array.fortest;
//for循环的练习：
import java.util.Scanner;

public class TestFor5 {
	public static void main(String[] args){
//		键盘输入一个数值，判断该数值是否为质数：
		boolean b = true;
		int a = new Scanner(System.in).nextInt();
	    for(int j = 2; j < a; j++){
	    	if((a % j) == 0){
	    		b = false;
	    		break;
	    	}
	    }
	    if (b) {
	    	System.out.println(a + "是质数");
	    }else{
	    	System.out.println(a + "不是质数");
	    }
	    
	    System.out.println("------------------------------------");

//		判断1-100之间有多少个质数，分别是什么：
		int count = 0;
		for(int i = 2; i <= 100; i++){
			boolean c = true;
			for(int j = 2; j < i; j++){
				if(i % j == 0){
					c = false;
					break;
				}
			}
			if(c){	
				System.out.println(i);	
				count++;
			}
		}
		System.out.println("1-100之间的质数有" + count + "个。");
	}
}
