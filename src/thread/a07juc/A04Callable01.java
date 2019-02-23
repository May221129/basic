package thread.a07juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 需求：主线程负责计算0~1000的总和，t1负责计算0~3000的总和，t2负责计算0~5000的总和，然后把这三个结果再求和。
 * 实现：1.用CountDownLatch来实现（见A03CountDownLatch02）； 2.用Callable来实现（这里用的是这种方式）。
 */
public class A04Callable01 {
	public static void main(String[] args) {
		GetSum2 getSum2 = new GetSum2();
		//1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
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
		
		//2.接收线程运算后的结果
		try {
			
			Integer sum2 = result2.get();//FutureTask 可用于 闭锁
			System.out.println("sum2 = " + sum2);
			
			Integer sum3 = result3.get();
			System.out.println("sum3 = " + sum3);
			
			System.out.println("总和 = " + (sum1 + sum2 + sum3));
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