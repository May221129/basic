package ifelse_switchcase.a01ifelse;
//��֧�ṹ��if else����ϰ
import java.util.Scanner;
public class TestIfElse {
	public static void main(String[] args){
		System.out.println("������С���ĳɼ���ȡֵ��ΧΪ0~100����ֵ��");
		Scanner xiaoMing = new Scanner(System.in);
		int grade = xiaoMing.nextInt();
		while(grade > 100 || grade < 0){
			System.out.println("ݔ�����`��Ո����ݔ��ȡֵ��Χ��0~100����ֵ��");
			grade = xiaoMing.nextInt();
		}
		
		
		while(grade <= 100 && grade >= 0){
		    if(grade == 100){
		    	System.out.println("����һ̨mac��");
		    	break;
		    }else if(grade >= 80 && grade <= 99){
		    	System.out.println("����һ̨Ipone��");
		    	break;
		    }else if(grade >= 60 && grade < 80){
		    	System.out.println("����һ���κ󸨵��顣");
		    	break;
		    }else{
		    	System.out.println("ʲô������û�С�");
		    	break;
		    }
//		    grade = xiaoMing.nextInt();
//			while(grade > 100 || grade < 0){
//				System.out.println("ݔ�����`��Ո����ݔ��ȡֵ��Χ��0~100����ֵ��");
//				grade = xiaoMing.nextInt();
//			}
		}
	}
}
