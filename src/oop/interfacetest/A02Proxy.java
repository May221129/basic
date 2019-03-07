package oop.interfacetest;
/**
 * �ӿڵ�Ӧ�á�����̬����ģʽ��Proxy��:�����ฺ���˱��������ִ��
 */
public class A02Proxy {
	public static void main(String[] args) {
		ObjectImpl obj = new ObjectImpl();//���
		Object1 o1 = new ProxyObject1(obj);
		o1.action();
	}
}
interface Object1{//�ӿڣ�Object1
	public abstract void action();
}
class ProxyObject1 implements Object1{//Proxy��ȥʵ��Object1�ӿ�
	Object1 obj;//obj�������ǳ�Ա��������Ϊ�����һ����Դ���ڣ������Ƴɾֲ����������ų����ִ������򲻻������κμ�¼
//������дObject1 obj����������Object obj������Ϊʲô������
	public ProxyObject1(Object1 obj){
		this.obj = obj;
		System.out.println("�ɹ�������������");
	}
	@Override
	public void action() {
		System.out.println("������������Դ");
		obj.action();
		System.out.println("������ǩ��ͬ���鷿�ȵȣ���Լ");
	}
}
class ObjectImpl implements Object1{
	@Override
	public void action() {
		System.out.println("�����ӡ�����Ǯ");
	}
}