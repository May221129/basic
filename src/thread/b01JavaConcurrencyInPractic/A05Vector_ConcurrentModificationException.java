package thread.b01JavaConcurrencyInPractic;

import java.util.Vector;

/**
 * 5.1.2 Vector在迭代时有加锁？为什么还会导致快速失败。
 * （1）代码解析：
 * 	下面是Vector迭代器的next()方法：
 * 		public E next() {
            synchronized (Vector.this) {
                checkForComodification();
                int i = cursor;
                if (i >= elementCount)
                    throw new NoSuchElementException();
                cursor = i + 1;
                return elementData(lastRet = i);
            }
        }
 *	 在并发情况下：线程A在迭代vector，同时，线程B使用Vector的删除方法在删除vector中的某个元素。
 *		expectedModCount是迭代器中的成员变量，是获得vector的迭代器时进行赋值的（int expectedModCount = modCount;），
 *		线程A抢到vector锁，此时在next()方法中执行“checkForComodification();” 得到“modCount == expectedModCount”，成功执行next()一次，释放锁；
 *		线程B抢到vector锁，删除元素成功（modCount就会加1，而expectedModCount则不变）后，释放锁；
 *		线程A再次抢到vector锁，再次执行next()方法，通过“checkForComodification();” 得到“modCount != expectedModCount”，报错，抛出异常。
 *
 *（2）结论：
 *		虽然Vector在迭代时也加了锁，但是使用Vector的自带的删除方法删除掉集合中的元素时只会更新modCount，
 *		而不会（也不能）更新迭代器的expectedModCount，所以最终判断“modCount ！= expectedModCount”成立后依旧会抛出异常。
 *		这是“快速失败”设计。
 *
 *（3）解决方案：
 *	3.1
 *		如果非得要一边迭代集合中的元素，一边删除掉集合中的某个元素，
 *		则用Vector的迭代器进行迭代，也用迭代器的删除方法对元素进行删除，而非用Vector自带的删除方法。
 *	3.2 
 *		在客户端对迭代进行加锁：
 *		synchronized(vector){
 *			for(int i = o; i < vector.size(); i++){
 *				doSomething(vector.get(i));
 *			}
 *		}
 *		加锁会导致新的问题出现：迭代时其他线程都无法进行读写了，如果集合元素很多，那等待时间会很长。
 * 
 * @author May
 * 2019年11月28日
 */
public class A05Vector_ConcurrentModificationException {
	public static void main(String[] args) {
		V v = new V();
		IteratorV ir = new IteratorV(v);
		RemoveV rr = new RemoveV(v);
		
		new Thread(ir).start();//一个线程对Vector进行迭代
		new Thread(rr).start();//一个线程对vector进行修改
	}
}

class RemoveV implements Runnable{
	private V v;
	public RemoveV(V v) {
		this.v = v;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 100; i+=10)
		v.removeElement(i);
	}
}

class IteratorV implements Runnable{
	private V v;
	public IteratorV(V v) {
		this.v = v;
	}
	
	@Override
	public void run() {
		for(Integer element : v.getVector()) {
			System.out.println(element);
		}
	}
}

class V{
	private Vector<Integer> vector = new Vector<>();
	{
		for(int i = 0; i < 100; i++) {
			vector.add(i);
		}
	}
	public Vector<Integer> getVector(){
		return vector;
	}
	public void removeElement(int index) {
		vector.remove(index);
	}
}