package thread.a07juc;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * jdk1.5后，java.util.concurrent.atomic包下提供了多种并发容器类来改进同步容器的性能。
 * 
 * 一、ConcurrentHashMap：线程安全的哈希表。
 * 	1.对于多线程的操作，介于HashMap与Hashtable之间。
 * 	2.内部采用“锁分段”机制代替Hashtable的独占锁，进而提高性能。
 * 		①分段锁默认是分16段，每个段（segment）都有一个独立的锁。
 * 		②可并行访问：一个段的锁只能被一个线程持有，但是支持多个线程同时访问不同的段。
 *	3.jdk 1.8，ConcurrentHashMap底层取消了锁分段机制，改用CAS，效率又更高了。
 * 
 * 二、CopyOnWriteArrayList/CopyOnWriteArraySet:写入并复制。
 * 	1.这两个容器是线程安全的，且效率高于Collections包装后的ArrayList或ArraySet。
 * 		提问：Collections包装后的ArrayList怎么就效率低了？
 * 			答：因为Collections的包装方式是容器的每个方法都用synchronized来修饰了，
 * 				这个容器全场通用一把锁，不论是读、写，所有线程都是串行执行的。
 * 		   	而CopyOnWriteArrayList/CopyOnWriteArraySet则允许同时读；涉及对容器
 * 				的写操作，那该容器底层都会复制出一个新的集合，然后再进行添加，而同时在执行读操作
 * 				的那个线程，依旧在读原集合(这是一种思想，在这种思想里，允许读线程读当前确定状态的
 * 				集合，而写线程在写时，集合的状态并非是确定的，是不稳定的。这和数据库的读已提交有点类似。)
 * 	2.“边遍历边往里添加元素”也不会出报错，因为当你每次写入时，底层都会复制出一个新的集合，然后再进行添加。
 * 		提问：①是复制原集合，还是复制其他并发线程修改过的集合？
 * 			答：复制“已提交的集合”，即状态当前是稳定状态的集合，而非正在被修改、处于不确定状态的集合。
 * 			②为什么添加进去的元素不能被遍历出来，但size确实显示已经有那么多个元素了？====>类似幻读。
 * 	3.因为2.所以注定了这两个容器不适合频繁做添加操作，开销会很大。但适合做并发多的迭代操作。
 * 		迭代操作会涉及到get元素，Collections返回的线程安全的容器，做get时是串行的，所以效率低很多。
 * 	4.是一添加就赋值给了容器？还是另外的时间赋值给容器的？ ==》 见代码中的探究4
 * 	5.为什么在遍历时同时添加了一个元素进去，list.size()有改变，但是遍历的结果却不会把这些添加进去的元素遍历出来呢？ ==》 见探究5
 */
public class A02ConcurrentCollection {
	public static void main(String[] args) {
		CopyOnWrite copyOnWrite = new CopyOnWrite();
		copyOnWrite.addElement();
		
		iterationAndAdd iterationAndAdd = new iterationAndAdd(copyOnWrite);
		onlyIteration onlyIteration = new onlyIteration(copyOnWrite);
		
		iterationAndAdd.setName("^_^iterationAndAdd");
		onlyIteration.setName("__________onlyIteration");
		
		iterationAndAdd.start();
		onlyIteration.start();
	}
}

class onlyIteration extends Thread{
	
	private CopyOnWrite cw;
	
	public onlyIteration(CopyOnWrite cw) {
		this.cw = cw;
	}
	
	@Override
	public void run() {
		cw.onlyIteration();
	}
}

class iterationAndAdd extends Thread{
	
	private CopyOnWrite cw;
	
	public iterationAndAdd(CopyOnWrite cw) {
		this.cw = cw;
	}
	
	@Override
	public void run() {
		cw.iterationAndAdd();
	}
}

class CopyOnWrite{
	/**
	 * 需求：遍历集合时，同时往集合中添加元素。
	 * 如果这里用的不是CopyOnWriteArrayList，而是ArrayList，就会报并发修改异常。
	 * 用了CopyOnWriteArrayList就可以完成边这个需求。
	 */
	private CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
	public void addElement(){
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
	}
	public void iterationAndAdd() {
		System.out.println(Thread.currentThread().getName() + " --> " + "list.size() = " + list.size());
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			System.out.println(Thread.currentThread().getName() + " --> " + iterator.next());
			/**
			 * 探究4：是一添加就赋值给了容器？还是另外的时间赋值给容器的？
			 * 	答：一添加就赋值给了容器。
			 * 探究5：为什么在遍历时同时添加了一个元素进去，list.size()有改变，但是遍历的结果却不会把这些添加进去的元素遍历出来呢？
			 * 	答：Iterator<String> iterator = list.iterator();这里的iterator是拿到了集合的快照来进行遍历（依据：
			 * 		可以看源码，且源码的注解也有说明），就算往集合中添加了元素，但是iterator的快照也不会改变，所以遍历结果也不会变。
			 * 	注：CopyOnWrite容器的迭代器和普通集合的迭代器是有区别的，这里探究的是Copy容器的迭代器。
			 */
			list.add("AAA");
			
			System.out.println(Thread.currentThread().getName() + " --> " + "list.size() = " + list.size());
		}
		
		System.out.println("----------------------");
	}
	
	public void onlyIteration() {
		System.out.println(Thread.currentThread().getName() + " --> " + "list.size() = " + list.size());
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			System.out.println(Thread.currentThread().getName() + " --> " + iterator.next());
			
			System.out.println(Thread.currentThread().getName() + " --> " + "list.size() = " + list.size());
		}
		
		System.out.println("***********************");
	}
}
