package collection.e_collections;
/**
 * 操作集合(Colletion和Map)的工具类：Colletions
 * 作用：Collections.synchronizedList(list类型的集合)，通过这一操作，让list类型的集合成为线程安全的集合。
 * 同理：对于map和set类型也是一样。
 * 
 * 把传进去的集合进行包装了一下，传进去的集合是作为成员变量的。新new出来的集合，也实现了和传进去的集合的类型一样的接口，所以他们都有同样的方法。
 * 
 * 以List类型的a2为例：
 * 为什么经过“Collections.synchronizedList(a2);”操作之后，返回的集合就是线程安全的了呢？
 * 1.synchronizedList()方法，将a2传给了SynchronizedRandomAccessList或SynchronizedList。
 * 	并返回SynchronizedRandomAccessList或SynchronizedList的对象；
 * 		public static <T> List<T> synchronizedList(List<T> list) {
        	return (list instanceof RandomAccess ?
                new SynchronizedRandomAccessList<>(list) :
                new SynchronizedList<>(list));
    	}
 * 2.list instanceof RandomAccess ?假设结果是false，new了new SynchronizedList<>(list))。
 * 	SynchronizedList将a2作为成员变量【代码以SynchronizedList的来说明】：
 * 		final List<E> list;

        SynchronizedList(List<E> list) {
            super(list);
            this.list = list;
    	}
 * 3.因为SynchronizedList实现了List接口，所以List里定义的方法，他们也定义了：
 * 		static class SynchronizedList<E>
        	extends SynchronizedCollection<E>
        	implements List<E> {...}
 * 4.他们将a2的所有方法用synchronized进行同步，实现线程安全：
 * 		public void add(int index, E element) {
            synchronized (mutex) {list.add(index, element);}
        }
        public E remove(int index) {
            synchronized (mutex) {return list.remove(index);}
        }
        ...
 * 5.因为所有的方法都用synchronized同步了，所以最后返回的集合是线程安全的。
 * 6.注：SynchronizedRandomAccessList是SynchronizedList的子类
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

public class TestColletions {
	@Test
	public void testCollections1(){
		ArrayList al = new ArrayList();
		al.add("A");
		al.add(1);
		al.add("B");
		al.add(2);
		System.out.println("输出集合中的所有元素：");
		System.out.println(al);
		System.out.println("用增强for循环遍历集合：");
		for(Object o : al){
			System.out.println(o);
		}
		System.out.println("用迭代器Iterator遍历集合中的所有元素：");
		Iterator i = al.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
		System.out.println("使用Collections工具类中的各种方法：");
		Collections.reverse(al);//反转al集合中的元素的顺序
		System.out.println(al);
		Collections.shuffle(al);//对al集合元素进行随机排序
		System.out.println(al);
//		Collections.sort(al);//根据元素的自然顺序对al集合元素按升序排序
//==>注意：集合中的元素必须是同一类型的，否则无法进行比较排序
//		System.out.println(al);
		Collections.swap(al, 0, 1);//将指定al集合中的两处元素进行交换
		System.out.println(al);
		
		System.out.println("实现ArrayList集合的复制：");
//		ArrayList a2 = new ArrayList();//错误的复制方式
//		Collections.copy(a2, al);
		List a2 = Arrays.asList(new Object[al.size()]);
//==>注意，新建的集合为什么一定要是List类型，而能使ArrayList类型？
		Collections.copy(a2, al);
		System.out.println(a2);
		
		System.out.println("线程安全处理");
		Collections.synchronizedList(a2);//这样操作过后，多线程操作时就是安全的了。
	}
}
