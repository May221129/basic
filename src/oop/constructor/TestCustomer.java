package oop.constructor;
//复习题：巩固构造器、this()的使用

public class TestCustomer {
	public static void main(String[] args) {
		Customer c = new Customer("Jane","Smith");
//		Account a = new Account(1000,2000,0.0123);显式赋值
//		匿名赋值 ==> 对于下面写的这个匿名赋值这一步骤，我并不熟练！！！
		c.setAccount(new Account(1000,2000,0.0123));//匿名赋值==>对于这一步骤，我并不熟练！！！
		Account account = c.getAccnount();//加写这一步，是为了最后打印的时候方便书写，不用写那么多
		account.withdraw(100);//如果没有上面那一步，就写成这样：c.getAccount().withdraw(1000);
		account.deposit(960);
		account.withdraw(2000);
		System.out.println("Customer[" + c.getLastName() + "," 
				+ c.getFirstName() + "] has a account id is : "
				+ account.getId() + ";annualInterstRate is : "
				+account.getAnnualInterestRate()*100 + "% ; balance is : "
				+account.getBalance());
	}

}
