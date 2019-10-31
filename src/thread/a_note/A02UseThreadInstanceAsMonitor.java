package thread.a_note;

/**
 * ̽�֣�Thread��ʵ�����Գ䵱�����������ɫ��
 * 
 * �����
 * 	Thread��ʵ���ǿ��Գ䵱�������ġ�������ڣ����ʵ���Լ��Ƿ�ִ����start����:
 * 	�����ִ�У�����ʵ����run()����û����ʱ����������������;
 * 	run����һ��������������������������ã�����ͬ��������еĴ��롣
 * 
 * Ҳ����˵����Thread��ʵ�����䵱���������������ģ����ԡ���û�±�������thread��ʵ�����䵱��������
 * 
 * @author May
 * 2019��10��30��
 */
public class A02UseThreadInstanceAsMonitor {
	public static void main(String[] args) {
		A a = new A();
		
		B b1 = new B(a);
		B b2 = new B(a);
		
		b1.setName("b1");
		b2.setName("b2");
		
		b1.start();
		b2.start();
		a.start();//����������=====
	}
}

class B extends Thread{
	private A a;
	
	public B(A a) {
		this.a = a;
	}
	
	@Override
	public void run() {
		synchronized(a) {//��Thread��ʵ�����䵱������
			for(int i = 0; i < 3; i++) {
				System.out.println(currentThread().getName() + " : " + i + " ,��Ҫ˯�ˡ�");
				try {
					a.wait();
					System.out.println(currentThread().getName() + " : �����ˡ�");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class A extends Thread{
	
	@Override
	public void run() {
		synchronized(this) {
			System.out.println("����A������������ ���� " + this);
		}
	}
}