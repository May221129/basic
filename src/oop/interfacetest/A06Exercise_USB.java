package oop.interfacetest;
/**
 * �ӿڵ���ϰ
 */
public class A06Exercise_USB {
	public static void main(String[] args) {
		Computer c = new Computer();
		c.doWork(new Printer());//��������ķ�ʽ�������ýӿڵķ���
		
		Flash f = new Flash();
		c.doWork(f);
//==>ʵ�ֽӿڵ�������Ķ��󣺷���һ
//==>��д��ʽ���ӿ�+�Դ������� = new+�ӿ�(){������});
//==>���ã�����.����(�Դ�������);
		USB phone = new USB(){
			@Override
			public void start() {
				System.out.println("�ֻ���ʼ����");	
			}
			@Override
			public void stop() {
				System.out.println("�ֻ���������");	
			}
		};
		c.doWork(phone);
//==>ʵ�ֽӿڵ�������Ķ��󣺷�����
//==>��д��ʽ������.����(��������(){������});
		c.doWork(new USB(){
			@Override
			public void start() {
				System.out.println("�ֻ���ʼ����");	
			}
			@Override
			public void stop() {
				System.out.println("�ֻ���������");	
			}
		});
	}
}

//�����Ҫע�⣬�Ҳ�������������
class Computer{
	 public void doWork(USB u){
		 u.start();
		 u.stop();
	 }
}
interface USB{
	public abstract void start();
	public abstract void stop();
}
class Flash implements USB{
	@Override
	public void start() {
		System.out.println("U�̿�ʼ����");
	}
	@Override
	public void stop() {
		System.out.println("U��ֹͣ����");	
	}
}
class Printer implements USB{
	@Override
	public void start() {
		System.out.println("��ӡ����ʼ����");	
	}
	@Override
	public void stop() {
		System.out.println("��ӡ��ֹͣ����");	
	}
}