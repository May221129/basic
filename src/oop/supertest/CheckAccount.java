package oop.supertest;
//��ļ̳�-super-��ϰ��-����
public class CheckAccount extends Account{
	
//	����:��͸֧�޶�
//�ɻ���ô�����Խ��г�ʼ����ֵ������
	private double overdraft;
	
//���Ӹ��༯�ɵ�3�����Ժ������Լ�������ȫ����ʼ��,������������д�����������������д��ʵ��������ʱ������
//	Account account = new Account();
	
//	��������
	public CheckAccount(){
		super();
	}
//	ͨ�������������Ӹ��༯�ɵ�3�����Ժ������Լ�������ȫ����ʼ����
	public CheckAccount(int id, double balance, double annualInteresRate ,double overdraft){
//ע�⣺���������������д����
		super(id, balance, annualInteresRate);
		this.overdraft = overdraft;
	}

	
//	��д�����е�withdrawȡ���
	public void withdiaw(double amount){
//�ɻ󣺸����е�balance�Ƕ���Ϊprivate����������ô����ȡ����amount���˻�������Աȣ�����
//����1��ͨ�����÷�������ȡ�����е�����;  
//		if(amount <= getBalance()){
//			double b = getBalance();//����һ����ʱ������������getBalance();
//			b -= amount;//�������ʱ����Ҫ�ñ����������㣬�������Ǳ���������ͨ�����÷�������ȡ�����е����ԣ���Ҫ������ʱ�������ܷ������������ﲻ���÷���1�����ǲ��÷���2����ʡ�
//����2�������������Ե�Ȩ�����η�д��protected������д��private��
		if(amount <= balance){
			balance -= amount;
		}else if(amount > balance){
			if(amount - balance <= overdraft){
				overdraft -= (amount - balance);//overdraft = (overdraft - (amount - balance));
				balance = 0;//�����д���˳���ܵ���������
			}else{
				System.out.println("͸֧ʧ�ܣ���͸֧��ȳ��� �� " + (amount - balance - overdraft));
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
