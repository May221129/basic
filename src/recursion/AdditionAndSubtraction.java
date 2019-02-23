package recursion;

import java.util.Scanner;
import org.junit.Test;

/**
 * �ݹ����ϰ���ֱ�ʹ��forѭ���͵ݹ���ʵ�� �ۼӡ��۳�
 */
public class AdditionAndSubtraction {
	public static void main(String[] args) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			int x = scanner.nextInt();
			
			System.out.println(getProduct3(x));
			
			System.out.println(add(x));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
	
	/**
	 * �ۼӣ��õݹ�ķ�ʽ����
	 * �磺x=5
	 * 	5+4���ۼӣ�4���ۼ�=4+3���ۼӣ�3���ۼ�=3+2���ۼӣ�2���ۼ�=2+1��
	 */
	public static int add(int x){
		return (x > 1) ? (x + add(x-1) ) : ((x < 0) ? 0 : 1 );
	}
	
	/**
	 * �ۼӣ���ѭ���ķ�ʽ������
	 */
	@Test
	public void getAdd(){
		int j = 0;
		for(int i = 0; i <= 3; i++){
			j += i;
		}
		System.out.println(j);
	}
	
	/**
	 * �õݹ���N�Ľײ㣬�磺�ͻ�����5�����5*4*3*2*1�Ļ���==>�з���ֵ�ĵݹ�
	 * 1.�ݹ�����Լ����Լ���
	 * 2.�ݹ�ĳ��ڣ�x>1
	 */
	public static int getProduct3(int x){
		return (x > 1) ? (x * getProduct3(x-1) ) : ((x < 0) ? 0 : 1 );
	}
	
	/**
	 * ���ʵ�ַ�����ƵĲ��ã�ÿ�����������Լ������壬x�Ǳ����������i������x�ı��������ã�x��������¼�˻������׻��ҡ�
	 */
	@Test
	public void getProduct(){//��N�Ľײ㣬�磺�ͻ�����5�����5*4*3*2*1�Ļ���
		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		for(int i = (x-1); i > 0; i-- ){
			x = x * i;
		}
		System.out.println(x);
	}
	
	@Test
	public void getProduct2(){//��N�Ľײ㣬�磺�ͻ�����5�����5*4*3*2*1�Ļ���
		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		int sum = 1;
		while(x > 0){
			sum *= x;
			x--;
		}
		System.out.println(sum);
	}
}
