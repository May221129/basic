package ifelse_switchcase.a02switchcase;
/*
 //��֧�ṹ��Switch case ����ϰ
 ����ѧ���ɼ����ڵ���60�ֵ���� �ϸ� ������60�ֵ���� ���ϸ�
 ������ʵ�ʳɼ�/10��int���ͣ���ȥС����
 */
import java.util.Scanner;
public class TestSwitchCase3 {
	public static void main(String[] args){
		System.out.println("���������ķ���");
		Scanner person = new Scanner(System.in);
		int grade = person.nextInt();
		grade = grade/10;
		switch(grade){
		case 0:
			System.out.println("���ϸ�");
			break;
		case 1:
			System.out.println("���ϸ�");
			break;
		case 2:
			System.out.println("���ϸ�");
			break;
		case 3:
			System.out.println("���ϸ�");
			break;
		case 4:
			System.out.println("���ϸ�");
			break;
		case 5:
			System.out.println("���ϸ�");
			break;
		case 6:
			System.out.println("�ϸ�");
			break;
		case 7:
			System.out.println("�ϸ�");
			break;
		case 8:
			System.out.println("�ϸ�");
			break;
		case 9:
			System.out.println("�ϸ�");
			break;
		case 10:
			System.out.println("�ϸ�");
			break;
		default:
			System.out.println("������������,������0-100");
			break;
		}	
	}

}
