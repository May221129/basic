package scanner;

import java.util.Scanner;

/**
 * Scanner类的用法，键盘输入值。
 */
public class TestScanner{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("请输入小明的期末成绩：（分）");
		int grade = s.nextInt();
		if(grade > 100 || grade < 0){
			System.out.println("您的输入有误，请重新输入范围在1-100以内的数值");
			
			grade = s.nextInt();
			if(grade > 100 || grade < 0){
				System.out.println("您的输入有误，请重新输入范围在1-100以内的数值");
				
				grade = s.nextInt();
				if(grade > 100 || grade < 0){
					System.out.println("您的输入有误，请重新输入范围在1-100以内的数值");
					
					grade = s.nextInt();
					if(grade > 100 || grade < 0){
						System.out.println("您的输入有误，请重新输入范围在1-100以内的数值");
						
						grade = s.nextInt();
						if(grade > 100 || grade < 0){
							System.out.println("您的输入有误，请重新输入范围在1-100以内的数值");
							
							grade = s.nextInt();
							if(grade > 100 || grade < 0){
								System.out.println("您的输入有误，请重新输入范围在1-100以内的数值");
							}else{
								if(grade == 100){
									System.out.println("奖励一辆BMW");
								}else if(80 < grade && grade <= 99){
									System.out.println("奖励一台iphone5s");
								}else if( 60 < grade && grade <= 80 ){
									System.out.println("奖励一本参考书");
								}else {
									System.out.println("什么奖励也没有");
								}
							}
						}else{
							if(grade == 100){
								System.out.println("奖励一辆BMW");
							}else if(80 < grade && grade <= 99){
								System.out.println("奖励一台iphone5s");
							}else if( 60 < grade && grade <= 80 ){
								System.out.println("奖励一本参考书");
							}else {
								System.out.println("什么奖励也没有");
							}
						}
					}else{
						if(grade == 100){
							System.out.println("奖励一辆BMW");
						}else if(80 < grade && grade <= 99){
							System.out.println("奖励一台iphone5s");
						}else if( 60 < grade && grade <= 80 ){
							System.out.println("奖励一本参考书");
						}else {
							System.out.println("什么奖励也没有");
						}
					}
				}else{
					if(grade == 100){
						System.out.println("奖励一辆BMW");
					}else if(80 < grade && grade <= 99){
						System.out.println("奖励一台iphone5s");
					}else if( 60 < grade && grade <= 80 ){
						System.out.println("奖励一本参考书");
					}else {
						System.out.println("什么奖励也没有");
					}
				}
			}else{
				if(grade == 100){
					System.out.println("奖励一辆BMW");
				}else if(80 < grade && grade <= 99){
					System.out.println("奖励一台iphone5s");
				}else if( 60 < grade && grade <= 80 ){
					System.out.println("奖励一本参考书");
				}else {
					System.out.println("什么奖励也没有");
				}
			}
		}else{
			if(grade == 100){
				System.out.println("奖励一辆BMW");
			}else if(80 < grade && grade <= 99){
				System.out.println("奖励一台iphone5s");
			}else if( 60 < grade && grade <= 80 ){
				System.out.println("奖励一本参考书");
			}else {
				System.out.println("什么奖励也没有");
			}
		}	

		Scanner j = new Scanner (System.in);
		System.out.println("请输入一个数字");
		int a = j.nextInt();
		System.out.println("请再次输入一个数字");
		int b = j.nextInt();
		int add = a + b;
		//System.out.println(add);
		System.out.println("您输入的数字" + a + "与" + b +"的和为" + add);
	}
}