package oop.constructor;
//��ϰ�⣺���̹�������this()��ʹ��

public class TestCustomer {
	public static void main(String[] args) {
		Customer c = new Customer("Jane","Smith");
//		Account a = new Account(1000,2000,0.0123);��ʽ��ֵ
//		������ֵ ==> ��������д�����������ֵ��һ���裬�Ҳ�������������
		c.setAccount(new Account(1000,2000,0.0123));//������ֵ==>������һ���裬�Ҳ�������������
		Account account = c.getAccnount();//��д��һ������Ϊ������ӡ��ʱ�򷽱���д������д��ô��
		account.withdraw(100);//���û��������һ������д��������c.getAccount().withdraw(1000);
		account.deposit(960);
		account.withdraw(2000);
		System.out.println("Customer[" + c.getLastName() + "," 
				+ c.getFirstName() + "] has a account id is : "
				+ account.getId() + ";annualInterstRate is : "
				+account.getAnnualInterestRate()*100 + "% ; balance is : "
				+account.getBalance());
	}

}
