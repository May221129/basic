package reflection.a03proxy;
//��̬����
/*����һ����̬������Ĺؼ����ڣ�
1����һ��ʵ����InvocationHandler�ӿڵ���;
2�� Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
3��public Object invoke(Object proxy, Method method, Object[] args);
*/
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//��̬�������ʵ�֣�
public class TestProxy1 {
	public static void main(String[] args) {
		ByProxy1 bp = new ByProxy1();
		MyInvocationHandler1 mih = new MyInvocationHandler1(bp);
		Object obj = Proxy.newProxyInstance(bp.getClass().getClassLoader(), bp.getClass().getInterfaces(), mih);
		Subject1 sj = (Subject1)obj;
		sj.doSomeThing();
	}
}
//�ӿڣ�
interface Subject1{
	void doSomeThing();
}
//�������ࣺ
class ByProxy1 implements Subject1{
	@Override
	public void doSomeThing() {
		System.out.println("---���Ǳ������࣬����ǩ�ָ����ˡ�---");
	}
}
//��̬�����ࣺ
class MyInvocationHandler1 implements InvocationHandler{
	Object obj;
	public MyInvocationHandler1(Object obj){
		this.obj = obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//�����Ǵ���ҪҪִ�еĹ�������
		System.out.println("���Ǵ����࣬��Ҫ��ʼΪ�������������ˣ�");
		System.out.println("��Ϊ�������඼����������е����飬����ֻ��Ҫ����������ǩ�ָ���Ϳ�����~");
		Object returnValue = method.invoke(obj, args);//����ʹ���˷��䣺Object returnValue = method.invoke(�������)��
		System.out.println("��~������������˱�����ί�еĹ�����");
		return returnValue;
	}
}