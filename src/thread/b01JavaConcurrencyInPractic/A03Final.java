package thread.b01JavaConcurrencyInPractic;

/**
 * 3.4 �����ԡ����ʼǣ�G:\mystudy\studynotes\javaSE\thread\��Java�������ʵս���ʼ�.xmind��
 * ����Ա������final���κ󣬸ó�Ա�����ĸ�ֵ��ֻ���ڹ�������̬���������ɡ�
 * 
 * @author May
 * 2019��11��19��
 */
public class A03Final {
	
}

class E{
	private final int number;
	
//	{
//		number = 20;
//	}
	
	public E(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
//	public void setNumber(int number) {
//		this.number = number;
//	}
}