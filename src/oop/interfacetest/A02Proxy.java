package oop.interfacetest;
/**
 * 接口的应用——静态代理模式（Proxy）:代理类负责了被代理类的执行
 */
public class A02Proxy {
	public static void main(String[] args) {
		ObjectImpl obj = new ObjectImpl();//买家
		Object1 o1 = new ProxyObject1(obj);
		o1.action();
	}
}
interface Object1{//接口：Object1
	public abstract void action();
}
class ProxyObject1 implements Object1{//Proxy类去实现Object1接口
	Object1 obj;//obj在这里是成员变量，作为代理的一个资源存在，如果设计成局部变量，随着程序的执行完成则不会留下任何记录
//上面是写Object1 obj？？？还是Object obj？？？为什么？？？
	public ProxyObject1(Object1 obj){
		this.obj = obj;
		System.out.println("成功创建被代理者");
	}
	@Override
	public void action() {
		System.out.println("代理——搜索房源");
		obj.action();
		System.out.println("代理——签合同，验房等等，解约");
	}
}
class ObjectImpl implements Object1{
	@Override
	public void action() {
		System.out.println("我买房子——付钱");
	}
}