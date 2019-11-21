package thread.a05volatile;

/**
 * ���ԡ�����volatile���ε��ֶΣ���ȷ���̰߳�ȫ����
 * 
 * �ش��������ǰ������Ҫ����ȷʲô���̰߳�ȫ�ԡ���������߳�ͬʱ����ͬһ����Ĺ������ݣ��������ݵĽ������ȷ�ġ���
 * 
 * ���Խ������1�������˴������ظ����֣� ��2���������� ��-1����
 * 	�������⣨1������Ϊ���̴߳��ڡ��ȼ���ִ�С��ľ�̬������
 * 		�����������߳�ͬʱӵ��CPU��ִ��Ȩ��������˫�˵ģ��������жϵ�����if (ticket > 0)������ͬʱ����ticket--��������
 * 	�������⣨2������Ϊ��
 * 		�ٵ�ticket==1ʱ�����������߳�ͬʱͨ���ˡ�if (ticket > 0)�����жϣ����������жϿ���ȥִ�д��룻
 * 		��Ȼ������ִ�е���Thread.sleep(100);����˯�ˣ�
 * 		��˯�Ѻ�����һ���̻߳�������cup��ִ��Ȩ��Ȼ��ִ�С�ticket--���������������µ�ticket��ֵ���͸�֪��ÿ���̣߳�
 * 		�ܴ�ʱ��Щ���жϿ��е��������̲߳������ٴ�����if (ticket > 0)�����жϣ�����ֱ�������µ�ticket������ticket--��������
 * 		�����߳��ڡ�ticket--��֮ǰÿ�ζ�����if (ticket > 0)�����жϣ�Ҳ���ɻ����̰߳�ȫ���⣬��Ϊ�ֿ��ܳ��֢�����ͬʱͨ���жϵ�״̬��
 * 
 * ���ۣ�volatile���ܱ�֤�̰߳�ȫ�ԣ���ΪҪ��֤�̰߳�ȫ�Ծ͵ñ�֤���Դ�����ʽ�����ʲ���������Դ�ģ���volatile��������㡣
 * 
 * @author May 2019��11��14��
 */
public class A03UseVolatileIsNotThreadSafe {
	public static void main(String[] args) {
		Window w = new Window();

		Thread t1 = new Thread(w);
		Thread t2 = new Thread(w);
		Thread t3 = new Thread(w);

		t1.setName("����1");
		t2.setName("����2");
		t3.setName("����3");

		t1.start();
		t2.start();
		t3.start();
	}
}

class Window implements Runnable {
	private volatile int ticket = 100;

	public void run() {
		for (;;) {
			//ͨ������Ģ٢������������ǿ��Է��֣���һ��������Դ���Զ���߳�ͬʱ�����޸ģ���Ȼ�ͻ����̰߳�ȫ���⡣
			if (ticket > 0) {
				try {
					Thread.sleep(100);//�ٶ���߳�ͬʱ�жϵ���ticket>0����Ȼ�������
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//�ڶ���߳�ͬʱ������ͬʱ���С�ticket--��������
				System.out.println(Thread.currentThread().getName() + ":" + ticket--);
			} else {
				break;
			}
		}
	}
}