package oop.equals;

import java.util.Date;

/**
 * equals的练习题-3
 * 1.比较自己重写的equals()方法 和 系统提供的equals()方法的重写；
 * 2.初步了解"=="和equals()方法的区别；
 * 3.自己重写toString()方法和系统提供的toString()方法的重写；
 */
public class A03TestEquals {
	public static void main(String[] args) {
		MyDate m1 = new MyDate(20,4,2017);
		MyDate m2 = new MyDate(20,4,2017);
//		== 的使用：比较的是两个引用的地址值
		if(m1 == m2){
			System.out.println("m1 == m2");
		}else{
			System.out.println("m1 != m2");
		}
//		equals()方法的使用，比较的是两个对象的属性是否完全相等
		if(m1.equals(m2)){
			System.out.println("m1 is equals to m2");
		}else{
			System.out.println("m1 is not equals m2");
		}
		
//		toString()方法的使用：
		System.out.println(m1.toString());
//在没有重写的时候，输出的是地址值：equals.MyDate@1e933c07;重写之后，返回的是对象m1的属性信息
		System.out.println(m1);
//在没有重写的时候，输出的是地址值：equals.MyDate@1e933c07；重写之后，返回的是对象m1的属性信息
		
		Date d = new Date();//这个Date导入的是util包下的
		System.out.println(d);//输出对象的实体内容--当前的时间：Thu Apr 20 18:08:56 GMT+08:00 2017
	}
}
class MyDate{
//	属性：
	protected int day;
	protected int month;
	protected int year;
//	构造器：
	public MyDate(){
		
	}
	public MyDate(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}
//	方法：
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * 手动重写Object中equals方法，比较两个MyDete对象的属性是否完全相同，相同则返回true。
	 * 但是自己写的不够严密：如果有个对象，是MaDate类的子类且属性和MaDate是一样的，如果采用自己写的方法，
	 * 就会被判断为两个对象是相同的，但其实是不相同的。所以在做开发的时候，一般用系统已经写好的，更为严密。
	 */
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}
		if(obj instanceof MyDate){
			MyDate md = (MyDate)obj;
			return this.year == md.year && this.month == md.month && this.day == md.day;
			//比较的顺序和构造器入参的顺序不一样，对于比较的结果不会有影响
		}else{
			return false;
		}
	}
	
	/**
	 * 手动重写Object类中的toString()方法，一般情况下，重写都是将对象的属性进行返回
	 * public String toString(){
	 *		return "MyDate [day = "+ day + ", month = "+ month + ",year = " + year + "]";
	 *	}
	 * toString()方法一帮情况下不用自己从写，调用系统已有的即可：Source ==> Generate toString()
	 */
	@Override
	public String toString() {
		return "MyDate [day=" + day + ", month=" + month + ", year=" + year + "]";
	}
}