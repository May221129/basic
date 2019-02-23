package array.whiletest;
//无限循环：while:从键盘输入不确定的正数，并判断输入的正数和负数的个数，输入为0时结束程序。
import java.util.Scanner;
public class TestWhile1 {
	public static void main(String[] args){
		Scanner l = new Scanner(System.in);
		int a;
		int countb = 0;
		int countc = 0;
		//for(;;){
		while(true){
			a = l.nextInt();
			if(a > 0){
				countb++;
			}else if(a < 0){
				countc++;
			}else{
				break;
			}
		}
		System.out.println("您输入的数值中，正数有" + countb + "个；");
		System.out.println("您输入的数值中，负数有" + countc + "个；");
	}
}