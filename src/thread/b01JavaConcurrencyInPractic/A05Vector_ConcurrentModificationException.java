package thread.b01JavaConcurrencyInPractic;

import java.util.Vector;

/**
 * 5.1.2 Vector�ڵ���ʱ�м�����Ϊʲô���ᵼ�¿���ʧ�ܡ�
 * ��1�����������
 * 	������Vector��������next()������
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
 *	 �ڲ�������£��߳�A�ڵ���vector��ͬʱ���߳�Bʹ��Vector��ɾ��������ɾ��vector�е�ĳ��Ԫ�ء�
 *		expectedModCount�ǵ������еĳ�Ա�������ǻ��vector�ĵ�����ʱ���и�ֵ�ģ�int expectedModCount = modCount;����
 *		�߳�A����vector������ʱ��next()������ִ�С�checkForComodification();�� �õ���modCount == expectedModCount�����ɹ�ִ��next()һ�Σ��ͷ�����
 *		�߳�B����vector����ɾ��Ԫ�سɹ���modCount�ͻ��1����expectedModCount�򲻱䣩���ͷ�����
 *		�߳�A�ٴ�����vector�����ٴ�ִ��next()������ͨ����checkForComodification();�� �õ���modCount != expectedModCount���������׳��쳣��
 *
 *��2�����ۣ�
 *		��ȻVector�ڵ���ʱҲ������������ʹ��Vector���Դ���ɾ������ɾ���������е�Ԫ��ʱֻ�����modCount��
 *		�����ᣨҲ���ܣ����µ�������expectedModCount�����������жϡ�modCount ��= expectedModCount�����������ɻ��׳��쳣��
 *		���ǡ�����ʧ�ܡ���ơ�
 *
 *��3�����������
 *	3.1
 *		����ǵ�Ҫһ�ߵ��������е�Ԫ�أ�һ��ɾ���������е�ĳ��Ԫ�أ�
 *		����Vector�ĵ��������е�����Ҳ�õ�������ɾ��������Ԫ�ؽ���ɾ����������Vector�Դ���ɾ��������
 *	3.2 
 *		�ڿͻ��˶Ե������м�����
 *		synchronized(vector){
 *			for(int i = o; i < vector.size(); i++){
 *				doSomething(vector.get(i));
 *			}
 *		}
 *		�����ᵼ���µ�������֣�����ʱ�����̶߳��޷����ж�д�ˣ��������Ԫ�غܶ࣬�ǵȴ�ʱ���ܳ���
 * 
 * @author May
 * 2019��11��28��
 */
public class A05Vector_ConcurrentModificationException {
	public static void main(String[] args) {
		V v = new V();
		IteratorV ir = new IteratorV(v);
		RemoveV rr = new RemoveV(v);
		
		new Thread(ir).start();//һ���̶߳�Vector���е���
		new Thread(rr).start();//һ���̶߳�vector�����޸�
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