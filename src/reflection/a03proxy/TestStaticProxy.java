package reflection.a03proxy;
//��̬����ģʽ

interface ClothFactory{//�ӿ�
	void productCloth();
}

class NikeClothFactory implements ClothFactory{//��������,ʵ��ClothFactory�ӿ�
	@Override
	public void productCloth() {
		System.out.println("��������Nike�·����������������");
	}	
}

class ProxyFactory implements ClothFactory{//�����࣬ͬ��ȥʵ��ClothFactory�ӿ�
	NikeClothFactory ncf;
	//����������Ķ���ʱ��ʵ�ʴ���һ����������Ķ���
	public ProxyFactory(NikeClothFactory ncf){
		this.ncf = ncf;
	}
	@Override
	public void productCloth() {
		System.out.println("1�������࿪ʼִ�У��մ����$1000");
		System.out.println("2�������࿪ʼ����Nike�·�");
		ncf.productCloth();
		System.out.println("3���յ���������Ŀ����������");
	}
}

public class TestStaticProxy {
	public static void main(String[] args) {
		NikeClothFactory nike = new NikeClothFactory();//������������Ķ���
		ProxyFactory proxy = new ProxyFactory(nike);//����������Ķ���
		proxy.productCloth();
	}
}
