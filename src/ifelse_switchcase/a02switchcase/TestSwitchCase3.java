package ifelse_switchcase.a02switchcase;
/*
 //分支结构：Switch case 的练习
 对于学生成绩大于等于60分的输出 合格 ，低于60分的输出 不合格。
 方法：实际成绩/10，int类型，舍去小数。
 */
import java.util.Scanner;
public class TestSwitchCase3 {
	public static void main(String[] args){
		System.out.println("请输入您的分数");
		Scanner person = new Scanner(System.in);
		int grade = person.nextInt();
		grade = grade/10;
		switch(grade){
		case 0:
			System.out.println("不合格");
			break;
		case 1:
			System.out.println("不合格");
			break;
		case 2:
			System.out.println("不合格");
			break;
		case 3:
			System.out.println("不合格");
			break;
		case 4:
			System.out.println("不合格");
			break;
		case 5:
			System.out.println("不合格");
			break;
		case 6:
			System.out.println("合格");
			break;
		case 7:
			System.out.println("合格");
			break;
		case 8:
			System.out.println("合格");
			break;
		case 9:
			System.out.println("合格");
			break;
		case 10:
			System.out.println("合格");
			break;
		default:
			System.out.println("您的输入有误,请输入0-100");
			break;
		}	
	}

}
