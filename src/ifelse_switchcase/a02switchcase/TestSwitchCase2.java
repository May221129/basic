package ifelse_switchcase.a02switchcase;
//��֧�ṹ��Switch case ����ϰ
import java.util.Scanner;
public class TestSwitchCase2 {
	public static void main(String[] args){
		Scanner l = new Scanner(System.in);
		int grade = l.nextInt();
		if(grade < 0){
			System.out.println("������������");
			return;
		}
		grade = grade / 10;
		switch(grade){
		case 10:
		case 9:
			System.out.println(" A");
			break;
		case 8:
		case 7:
			System.out.println("B");
			break;
		case 6:
			System.out.println("C");
			break;
		case 5:
		case 4:
		case 3:
		case 2:
		case 1:
		case 0:
			System.out.println("D");
			break;
		default:
			System.out.println("������������");
		}
	}

}
