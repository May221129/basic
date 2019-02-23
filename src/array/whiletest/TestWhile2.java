package array.whiletest;
//循环的练习题：while:从键盘输入不确定的正数，并判断输入的正数和负数的个数，输入为0时结束程序。
import java.util.Scanner;
public class TestWhile2 {
	public static void main(String[] args){
		Scanner ll = new Scanner(System.in);
		int count1 = 0;
		int count2 = 0;
		int i = ll.nextInt();
		while(i != 0){
			if(i > 0){
				count1++;
			}else{
				count2++;
			}
			i = ll.nextInt();	
		}
		System.out.println("您输入的数字中，正数有" + count1 + "个，" + "负数有" + count2 + "个。");
	}

}
