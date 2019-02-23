package oop.interfacetest;
/**
 * ������ʦ�ķ���д�ģ�
 * ���̶��󡢼̳С���װ����̬����������Ӧ�ã���д����ϵͳ��ʵ�ֲ�ͬԱ������̬���İ��·��Ź��ʣ�������³���ĳ��Employee��������գ���������100Ԫ��
 */

import java.util.Scanner;

public class A03Exercise_Employee1 {
	public static void main(String[] args) {
		Employee2[] emp2 = new Employee2[3];
		emp2[0] = new SalariedEmployee("����", 1001, new MyDate2(1990,1,2), 10000);
		emp2[1] = new SalariedEmployee("����", 1002, new MyDate2(1990,4,20), 10000);
		emp2[2] = new HourlySalary("����", 1003, new MyDate2(1880,4,7), 100, 8);
		
		System.out.println("�����뵱ǰ�·ݣ��Ա��֪��ЩԱ���ڱ������գ�");
		Scanner s2 = new Scanner(System.in);
		int month2 = s2.nextInt();
		
		for(int i = 0; i < emp2.length; i++){
			if(emp2[i].getBirthday().getMonth() == month2){
				System.out.println(emp2[i].toString() + "; �������գ���н100Ԫ��");
			}else{
				System.out.println(emp2[i].toString());
			}
		}
	}
}

/**
 * ʵ�ְ��¼���Ա�����ʣ�
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
	public double earnings() {//�¹���
		return monthlySalary;
	}
	public String toString(){
		return this.getName() + ": " + this.getNumber() + ", " + this.getBirthday().toDateString() + ", " + this.earnings();
	}
}
/**
 * ʵ�ְ�Сʱ����Ա�����ʣ�
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
	public double earnings() {//�ӵ㹤��
		return wage * hour;
	}
	public String toString(){
		return this.getName() + ": " + this.getNumber() + ", " + this.getBirthday().toDateString() + ", " + this.earnings();
	}
}

/**
 * �����ࣺ��Ա��
 */
abstract class Employee2{
//	���ԣ�
	private String name;
	private int number;
	private MyDate2 birthday;
//	��������
	public Employee2(){
		super();
	}
	public Employee2(String name, int number, MyDate2 birthday){
		this.name = name;
		this.number = number;
		this.birthday = birthday;
	}
//	������
	public abstract double earnings();//���󷽷�:����

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
 * ʱ���ࣺ
 */
class MyDate2{
//	���ԣ�
	private int year;
	private int month;
	private int day;
//	��������
	public MyDate2(){
		super();
	}
	public MyDate2(int year, int month, int day){
		this.year = year;
		this.month = month;
		this.day = day;
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
	public String toDateString(){
		return year + "��" + month + "��" + day + "��";
	}
}