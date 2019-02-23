package collection.d_queue;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

/**
 * Queue接口：
 * 		offer(E e):
 * 			添加元素. 成功返回true, 失败返回false.
 * 		add(E e):
 * 			添加元素. 如果队列满了, 他么还会报异常. 
 * 			AbstractQueue的add方法实现是调用offer方法, 成功返回true, 失败报异常.
 * 		
 * 		peek():
 * 			获取队列的头，但不移除此头,如果此队列为空，则返回 null.(具体实现类实现)
 * 		element():
 * 			获取队列的头，但不移除此头。 
 * 			AbstractQueue的element方法实现是调用peek方法, 成功返回E, 失败报异常.
 * 
 * 		poll():
 * 			获取队列的头，并移除此头,如果此队列为空，则返回 null.(具体实现类实现)
 * 		remove():
 * 			取队列的头，并移除此头。如果队列为空,会抛异常.
 * 			AbstractQueue的remove方法实现是调用poll方法, 成功返回E, 失败报异常.
 * 
 * 		AbstractQueue是大多数队列的父类,跟BlockingQueue接口同级, 一样实现Queue接口.
 * 			所以重要的offer,peek,poll方法都在其子类中实现.
 * 
 * 		普通队列的实现可以是Object数组, 也可以是链表等等.
 * 
 * BlockingQueue接口:
 * 		Queue是两种结果, 一种是返回特殊值,一种是抛异常.
 * 		BlockingQueue有四种情况:
 * 				抛出异常		特殊值 		阻塞			超时
 * 		插入:		add(e)		offer(e)	put(e)		offer(e, time, unit)
 * 		移除:		remove()	poll()		take()		poll(time, unit)
 * 		取但不移除:	element()	peek()		不可用		不可用
 * 
 * 		其他API：
 * 		    boolean contains(Object o):
 * 		    	判断是否存在
 * 		    int drainTo(Collection<? super E> c):
 * 				移除此队列中所有可用的元素，并将它们添加到给定 collection中
 * 			int drainTo(Collection<? super E> c, int maxElements)
 * 				最多从此队列中移除给定数量的可用元素，并将这些元素添加到给定 collection中
 * 			int remainingCapacity()
 * 				该队列仍然有多少空间可以存放元素.
 * 			
 * 		该接口的实现类:
 * 			ArrayBlockingQueue:
 * 				1:用Object[]实现, 所以该队列长度是定长.
 * 				2:该队列默认是非公平的, 可以通过制定的构造方法将其变成公平的队列.具体实现是new的ReentrantLock是否是公平的
 * 				3:其阻塞是通过两个condition来实现的,notEmpty和notFull
 * 					比如:
 * 						put方法:队列满了.就调用notFull.await()方法.
 * 						poll方法:它会调用dequeue方法,该方法中会调用notFull.notify()方法来唤醒.
 * 				4:其过程是线程安全的, 都是用ReentrantLock对象来锁操作.
 * 				5:其超时也是用的Condition来做的, 用的condition的awaitNanos方法, 来让线程等待一段时间.
 * 					这个超时实现跟FutureTask的超时实现不一样. futureTask的超时好像是用LockSupport和UNSafe来做的(其实condition的awaitNanos方法也是调用的LockSupport来做的).
 * 				
 * 				补充:数组是如何实现队列的?
 * 					答:有一个putIndex成员变量, 会不断的往array.length的方向挪动, 当挪到array.length的时候, 将putIndex置0, 周而复始.
 * 
 * 			LinkedBlockingDeque:
 * 				1:用自定义的双向链表来实现, 默认长度是Integer.MAX_VALUE, 也可以自定义其长度.
 * 					(LinkedBlockingDeque和PriorityBlockingQueue总是有一定长度的,但LinkedBlockingDeque会被限定,也就是人为封顶,但是
 * 					PriorityBlockingQueue没有人为封顶, 当它超过Integer.MAX_VALUE-8的时候则人为内存溢出了).
 * 					虽然链表的长度是可扩展的,但是代码限制死了,如果超出其长度要么返回false,要么会阻塞.
 * 				2:用的非公平的ReentrantLock,所以它是非公平队列.
 * 				3:它的锁操作和阻塞操作和ArrayBlockingQueue一样.
 * 				4:相比于ArrayBlockingQueue, 它可以从头部和尾部，添加或者删除.
 * 					
 * 			LinkedBlockingQueue:
 * 				1:跟LinkedBlockingDeque基本一样,下面说一下其不一致的地方
 * 				2:其实现是单向链表,也就只能先进先出.
 * 				3:用了两个ReentrantLock, 一个是控制入队的,另一个是控制出队的.
 * 					用两个ReentrantLock实现更加细粒度的锁控制.
 * 					为什么不会出现线程安全问题:
 * 						出队和入队操作的成员变量不同(链表入队和出队的时候操作分别是头和尾的node,所以互不相干),也就不会有线程安全问题,
 * 						两种操作唯一控制相同的东西就是计数(count),LinkedBlockingQueue将其声明称AtomicInteger,代码中对其操作也都是原子的.
 * 						
 * 			PriorityBlockingQueue:			
 * 				1:用的数据结构:堆.(用一维数组模拟堆的数据结构).
 * 					所以在添加元素,删除元素的时候就会出现堆操作:
 * 						上浮 shift_up
 * 						下沉 shift_down
 * 						等等
 * 						对应于源码:
 * 							siftUpComparable		:默认排序的上浮操作
 * 							siftUpUsingComparator	:指定排序的上浮操作
 * 							siftDownComparable		:默认排序的下沉操作
 * 							siftDownUsingComparator	:指定排序的下沉操作
 * 
 * 				2:其成员变量中就有Comparator,来比较队列元素的大小关系.
 * 					如果不设置Comparator, 也可以让放进去的元素实现Comparable接口.
 * 					如果两者都没有的话, 使用这个队列就会报错. 因为它没办法判定大小.
 * 				3:默认长度是11,当然也可以指定其初始长度.
 * 				4:该队列是不限制总大小的. 如果一直offer元素进去,当超过其容量的时候会进行扩容操作(tryGrow)
 * 					但是超过了Integer.MAX_VALUE - 8 的限制也是会报OutOfMemoryError的. 相比于LinkedBlockingQueue,它的这个限制是无法改变的.
 * 				5:也因为该队列不限制其总大小, 所以condition就只有一个. 
 * 					对于有限制大小的队列,condition本应该有两个,例子:
 * 					场景1:t1 put的时候,发现满了,就await住自己.等待t2线程poll成功的时候singal一下.(这是一个Condition)
 * 					场景2:t1 put的时候成功了, 就会顺道singal一下, 因为可能会唤醒其他想要take出元素的t2.	(这是另一个Condition)
 * 						注意:只有take,put等会阻塞的方法才会调用await, 但是不管是阻塞或者非阻塞的方法都会调用singal
 * 
 * 					对于无界队列:
 * 						put的时候就不会阻塞(因为无界),所以就也不存在poll的时候要singal一下(也就是第一个condition就没有用了)
 * 
 * 				6:像这种无限容量的队列,自然也用不到阻塞方法
 * 					offer(E e, long timeout, TimeUnit unit)的内容是直接调用offer(e).
 * 					包括put方法, 也是直接调用的offer
 * 				7:为什么要用堆做数据结构,因为堆要么是最大,要么是最小在根处, 每次要取就是取根就行,不会从堆中抽一个元素出来.
 * 					这跟队列的特性很像,具体就没研究了.			
 * 	
 * 			DelayQueue:
 * 				1：DelayQueue = BlockingQueue + PriorityQueue + Delayed
 * 					BlockingQueue(阻塞):跟PriorityBlockingQueue一样的锁机制,因为它用的就是PriorityQueue,所以它肯定是无界的.且Condition对象只有一个.
 * 					PriorityQueue:有一个PriorityQueue成员变量,也就是说DelayQueue实际上操作的就是一个优先级队列(非阻塞).
 * 					Delayed:严格要求该队列放进去的对象必须是继承自Delayed接口.该接口又实现了Comparable接口. 所以该对象能互相比较,也能得到过期时间.	
 * 				2:add, offer, put, offer(E e, long timeout, TimeUnit unit):
 * 					塞元素到队列中,几乎是和PriorityBlockingQueue一样的. 也就是跟无界阻塞队列一致.
 * 					只不过offer中的，一个判断有点疑惑,还会操作线程。。。详细了解下.
 * 				3:poll
 * 					不仅仅队列为空的时候,返回值为空, 就算拿到元素,但是元素的过期时间还没到,也会返回null.
 * 				4:take
 * 					不仅仅队列为空的时候,会阻塞住,  就算拿到元素,但是元素的过期时间也会阻塞住,知道到了过期时间,才会take出来.
 * 				5:poll(long timeout, TimeUnit unit)
 * 					在take的基础上,再限制了这个程序会等待多久(这个等待时间 = 队列为空的时间 + 元素的过期时间)
 * 				6:看poll,take,pull(timeout)就知道DelayQueue的区别其他阻塞队列的是:
 * 					就是其元素也有过期时间, 在取出的时候要一起要考虑到元素的过期时间, 如果元素还未过期,就不让取出来.
 * 				7:因为元素附加的阻塞时间,而引发的思考:
 * 					看源码:
 * 						成员变量:private Thread leader = null;
 * 						offer方法.take方法,poll(timeout)都会用到leader
 * 					解释源码take(多个线程来获取资源的时候):
 * 						线程t1想要take元素, 结果被元素自身的过期时间阻塞住了available.awaitNanos(delay).		
 * 						t1释放了锁(因为awaitNanos).	这时候t2就可以进入take方法,t2也想要获取一个元素. 
 * 						t2和t1一样peek到同一个元素,然后因为leader的值不等于当前线程,所以就available.await();
 * 						t1的awaitNanos到期之后,t1就去poll元素, ,并available.signal();
 * 						t2就被唤醒,然后去peek下一个元素,继续如上流程.
 * 						
 * 						因为延迟队列加上了元素的延迟时间,看它take方法的逻辑就知道这个额外的等待时间可能会导致线程等待不必要的时间.
 * 						这个是leader的作用就是为了约束各个线程的流程(对leader的操作就是赋值当前线程对象,以及判空).
 * 						具体leader的作用可以看take方法以及它的声明上面的解释.
 * 				8:注意点,Delayed对象里面的getDelay方法返回的只是long类型(没有指明时间类型),在DelayQueue延时的时候是按纳秒的.
 * 					也就是说如果getDelay方法自以为是按毫秒或者是秒的话,会照成take等方法空转次数飚高,浪费cpu资源.
 * 					可以搜一下Delayed的实现类. 看看人家的getDelay方法的实现方式.
 * 				9:DelayQueue运用场景:
 * 					a) 关闭空闲连接。服务器中，有很多客户端的连接，空闲一段时间之后需要关闭之。
 *					b) 缓存。缓存中的对象，超过了空闲时间，需要从缓存中移出。
 *					c) 任务超时处理。在网络协议滑动窗口请求应答式交互时，处理超时未响应的请求。
 *					想象一下过期缓存怎么做: 搞一个线程持续不断的调用delayqueue的take方法. 就可以不断的把过期的元素去掉.
 *						注意delayQueue里面是优先级队列. 始终会把最大或者最小元素放在根部. 所以重点就是Delayed元素的实现.
 *						注意如果这个空闲连接被重新使用了,那么就会被移出延时队列.具体做法可以看remove(Object obj),大概就是先遍历这个数组(堆)
 *							然后找到之后就删除掉这个元素, 然后再去调整堆的平衡.
 *
 *					代码实例:https://www.cnblogs.com/jobs/archive/2007/04/27/730255.html
 *					
 * 			SynchronousQueue:	
 * 				SynchronousQueue，实际上它不是一个真正的队列，因为它不会为队列中元素维护存储空间。
 * 				SynchronousQueue是一个没有数据缓冲的BlockingQueue，生产者线程对其的插入操作put必须等待消费者的移除操作take，反过来也一样。
 * 				在jdk1.6以后采用的是无锁算法,所以效率更高. 之所以能采用无锁算法大概是因为没有数据缓冲的原因.
 * 					所以也就是说如果有有尝试适合SynchronousQueue和ArrayBlockingQueue的时候,就去选择效率更高的SynchronousQueue.
 * 				下面有一个单元测试以及一些小总结:
 * 					testSynchronousQueue
 * 
 * 				具体实现:
 * 					1:有一个内部抽象类Transferer,它有两个子类	
 * 						分别是TransferQueue(基于队列实现)和TransferStack(基于栈实现)
 * 					2:SynchronousQueue的操作实际上都是用的Transferer(构造方法就是new 一个TransferQueue或者TransferStack).
 * 						公平锁:TransferQueue   非公平锁:TransferStack
 * 					3:offer,put,offer(timeout),poll,take,poll(timeout)方法
 * 						都是调用的Transferer的transfer(E e, boolean timed, long nanos)方法.
 * 						offer,poll:是不允许等待(第二个参数为false).
 * 						put,offer(timeout),take,poll(timeout):是要阻塞(第二个参数为true).
 * 						也就是说offer,poll方法是很可能直接失败的.
 * 
 * 				优点:
 * 					如果以洗盘子的比喻为例，那么这就相当于没有盘架，而是将洗好的盘子直接放入下一个空闲的烘干机中。这种实现队列的方式看似很奇怪，
 * 						但由于可以直接交付工作，从而降低了将数据从生产者移动到消费者的延迟.
 * 					直接交付方式还会将更多关于任务状态的信息反馈给生产者。当交付被接受时，它就知道消费者已经得到了任务，而不是简单地把任务放入一个队列.
 * 
 * 				适合场景:
 * 					因为SynchronousQueue没有存储功能，因此put和take会一直阻塞，直到有另一个线程已经准备好参与到交付过程中。
 * 						仅当有足够多的消费者，并且总是有一个消费者准备好获取交付的工作时，才适合使用同步队列
 * 				实现1:
 * 					Java 6的SynchronousQueue的实现采用了一种性能更好的无锁算法.
 * 					其节点有三种状态:
 * 						持有数据 – put()方法的元素
 *						持有请求 – take()方法
 *						空
 *					这个算法的特点就是任何操作都可以根据节点的状态判断执行，而不需要用到锁。
 *					入列和出列都基于Spin和CAS方法。
 *					以上并没有看源码,只是摘自博客:http://ifeve.com/java-synchronousqueue/
 *
 * 				实现2:是信号量来实现同步队列:(信号量实际上是由AQS来实现的,AQS就会设计到线程的挂起)
 * 					在thread01.A04Semaphore中
 * 
 * 用AQS的知识来描述ArrayBlockingQueue:
 * 		解释:一个个线程操作take,put的时候先lock,进了AQS队列, 然后遇到比如队列满了,或者空了,就会调用condition.await进入了相应
 * 			的条件队列中,当条件队列中的线程被唤醒的时候又会重新进入AQS队列中.
 * 					
 *   |--------------------------------------------------|
 * 	 |			   	|-------->condition(take队列)------> 	|
 * 	 |->AQS队列------	|
 * 	 |				|-------->condition(put队列)-----	-->	|
 * 	 |--------------------------------------------------|												|
 * 
 * @author lgr
 *
 */
