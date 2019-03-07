package thread.a07juc;

/**
 * �Լ�ʵ��һ�����׵�CAS��
 * ��Ȼ������Զ����CAS������׼����Ϊ������CAS�㷨��Ӳ�����ڲ��������������ݵ�֧�֣���������ֻ����synchronized��
 */
public class A01MyCAS2 {
	public static void main(String[] args) {
		
		final MyCAS cas = new MyCAS();
		
		for(int i = 0; i < 10; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					int expectedValue = cas.getValue();
					Boolean bool = cas.isSucceed(expectedValue, (int)(Math.random() * 101));
					System.out.println("----------" + bool);
				}
			}).start();			
		}
	}
}

class MyCAS{
	private int value;
	
	//��ȡ�ڴ�ֵ
	public synchronized int getValue(){
		return value;
	}
	
	//�Ƚ��ڴ�ֵ�Ƿ�==Ԥ��ֵ
	public synchronized int CompareAndSet(int expectedValue, int newValue){
		/**
		 * ����ΪʲôҪ��value��ֵ��oldValue?
		 * ��Ϊ��Boolean isSucceed(int expectedValue, int newValue)������
		 * 	isSucceed()��Ҫ֪��û����֮ǰ���ڴ�ֵ��ʲô�����û��ֵ��oldValue��
		 * 	��return expectedValue == CompareAndSet(expectedValue, newValue)��ʼ�շ���false��
		 * 	��Ϊ CompareAndSet()���ص��ǳɹ���������ڴ�ֵ��
		 */
		int oldValue = value;
		System.out.println(Thread.currentThread().getName() + " : oldValue = " + oldValue + "; expectedValue = " + expectedValue);
		if(value == expectedValue){
			value = newValue;
		}
		return oldValue;
	}
	
	//�����ڴ�ֵ�͸���ֵ���Ƿ񽻻��ɹ���
	public synchronized Boolean isSucceed(int expectedValue, int newValue){
		return expectedValue == CompareAndSet(expectedValue, newValue);
	}
}