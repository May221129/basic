package recursion;

/**
 * ���õݹ�ʵ��N�Ľ׳ˣ���5�Ľ׳�Ϊ5*4*3*2*1
 * ���ģ�5�Ľ׳���5*4�Ľ׳�
 */
public class Factorial {
	public static void main(String[] args) {
		showFactorialByN(6);
	}
	
	public static void showFactorialByN(int target){
		if(target > 0){
			System.out.println(getFactorialByN(target));
		}else{
			System.out.println("��������������");
		}
	}
	
	public static int getFactorialByN(int target){
		if(target != 1){
			return target * getFactorialByN(target - 1);
		}else{
			return 1;
		}
	}
}
