package oop.abstracttest;

/**
 * ������abstract����ϰ��
 * 	abstract�������������ࡢ���������η����������ƣ����ǿ��Ա���д�ķ�������û��static���εķ�����
 */
public class A01AbstractTest {
	public static void main(String[] args) {
		Employee e1 =  new Manager();
		e1.work();
		
		Employee e2 = new CommonEmployee();
		e2.work();
	}
}

// 1.��abstract�����ࣺ
abstract class Employee{//��Ա
//	���ԣ�
	protected String name;
	protected int id;
	protected double salary;
//	��������
	public Employee(){
		super();
	}
	// 2.��abstract���η�����
	public abstract void work();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + ", salary=" + salary + "]";
	}
}

class Manager extends Employee{//����
//	���ԣ�
	private double bonus;//����
//	��������
	public Manager(){
		super();
	}
	public Manager(String name, int id, double salary, double bonus){
		this();
		this.name = name;
		this.id = id;
		this.salary = salary;
		this.bonus = bonus;
	}
//	������
	public void work(){
		System.out.println("����������Ҫ�������쵼������");
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
}

class CommonEmployee extends Employee{
	public CommonEmployee(){
		super();
	}
	public CommonEmployee(String name, int id, double salary){
		this.name = name;
		this.id = id;
		this.salary = salary;
	}
	public void work(){
		System.out.println("��ͨԱ������Ҫ��������������Ʒ");
	}
}