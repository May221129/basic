package collection.a_note;

/**
 * 论点：在遍历过程中，使用集合的remove方法，会使程序报错，抛出异常。这是为什么？
 * 2个重点：1.底层实现-用的是哪个集合； 2.用的是那种循环方式；
 * 
 * 重点：Iterator由集合生成之后，只能使用一次。代码：A02forwhileiterator
 * 
 * 问1：为什么有Iterator？
 * 答：集合作为容器，负责存储元素。因“功能最好单一”的原因，所以有了Iterator接口。Itr继承了Iterator接口，作为集合的内部类，专门用于迭代元素。
 * 	如果没有迭代器，拿到一个多态的list，还得用instanceof去判断它属于那种集合，代码：testList2()和testList1()
 * 
 * 问2：用Iterator遍历集合时，删除某一个元素后，会报什么错？为什么？
 * 答：错误：ConcurrentModificationException（并发修改异常）
 * 原因：以ArrayList为例
 * 	1.在ArrayList的父类AbstractList中定义了一个int型的属性：modCount，记录了ArrayList结构性变化的次数：protected transient int modCount = 0;
 * 		在ArrayList的所有涉及结构变化的方法中都增加modCount的值，包括：add()、remove()、addAll()、removeRange()及clear()方法。这些方法每调用一次，modCount的值就加1。
 * 	2.Itr（问1）中也定义了一个int型的属性：expectedModCount，这个属性在Itr类初始化时被赋予ArrayList对象的modCount属性的值：int expectedModCount = modCount;
 * 		注：内部成员类Itr也是ArrayList类的一个成员，它可以访问所有的AbstractList的属性和方法。
 * 	3.在Itr.hasNext()方法中：public boolean hasNext() { return cursor != size(); }
 * 		调用了AbstractList的size()方法，比较当前光标位置是否越界。
 *  4.在Itr.next()方法中，Itr也调用了定义在AbstractList中的get(int)方法，返回当前光标处的元素：
 *  	public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }
 *  5.注意，在上面的next()方法中调用了checkForComodification()方法，进行对修改的同步检查：
 * 		final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
 * 总结：通过集合生成迭代器时，会将集合中的modCount赋值给迭代器的expectedModCount，
 * 	只是遍历集合，而不对集合进行增、删等结构性变化操作时，这两个数都是相等的。
 * 	一旦在遍历集合的过程中，通过集合的remove()方法删一个元素时，remove方法中会修改集合的modCount，但却不会去修改迭代器的expectedModCount，
 * 	所以此时modCount != expectedModCount，就会报ConcurrentModificationException错误。
 * 
 * 备注：ArrayList有add()、remove()方法，但这两个方法只会=修改集合的modCount，却不会去修改迭代器的expectedModCount；
 * 	Iterator有remove()方法，没有add()方法。Iterator的remove()方法，删除了集合的元素后，会将自身的expectedModCount和集合中的modCount同步做修改。
 * 	
 * 问3.用增强for循环来遍历集合时删除某个元素是否可行？
 * 答：不行，因为增强for循环的底层就是迭代器。
 * 
 * 问4.那如何在遍历的过程中删除某个元素后不会报错？
 * 答：用迭代器的remove()方法来删除元素，就不会报错，案例：testIterator2()
 * 	如果通过迭代器的remove()方法来删除某一元素，则迭代器的expectedModCount和集合中的modCount同步：expectedModCount = modCount;
 * 	public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
    }
 * 
 * 问5.用迭代器遍历集合时，用集合的remove()方法 删除倒数第二个元素，却不会报错，为什么？代码：testIterator1()
 * 答：
 * 
 * 问6.普通循环过程中删除一个元素为什么遍历输出会少一个元素？
 * 答：因为删除一个元素后，会导致后面所有元素都往前进一位，而i依旧会走到“被删除的元素”的后一位去，所以导致填补“被删除的元素”的那个元素不会被输出了。
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class B02Iterator_Remove {
	
	/**
	 * 如果没有使用迭代器，而拿到的集合是一个多态时:
	 * 也是可以直接遍历的呀？
	 * 因为ArrayList是实现了List接口，get(index i)这个方法，也是List接口中定义的方法，由ArrayList去实现了。所以list也能直接使用get(index i)方法。
	 */
	@Test
	public void testList2(){
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	
	/**
	 * 如果没有使用迭代器，而拿到的集合是一个多态时:
	 * 为什么要知道list是属于谁的实例呢？
	 * 因为不同的集合，数据结构不同，所以导致他们遍历的时候没有用对方式会很慢。
	 * 比如ArrayList用或许元素下标的方式来遍历会很快；LinkedList没有真正的元素下标，所以通过get(index)遍历会很慢，但通过迭代器的Next()方法来遍历会快。
	 */
	@Test
	public void testList(){
		
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		if(list instanceof ArrayList){
			ArrayList<Integer> al = (ArrayList<Integer>) list;
			for(int i = 0; i < al.size(); i++){
				al.get(i);
			}
		}if(list instanceof LinkedList){
			LinkedList<Integer> ll = (LinkedList<Integer>) list;
			for(int i = 0; i < ll.size(); i++){
				ll.get(i);
			}
		}
	}
	/**
	 * 下面这个用迭代器遍历集合时，删除某一个元素后，不会报错。
	 * 因为用的是迭代器的remove方法。
	 */
	@Test
	public void testIterator2(){
		
		ArrayList<Integer> age = new ArrayList<Integer>();
		age.add(1);
		age.add(2);
		age.add(3);
		age.add(4);
		age.add(5);
		
		Iterator<Integer> ai = age.iterator();
		while(ai.hasNext()){
			int i = ai.next();
			if(i == 3){
				ai.remove();
			}else{
				System.out.println(i);
			}
		}
		
//		Iterator由集合生成之后，只能使用一次：
//		age.add(6);
//		while(ai.hasNext()){
//			System.out.println(ai.next());
//		}
		
//		第一次遍历完age后，对age做了添加操作，想要再次对这个age集合进行遍历，需要重新生成一个Iterator：
		age.add(6);
		age.add(7);
		Iterator<Integer> ai2 = age.iterator();
		while(ai2.hasNext()){
			System.out.println(ai2.next());
		}
	}
	
	/**
	 * 迭代器Iterator：在使用迭代器去遍历集合的过程中，用了集合的remove()方法删除其中某个元素
	 * 
	 * 问题：删除倒数第二个元素，程序却不会抛出异常。
	 * 答：最好画个图，比较好理解。
	 * 	1.用迭代器遍历集合的1 2 3 都是没有问题的，能够正常输出；
	 * 	2.当遍历到4时，用了集合的remove()方法把“4”进行删除，“4”后面的元素全部往前移一位，元素“5”到了“4”的位置；
	 * 	3.根据2可得：此时集合为{1,2,3,5}，而此时迭代器的指针还指向原本元素“4”的那一行（也就是现在“5”的那一行）；
	 * 	4.此时迭代器通过hasNext()方法判断是否还有下一个元素，答案是false，所以不会执行next()方法，遍历结束。
	 * 	5.对4进行说明：为什么不执行迭代器的next()方法，程序就不会抛出异常？因为next()方法中，会判断“迭代器的expectedModCount”和“集合的modCount”是否相等。
	 * 		对“4”进行删除后，“集合的modCount”加了1，“迭代器的expectedModCount”不变，所以实际上它们是不相等的，但没执行next()方法，程序并不知道。
	 */
	@Test
	public void testIterator1(){
		
		ArrayList<Integer> age = new ArrayList<Integer>();
		age.add(1);
		age.add(2);
		age.add(3);
		age.add(4);
		age.add(5);
		
		Iterator<Integer> ai = age.iterator();
		while(ai.hasNext()){//输出结果：1 2 3
			int i = ai.next();
			if(i == 4){
				age.remove(i);
			}else{
				System.out.println(i);
			}
		}
	}
	
	/**
	 * 增强for循环：
	 * 下面的操作，会引起“并发修改异常”。
	 */
	@Test
	public void testFor(){

		List<Integer> age = new ArrayList<Integer>();
		age.add(1);
		age.add(2);
		age.add(3);
		age.add(4);
		age.add(5);
		for(int newAge : age){
			System.out.println("前： " + newAge);
			if(newAge == 3){
				age.remove(newAge);
				System.out.println("中： " + newAge);
			}
			System.out.println("后：  " + newAge);
		}
	}
	
	/**
	 * 普通for循环：在遍历过程中，通过判断删除某元素后，在该删除元素之后的所有元素都会往前移，元素下标也会随之改变。
	 */
	@Test
	public void testCommonFor(){
		
		System.out.println("commonFor----------");
		List<String> name = new ArrayList<String>();
		name.add("A");
		name.add("B");
		name.add("C");
		name.add("D");
		name.add("E");
		
		for(int i = 0; i < name.size(); i++){
			if(i == 1){
				name.remove(i);//删除一个元素，导致后面所有元素都往前进一位：ACDE
			}else{
				System.out.println(name.get(i));
			}
		}
	}
}
