package array.whiletest;
//3��ѭ�����͵�����
public class ForAndWhileAndDoWhile {
	public static void main(String[] args){
		for( int i = 0; i < 10; i ++){
			System.out.println("forѭ����" + i);
		}
		
		System.out.println("------------------------------------");
		
		int i = 0;
		while(i < 10){
			System.out.println("whileѭ����" + i);
			i ++;
		}
		
		System.out.println("------------------------------------");
		
		int d = 0;
		do{
			System.out.println("do-whileѭ����" + d);
			d ++;
		}while(d < 10);
	}
}
 