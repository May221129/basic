package oop.supertest;
//类的继承-super-练习题-子类
public class CheckAccount extends Account{
	
//	属性:可透支限额
//疑惑：怎么给属性进行初始化赋值？？？
	private double overdraft;
	
//将从父类集成的3个属性和子类自己的属性全部初始化,不是下面这种写法！！！下面这个是写在实例化对象时！！！
//	Account account = new Account();
	
//	构造器：
	public CheckAccount(){
		super();
	}
//	通过构造器，将从父类集成的3个属性和子类自己的属性全部初始化：
	public CheckAccount(int id, double balance, double annualInteresRate ,double overdraft){
//注意：这个构造器是这样写的吗？
		super(id, balance, annualInteresRate);
		this.overdraft = overdraft;
	}

	
//	重写父类中的withdraw取款方法
	public void withdiaw(double amount){
//疑惑：父类中的balance是定义为private，子类中怎么将所取款项amount与账户余额做对比？？？
//方法1：通过调用方法来获取父类中的属性;  
//		if(amount <= getBalance()){
//			double b = getBalance();//创建一个临时变量，来储存getBalance();
//			b -= amount;//做运算的时候，需要用变量来做运算，方法不是变量，所以通过调用方法来获取父类中的属性，还要创建临时变量，很繁琐。所以这里不采用方法1，而是采用方法2会合适。
//方法2：将父类中属性的权限修饰符写成protected，而不写成private。
		if(amount <= balance){
			balance -= amount;
		}else if(amount > balance){
			if(amount - balance <= overdraft){
				overdraft -= (amount - balance);//overdraft = (overdraft - (amount - balance));
				balance = 0;//这两行代码顺序不能调换！！！
			}else{
				System.out.println("透支失败，可透支额度超了 ： " + (amount - balance - overdraft));
			}
		}
	}
	public void setOverdraft(double overdraft){
		this.overdraft = overdraft;
	}
	public double getOverdraft(){
		return overdraft;
	}
}
