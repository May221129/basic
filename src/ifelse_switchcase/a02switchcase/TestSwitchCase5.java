package ifelse_switchcase.a02switchcase;
/*
 * ��֧�ṹ��Switch case ����ϰ
 * �Ӽ�������ĳ���month��day,Ҫ��ͨ��������� ��������������ĵڼ��졣
 */
import java.util.Scanner;
public class TestSwitchCase5 {
	public static void main(String[] args){
		System.out.println("���������");
		Scanner s = new Scanner(System.in);
		int year = s.nextInt();
		System.out.println("�����뷶Χ��1-12֮����·�");
		int mouth = s.nextInt();
		System.out.println("�����뷶Χ������");
		int day = s.nextInt();
		
		boolean b;
		if(year % 100 ==0){
			if(year % 400 == 0){
				b = true;
				//System.out.println(year + "������");
			}else{
				b = false;
				//System.out.println(year + "��ƽ��");
			}
		}else{
			if(year % 4 == 0){
				b = true;
				//System.out.println(year + "������");
			}else {
				b = false;
				//System.out.println(year + "��ƽ��");
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
				System.out.println("������������");
		}
		System.out.println(year + "��" + mouth + "��" + day + "��" + "��" + year +"��ĵ�" + sum + "�졣");
	}

}

