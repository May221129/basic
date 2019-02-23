package ifelse_switchcase.a02switchcase;
/*
 分支结构：Switch case 的练习
 使用switch把小写类型的char型转为大写。只转 a,b,c,d,e,其他的输出 other。
 */
import java.util.Scanner;
public class TestSwitchCase {
	public static void main(String[] args){
		Scanner l = new Scanner(System.in);
		String x = l.next();
		switch (x){
		case "a":
			System.out.println("A");
			break;
		case "b":
			System.out.println("B");
			break;
		case "c":
			System.out.println("C");
			break;
		case "d":
			System.out.println("D");
			break;
		case "e":
			System.out.println("E");
			break;
		default:
			System.out.println("other");
			break;
		}
	}

}
