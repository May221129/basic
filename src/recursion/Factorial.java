package recursion;

/**
 * 利用递归实现N的阶乘，如5的阶乘为5*4*3*2*1
 * 核心：5的阶乘是5*4的阶乘
 */
public class Factorial {
	public static void main(String[] args) {
		showFactorialByN(6);
	}
	
	public static void showFactorialByN(int target){
		if(target > 0){
			System.out.println(getFactorialByN(target));
		}else{
			System.out.println("请输入正整数！");
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
