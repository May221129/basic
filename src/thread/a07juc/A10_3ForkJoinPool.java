package thread.a07juc;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

import org.junit.Test;

/**
 * ①利用Fork/Join框架完成大的计算任务的拆分计算。②利用java8新特性来完成大的计算任务的拆分计算。③和普通for循环完成大的计算任务相比较。
 * 
 * 	1.Fork/Join框架的作用：在必要情况下，将一个大任务拆分(fork)成若干个小任务(拆到不可再拆为止)，
 * 		再将这些小任务的运算结果进行汇总(join)。
 * 
 * 	2.注意：
 * 		2.1 拆分任务也是需要时间的，所以需要慎重地去设定临界值。
 * 		2.2 运算耗时和数值的大小也有关系，运算的数值越大，Fork/Join框架拆分去运算的速度越是比单线程去执行运算时的快。
 * 
 * 	3.测试：分别用下面三种方式来完成任务的计算工作：
 * 		（1）用Fork/Join框架来完成运算。
 * 		（2）用一个普通for循环去完成整个计算任务。
 * 		（3）用java8 新特性来完成整个的计算任务。
 * 		测试结果：java8 新特性的速度最快。但是需要计算的数值越大，Fork/Join计算速度的优势也越能体现，几乎和java8 新特性的计算速度差不多了。
 * 			当计算的数值较小的时候，普通for循环的计算优势更大。这个规律和第2点说的相符合。
 * 		所以：如果企业中有用java8的，那用java8来完成大任务的计算是最合适的，因为使用Fork/Join比较麻烦，还得自己拆分，
 * 			如果拆分的逻辑很复杂，就容易出错且麻烦。
 * 		注：java8是底层实现上有做优化，充分利用了CPU。
 */
public class A10_3ForkJoinPool {
	
	/**
	 * 用Fork/Join框架来完成运算
	 */
	public static void main(String[] args) {
		Instant start = Instant.now();
		
		ForkJoinPool pool = new ForkJoinPool();
		
		//数值分别为①100000000； ②10000000000 ；③500000000000
		ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 50000000000L);
		
		Long sum = pool.invoke(task);
		
		System.out.println(sum);
		
		Instant end = Instant.now();
		
		//根据上面数值的不同，运算时间分别为：①69； ②2050（运算结果是错的，超了long的范围：-5340232216128654848，但是不影响看运算耗时） ③14459（同②，运算结果也是错的）
		System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());
	}
	
	/**
	 * 测试：用一个普通for循环去完成整个计算任务。
	 */
	@Test
	public void test1(){
		Instant start = Instant.now();
		
		long sum = 0L;
		
		//数值分别为①100000000； ②10000000000 ；③50000000000
		for (long i = 0L; i <= 50000000000L; i++) {
			sum += i;
		}
		System.out.println(sum);
		
		Instant end = Instant.now();
		
		//根据上面数值的不同，运算时间分别为：①36； ②3363(运算结果是错的，超了long的范围：-5340232216128654848) ③16853
		System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());
	}
	
	/**
	 * 用java8 新特性来完成整个的计算任务。
	 */
	@Test
	public void test2(){
		Instant start = Instant.now();
		
		//数值分别为①100000000； ②10000000000 ；③50000000000
		Long sum = LongStream.rangeClosed(0L, 10000000000L)
							 .parallel()
							 .reduce(0L, Long::sum);
		
		System.out.println(sum);
		
		Instant end = Instant.now();
		
		//根据上面数值的不同，运算时间分别为：①84； ②1726(运算结果是错的，超了long的范围：-5340232216128654848) ③8555
		System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());
	}
}

/**
 * 使用Fork/Join框架，需要继承RecursiveTask<？>类。
 * 大任务拆分为小任务，最终完成整个的计算工作。
 */
class ForkJoinSumCalculate extends RecursiveTask<Long>{

	private static final long serialVersionUID = -259195479995561737L;
	private long start;
	private long end;
	private static final long THURSHOLD = 10000L;  //临界值
	
	public ForkJoinSumCalculate(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		long length = end - start;
		
		if(length <= THURSHOLD){//任务已经不可再拆分了，那就直接进行小任务的计算工作
			long sum = 0L;
			
			for (long i = start; i <= end; i++) {
				sum += i;
			}
			return sum;
		}else{//任务还能进行拆分，那就继续拆分任务
			long middle = (start + end) / 2;
			
			ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle); 
			left.fork(); //进行拆分，同时压入线程队列
			
			ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle+1, end);
			right.fork(); //进行拆分，同时压入线程队列
			
			return left.join() + right.join();//将结果进行汇总
		}
	}
}