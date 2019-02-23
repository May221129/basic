package reflection.a03proxy;
//��̬�������ʵ�֣�����

public class TestStaticProxy1 {
	public static void main(String[] args) {
		ByProxy bp = new ByProxy();
		ProxyBuyHourse pbh = new ProxyBuyHourse(bp);
		pbh.buyHouse();
	}
}

interface BuyHouse{//�ӿڣ�����
	void buyHouse();//���󷽷������ӵķ���
}

class ByProxy implements BuyHouse{//�������ࣺʵ��BuyHouse�ӿڣ���ʵ�ֽӿ���ĳ��󷽷�
	public void buyHouse(){
		System.out.println("---���Ǳ�������---");
		System.out.println("---����---");
	}
}

class ProxyBuyHourse implements BuyHouse{//�����ࣺͬ��ȥʵ��BuyHouse�ӿڣ���ʵ�ֽӿ���ĳ��󷽷�
	private ByProxy bp;//���������ഫ����
	public ProxyBuyHourse(ByProxy bp){
		this.bp = bp;
	}
	@Override
	public void buyHouse() {
		System.out.println("1�����Ǵ�����");
		System.out.println("2�������������˱�������ί�е�����");
		System.out.println("3��������Դ");
		System.out.println("4����ѡ�����ʵķ���");
		System.out.println("5����ʵ���׺��ʷ��ݵĸ���֤��");
		System.out.println("6���������ּۻ���");
		System.out.println("7��ǩ���ͬ");
		bp.buyHouse();//������ִ�С����
		System.out.println("8���������ึ���ʸ������࣬��������");
	}
}