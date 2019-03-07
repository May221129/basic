package thread.a07juc;

/**
 * 一、原子性问题
 * 	1."a = i++"在计算机底层的实际操作：
 * 		int temp = i;
 * 		i = i + 1;
 * 		a = temp;
 * 二、原子变量：jdk1.5后，java.util.concurrent.atomic包下提供了常用的原子变量。
 * 	1.原子变量的特点：
 * 		①用volatile修饰，保证内存可见性（如：java.util.concurrent.atomic.AtomicInteger）
 * 		②用CAS（Compare And Swap）算法，保证数据的原子性。
 * 			CAS算法是硬件对于并发操作共享数据的支持。
 * 			CAS算法包含三个操作数：内存值 V、预估值 A、更新值 B。仅当V == A时，V = B，否则将不做任何操作。
 * 			CAS算法的效率要高于同步锁，因为当V ！= A时，当前线程不会被挂起也不会放弃CPU的执行权，而可以马上再进行判断（通过for循环实现）。
 * 		③CAS算法的测试见：thread.a07juc.A01Atomic1
 * 		④自己实现CAS见：thread.a07juc.A01MyCAS2
 */
public class A01Atomic {
	public static void main(String[] args) {
		int i = 10;
		int a = i++;
		System.out.println(i);//11
		System.out.println(a);//10
	}
}