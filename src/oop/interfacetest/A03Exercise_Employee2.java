package oop.interfacetest;
/**
 * 自己写的：
 * 巩固对象、继承、封装、多态、抽象的组合应用，编写工资系统，实现不同员工（多态）的按月发放工资，如果当月出现某个Employee对象的生日，则工资增加100元。
 */
import java.util.Scanner;

public class A03Exercise_Employee2 {
	public static void main(String[] args) {
		
		Employee[] emp =  new Employee[2];
		emp[0] = new Manager("李雷",new MyDate(29,4,1990));
		emp[1] = new CommonEmployee("张三", new MyDate(22, 11, 1980));
		
		Scanner s = new Scanner(System.in);
		System.out.println("请输入当前的月份：");
		int month = s.nextInt();
		for(int i = 0; i < emp.length; i++){
			if(emp[i].getBirthday().getMonth() == month){
				System.out.println(emp[i].toString() + " 注：本月生日，工资再加100大洋！");
			}else{
				System.out.println(emp[i].toString());
			}
		}
	}
}

//经理类：
class Manager extends Employee{
	public Manager(){
		super();
	}
	public Manager(String name, MyDate birthday){
		super(name, birthday);
		monthlySalary = 10000;
	}
	public Manager(String name, MyDate birthday, double monthlySalary){
		super(name, birthday, monthlySalary);
	}
}

//普工类：
class CommonEmployee extends Employee{
	public CommonEmployee(){
		super();
	}
	public CommonEmployee(String name, MyDate birthday){
		super(name, birthday);
		monthlySalary = 5000;
	}
	public CommonEmployee(String name, MyDate birthday, double monthlySalary){
		super(name, birthday, monthlySalary);
	}	
}

//雇员类：
class Employee{
//	属性：
	protected String name;//姓名
	protected MyDate birthday;//生日
	protected double monthlySalary;//工资
//	构造器：
	public Employee(){
		super();
	}
	public Employee(String name, MyDate birthday){
		this.name = name;
		this.birthday = birthday;
	}
	public Employee(String name, MyDate birthday, double monthlySalary){
		this.name = name;
		this.birthday = birthday;
		this.monthlySalary = monthlySalary;
	}
//	方法：
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MyDate getBirthday() {
		return birthday;
	}
	public void setBirthday(MyDate birthday) {
		this.birthday = birthday;
	}
	public double getMonthlySalary() {
		return monthlySalary;
	}
	public void setSalary(double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	@Override
	public String toString() {
		return name + ": " + birthday + " ; " + monthlySalary + "元 ; ";
	}
}

//日期类：
class MyDate{
//	属性：
	public int day;//日
	public int month;//月
	public int year;//年
//	构造器：
	public MyDate(){
		super();
	}
	public MyDate(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}
//	方法：
	public int getDay(){
		return day;
	}
	public int getMonth(){
		return month;
	}
	public int getYear(){
		return year;
	}
	public void setDay(int day){
		this.day = day;
	}
	public void setMonth(int month){
		this.month = month;
	}
	public void setYear(int year){
		this.year = year;
	}
	public String toString(){
		return year + "年" + month +"月" + day + "日";
	}
}