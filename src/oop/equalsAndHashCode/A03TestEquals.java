package oop.equals;

import java.util.Date;

/**
 * equals����ϰ��-3
 * 1.�Ƚ��Լ���д��equals()���� �� ϵͳ�ṩ��equals()��������д��
 * 2.�����˽�"=="��equals()����������
 * 3.�Լ���дtoString()������ϵͳ�ṩ��toString()��������д��
 */
public class A03TestEquals {
	public static void main(String[] args) {
		MyDate m1 = new MyDate(20,4,2017);
		MyDate m2 = new MyDate(20,4,2017);
//		== ��ʹ�ã��Ƚϵ����������õĵ�ֵַ
		if(m1 == m2){
			System.out.println("m1 == m2");
		}else{
			System.out.println("m1 != m2");
		}
//		equals()������ʹ�ã��Ƚϵ�����������������Ƿ���ȫ���
		if(m1.equals(m2)){
			System.out.println("m1 is equals to m2");
		}else{
			System.out.println("m1 is not equals m2");
		}
		
//		toString()������ʹ�ã�
		System.out.println(m1.toString());
//��û����д��ʱ��������ǵ�ֵַ��equals.MyDate@1e933c07;��д֮�󣬷��ص��Ƕ���m1��������Ϣ
		System.out.println(m1);
//��û����д��ʱ��������ǵ�ֵַ��equals.MyDate@1e933c07����д֮�󣬷��ص��Ƕ���m1��������Ϣ
		
		Date d = new Date();//���Date�������util���µ�
		System.out.println(d);//��������ʵ������--��ǰ��ʱ�䣺Thu Apr 20 18:08:56 GMT+08:00 2017
	}
}
class MyDate{
//	���ԣ�
	protected int day;
	protected int month;
	protected int year;
//	��������
	public MyDate(){
		
	}
	public MyDate(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}
//	������
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
	 * �ֶ���дObject��equals�������Ƚ�����MyDete����������Ƿ���ȫ��ͬ����ͬ�򷵻�true��
	 * �����Լ�д�Ĳ������ܣ�����и�������MaDate������������Ժ�MaDate��һ���ģ���������Լ�д�ķ�����
	 * �ͻᱻ�ж�Ϊ������������ͬ�ģ�����ʵ�ǲ���ͬ�ġ���������������ʱ��һ����ϵͳ�Ѿ�д�õģ���Ϊ���ܡ�
	 */
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}
		if(obj instanceof MyDate){
			MyDate md = (MyDate)obj;
			return this.year == md.year && this.month == md.month && this.day == md.day;
			//�Ƚϵ�˳��͹�������ε�˳��һ�������ڱȽϵĽ��������Ӱ��
		}else{
			return false;
		}
	}
	
	/**
	 * �ֶ���дObject���е�toString()������һ������£���д���ǽ���������Խ��з���
	 * public String toString(){
	 *		return "MyDate [day = "+ day + ", month = "+ month + ",year = " + year + "]";
	 *	}
	 * toString()����һ������²����Լ���д������ϵͳ���еļ��ɣ�Source ==> Generate toString()
	 */
	@Override
	public String toString() {
		return "MyDate [day=" + day + ", month=" + month + ", year=" + year + "]";
	}
}