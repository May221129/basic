package oop.interfacetest;
/**
 * �Լ�д�ģ�
 * ���̶��󡢼̳С���װ����̬����������Ӧ�ã���д����ϵͳ��ʵ�ֲ�ͬԱ������̬���İ��·��Ź��ʣ�������³���ĳ��Employee��������գ���������100Ԫ��
 */
import java.util.Scanner;

public class A03Exercise_Employee2 {
	public static void main(String[] args) {
		
		Employee[] emp =  new Employee[2];
		emp[0] = new Manager("����",new MyDate(29,4,1990));
		emp[1] = new CommonEmployee("����", new MyDate(22, 11, 1980));
		
		Scanner s = new Scanner(System.in);
		System.out.println("�����뵱ǰ���·ݣ�");
		int month = s.nextInt();
		for(int i = 0; i < emp.length; i++){
			if(emp[i].getBirthday().getMonth() == month){
				System.out.println(emp[i].toString() + " ע���������գ������ټ�100����");
			}else{
				System.out.println(emp[i].toString());
			}
		}
	}
}

//�����ࣺ
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

//�չ��ࣺ
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

//��Ա�ࣺ
class Employee{
//	���ԣ�
	protected String name;//����
	protected MyDate birthday;//����
	protected double monthlySalary;//����
//	��������
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
//	������
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
		return name + ": " + birthday + " ; " + monthlySalary + "Ԫ ; ";
	}
}

//�����ࣺ
class MyDate{
//	���ԣ�
	public int day;//��
	public int month;//��
	public int year;//��
//	��������
	public MyDate(){
		super();
	}
	public MyDate(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}
//	������
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
		return year + "��" + month +"��" + day + "��";
	}
}