package oop.wrapperclass;
//关于包装类的练习：利用集合Vector做和学生成绩相关的运算：
import java.util.Scanner;
import java.util.Vector;//集合，和数组对比，优势在于储存的内容个数不做初始化限制
public class TestScore {
	public static void main(String[] args) {
//1、创建Scanner的对象，提示从键盘输入学生的成绩（以负数代表输入结束）：
		Scanner s = new Scanner(System.in);
		System.out.println("请输入学生的成绩，以负数代表输入结束");	
//2、创建一个无线循环，从键盘依次获取学生的成绩，并填入由Vector = new Vector()创建的对象v中：
		Vector v = new Vector();//集合，用于储存学生成绩
		int max = 0;//记录最高分
		for(;;){//无线循环
			int score = s.nextInt();//键盘输入学生成绩
			if(score < 0){
				break;//以负数代表输入结束
			}
//3、求出输入的正数的成绩中的最高分：
			if(max < score){
				max = score;
			}
			Integer score1 = new Integer(score);//将基本数据类型转成包装类
			v.addElement(score1);//录入集合v中
		}
//4、通过循环，判断并输出每个成绩的等级：与最高分相差10分以内==>A; 20分以内==>B; 30分以内==>C; 其他==> D;
		for(int i = 0; i < v.size(); i++){
//5、通过v.elementAt(i)依次获取之前填入v中的元素
			Integer score = (Integer)v.elementAt(i);
			char level;//用于接收等级
			if(max - score <= 10){
				level = 'A';
			}else if(max - score <= 20){
				level = 'B';
			}else if(max - score <= 30){
				level = 'C';
			}else{
				level = 'D';
			}
			System.out.println("学生成绩为： " + score + ", 等级为： " + level);
		}
	}
}
