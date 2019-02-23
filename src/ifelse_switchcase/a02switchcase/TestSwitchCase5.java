package ifelse_switchcase.a02switchcase;
/*
 * 分支结构：Switch case 的练习
 * 从键盘输入某年的month和day,要求通过程序输出 输入日期是这年的第几天。
 */
import java.util.Scanner;
public class TestSwitchCase5 {
	public static void main(String[] args){
		System.out.println("请输入年份");
		Scanner s = new Scanner(System.in);
		int year = s.nextInt();
		System.out.println("请输入范围在1-12之间的月份");
		int mouth = s.nextInt();
		System.out.println("请输入范围在日期");
		int day = s.nextInt();
		
		boolean b;
		if(year % 100 ==0){
			if(year % 400 == 0){
				b = true;
				//System.out.println(year + "是闰年");
			}else{
				b = false;
				//System.out.println(year + "是平年");
			}
		}else{
			if(year % 4 == 0){
				b = true;
				//System.out.println(year + "是闰年");
			}else {
				b = false;
				//System.out.println(year + "是平年");
			}
		}

		int sum = 0;
		
		switch(mouth){
		
			case 12:
				sum += 31;
			case 11:
				sum += 30;
			case 10:
				sum += 31;
			case 9:
				sum += 30;
			case 8:
				sum += 31;
			case 7:
				sum += 30;
			case 6:
				sum += 31;
			case 5:
				sum += 30;
			case 4:
				sum += 31;
			case 3:
				if(b){
					sum += 29;
				}else{
					sum += 28;
				}
			case 2:
				sum += 31;
			case 1:
				sum += day;
				break;
			default:
				System.out.println("您的输入有误！");
		}
		System.out.println(year + "年" + mouth + "月" + day + "日" + "是" + year +"年的第" + sum + "天。");
	}

}

