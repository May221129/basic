package array.fortest;
//forѭ������ϰ��
import java.util.Scanner;

public class TestFor5 {
	public static void main(String[] args){
//		��������һ����ֵ���жϸ���ֵ�Ƿ�Ϊ������
		boolean b = true;
		int a = new Scanner(System.in).nextInt();
	    for(int j = 2; j < a; j++){
	    	if((a % j) == 0){
	    		b = false;
	    		break;
	    	}
	    }
	    if (b) {
	    	System.out.println(a + "������");
	    }else{
	    	System.out.println(a + "��������");
	    }
	    
	    System.out.println("------------------------------------");

//		�ж�1-100֮���ж��ٸ��������ֱ���ʲô��
		int count = 0;
		for(int i = 2; i <= 100; i++){
			boolean c = true;
			for(int j = 2; j < i; j++){
				if(i % j == 0){
					c = false;
					break;
				}
			}
			if(c){	
				System.out.println(i);	
				count++;
			}
		}
		System.out.println("1-100֮���������" + count + "����");
	}
}
