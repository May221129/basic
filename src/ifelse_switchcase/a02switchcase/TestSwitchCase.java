package ifelse_switchcase.a02switchcase;
/*
 ��֧�ṹ��Switch case ����ϰ
 ʹ��switch��Сд���͵�char��תΪ��д��ֻת a,b,c,d,e,��������� other��
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
