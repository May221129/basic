package oop.interfacetest;
/**
 * 参照老师的方法写的：
 * 巩固对象、继承、封装、多态、抽象的组合应用，编写工资系统，实现不同员工（多态）的按月发放工资，如果当月出现某个Employee对象的生日，则工资增加100元。
 */

import java.util.Scanner;

public class A03Exercise_Employee1 {
	public static void main(String[] args) {
		Employee2[] emp2 = new Employee2[3];
		emp2[0] = new SalariedEmployee("张三", 1001, new MyDate2(1990,1,2), 10000);
		emp2[1] = new SalariedEmployee("李四", 1002, new MyDate2(1990,4,20), 10000);
		emp2[2] = new HourlySalary("王五", 1003, new MyDate2(1880,4,7), 100, 8);
		
		System.out.println("请输入当前月份，以便获知哪些员工在本月生日！");
		Scanner s2 = new Scanner(System.in);
		int month2 = s2.nextInt();
		
		for(int i = 0; i < emp2.length; i++){
			if(emp2[i].getBirthday().getMonth() == month2){
				System.out.println(emp2[i].toString() + "; 本月生日，加薪100元！");
			}else{
				System.out.println(emp2[i].toString());
			}
		}
	}
}

/**
 * 实现按月计算员工工资：
 */
class SalariedEmployee extends Employee2{
	private double monthlySalary;
	public SalariedEmployee(){
		super();
	}
	public SalariedEmployee(String name, int number, MyDate2 birthday, double monthlySalary){
		super(name, number, birthday);
		this.monthlySalary = monthlySalary;
	}
	@Override
	public double earnings() {//月工资
		return monthlySalary;
	}
	public String toString(){
		return this.getName() + ": " + this.getNumber() + ", " + this.getBirthday().toDateString() + ", " + this.earnings();
	}
}
/**
 * 实现按小时计算员工工资：
 */
class HourlySalary extends Employee2{
	private int hour;
	private double wage;
	
	public HourlySalary(){
		super();
	}
	public HourlySalary(String name, int number, MyDate2 birthday, double wage, int hour){
		super(name, number, birthday);
		this.wage = wage;
		this.hour = hour;
	}
	@Override
	public double earnings() {//钟点工资
		return wage * hour;
	}
	public String toString(){
		return this.getName() + ": " + this.getNumber() + ", " + this.getBirthday().toDateString() + ", " + this.earnings();
	}
}

/**
 * 抽象类：雇员：
 */
abstract class Employee2{
//	属性：
	private String name;
	private int number;
	private MyDate2 birthday;
//	构造器：
	public Employee2(){
		super();
	}
	public Employee2(String name, int number, MyDate2 birthday){
		this.name = name;
		this.number = number;
		this.birthday = birthday;
	}
//	方法：
	public abstract double earnings();//抽象方法:收入

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public MyDate2 getBirthday() {
		return birthday;
	}
	public void setBirthday(MyDate2 birthday) {
		this.birthday = birthday;
	}
}

/**
 * 时间类：
 */
class MyDate2{
//	属性：
	private int year;
	private int month;
	private int day;
//	构造器：
	public MyDate2(){
		super();
	}
	public MyDate2(int year, int month, int day){
		this.year = year;
		this.month = month;
		this.day = day;
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
	public String toDateString(){
		return year + "年" + month + "月" + day + "日";
	}
}