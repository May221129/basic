package reflection.a03proxy;
//动态代理：
/*创建一个动态代理类的关键所在：
1、有一个实现了InvocationHandler接口的类;
2、 Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
3、public Object invoke(Object proxy, Method method, Object[] args);
*/
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//动态代理类的实现：
public class TestProxy1 {
	public static void main(String[] args) {
		ByProxy1 bp = new ByProxy1();
		MyInvocationHandler1 mih = new MyInvocationHandler1(bp);
		Object obj = Proxy.newProxyInstance(bp.getClass().getClassLoader(), bp.getClass().getInterfaces(), mih);
		Subject1 sj = (Subject1)obj;
		sj.doSomeThing();
	}
}
//接口：
interface Subject1{
	void doSomeThing();
}
//被代理类：
class ByProxy1 implements Subject1{
	@Override
	public void doSomeThing() {
		System.out.println("---我是被代理类，我来签字付款了。---");
	}
}
//动态代理类：
class MyInvocationHandler1 implements InvocationHandler{
	Object obj;
	public MyInvocationHandler1(Object obj){
		this.obj = obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//这里是代理要要执行的工作代码
		System.out.println("我是代理类，我要开始为被代理类做事了！");
		System.out.println("我为被代理类都处理好了所有的事情，现在只需要被代理类来签字付款就可以啦~");
		Object returnValue = method.invoke(obj, args);//这里使用了反射：Object returnValue = method.invoke(对象，入参)；
		System.out.println("嗯~我完美的完成了被代理委托的工作！");
		return returnValue;
	}
}