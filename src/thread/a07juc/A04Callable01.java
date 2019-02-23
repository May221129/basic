package thread.a07juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * �������̸߳������0~1000���ܺͣ�t1�������0~3000���ܺͣ�t2�������0~5000���ܺͣ�Ȼ����������������͡�
 * ʵ�֣�1.��CountDownLatch��ʵ�֣���A03CountDownLatch02���� 2.��Callable��ʵ�֣������õ������ַ�ʽ����
 */
public class A04Callable01 {
	public static void main(String[] args) {
		GetSum2 getSum2 = new GetSum2();
		//1.ִ�� Callable ��ʽ����Ҫ FutureTask ʵ�����֧�֣����ڽ�����������
		FutureTask<Integer> result2 = new FutureTask<>(getSum2);
		new Thread(result2).start();
		
		GetSum3 getSum3 = new GetSum3();
		FutureTask<Integer> result3 = new FutureTask<>(getSum3);
		new Thread(result3).start();
		
		int sum1 = 0;
		for(int i = 0; i <= 1000; i++){
			sum1 += i;
		}
		System.out.println("sum1 = " + sum1);
		
		//2.�����߳������Ľ��
		try {
			
			Integer sum2 = result2.get();//FutureTask ������ ����
			System.out.println("sum2 = " + sum2);
			
			Integer sum3 = result3.get();
			System.out.println("sum3 = " + sum3);
			
			System.out.println("�ܺ� = " + (sum1 + sum2 + sum3));
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}

class GetSum2 implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 0; i <= 3000; i++) {
			sum += i;
		}
		System.out.println(Thread.currentThread().getName() + " : " + sum);
		return sum;
	}
}

class GetSum3 implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for(int i = 0; i <= 6000; i++){
			sum += i;
		}
		System.out.println(Thread.currentThread().getName() + " : " + sum);
		return sum;
	}
}