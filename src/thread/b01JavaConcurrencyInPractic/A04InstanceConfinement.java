package thread.b01JavaConcurrencyInPractic;

/**
 * 4.2 ʵ�����
 * ̽����һ������Է��ʵ��Լ������ĳ�Ա�����ķ�����
 * 
 * @author May
 * 2019��11��26��
 */
public class A04InstanceConfinement {
	public static void main(String[] args) {
		new BB(new AA()).print();
	}
}

class BB{
	private AA a;
	
	public BB(AA a) {
		this.a = a;
	}
	
	public void print() {
		System.out.println(a.getNumber());
	}
}

class AA{
	private int  number = 10;
	public int getNumber() {
		return number;
	}
	
}