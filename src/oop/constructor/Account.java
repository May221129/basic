package oop.constructor;
//��ϰ�⣺���̹�������this()��ʹ��
public class Account {
	private int id;
	private double balance;
	private double annualInterestRate;
	
	public Account(int id, double balance, double annuallnterestRate){
		this.id = id;
		this.balance = balance;
		this.annualInterestRate = annuallnterestRate;
	}
	public int getId(){//��ȡ�˺�
		return id;
	}
	public double getBalance(){//��ȡ���
		return balance;
	}
	public double getAnnualInterestRate(){//��ȡ������
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
	public void withdraw(double amount){//ȡ���
		if(amount <= balance){
			balance -= amount;
			System.out.println("�ɹ�ȡ���� " + amount);
		}else{
			System.out.println("�����˻����㣬ȡ��ʧ�ܣ�");
		}
	}
	public void deposit(double amount){//����
		balance += amount;
		System.out.println("�ɹ����룺 " + amount);
	}
} 
