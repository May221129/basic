package thread.b01JavaConcurrencyInPractic;

/**
 * 3.2 �������ݳ������ʼǣ�G:\mystudy\studynotes\javaSE\thread\��Java�������ʵս���ʼ�.xmind��
 * 
 * ̽�֣�����һ����δ������ɵĶ�����ᵼ�¸ö�����ݳ���
 * 
 * �ʣ�
 * 	1. �������ǹ���������أ�
 * 		�𣺶�������˳�ʼ����ʵ��������ʼ����� + ִ���궯̬����� + ִ���깹�캯����
 * 
 * �������Ĵ���ʱ����Ҫ����˳�����ִ��˳��
 * 
 * @author May
 * 2019��11��16��
 */
public class A03PublishAndEscape {
	public static void main(String[] args) {
		new B(new A());//�����B�ĳ�ʼ��������A�ĳ�ʼ����
	}
}

class B{
	//������������Ա������Ϊ����֤��B��ʵ����δ��ɳ�ʼ���ͷ�������A����ᵼ��B��ʵ�����ݳ�����
	public int number;
	public int number2;
	
	public B(A a) {
		System.out.println("B�Ĺ��캯��");
		number = 10;//number�ḳֵ�ɹ���
		a.escape(this);//this��B��ʵ������ʱB�Ĺ��췽����δִ���꣬this�ǲ������ģ���this������a����ʱthis�ݳ��ˡ�
		number2 = 20;//B��ʵ���ݳ���number2Ӧ�ûḳֵʧ�ܡ�
		System.out.println("==> b.number2 = " + number2);//==> b.number2 = 20
	}
}

class A{
	public  void escape(B b) {
		System.out.println("A�Ĺ��캯��");
		System.out.println("b.number = " + b.number);
		System.out.println("b.number2 = " + b.number2);
	}
}
