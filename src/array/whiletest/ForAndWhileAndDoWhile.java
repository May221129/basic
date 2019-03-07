package array.whiletest;
//3种循环类型的运用
public class ForAndWhileAndDoWhile {
	public static void main(String[] args){
		for( int i = 0; i < 10; i ++){
			System.out.println("for循环：" + i);
		}
		
		System.out.println("------------------------------------");
		
		int i = 0;
		while(i < 10){
			System.out.println("while循环：" + i);
			i ++;
		}
		
		System.out.println("------------------------------------");
		
		int d = 0;
		do{
			System.out.println("do-while循环：" + d);
			d ++;
		}while(d < 10);
	}
}
 