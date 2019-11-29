package thread.b01JavaConcurrencyInPractic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 4.4.1 要添加一个"若没有则添加"的新原子操作——实现方式三：扩展类的功能
 * ListHelper<E>是失败的；ListHelper2<E>是成功的。
 * 
 * @author May
 * 2019年11月28日
 */
public class A04AddAtomicMethod {
	
}

/**
 * ①通过 Collections.synchronizedList(new ArrayList<>()); 得到的list是线程安全的集合，在list中全局都是用list自身作为锁。
 * ②putIfAbsent(E e) 方法中使用了synchronized来做同步，但是使用的锁是当前的ListHelper对象，而非list对象。
 * ①和②根本就没有使用同一把锁，所以，在添加"若没有则添加"的新原子操作失败，依旧可能出现线程安全问题。
 * 
 * @author May
 * 2019年11月28日
 */
class ListHelper<E>{
	private List<E> list = Collections.synchronizedList(new ArrayList<>());
	public synchronized boolean putIfAbsent(E e) {
		boolean absent = !list.contains(e);
		if(absent) {
			list.add(e);
		}
		return absent;
	}
}

/**
 * ①通过 Collections.synchronizedList(new ArrayList<>()); 得到的list是线程安全的集合，在list中全局都是用list自身作为锁。
 * ②putIfAbsent(E e) 方法中使用了synchronized（this）来指定使用的锁是list对象。
 * ①和②使用的是同一把锁，所以，添加"若没有则添加"的新原子操作成功。
 * 
 * @author May
 * 2019年11月28日
 */
class ListHelper2<E>{
	private List<E> list = Collections.synchronizedList(new ArrayList<>());
	public boolean putIfAbsent(E e) {
		synchronized(list) {
			boolean absent = !list.contains(e);
			if(absent) {
				list.add(e);
			}
			return absent;
		}
	}
}