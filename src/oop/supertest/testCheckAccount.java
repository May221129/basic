package oop.supertest;
//��ļ̳�-super-��ϰ��-����CheckAccount
public class testCheckAccount {
	public static void main(String[] args) {
		
		CheckAccount ca = new CheckAccount(1122, 20000, 0.045, 5000);
		
//��ô���÷���������==>��ǰ�洴����ca�������������еķ����������еķ�����Ϊ������̳��ˣ�����Ҳ����ֱ�ӵ��ã��������ca.getOverdraft();
		ca.withdiaw(5000);//ȡ5000
		System.out.println("��ǰ�˻����Ϊ�� " + ca.getBalance() + "; " + "��͸֧�޶ " + ca.getOverdraft());
	
		ca.withdiaw(18000);//ȡ18000
		System.out.println("��ǰ�˻����Ϊ�� " + ca.getBalance() + "; " + "��͸֧�޶ " + ca.getOverdraft());
	
		ca.withdiaw(3000);//ȡ3000
		System.out.println("�˻����Ϊ�� " + ca.balance + "; " + "��͸֧�޶ " + ca.getOverdraft());
	}
}