package thread.a07juc;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

import org.junit.Test;

/**
 * ������Fork/Join�����ɴ�ļ�������Ĳ�ּ��㡣������java8����������ɴ�ļ�������Ĳ�ּ��㡣�ۺ���ͨforѭ����ɴ�ļ���������Ƚϡ�
 * 
 * 	1.Fork/Join��ܵ����ã��ڱ�Ҫ����£���һ����������(fork)�����ɸ�С����(�𵽲����ٲ�Ϊֹ)��
 * 		�ٽ���ЩС��������������л���(join)��
 * 
 * 	2.ע�⣺
 * 		2.1 �������Ҳ����Ҫʱ��ģ�������Ҫ���ص�ȥ�趨�ٽ�ֵ��
 * 		2.2 �����ʱ����ֵ�Ĵ�СҲ�й�ϵ���������ֵԽ��Fork/Join��ܲ��ȥ������ٶ�Խ�Ǳȵ��߳�ȥִ������ʱ�Ŀ졣
 * 
 * 	3.���ԣ��ֱ����������ַ�ʽ���������ļ��㹤����
 * 		��1����Fork/Join�����������㡣
 * 		��2����һ����ͨforѭ��ȥ���������������
 * 		��3����java8 ����������������ļ�������
 * 		���Խ����java8 �����Ե��ٶ���졣������Ҫ�������ֵԽ��Fork/Join�����ٶȵ�����ҲԽ�����֣�������java8 �����Եļ����ٶȲ���ˡ�
 * 			���������ֵ��С��ʱ����ͨforѭ���ļ������Ƹ���������ɺ͵�2��˵������ϡ�
 * 		���ԣ������ҵ������java8�ģ�����java8����ɴ�����ļ���������ʵģ���Ϊʹ��Fork/Join�Ƚ��鷳�������Լ���֣�
 * 			�����ֵ��߼��ܸ��ӣ������׳������鷳��
 * 		ע��java8�ǵײ�ʵ���������Ż������������CPU��
 */
public class A10_3ForkJoinPool {
	
	/**
	 * ��Fork/Join������������
	 */
	public static void main(String[] args) {
		Instant start = Instant.now();
		
		ForkJoinPool pool = new ForkJoinPool();
		
		//��ֵ�ֱ�Ϊ��100000000�� ��10000000000 ����500000000000
		ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 50000000000L);
		
		Long sum = pool.invoke(task);
		
		System.out.println(sum);
		
		Instant end = Instant.now();
		
		//����������ֵ�Ĳ�ͬ������ʱ��ֱ�Ϊ����69�� ��2050���������Ǵ�ģ�����long�ķ�Χ��-5340232216128654848�����ǲ�Ӱ�쿴�����ʱ�� ��14459��ͬ�ڣ�������Ҳ�Ǵ�ģ�
		System.out.println("�ķ�ʱ��Ϊ��" + Duration.between(start, end).toMillis());
	}
	
	/**
	 * ���ԣ���һ����ͨforѭ��ȥ���������������
	 */
	@Test
	public void test1(){
		Instant start = Instant.now();
		
		long sum = 0L;
		
		//��ֵ�ֱ�Ϊ��100000000�� ��10000000000 ����50000000000
		for (long i = 0L; i <= 50000000000L; i++) {
			sum += i;
		}
		System.out.println(sum);
		
		Instant end = Instant.now();
		
		//����������ֵ�Ĳ�ͬ������ʱ��ֱ�Ϊ����36�� ��3363(�������Ǵ�ģ�����long�ķ�Χ��-5340232216128654848) ��16853
		System.out.println("�ķ�ʱ��Ϊ��" + Duration.between(start, end).toMillis());
	}
	
	/**
	 * ��java8 ����������������ļ�������
	 */
	@Test
	public void test2(){
		Instant start = Instant.now();
		
		//��ֵ�ֱ�Ϊ��100000000�� ��10000000000 ����50000000000
		Long sum = LongStream.rangeClosed(0L, 10000000000L)
							 .parallel()
							 .reduce(0L, Long::sum);
		
		System.out.println(sum);
		
		Instant end = Instant.now();
		
		//����������ֵ�Ĳ�ͬ������ʱ��ֱ�Ϊ����84�� ��1726(�������Ǵ�ģ�����long�ķ�Χ��-5340232216128654848) ��8555
		System.out.println("�ķ�ʱ��Ϊ��" + Duration.between(start, end).toMillis());
	}
}

/**
 * ʹ��Fork/Join��ܣ���Ҫ�̳�RecursiveTask<��>�ࡣ
 * ��������ΪС����������������ļ��㹤����
 */
class ForkJoinSumCalculate extends RecursiveTask<Long>{

	private static final long serialVersionUID = -259195479995561737L;
	private long start;
	private long end;
	private static final long THURSHOLD = 10000L;  //�ٽ�ֵ
	
	public ForkJoinSumCalculate(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		long length = end - start;
		
		if(length <= THURSHOLD){//�����Ѿ������ٲ���ˣ��Ǿ�ֱ�ӽ���С����ļ��㹤��
			long sum = 0L;
			
			for (long i = start; i <= end; i++) {
				sum += i;
			}
			return sum;
		}else{//�����ܽ��в�֣��Ǿͼ����������
			long middle = (start + end) / 2;
			
			ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle); 
			left.fork(); //���в�֣�ͬʱѹ���̶߳���
			
			ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle+1, end);
			right.fork(); //���в�֣�ͬʱѹ���̶߳���
			
			return left.join() + right.join();//��������л���
		}
	}
}