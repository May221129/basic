package ifelse_switchcase.a01ifelse;
/*
 * Scanner类，键盘输入，if else 的练习题：
 * 看男生身高，存款，外貌，这三个条件，做判断。
 */
import java.util.Scanner;
public class TestIfElse2 {
	public static void main(String[] args){
	Scanner person = new Scanner(System.in);
	System.out.println("对方的身高为多少厘米，请输入数字");
	int tall = person.nextInt();
	System.out.println("对方拥有多少万的存款，请输入数字");
	int money = person.nextInt();
	System.out.println("对方是否帅气，帅气请输入 帅，不帅请输入 土");
	String waimao = person.next();
	if(tall > 180 && money > 1000 && "帅".equals(waimao) ){
		System.out.println("我一定要嫁给他！！！");
	}else if(tall > 180 || money > 1000 || "是".equals(waimao)){
		System.out.println("嫁吧，比上不足比下有余。");
	}else{
		System.out.println("不嫁"); 
		}
	}
}