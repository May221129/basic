package array.fortest;
//for循环练习：有关水仙花数（水仙花数（三位数内，如153=1*1*1+5*5*5+3*3*3））
public class TestFor2 {
	public static void main(String[] args){//求：1000以内所有的水仙花数的个数
		for(int i = 100; i < 1000; i++){
			int d = i / 100;
			int e = (i % 100) / 10;
			int f = i % 10;
			if(i == d*d*d + e*e*e + f*f*f){
				System.out.println("水仙花数有:" + i);
			} 
		}
		
		System.out.println("-----------------------------------------");
		
		for(int i = 100; i < 1000; i++){//求：100~999之间的水仙花数的个数
			int a = i / 100;
			int b = (i - (a * 100)) / 10;
			int c = i - (a * 100) - (b * 10);
			if(i == a*a*a + b*b*b + c*c*c){	
				System.out.println("100~999之间的水仙花数有" + i);
			}
		}
	}
}
