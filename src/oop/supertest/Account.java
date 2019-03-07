package oop.supertest;
//类的继承-super-练习题-父类
class Account {
//	属性
	protected int id;//账号
	protected double balance;//余额
	protected double annualInteresRate;//年利率
//	构造器
	public Account(){
		super();
	}
	public Account(int id, double balance, double annualInteresRate){
		this.id = id;
		this.balance = balance;
		this.annualInteresRate = annualInteresRate;
	}
//	方法
	public int getId(){//获取账号
		return id;
	}
	public double getBalance(){//获取余额
		return balance;
	}
	public double getAnnualInteresRate(){//获取年利率
		return annualInteresRate;
	}
	public void setId(int id){//设置账号
		this.id = id;
	}
	public void setBalance(double balance){//设置余额 
		this.balance = balance;
	}
	public void setAnnualIneresRate(double annualInteresRate){//设置年利率
		this.annualInteresRate = annualInteresRate;
	}
	public double getMonthlyInterest(){//返回月利率
		return this.annualInteresRate / 12;
	}
	public void withdiaw(double amount){//取款方法
		if(amount <= balance){
			balance -= amount;
		}else{
			System.out.println("余额不足！");
		}
	}
	public void deposit(double amount){//存款方法
		balance += amount;
	}
}
