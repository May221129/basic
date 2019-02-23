package ifelse_switchcase.a01ifelse;
/*
 * //分支结构：if else的练习
 * 比较任意三个数的大小。
 */
import java.util.Scanner;
public class TestIfElse1 {
	public static void main(String[] args){
		Scanner l = new Scanner(System.in);
		int a = l.nextInt();
		int b = l.nextInt();
		int c = l.nextInt();
		if(a > b && a > c && b > c){
			System.out.println("a>b>c");
		}else{
			if(a > b && a > c && c > b ){
				System.out.println("a>c>b");
			}else if( b > a && b > c && a > c ){
				System.out.println("b>a>c");
			}else if( b > a && b > c && c > a){
				System.out.println("b>c>a");
			}else if( c > a && c > b && a > b){
				System.out.println("c>a>b");
			}else if( c > a && c > b && b > a){
				System.out.println("c>b>a");
			}else{
				System.out.println("a=b=c");
			}
		}
		

	}
}
