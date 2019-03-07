package array.helloworld;
/**
 * 数组练习题：
 * 从键盘输入学生成绩，找出最高分，并输出学生成绩等级：
 * 成绩 >= 最高分-10分，等级为A;
 * 成绩 >= 最高分-20分，等级为B；
 * 成绩 >= 最高分-30分，等级为C；
 * 其余                                   等级为D；
 * 提示：先读入学生人数，根据人数创建int数组，存放学生成绩。
 * 1.创建Scanner的对象，并从键盘获取学生的个数n；
 * 2.根据输入的学生个数n，创建一个长度为n的int型数组；
 * 3.一次从键盘获取n个学生的成绩，并赋给相应的数组元素，并获取n个学生中的最高分；
 * 4.遍历学生成绩的数组，并根据学生成绩与最高分的差值，赋予相应的等级，并输出。
 */
import java.util.Scanner;
public class TestArray {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		System.out.println("请输入学生的个数");
		int score = s.nextInt();
		int[] j = new int[score];
		System.out.println("请依次输入学生的成绩");
		for(int i = 0; i < j.length; i++){
			j[i] = s.nextInt();
		}
		int max = 0;
		for(int i = 0; i < j.length; i++){
			max = ( j[ i ] > max) ? j[ i ] : max;
		}
		System.out.println("最高分为：" + max);
		String level;
		for(int i = 0; i < j.length; i++){
			if( max - j[i] <= 10){
				level = "A";
				//System.out.println(j[i] + "分，等级为A");
			}else if( max - j[i] <= 20){
				level = "B";
				//System.out.println(j[i] + "分，等级为B");
			}else if( max - j[i] <= 30){
				level = "C";
				//System.out.println(j[i] + "分，等级为C");
			}else{
				level = "D";
				//System.out.println(j[i] + "分，等级为D");
			}
			System.out.println("student" + i + " score is " + j[i] +"，grade is " + level);
		}
	}
}
