package oop.statictest;
/**
 * static的练习：
 */
public class A02StaticTest_Account {
	public static void main(String[] args) {
		Account a1 = new Account(123450, 1000);
		System.out.println(a1.toString());
		Account a2 = new Account(123451, 2000);
		System.out.println(a2);
		Account a3 = new Account();
		System.out.println(a3);	
	}
}
class Account{//银行账户
//	属性：
	private  int id;//账号
	private int password;//密码
	private double balance;//存款余额
	private static double interestRate = 0.05;//利率
	private static double minBalance = 10;//最小余额
	private static int init = 1000;//自动生成的编号，用来赋值给id的
//	构造器：
	public Account(){
		super();
		this.id = init++;
	}
	public Account(int password, double balance){
		this.password =  password;
		this.balance = balance;
		this.id = init++;
	}
//	方法：
	public int getPassword() {//获取密码
		return password;
	}
	public void setPassword(int password) {//设置密码
		this.password = password;
	}
	public double getBalance() {//获取余额
		return balance;
	}
	public void setBalance(double balance) {//设置余额
		this.balance = balance;
	}
	public static double getInterestRate() {//获取利率
		return interestRate;
	}
	public static void setInterestRate(double interestRate) {//设置利率
		Account.interestRate = interestRate;
	}
	public double getMinBalance() {//获取最小余额
		return minBalance;
	}
	public void setMinBalance(double minBalance) {//设置最小余额
		this.minBalance = minBalance;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", password=" + password + ", balance=" + balance + "]";
	}
	public void info(){
		System.out.println(minBalance);
	}
}