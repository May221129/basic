package oop.supertest;
//��ļ̳�-super-��ϰ��-����Account
public class testAccouont {
	public static void main(String[] args) {
		
		Account account = new Account(1122, 20000,0.045 );//��������
		
		account.withdiaw(30000);//ȡ30000
		
		System.out.println("�����˻����Ϊ�� " + account.getBalance());
		System.out.println();
		
		account.withdiaw(2500);//ȡ3000
		account.deposit(3000);//��2500
		System.out.println("�����˻����Ϊ�� " + account.getBalance());
		
		System.out.println("������Ϊ�� " + account.getMonthlyInterest());
	}

}
