package array.whiletest;
//����ѭ����while:�Ӽ������벻ȷ�������������ж�����������͸����ĸ���������Ϊ0ʱ��������
import java.util.Scanner;
public class TestWhile1 {
	public static void main(String[] args){
		Scanner l = new Scanner(System.in);
		int a;
		int countb = 0;
		int countc = 0;
		//for(;;){
		while(true){
			a = l.nextInt();
			if(a > 0){
				countb++;
			}else if(a < 0){
				countc++;
			}else{
				break;
			}
		}
		System.out.println("���������ֵ�У�������" + countb + "����");
		System.out.println("���������ֵ�У�������" + countc + "����");
	}
}