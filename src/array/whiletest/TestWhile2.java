package array.whiletest;
//ѭ������ϰ�⣺while:�Ӽ������벻ȷ�������������ж�����������͸����ĸ���������Ϊ0ʱ��������
import java.util.Scanner;
public class TestWhile2 {
	public static void main(String[] args){
		Scanner ll = new Scanner(System.in);
		int count1 = 0;
		int count2 = 0;
		int i = ll.nextInt();
		while(i != 0){
			if(i > 0){
				count1++;
			}else{
				count2++;
			}
			i = ll.nextInt();	
		}
		System.out.println("������������У�������" + count1 + "����" + "������" + count2 + "����");
	}

}
