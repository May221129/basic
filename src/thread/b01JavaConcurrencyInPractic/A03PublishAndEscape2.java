package thread.b01JavaConcurrencyInPractic;

/**
 * 3.2 �������ݳ����ʼǣ�G:\mystudy\studynotes\javaSE\thread\��Java�������ʵս���ʼ�.xmind��
 * ̽�֣��ڹ��캯���е���һ���ɸ�д��ʵ������ʱ���Ȳ���˽�з�����Ҳ�����ս᷽������ͬ���ᵼ��this�����ڹ���������ݳ���
 * 
 * @author May
 * 2019��11��16��
 */
public class A03PublishAndEscape2 {
	public static void main(String[] args) {
		new C();
	}
}

class C{
	private int number;
	private int number2;
	
	public C() {
		number = 10;
		//�����ڲ���D�ǿ��Է��ʵ�C�ĳ�Ա�����ģ����������ڲ�������ԡ�
		new D() {
			@Override
			public void test(C c) {
				//C��ʵ���������ݳ��ˣ����ＴʹʲôҲûд��Ҳ������C�ĳ�Ա�����Ѿ���¶����D
			}
		};
		number2 = 20;
	}
}

class D{
	public void test(C c) {}
}
