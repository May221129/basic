package ifelse_switchcase.a01ifelse;
/*
 * Scanner�࣬�������룬if else ����ϰ�⣺
 * ��������ߣ�����ò�����������������жϡ�
 */
import java.util.Scanner;
public class TestIfElse2 {
	public static void main(String[] args){
	Scanner person = new Scanner(System.in);
	System.out.println("�Է������Ϊ�������ף�����������");
	int tall = person.nextInt();
	System.out.println("�Է�ӵ�ж�����Ĵ�����������");
	int money = person.nextInt();
	System.out.println("�Է��Ƿ�˧����˧�������� ˧����˧������ ��");
	String waimao = person.next();
	if(tall > 180 && money > 1000 && "˧".equals(waimao) ){
		System.out.println("��һ��Ҫ�޸���������");
	}else if(tall > 180 || money > 1000 || "��".equals(waimao)){
		System.out.println("�ްɣ����ϲ���������ࡣ");
	}else{
		System.out.println("����"); 
		}
	}
}