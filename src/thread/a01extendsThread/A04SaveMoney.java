package thread.a01extendsThread;

/**
 * ������һ���˻��������������ֱ���ͬһ���˻���3000Ԫ�� ÿ�δ�1000Ԫ����3�Σ������ӡ�˻����.
 *  ��չ��ʵ�ֶ��߽����ӡ��ʹ���̵߳�ͨ��
 * 
 * @author May 2019��10��29��
 */
public class A04SaveMoney {
	public static void main(String[] args) {
		Man zhang = new Man();
		Man li = new Man();

		zhang.setName("��");
		li.setName("��");

		zhang.start();
		li.start();
	}
}

class Man extends Thread {
	private static int money = 0;
	
	@Override
	public void run() {
		saveMoney();
	}
	
	/**
	 * ���ĸ��������䵱��������Ҫ�ĸ������������ڵȴ��ö���������̣߳�
	 * synchronized(Man.class) {
	 * 	Man.class.notifyAll();
	 * 	Man.class.wait();
	 * }
	 */
	public void saveMoney() {
		synchronized(Man.class) {
			for(int i = 0; i < 3; i++) {
				// ���ѷ���������˭�����ã�==��Ҫ�����ĸ������ϵȴ������̣߳������ĸ����������á�
				//notifyAll()�����ķ���λ��Ҳ����Ҫ���̱߳����Ѻ󣬴�wait()������������ִ�С�
				Man.class.notifyAll();
				money += 1000;
				System.out.println(currentThread().getName() + " �ɹ���� 1000 Ԫ����ǰ�˻����Ϊ " + money + " Ԫ��");
				
				try {
					Man.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}