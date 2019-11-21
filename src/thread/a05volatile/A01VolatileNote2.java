package thread.a05volatile;

/**
 * volatile总结【老公的笔记】：
 * 1:重排序： 如果两个操作没有数据性依赖，处理器会对这两个操作进行重排序， 在单线程中最终结果是一致的， 但是在多线程中， 重排序行为可能会导致执行结果不一致
 * 		解决点： 重排序问题要刨底。
 * 		在文档    线程基础知识点.docx中的第六点有笔记
 * 
 * 2：定律:线程对变量的所有操作(取值，赋值等)都必须在工作内存中进行，而不能直接读写住内存中变量。 不同工作内存也不能相互访问
 * 		对于volatile变量来说，由于它特殊的操作顺序性规定，看起来如同操作主内存一般，但实际上 volatile变量也是准许这这一定律。
 * 
 * 3：(主内存和工作内存)与(堆，虚拟机栈，方法区等)并不是同一个层次的内存划分，明显， 主内存和工作内存划分的比较泛泛。 
 * 		如果要勉强对应上的话， 主内存对应于堆中对象的实例数据部分， 工作内存对应于虚拟机栈中的部分区域。
 * 		要更底层一点：主内存对应于硬件的内存，而为了获取更好的运行速度，虚拟机及硬件系统可能会让工作内存优先存储于寄存器或者高速缓存中
 * 
 * 4：关于主存与工作内存之间具体的交互协议，即一个变量如何从主存拷贝到工作内存、如何从工作内存同步到主存之类的实现细节，
 * 		java内存模型中定义了以下八种操作来完成：
 * 		
 * 		lock:(锁定), unlock(解锁), read(读取), load(载入), use(试用), assign(赋值), store(存储), write(写入)		
 * 
 *   	关于这八种操作的定义，以及使用它们的限定，看thread文件中的volatile文档
 * 
 * 5：volatile 对这八种操作有这特殊的限定，正因为有这些限定才让volatile修饰的变量有可见性 以及 可以禁止指令重排序的问题  
 * 		5.1 可见性(详细的可见性动作  未知)
 * 
 * 			5.1.1  规则:
 * 			规则1：use动作之前必须要要有read和load动作， 这三个动作必须是连续出现的。
 * 				表示：每次工作内存要使用volatile变量之前必须去主存中拿取最新的volatile变量
 * 
 * 			规则2：assign动作之后必须跟着store和write动作，这三个动作必须是连续出现的。
 * 				表示: 每次工作内存改变了volatile变量的值，就必须把该值写回到主存中
 * 			【注：不加volatile就代表动作不一定是连续的动作】
 * 			有如上两条规则就能保证： 每个线程每次去拿volatile变量的时候，那个变量肯定是最新的， 其实也就相当于好多个线程用的是同一个内存，无工作内存和主存之分。
 * 
 * 			从规则1,2。 总结是两点规则， 一是：用到变量的时候每次都要读，改变值的时候每次都要写。 二是：各自的三个动作都是连续的。 从第一个规则我它是防止
 * 			可见性的重要规则。 第二个规则要求连续(不连续的情况 read a, read b, load b, load a, use b, use a . 
 * 			连续的情况: read a, load a, use a.)，场景：线程1改变了值a, 线程2想要读取a, 如果线程1改变的时候 assign,store,write不连续的话, 线程2就可能穿插到其中,
 * 			导致线程1, 线程2哪到的a是不同的.
 * 			
 * 			5.1.2 volatile建议被使用的场景
 * 			private volatile boolean flag = ture;
 * 			public void doSomething(){
 * 				while(flag){
 * 					//doSomething.....
 * 				}
 * 			}
 * 			public void setFlag(boolean f){
 * 				this.flag = flag;
 * 			}
 * 			 首先讲讲，上面这个例子如果没有加上volatile关键字为什么就有可见性问题
 * 				从两方面来讲:
 * 				1:每次判断flag的值，如果不是每次去主存中取最新的值就会产生不可见问题
 * 				2:就算每次都去拿，如果setFlag改完之后不马上存到主存中，也是会产生不可见问题
 * 			那么什么时候会去主存中重新拉一遍值呢？ 加volatile是一种， 有没有其他的呢  肯定有， 比如CPU怀疑人生了的情况(其他情况不知且重要)。
 * 			什么时候会主动存到主存中，除了volatile, synchronize代码块也会有这样的效能，具体不知(其他情况不知且重要)。
 * 			
 * 			5.1.3  用volatile修饰i++操作不可行的原因
 * 			private volatile int i = 5;
 * 			public void doSomething(){
 * 				i++;
 * 			}
 * 			两个线程同时取到了i=5， 然后各自操作，然后写入， 本应是7，却得到6. 用了volatile毛用没有。 把i想象成就只有主存中结果也是一样的。
 * 			如果使用lock unlock应该可以让i++变成原子性， 不过还没涉及到。
 * 			当然用synchronize或者原子变量就可以做到。
 * 
 *     	5.2 禁止指令重排序(重排序规则模糊，   双判断优化的单例模式有重排序隐患可借鉴   类：F03SingletonDoubleCheck)
 * 			
 * 
 * 6: 用volatile修饰对象是什么情况。    (感觉其实都是对象的引用被拷贝到工作内存了, 看看以往的例子, 貌似有这种感觉哦  ：）)
 * 		6.1 最开始的认识： 
 * 			保存到工作内存的是对象的堆地址，不管对地址如何的操作都会直接影响到内存，所以认为用不用volatile修饰都是一样的
 * 		6.2  如下例子： People如果不用volatile修饰，那么线程就跳不出while循环。 说明volatile修饰对象是有用的。
 * 		6.3 现在的认识：
 * 			6.3.1: volatile People people;保存到工作内存的确实是people的地址, 符合所有以上对volatile的总结
 * 			6.3.2: 不管有没有加volatile修饰people,people.setAge()的时候确实直接改的是堆中的内存真实内容. 因为线程中保存的是地址
 * 			6.3.3: while (people.getAge() == 10),people.getAge()这个动作肯定也是去主存中找到age值
 * 				最开始想的时候这里想的想当然了:认为既然是引用,那么getXXX的时候肯定也是直接去主存中拿最新值。   
 * 				但是其实：这个引用主存中有一个,工作内存中也有一个,线程第一次用age的时候肯定会从主存中搞一个age过来,
 * 				但是接下来想要use的时候会不会read和load就得看这个字段或者这个字段所在的对象有没有被volatile修饰。
 * 				如果是直接修饰该字段的话,那就很明显了,该字段在每次使用的时候都会从主存中拿一遍. 
 * 				如果是修饰该字段所在的对象的话, 猜测:每次使用该对象的属性的时候都会从主存中拿一遍。
 * 				
 * 			6.3.4 被volatile修饰的类嵌套问题可见类A03TestVolatile2,里面有详述
 * 		6.4 补充点   以下面代码为例：
 * 			MyThread其实就是堆中的一个对象, 一个线程有了自己的工作内存之后应该也会有MyThread这个对象的引用。 相对于People,MyThread只不过
 * 			不能被volatile修饰而已。
 * 			(该补充点是补充说明, 如果MyThread类中有成员变量的情况)
 * 		6.5 总结
 * 			被volatile修饰的对象,被线程copy的确实只是地址,所以在用对象设值属性的时候,是会直接作用到堆中的对象属性(这也只是猜测,当然也有可能
 * 			该对象的某属性先被复制了到工作内存中,然后改工作内存中的,最后在某个时机同步回主存)。  在get某属性的时候就会实时地从主存中拿到该属性的最新值。
 * 			也就是说被volatile修饰的对象中的属性是按需读取到工作内存中的。
 * 
 * 7: 关于数组的结论:volatile的数组只针对数组的引用具有volatile的语义，而不是它的元素
 * 
 * 8: 线程何时会从主存中拿最新的值
 * 		场景: while(){ 这里面执行了稍微耗时的操作}, 然后不可见的现象就重现不出来。 在while的方法体为空或者进行几乎不耗时的操作,不可见现象才能重现。
 * 		原因:CPU在执行while判断后,执行的时间过长,就会判定自己持有的缓存可能不是最新的,然后就会自动到主存中拿最新的值。
 * 			所以,在while方法体中做几乎不耗时的操作即可重现不可见现象。  但是,while方法体为空也不能保证CPU不会重新去更新缓存,
 * 			因为CPU可能还会执行其他的任务,然后一切换回来,还是会去重新拿值。
 * 		
 * 		总结：这样不可控的情况可能还有很多, 所以只要有不可见的场景,就要加上volatile, 让代码变得可控。
 * 		对于何时写回主存也是一样, volatile和synchronize算是可控的, 可能还有其他情况是不可控的,所以可见性和有序性有问题的地方都要进行主动处理。
 * 
 * 9:jvm规定的原子操作有:
 * 		all assignments of primitive types except for long and double
		all assignments of references
		all operations of java.concurrent.Atomic* classes
		all assignments to volatile longs and doubles
		
		double long类型的操作可能是非原子的.
		
		那么为什么用volatile去修饰long或者double,就变成原子的嘞?
			规范规定:
				实现对普通long与double的读写不要求是原子的（但如果实现为原子操作也OK）
				实现对volatile long与volatile double的读写必须是原子的（没有选择余地）
		
		现实中:
			1:long 在 32位jre8、jre9 上运行是非原子的，会出现写高低位的问题。
			2:在64位的jvm上 double long 事实上的确能保证原子性
			3:JDK9中包含了 -XX:+AlwaysAtomicAccesses 参数以实现对所有Access的原子性保证（我的测试情况来看，只保证64位jdk的情况，32使用此参数依然无效）
			4:具体看：https://www.zhihu.com/question/38816432
			
   10:由初始化器赋予的变量, 是对所有线程可见的, 不用volatile修饰.
   	比如static People p = new People();  当然了如果只是声明而不是初始化(没new), 就不能保证了.
   	还比如static{ p = new People();}
   	详细看F04FinalSingleton.java
 */

public class A01VolatileNote2 {
	public static void main(String[] args) {
		People people = new People();

		Thread t1 = new MyThread(people);

		t1.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		people.setAge(20);
		
	}
}

class MyThread extends Thread {
	private volatile People people;

	public MyThread(People people) {
		this.people = people;
	}

	@Override
	public void run() {
		while (people.getAge() == 10) {
//			System.out.println("10岁的小萝莉");
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
}



class People {
	private int age = 10;

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}
}
