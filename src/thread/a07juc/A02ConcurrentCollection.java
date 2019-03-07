package thread.a07juc;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * jdk1.5��java.util.concurrent.atomic�����ṩ�˶��ֲ������������Ľ�ͬ�����������ܡ�
 * 
 * һ��ConcurrentHashMap���̰߳�ȫ�Ĺ�ϣ��
 * 	1.���ڶ��̵߳Ĳ���������HashMap��Hashtable֮�䡣
 * 	2.�ڲ����á����ֶΡ����ƴ���Hashtable�Ķ�ռ��������������ܡ�
 * 		�ٷֶ���Ĭ���Ƿ�16�Σ�ÿ���Σ�segment������һ������������
 * 		�ڿɲ��з��ʣ�һ���ε���ֻ�ܱ�һ���̳߳��У�����֧�ֶ���߳�ͬʱ���ʲ�ͬ�ĶΡ�
 *	3.jdk 1.8��ConcurrentHashMap�ײ�ȡ�������ֶλ��ƣ�����CAS��Ч���ָ����ˡ�
 * 
 * ����CopyOnWriteArrayList/CopyOnWriteArraySet:д�벢���ơ�
 * 	1.�������������̰߳�ȫ�ģ���Ч�ʸ���Collections��װ���ArrayList��ArraySet��
 * 		���ʣ�Collections��װ���ArrayList��ô��Ч�ʵ��ˣ�
 * 			����ΪCollections�İ�װ��ʽ��������ÿ����������synchronized�������ˣ�
 * 				�������ȫ��ͨ��һ�����������Ƕ���д�������̶߳��Ǵ���ִ�еġ�
 * 		   	��CopyOnWriteArrayList/CopyOnWriteArraySet������ͬʱ�����漰������
 * 				��д�������Ǹ������ײ㶼�Ḵ�Ƴ�һ���µļ��ϣ�Ȼ���ٽ�����ӣ���ͬʱ��ִ�ж�����
 * 				���Ǹ��̣߳������ڶ�ԭ����(����һ��˼�룬������˼���������̶߳���ǰȷ��״̬��
 * 				���ϣ���д�߳���дʱ�����ϵ�״̬������ȷ���ģ��ǲ��ȶ��ġ�������ݿ�Ķ����ύ�е����ơ�)
 * 	2.���߱������������Ԫ�ء�Ҳ�����������Ϊ����ÿ��д��ʱ���ײ㶼�Ḵ�Ƴ�һ���µļ��ϣ�Ȼ���ٽ�����ӡ�
 * 		���ʣ����Ǹ���ԭ���ϣ����Ǹ������������߳��޸Ĺ��ļ��ϣ�
 * 			�𣺸��ơ����ύ�ļ��ϡ�����״̬��ǰ���ȶ�״̬�ļ��ϣ��������ڱ��޸ġ����ڲ�ȷ��״̬�ļ��ϡ�
 * 			��Ϊʲô��ӽ�ȥ��Ԫ�ز��ܱ�������������sizeȷʵ��ʾ�Ѿ�����ô���Ԫ���ˣ�====>���ƻö���
 * 	3.��Ϊ2.����ע�����������������ʺ�Ƶ������Ӳ�����������ܴ󡣵��ʺ���������ĵ���������
 * 		�����������漰��getԪ�أ�Collections���ص��̰߳�ȫ����������getʱ�Ǵ��еģ�����Ч�ʵͺܶࡣ
 * 	4.��һ��Ӿ͸�ֵ�������������������ʱ�丳ֵ�������ģ� ==�� �������е�̽��4
 * 	5.Ϊʲô�ڱ���ʱͬʱ�����һ��Ԫ�ؽ�ȥ��list.size()�иı䣬���Ǳ����Ľ��ȴ�������Щ��ӽ�ȥ��Ԫ�ر��������أ� ==�� ��̽��5
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
	 * ���󣺱�������ʱ��ͬʱ�����������Ԫ�ء�
	 * ��������õĲ���CopyOnWriteArrayList������ArrayList���ͻᱨ�����޸��쳣��
	 * ����CopyOnWriteArrayList�Ϳ�����ɱ��������
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
			 * ̽��4����һ��Ӿ͸�ֵ�������������������ʱ�丳ֵ�������ģ�
			 * 	��һ��Ӿ͸�ֵ����������
			 * ̽��5��Ϊʲô�ڱ���ʱͬʱ�����һ��Ԫ�ؽ�ȥ��list.size()�иı䣬���Ǳ����Ľ��ȴ�������Щ��ӽ�ȥ��Ԫ�ر��������أ�
			 * 	��Iterator<String> iterator = list.iterator();�����iterator���õ��˼��ϵĿ��������б��������ݣ�
			 * 		���Կ�Դ�룬��Դ���ע��Ҳ��˵�����������������������Ԫ�أ�����iterator�Ŀ���Ҳ����ı䣬���Ա������Ҳ����䡣
			 * 	ע��CopyOnWrite�����ĵ���������ͨ���ϵĵ�������������ģ�����̽������Copy�����ĵ�������
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
