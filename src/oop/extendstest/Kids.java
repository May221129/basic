package oop.extendstest;
//类的继承练习题-1
public class Kids extends Mankind{
	public static void main(String[] args) {
		Kids someKid = new Kids();
		someKid.yearsOld = 26;
		someKid.printAge();
//		someKid.sex = 1; 父类中的私人属性，在子类中可以被获取，但是不能直接调用。
		someKid.setSex(0);
		someKid.setSalary(10000);
		someKid.manOrWomen();
		someKid.employeed();
	}
//	子类
//	属性、方法
	private int yearsOld;
	
	public int getYearsOld() {
		return yearsOld;
	}

	public void setYearsOld(int yearsOld) {
		this.yearsOld = yearsOld;
	}

	public void printAge(){
		System.out.println("yearsOld : " + yearsOld);
	}
	public void employeed(){//方法的重写：覆盖父类Mankind中的employeed方法
		System.out.println("Kids should study and no job.");
	}
}
