package scanner;

import java.util.Scanner;

/**
 * Scanner����÷�����������ֵ��
 */
public class TestScanner{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("������С������ĩ�ɼ������֣�");
		int grade = s.nextInt();
		if(grade > 100 || grade < 0){
			System.out.println("���������������������뷶Χ��1-100���ڵ���ֵ");
			
			grade = s.nextInt();
			if(grade > 100 || grade < 0){
				System.out.println("���������������������뷶Χ��1-100���ڵ���ֵ");
				
				grade = s.nextInt();
				if(grade > 100 || grade < 0){
					System.out.println("���������������������뷶Χ��1-100���ڵ���ֵ");
					
					grade = s.nextInt();
					if(grade > 100 || grade < 0){
						System.out.println("���������������������뷶Χ��1-100���ڵ���ֵ");
						
						grade = s.nextInt();
						if(grade > 100 || grade < 0){
							System.out.println("���������������������뷶Χ��1-100���ڵ���ֵ");
							
							grade = s.nextInt();
							if(grade > 100 || grade < 0){
								System.out.println("���������������������뷶Χ��1-100���ڵ���ֵ");
							}else{
								if(grade == 100){
									System.out.println("����һ��BMW");
								}else if(80 < grade && grade <= 99){
									System.out.println("����һ̨iphone5s");
								}else if( 60 < grade && grade <= 80 ){
									System.out.println("����һ���ο���");
								}else {
									System.out.println("ʲô����Ҳû��");
								}
							}
						}else{
							if(grade == 100){
								System.out.println("����һ��BMW");
							}else if(80 < grade && grade <= 99){
								System.out.println("����һ̨iphone5s");
							}else if( 60 < grade && grade <= 80 ){
								System.out.println("����һ���ο���");
							}else {
								System.out.println("ʲô����Ҳû��");
							}
						}
					}else{
						if(grade == 100){
							System.out.println("����һ��BMW");
						}else if(80 < grade && grade <= 99){
							System.out.println("����һ̨iphone5s");
						}else if( 60 < grade && grade <= 80 ){
							System.out.println("����һ���ο���");
						}else {
							System.out.println("ʲô����Ҳû��");
						}
					}
				}else{
					if(grade == 100){
						System.out.println("����һ��BMW");
					}else if(80 < grade && grade <= 99){
						System.out.println("����һ̨iphone5s");
					}else if( 60 < grade && grade <= 80 ){
						System.out.println("����һ���ο���");
					}else {
						System.out.println("ʲô����Ҳû��");
					}
				}
			}else{
				if(grade == 100){
					System.out.println("����һ��BMW");
				}else if(80 < grade && grade <= 99){
					System.out.println("����һ̨iphone5s");
				}else if( 60 < grade && grade <= 80 ){
					System.out.println("����һ���ο���");
				}else {
					System.out.println("ʲô����Ҳû��");
				}
			}
		}else{
			if(grade == 100){
				System.out.println("����һ��BMW");
			}else if(80 < grade && grade <= 99){
				System.out.println("����һ̨iphone5s");
			}else if( 60 < grade && grade <= 80 ){
				System.out.println("����һ���ο���");
			}else {
				System.out.println("ʲô����Ҳû��");
			}
		}	

		Scanner j = new Scanner (System.in);
		System.out.println("������һ������");
		int a = j.nextInt();
		System.out.println("���ٴ�����һ������");
		int b = j.nextInt();
		int add = a + b;
		//System.out.println(add);
		System.out.println("�����������" + a + "��" + b +"�ĺ�Ϊ" + add);
	}
}