package reflection.a03proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理的使用，体会反射是动态语言的关键
 * 创建一个动态代理类的关键所在：
 * 有一个实现了InvocationHandler接口的类;
 *  Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
 *  public Object invoke(Object proxy, Method method, Object[] args);
 */
public class TestProxy {
	public static void main(String[] args) {
		//1.被代理类的对象
		RealSubject real = new RealSubject();
		
		//2.创建一个实现了InvacationHandler接口的类的对象
		MyInvocationHandler handler = new MyInvocationHandler();
		
		//3.调用blind()方法，动态的返回一个同样实现了real所在类实现的接口Subject的代理类的对象。
		Object obj = handler.blind(real);
		Subject sub = (Subject)obj;//此时sub就是代理类的对象
//		为什么可以强转成功，
//		说明newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this)
//		这里的第二个参数 动态代理类实现了"obj.getlass().getInerfaces()"这个参数，这个参数实现了接口Subject。

	
		sub.action();//调用action()方法后，会转到对InvacationHandler接口的实现类的invoke()方法的调用
//		因为Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this)
//		这里的this是实现了InvocationHandler接口的类，里面实现了invoke()方法。
		
//		第二个例子：
//		NikeClothFactory nike = new NikeClothFactory();
//		ClothFactory proxyCloth = (ClothFactory)handler.blind(nike);//proxyCloth即为代理类的对象
//		proxyCloth.productCloth();	
	}
}

interface Subject {//接口
	void action();
}

// 被代理类
class RealSubject implements Subject {
	public void action() {
		System.out.println("我是被代理类，这是我要做的事情。");
	}
}

class MyInvocationHandler implements InvocationHandler {//这不是代理类，更不是被代理类。这只是一个实现了InvocationHandler接口的类。
	
	Object obj;// 实现了InvocationHandler接口的被代理类的对象的声明

//	创建下面这个方法的目的：  ①给被代理的对象实例化    ②返回一个代理类的对象
	public Object blind(Object obj) {
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}
//	.getclass()得到的是Class对象，获得的是对这个类的描述，包括了这个类继承了什么类，实现了什么接口等都可以获知
//	newProxyInstance（）方法能够动态的去创建一个代理类并实例化将对象返回。  
//	三个参数分别是：加载器，被代理类所实现的接口，实现了invocationHandler接口的对象。
//	三个参数的作用分别是：将动态的代理类加载到内存中；  实现Subject接口，并实现其action()方法；  实现了InvocationHandler接口、并实现其invoke()方法的对象。
//	这个newProxyInstance()方法，有可能创建出如下效果：
//	class dongtai implements Subject{
//		MyInvocationHandler mih;//成员变量 
//		public void action(){//这个方法里，有执行invoke()方法
//			mih.invoke(this, mathod, args);//this就是指代本动态代理对象， method是通过接口获取的方法对象，args是通过方法对象获取到的入参列表
//		}
//	}
	
//	当通过代理类的对象发起对被重写方法的调用时，都会转换为对如下的invoke方法的调用
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
//		三个入参分别是：代理类，实现Subject接口的action()方法，方法的入参列表
		System.out.println("我是代理人");
		Object returnVal = method.invoke(obj, args);//method方法的返回值是returnVal
		return returnVal;
	}
}