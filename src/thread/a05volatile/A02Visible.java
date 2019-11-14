package thread.a05volatile;

/**
 * ͨ��volatileʵ���ֶοɼ��ԡ�
 * 
 * @author May
 * 2019��11��14��
 */
public class A02Visible {
	public static void main(String[] args) {
		Honey honey = new Honey();
		new SetHoneyNub(honey).start();
		new PrintHoneyNub(honey).start();
	}
}

class PrintHoneyNub extends Thread{
	private Honey honey;
	public PrintHoneyNub(Honey honey) {
		this.honey = honey;
	}
	@Override
	public void run() {
		while(honey.getHoneyNub() == 520) {
			
		}
		System.out.println("ż�����honey����ˣ�");
	}
}

class SetHoneyNub extends Thread{
	private Honey honey;
	public SetHoneyNub(Honey honey) {
		this.honey = honey;
	}
	@Override
	public void run() {
		try {
			currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		honey.setHoneyNub(88);
		System.out.println("honeyNub == 88");
	}
}

class Honey{
	private volatile int honeyNub = 520;
	public void setHoneyNub(int newNub) {
		this.honeyNub = newNub;
	}
	public int getHoneyNub() {
		return this.honeyNub;
	}
}