public class A01Queue {

	/**
	 * 现象：
	 * 		10个线程全部在take的时候阻塞住了.
	 * 		put线程, 每隔1秒put一下, 对应于一个take会唤醒(并非要put10次之后,take才全部唤醒).
	 * 		唤醒的take是无序的,这取决于创建的SynchronousQueue是否是公平的,
	 * 			也就是new SynchronousQueue<>(true)和new SynchronousQueue<>()的区别.
	 * 			true:	队列模式
	 * 			false:	栈模式
	 * 			默认是false.
	 * 		上面锁的是take等put, 其实反之亦然.
	 * @throws Exception
	 * @author lgr
	 */
	@Test
	public void testSynchronousQueue()throws Exception{
		SynchronousQueue<String> queue = new SynchronousQueue<>(true);
		
		for(int i = 0; i < 10; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + "take");
						queue.take();
						System.out.println(Thread.currentThread().getName() + "taked");
					} catch (Exception e) {
					}
				}
			}).start();
		}
		
		for(int i = 0; i < 10; i++){
			Thread.sleep(1000);
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("put");
						queue.put("s");
					} catch (Exception e) {
					}
				}
			}).start();
		}
		
		Thread.sleep(15000);
		
	}
	
	
	/**
	 * 会出错. 因为没有给PriorityBlockingQueue设置一个Comparator.
	 * 		而且放进去的元素也不没有实现Comparable
	 * 
	 * @author lgr
	 */
	@Test
	public void testPriorityQueue(){
		PriorityBlockingQueue<P> queue = new PriorityBlockingQueue<>(1);
		
		queue.offer(new P("1"));
		queue.offer(new P("2"));
		queue.offer(new P("3"));
		queue.offer(new P("4"));
		
		
	}
	
	class P{
		private String name;
		public P(String name) {
			this.name = name;
		}
	}
}
