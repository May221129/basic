package oop.supertest;
//类的继承-super-练习题-测试Account
public class testAccouont {
	public static void main(String[] args) {
		
		Account account = new Account(1122, 20000,0.045 );//创建对象
		
		account.withdiaw(30000);//取30000
		
		System.out.println("您的账户余额为： " + account.getBalance());
		System.out.println();
		
		account.withdiaw(2500);//取3000
		account.deposit(3000);//存2500
		System.out.println("您的账户余额为： " + account.getBalance());
		
		System.out.println("月利率为： " + account.getMonthlyInterest());
	}

}
