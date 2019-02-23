package reflection.a03proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ��̬�����ʹ�ã���ᷴ���Ƕ�̬���ԵĹؼ�
 * ����һ����̬������Ĺؼ����ڣ�
 * ��һ��ʵ����InvocationHandler�ӿڵ���;
 *  Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
 *  public Object invoke(Object proxy, Method method, Object[] args);
 */
public class TestProxy {
	public static void main(String[] args) {
		//1.��������Ķ���
		RealSubject real = new RealSubject();
		
		//2.����һ��ʵ����InvacationHandler�ӿڵ���Ķ���
		MyInvocationHandler handler = new MyInvocationHandler();
		
		//3.����blind()��������̬�ķ���һ��ͬ��ʵ����real������ʵ�ֵĽӿ�Subject�Ĵ�����Ķ���
		Object obj = handler.blind(real);
		Subject sub = (Subject)obj;//��ʱsub���Ǵ�����Ķ���
//		Ϊʲô����ǿת�ɹ���
//		˵��newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this)
//		����ĵڶ������� ��̬������ʵ����"obj.getlass().getInerfaces()"����������������ʵ���˽ӿ�Subject��

	
		sub.action();//����action()�����󣬻�ת����InvacationHandler�ӿڵ�ʵ�����invoke()�����ĵ���
//		��ΪProxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this)
//		�����this��ʵ����InvocationHandler�ӿڵ��࣬����ʵ����invoke()������
		
//		�ڶ������ӣ�
//		NikeClothFactory nike = new NikeClothFactory();
//		ClothFactory proxyCloth = (ClothFactory)handler.blind(nike);//proxyCloth��Ϊ������Ķ���
//		proxyCloth.productCloth();	
	}
}

interface Subject {//�ӿ�
	void action();
}

// ��������
class RealSubject implements Subject {
	public void action() {
		System.out.println("���Ǳ������࣬������Ҫ�������顣");
	}
}

class MyInvocationHandler implements InvocationHandler {//�ⲻ�Ǵ����࣬�����Ǳ������ࡣ��ֻ��һ��ʵ����InvocationHandler�ӿڵ��ࡣ
	
	Object obj;// ʵ����InvocationHandler�ӿڵı�������Ķ��������

//	�����������������Ŀ�ģ�  �ٸ�������Ķ���ʵ����    �ڷ���һ��������Ķ���
	public Object blind(Object obj) {
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}
//	.getclass()�õ�����Class���󣬻�õ��Ƕ������������������������̳���ʲô�࣬ʵ����ʲô�ӿڵȶ����Ի�֪
//	newProxyInstance���������ܹ���̬��ȥ����һ�������ಢʵ���������󷵻ء�  
//	���������ֱ��ǣ�������������������ʵ�ֵĽӿڣ�ʵ����invocationHandler�ӿڵĶ���
//	�������������÷ֱ��ǣ�����̬�Ĵ�������ص��ڴ��У�  ʵ��Subject�ӿڣ���ʵ����action()������  ʵ����InvocationHandler�ӿڡ���ʵ����invoke()�����Ķ���
//	���newProxyInstance()�������п��ܴ���������Ч����
//	class dongtai implements Subject{
//		MyInvocationHandler mih;//��Ա���� 
//		public void action(){//����������ִ��invoke()����
//			mih.invoke(this, mathod, args);//this����ָ������̬������� method��ͨ���ӿڻ�ȡ�ķ�������args��ͨ�����������ȡ��������б�
//		}
//	}
	
//	��ͨ��������Ķ�����Ա���д�����ĵ���ʱ������ת��Ϊ�����µ�invoke�����ĵ���
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
//		������ηֱ��ǣ������࣬ʵ��Subject�ӿڵ�action()����������������б�
		System.out.println("���Ǵ�����");
		Object returnVal = method.invoke(obj, args);//method�����ķ���ֵ��returnVal
		return returnVal;
	}
}