package oop.extendstest;
//类的继承练习题-1
//父类
public class Mankind {
	private int sex;
	private int salary;
	
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void manOrWomen(){
		if(sex == 1){
			System.out.println("man");
		}else if(sex == 0){
			System.out.println("women");
		}else{
			System.out.println("您的输入有误！");
		}
	}
	public void employeed(){
		if(salary == 0){
			System.out.println("no job");
		}else if(salary > 0){
			System.out.println("job");
		}else{
			System.out.println("您的输入有误！");
		}
	}
}
