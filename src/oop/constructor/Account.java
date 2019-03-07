package oop.constructor;
//复习题：巩固构造器、this()的使用
public class Account {
	private int id;
	private double balance;
	private double annualInterestRate;
	
	public Account(int id, double balance, double annuallnterestRate){
		this.id = id;
		this.balance = balance;
		this.annualInterestRate = annuallnterestRate;
	}
	public int getId(){//获取账号
		return id;
	}
	public double getBalance(){//获取余额
		return balance;
	}
	public double getAnnualInterestRate(){//获取年利率
		return  annualInterestRate;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setBalance(double balance){
		this.balance = balance;
	}
	public void setAnnualInterestRate(double annualInterestRate){
		this.annualInterestRate =  annualInterestRate;
	}
	public void withdraw(double amount){//取款方法
		if(amount <= balance){
			balance -= amount;
			System.out.println("成功取出： " + amount);
		}else{
			System.out.println("您的账户余额不足，取款失败！");
		}
	}
	public void deposit(double amount){//存款方法
		balance += amount;
		System.out.println("成功存入： " + amount);
	}
} 
