package oop.supertest;
//��ļ̳�-super-��ϰ��-����
class Account {
//	����
	protected int id;//�˺�
	protected double balance;//���
	protected double annualInteresRate;//������
//	������
	public Account(){
		super();
	}
	public Account(int id, double balance, double annualInteresRate){
		this.id = id;
		this.balance = balance;
		this.annualInteresRate = annualInteresRate;
	}
//	����
	public int getId(){//��ȡ�˺�
		return id;
	}
	public double getBalance(){//��ȡ���
		return balance;
	}
	public double getAnnualInteresRate(){//��ȡ������
		return annualInteresRate;
	}
	public void setId(int id){//�����˺�
		this.id = id;
	}
	public void setBalance(double balance){//������� 
		this.balance = balance;
	}
	public void setAnnualIneresRate(double annualInteresRate){//����������
		this.annualInteresRate = annualInteresRate;
	}
	public double getMonthlyInterest(){//����������
		return this.annualInteresRate / 12;
	}
	public void withdiaw(double amount){//ȡ���
		if(amount <= balance){
			balance -= amount;
		}else{
			System.out.println("���㣡");
		}
	}
	public void deposit(double amount){//����
		balance += amount;
	}
}
