package reflection.a03proxy;
//静态代理模式

interface ClothFactory{//接口
	void productCloth();
}

class NikeClothFactory implements ClothFactory{//被代理类,实现ClothFactory接口
	@Override
	public void productCloth() {
		System.out.println("……验收Nike衣服，并付款给代理类");
	}	
}

class ProxyFactory implements ClothFactory{//代理类，同样去实现ClothFactory接口
	NikeClothFactory ncf;
	//创建代理类的对象时，实际传入一个被代理类的对象
	public ProxyFactory(NikeClothFactory ncf){
		this.ncf = ncf;
	}
	@Override
	public void productCloth() {
		System.out.println("1、代理类开始执行，收代理费$1000");
		System.out.println("2、代理类开始制作Nike衣服");
		ncf.productCloth();
		System.out.println("3、收到被代理类的款项，合作接收");
	}
}

public class TestStaticProxy {
	public static void main(String[] args) {
		NikeClothFactory nike = new NikeClothFactory();//创建被代理类的对象
		ProxyFactory proxy = new ProxyFactory(nike);//创建代理类的对象
		proxy.productCloth();
	}
}
