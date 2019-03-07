package oop.statictest;
/**
 * static����ϰ��
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
class Account{//�����˻�
//	���ԣ�
	private  int id;//�˺�
	private int password;//����
	private double balance;//������
	private static double interestRate = 0.05;//����
	private static double minBalance = 10;//��С���
	private static int init = 1000;//�Զ����ɵı�ţ�������ֵ��id��
//	��������
	public Account(){
		super();
		this.id = init++;
	}
	public Account(int password, double balance){
		this.password =  password;
		this.balance = balance;
		this.id = init++;
	}
//	������
	public int getPassword() {//��ȡ����
		return password;
	}
	public void setPassword(int password) {//��������
		this.password = password;
	}
	public double getBalance() {//��ȡ���
		return balance;
	}
	public void setBalance(double balance) {//�������
		this.balance = balance;
	}
	public static double getInterestRate() {//��ȡ����
		return interestRate;
	}
	public static void setInterestRate(double interestRate) {//��������
		Account.interestRate = interestRate;
	}
	public double getMinBalance() {//��ȡ��С���
		return minBalance;
	}
	public void setMinBalance(double minBalance) {//������С���
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