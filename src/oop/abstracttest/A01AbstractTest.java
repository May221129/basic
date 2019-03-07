package oop.abstracttest;

/**
 * 抽象类abstract的练习：
 * 	abstract可以用来修饰类、方法（修饰方法是有限制：①是可以被重写的方法；②没有static修饰的方法）
 */
public class A01AbstractTest {
	public static void main(String[] args) {
		Employee e1 =  new Manager();
		e1.work();
		
		Employee e2 = new CommonEmployee();
		e2.work();
	}
}

// 1.用abstract修饰类：
abstract class Employee{//雇员
//	属性：
	protected String name;
	protected int id;
	protected double salary;
//	构造器：
	public Employee(){
		super();
	}
	// 2.用abstract修饰方法：
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

class Manager extends Employee{//经理
//	属性：
	private double bonus;//奖金
//	构造器：
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
//	方法：
	public void work(){
		System.out.println("经理做的主要工作是领导好下属");
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
		System.out.println("普通员工的主要工作就是生产产品");
	}
}