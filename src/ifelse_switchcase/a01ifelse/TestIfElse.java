package ifelse_switchcase.a01ifelse;
//分支结构：if else的练习
import java.util.Scanner;
public class TestIfElse {
	public static void main(String[] args){
		System.out.println("请输入小明的成绩，取值范围为0~100的数值：");
		Scanner xiaoMing = new Scanner(System.in);
		int grade = xiaoMing.nextInt();
		while(grade > 100 || grade < 0){
			System.out.println("輸入有誤，請重新輸入取值范围在0~100的数值：");
			grade = xiaoMing.nextInt();
		}
		
		
		while(grade <= 100 && grade >= 0){
		    if(grade == 100){
		    	System.out.println("奖励一台mac。");
		    	break;
		    }else if(grade >= 80 && grade <= 99){
		    	System.out.println("奖励一台Ipone。");
		    	break;
		    }else if(grade >= 60 && grade < 80){
		    	System.out.println("奖励一本课后辅导书。");
		    	break;
		    }else{
		    	System.out.println("什么奖励都没有。");
		    	break;
		    }
//		    grade = xiaoMing.nextInt();
//			while(grade > 100 || grade < 0){
//				System.out.println("輸入有誤，請重新輸入取值范围在0~100的数值：");
//				grade = xiaoMing.nextInt();
//			}
		}
	}
}
