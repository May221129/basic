package ifelse_switchcase.a02switchcase;
//��֧�ṹ��Switch case ����ϰ
import java.util.Scanner;
public class TestSwitchCase1 {
	public static void main(String[] args){
		System.out.println("������һ������");
		Scanner l = new Scanner(System.in);
		int a = l.nextInt();
		int x = 100;
		switch(a){
			case 1:
				x += 5;
				break;
			case 2:
				x += 10;
				break;
			case 3:
				x += 16;
				break;
			default:
				x += 34;
				break;
		}
		System.out.println("��a=" + a +"ʱ��x=" + x );
	}

}
