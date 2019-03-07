package thread.a07juc;

/**
 * 自己实现一个简易的CAS。
 * 当然，这个自定义的CAS并不精准，因为真正的CAS算法是硬件对于并发操作共享数据的支持，而我这里只是用synchronized。
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
	
	//获取内存值
	public synchronized int getValue(){
		return value;
	}
	
	//比较内存值是否==预估值
	public synchronized int CompareAndSet(int expectedValue, int newValue){
		/**
		 * 这里为什么要将value赋值给oldValue?
		 * 答：为了Boolean isSucceed(int expectedValue, int newValue)方法，
		 * 	isSucceed()需要知道没交换之前的内存值是什么，如果没赋值给oldValue，
		 * 	那return expectedValue == CompareAndSet(expectedValue, newValue)就始终返回false，
		 * 	因为 CompareAndSet()返回的是成功交换后的内存值。
		 */
		int oldValue = value;
		System.out.println(Thread.currentThread().getName() + " : oldValue = " + oldValue + "; expectedValue = " + expectedValue);
		if(value == expectedValue){
			value = newValue;
		}
		return oldValue;
	}
	
	//看“内存值和更新值”是否交换成功了
	public synchronized Boolean isSucceed(int expectedValue, int newValue){
		return expectedValue == CompareAndSet(expectedValue, newValue);
	}
}