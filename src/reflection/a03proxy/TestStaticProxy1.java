package reflection.a03proxy;
//静态代理类的实现：买房子

public class TestStaticProxy1 {
	public static void main(String[] args) {
		ByProxy bp = new ByProxy();
		ProxyBuyHourse pbh = new ProxyBuyHourse(bp);
		pbh.buyHouse();
	}
}

interface BuyHouse{//接口：买房子
	void buyHouse();//抽象方法：买房子的方法
}

class ByProxy implements BuyHouse{//被代理类：实现BuyHouse接口，并实现接口里的抽象方法
	public void buyHouse(){
		System.out.println("---我是被代理类---");
		System.out.println("---付款---");
	}
}

class ProxyBuyHourse implements BuyHouse{//代理类：同样去实现BuyHouse接口，并实现接口里的抽象方法
	private ByProxy bp;//将被代理类传进来
	public ProxyBuyHourse(ByProxy bp){
		this.bp = bp;
	}
	@Override
	public void buyHouse() {
		System.out.println("1、我是代理类");
		System.out.println("2、代理类受理了被代理类委托的事项");
		System.out.println("3、搜索房源");
		System.out.println("4、挑选到合适的房子");
		System.out.println("5、核实这套合适房屋的各种证件");
		System.out.println("6、和卖家讨价还价");
		System.out.println("7、签署合同");
		bp.buyHouse();//代理类执行“付款”
		System.out.println("8、被代理类付工资给代理类，合作结束");
	}
}