package oop.supertest;
//类的继承-super-练习题-测试CheckAccount
public class testCheckAccount {
	public static void main(String[] args) {
		
		CheckAccount ca = new CheckAccount(1122, 20000, 0.045, 5000);
		
//怎么调用方法？？？==>用前面创建的ca，来调用子类中的方法，父类中的方法因为被子类继承了，所以也可以直接调用，如下面的ca.getOverdraft();
		ca.withdiaw(5000);//取5000
		System.out.println("当前账户余额为： " + ca.getBalance() + "; " + "可透支限额： " + ca.getOverdraft());
	
		ca.withdiaw(18000);//取18000
		System.out.println("当前账户余额为： " + ca.getBalance() + "; " + "可透支限额： " + ca.getOverdraft());
	
		ca.withdiaw(3000);//取3000
		System.out.println("账户余额为： " + ca.balance + "; " + "可透支限额： " + ca.getOverdraft());
	}
}