package collection.d_queue;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

/**
 * Queue�ӿڣ�
 * 		offer(E e):
 * 			���Ԫ��. �ɹ�����true, ʧ�ܷ���false.
 * 		add(E e):
 * 			���Ԫ��. �����������, ��ô���ᱨ�쳣. 
 * 			AbstractQueue��add����ʵ���ǵ���offer����, �ɹ�����true, ʧ�ܱ��쳣.
 * 		
 * 		peek():
 * 			��ȡ���е�ͷ�������Ƴ���ͷ,����˶���Ϊ�գ��򷵻� null.(����ʵ����ʵ��)
 * 		element():
 * 			��ȡ���е�ͷ�������Ƴ���ͷ�� 
 * 			AbstractQueue��element����ʵ���ǵ���peek����, �ɹ�����E, ʧ�ܱ��쳣.
 * 
 * 		poll():
 * 			��ȡ���е�ͷ�����Ƴ���ͷ,����˶���Ϊ�գ��򷵻� null.(����ʵ����ʵ��)
 * 		remove():
 * 			ȡ���е�ͷ�����Ƴ���ͷ���������Ϊ��,�����쳣.
 * 			AbstractQueue��remove����ʵ���ǵ���poll����, �ɹ�����E, ʧ�ܱ��쳣.
 * 
 * 		AbstractQueue�Ǵ�������еĸ���,��BlockingQueue�ӿ�ͬ��, һ��ʵ��Queue�ӿ�.
 * 			������Ҫ��offer,peek,poll����������������ʵ��.
 * 
 * 		��ͨ���е�ʵ�ֿ�����Object����, Ҳ����������ȵ�.
 * 
 * BlockingQueue�ӿ�:
 * 		Queue�����ֽ��, һ���Ƿ�������ֵ,һ�������쳣.
 * 		BlockingQueue���������:
 * 				�׳��쳣		����ֵ 		����			��ʱ
 * 		����:		add(e)		offer(e)	put(e)		offer(e, time, unit)
 * 		�Ƴ�:		remove()	poll()		take()		poll(time, unit)
 * 		ȡ�����Ƴ�:	element()	peek()		������		������
 * 
 * 		����API��
 * 		    boolean contains(Object o):
 * 		    	�ж��Ƿ����
 * 		    int drainTo(Collection<? super E> c):
 * 				�Ƴ��˶��������п��õ�Ԫ�أ�����������ӵ����� collection��
 * 			int drainTo(Collection<? super E> c, int maxElements)
 * 				���Ӵ˶������Ƴ����������Ŀ���Ԫ�أ�������ЩԪ����ӵ����� collection��
 * 			int remainingCapacity()
 * 				�ö�����Ȼ�ж��ٿռ���Դ��Ԫ��.
 * 			
 * 		�ýӿڵ�ʵ����:
 * 			ArrayBlockingQueue:
 * 				1:��Object[]ʵ��, ���Ըö��г����Ƕ���.
 * 				2:�ö���Ĭ���Ƿǹ�ƽ��, ����ͨ���ƶ��Ĺ��췽�������ɹ�ƽ�Ķ���.����ʵ����new��ReentrantLock�Ƿ��ǹ�ƽ��
 * 				3:��������ͨ������condition��ʵ�ֵ�,notEmpty��notFull
 * 					����:
 * 						put����:��������.�͵���notFull.await()����.
 * 						poll����:�������dequeue����,�÷����л����notFull.notify()����������.
 * 				4:��������̰߳�ȫ��, ������ReentrantLock������������.
 * 				5:�䳬ʱҲ���õ�Condition������, �õ�condition��awaitNanos����, �����̵߳ȴ�һ��ʱ��.
 * 					�����ʱʵ�ָ�FutureTask�ĳ�ʱʵ�ֲ�һ��. futureTask�ĳ�ʱ��������LockSupport��UNSafe������(��ʵcondition��awaitNanos����Ҳ�ǵ��õ�LockSupport������).
 * 				
 * 				����:���������ʵ�ֶ��е�?
 * 					��:��һ��putIndex��Ա����, �᲻�ϵ���array.length�ķ���Ų��, ��Ų��array.length��ʱ��, ��putIndex��0, �ܶ���ʼ.
 * 
 * 			LinkedBlockingDeque:
 * 				1:���Զ����˫��������ʵ��, Ĭ�ϳ�����Integer.MAX_VALUE, Ҳ�����Զ����䳤��.
 * 					(LinkedBlockingDeque��PriorityBlockingQueue������һ�����ȵ�,��LinkedBlockingDeque�ᱻ�޶�,Ҳ������Ϊ�ⶥ,����
 * 					PriorityBlockingQueueû����Ϊ�ⶥ, ��������Integer.MAX_VALUE-8��ʱ������Ϊ�ڴ������).
 * 					��Ȼ����ĳ����ǿ���չ��,���Ǵ�����������,��������䳤��Ҫô����false,Ҫô������.
 * 				2:�õķǹ�ƽ��ReentrantLock,�������Ƿǹ�ƽ����.
 * 				3:����������������������ArrayBlockingQueueһ��.
 * 				4:�����ArrayBlockingQueue, �����Դ�ͷ����β������ӻ���ɾ��.
 * 					
 * 			LinkedBlockingQueue:
 * 				1:��LinkedBlockingDeque����һ��,����˵һ���䲻һ�µĵط�
 * 				2:��ʵ���ǵ�������,Ҳ��ֻ���Ƚ��ȳ�.
 * 				3:��������ReentrantLock, һ���ǿ�����ӵ�,��һ���ǿ��Ƴ��ӵ�.
 * 					������ReentrantLockʵ�ָ���ϸ���ȵ�������.
 * 					Ϊʲô��������̰߳�ȫ����:
 * 						���Ӻ���Ӳ����ĳ�Ա������ͬ(������Ӻͳ��ӵ�ʱ������ֱ���ͷ��β��node,���Ի������),Ҳ�Ͳ������̰߳�ȫ����,
 * 						���ֲ���Ψһ������ͬ�Ķ������Ǽ���(count),LinkedBlockingQueue����������AtomicInteger,�����ж������Ҳ����ԭ�ӵ�.
 * 						
 * 			PriorityBlockingQueue:			
 * 				1:�õ����ݽṹ:��.(��һά����ģ��ѵ����ݽṹ).
 * 					���������Ԫ��,ɾ��Ԫ�ص�ʱ��ͻ���ֶѲ���:
 * 						�ϸ� shift_up
 * 						�³� shift_down
 * 						�ȵ�
 * 						��Ӧ��Դ��:
 * 							siftUpComparable		:Ĭ��������ϸ�����
 * 							siftUpUsingComparator	:ָ��������ϸ�����
 * 							siftDownComparable		:Ĭ��������³�����
 * 							siftDownUsingComparator	:ָ��������³�����
 * 
 * 				2:���Ա�����о���Comparator,���Ƚ϶���Ԫ�صĴ�С��ϵ.
 * 					���������Comparator, Ҳ�����÷Ž�ȥ��Ԫ��ʵ��Comparable�ӿ�.
 * 					������߶�û�еĻ�, ʹ��������оͻᱨ��. ��Ϊ��û�취�ж���С.
 * 				3:Ĭ�ϳ�����11,��ȻҲ����ָ�����ʼ����.
 * 				4:�ö����ǲ������ܴ�С��. ���һֱofferԪ�ؽ�ȥ,��������������ʱ���������ݲ���(tryGrow)
 * 					���ǳ�����Integer.MAX_VALUE - 8 ������Ҳ�ǻᱨOutOfMemoryError��. �����LinkedBlockingQueue,��������������޷��ı��.
 * 				5:Ҳ��Ϊ�ö��в��������ܴ�С, ����condition��ֻ��һ��. 
 * 					���������ƴ�С�Ķ���,condition��Ӧ��������,����:
 * 					����1:t1 put��ʱ��,��������,��awaitס�Լ�.�ȴ�t2�߳�poll�ɹ���ʱ��singalһ��.(����һ��Condition)
 * 					����2:t1 put��ʱ��ɹ���, �ͻ�˳��singalһ��, ��Ϊ���ܻỽ��������Ҫtake��Ԫ�ص�t2.	(������һ��Condition)
 * 						ע��:ֻ��take,put�Ȼ������ķ����Ż����await, ���ǲ������������߷������ķ����������singal
 * 
 * 					�����޽����:
 * 						put��ʱ��Ͳ�������(��Ϊ�޽�),���Ծ�Ҳ������poll��ʱ��Ҫsingalһ��(Ҳ���ǵ�һ��condition��û������)
 * 
 * 				6:���������������Ķ���,��ȻҲ�ò�����������
 * 					offer(E e, long timeout, TimeUnit unit)��������ֱ�ӵ���offer(e).
 * 					����put����, Ҳ��ֱ�ӵ��õ�offer
 * 				7:ΪʲôҪ�ö������ݽṹ,��Ϊ��Ҫô�����,Ҫô����С�ڸ���, ÿ��Ҫȡ����ȡ������,����Ӷ��г�һ��Ԫ�س���.
 * 					������е����Ժ���,�����û�о���.			
 * 	
 * 			DelayQueue:
 * 				1��DelayQueue = BlockingQueue + PriorityQueue + Delayed
 * 					BlockingQueue(����):��PriorityBlockingQueueһ����������,��Ϊ���õľ���PriorityQueue,�������϶����޽��.��Condition����ֻ��һ��.
 * 					PriorityQueue:��һ��PriorityQueue��Ա����,Ҳ����˵DelayQueueʵ���ϲ����ľ���һ�����ȼ�����(������).
 * 					Delayed:�ϸ�Ҫ��ö��зŽ�ȥ�Ķ�������Ǽ̳���Delayed�ӿ�.�ýӿ���ʵ����Comparable�ӿ�. ���Ըö����ܻ���Ƚ�,Ҳ�ܵõ�����ʱ��.	
 * 				2:add, offer, put, offer(E e, long timeout, TimeUnit unit):
 * 					��Ԫ�ص�������,�����Ǻ�PriorityBlockingQueueһ����. Ҳ���Ǹ��޽���������һ��.
 * 					ֻ����offer�еģ�һ���ж��е��ɻ�,��������̡߳�������ϸ�˽���.
 * 				3:poll
 * 					����������Ϊ�յ�ʱ��,����ֵΪ��, �����õ�Ԫ��,����Ԫ�صĹ���ʱ�仹û��,Ҳ�᷵��null.
 * 				4:take
 * 					����������Ϊ�յ�ʱ��,������ס,  �����õ�Ԫ��,����Ԫ�صĹ���ʱ��Ҳ������ס,֪�����˹���ʱ��,�Ż�take����.
 * 				5:poll(long timeout, TimeUnit unit)
 * 					��take�Ļ�����,����������������ȴ����(����ȴ�ʱ�� = ����Ϊ�յ�ʱ�� + Ԫ�صĹ���ʱ��)
 * 				6:��poll,take,pull(timeout)��֪��DelayQueue�����������������е���:
 * 					������Ԫ��Ҳ�й���ʱ��, ��ȡ����ʱ��Ҫһ��Ҫ���ǵ�Ԫ�صĹ���ʱ��, ���Ԫ�ػ�δ����,�Ͳ���ȡ����.
 * 				7:��ΪԪ�ظ��ӵ�����ʱ��,��������˼��:
 * 					��Դ��:
 * 						��Ա����:private Thread leader = null;
 * 						offer����.take����,poll(timeout)�����õ�leader
 * 					����Դ��take(����߳�����ȡ��Դ��ʱ��):
 * 						�߳�t1��ҪtakeԪ��, �����Ԫ������Ĺ���ʱ������ס��available.awaitNanos(delay).		
 * 						t1�ͷ�����(��ΪawaitNanos).	��ʱ��t2�Ϳ��Խ���take����,t2Ҳ��Ҫ��ȡһ��Ԫ��. 
 * 						t2��t1һ��peek��ͬһ��Ԫ��,Ȼ����Ϊleader��ֵ�����ڵ�ǰ�߳�,���Ծ�available.await();
 * 						t1��awaitNanos����֮��,t1��ȥpollԪ��, ,��available.signal();
 * 						t2�ͱ�����,Ȼ��ȥpeek��һ��Ԫ��,������������.
 * 						
 * 						��Ϊ�ӳٶ��м�����Ԫ�ص��ӳ�ʱ��,����take�������߼���֪���������ĵȴ�ʱ����ܻᵼ���̵߳ȴ�����Ҫ��ʱ��.
 * 						�����leader�����þ���Ϊ��Լ�������̵߳�����(��leader�Ĳ������Ǹ�ֵ��ǰ�̶߳���,�Լ��п�).
 * 						����leader�����ÿ��Կ�take�����Լ�������������Ľ���.
 * 				8:ע���,Delayed���������getDelay�������ص�ֻ��long����(û��ָ��ʱ������),��DelayQueue��ʱ��ʱ���ǰ������.
 * 					Ҳ����˵���getDelay��������Ϊ�ǰ������������Ļ�,���ճ�take�ȷ�����ת����쮸�,�˷�cpu��Դ.
 * 					������һ��Delayed��ʵ����. �����˼ҵ�getDelay������ʵ�ַ�ʽ.
 * 				9:DelayQueue���ó���:
 * 					a) �رտ������ӡ��������У��кܶ�ͻ��˵����ӣ�����һ��ʱ��֮����Ҫ�ر�֮��
 *					b) ���档�����еĶ��󣬳����˿���ʱ�䣬��Ҫ�ӻ������Ƴ���
 *					c) ����ʱ����������Э�黬����������Ӧ��ʽ����ʱ������ʱδ��Ӧ������
 *					����һ�¹��ڻ�����ô��: ��һ���̳߳������ϵĵ���delayqueue��take����. �Ϳ��Բ��ϵİѹ��ڵ�Ԫ��ȥ��.
 *						ע��delayQueue���������ȼ�����. ʼ�ջ����������СԪ�ط��ڸ���. �����ص����DelayedԪ�ص�ʵ��.
 *						ע���������������ӱ�����ʹ����,��ô�ͻᱻ�Ƴ���ʱ����.�����������Կ�remove(Object obj),��ž����ȱ����������(��)
 *							Ȼ���ҵ�֮���ɾ�������Ԫ��, Ȼ����ȥ�����ѵ�ƽ��.
 *
 *					����ʵ��:https://www.cnblogs.com/jobs/archive/2007/04/27/730255.html
 *					
 * 			SynchronousQueue:	
 * 				SynchronousQueue��ʵ����������һ�������Ķ��У���Ϊ������Ϊ������Ԫ��ά���洢�ռ䡣
 * 				SynchronousQueue��һ��û�����ݻ����BlockingQueue���������̶߳���Ĳ������put����ȴ������ߵ��Ƴ�����take��������Ҳһ����
 * 				��jdk1.6�Ժ���õ��������㷨,����Ч�ʸ���. ֮�����ܲ��������㷨�������Ϊû�����ݻ����ԭ��.
 * 					����Ҳ����˵������г����ʺ�SynchronousQueue��ArrayBlockingQueue��ʱ��,��ȥѡ��Ч�ʸ��ߵ�SynchronousQueue.
 * 				������һ����Ԫ�����Լ�һЩС�ܽ�:
 * 					testSynchronousQueue
 * 
 * 				����ʵ��:
 * 					1:��һ���ڲ�������Transferer,������������	
 * 						�ֱ���TransferQueue(���ڶ���ʵ��)��TransferStack(����ջʵ��)
 * 					2:SynchronousQueue�Ĳ���ʵ���϶����õ�Transferer(���췽������new һ��TransferQueue����TransferStack).
 * 						��ƽ��:TransferQueue   �ǹ�ƽ��:TransferStack
 * 					3:offer,put,offer(timeout),poll,take,poll(timeout)����
 * 						���ǵ��õ�Transferer��transfer(E e, boolean timed, long nanos)����.
 * 						offer,poll:�ǲ�����ȴ�(�ڶ�������Ϊfalse).
 * 						put,offer(timeout),take,poll(timeout):��Ҫ����(�ڶ�������Ϊtrue).
 * 						Ҳ����˵offer,poll�����Ǻܿ���ֱ��ʧ�ܵ�.
 * 
 * 				�ŵ�:
 * 					�����ϴ���ӵı���Ϊ������ô����൱��û���̼ܣ����ǽ�ϴ�õ�����ֱ�ӷ�����һ�����еĺ�ɻ��С�����ʵ�ֶ��еķ�ʽ���ƺ���֣�
 * 						�����ڿ���ֱ�ӽ����������Ӷ������˽����ݴ��������ƶ��������ߵ��ӳ�.
 * 					ֱ�ӽ�����ʽ���Ὣ�����������״̬����Ϣ�����������ߡ�������������ʱ������֪���������Ѿ��õ������񣬶����Ǽ򵥵ذ��������һ������.
 * 
 * 				�ʺϳ���:
 * 					��ΪSynchronousQueueû�д洢���ܣ����put��take��һֱ������ֱ������һ���߳��Ѿ�׼���ò��뵽���������С�
 * 						�������㹻��������ߣ�����������һ��������׼���û�ȡ�����Ĺ���ʱ�����ʺ�ʹ��ͬ������
 * 				ʵ��1:
 * 					Java 6��SynchronousQueue��ʵ�ֲ�����һ�����ܸ��õ������㷨.
 * 					��ڵ�������״̬:
 * 						�������� �C put()������Ԫ��
 *						�������� �C take()����
 *						��
 *					����㷨���ص�����κβ��������Ը��ݽڵ��״̬�ж�ִ�У�������Ҫ�õ�����
 *					���кͳ��ж�����Spin��CAS������
 *					���ϲ�û�п�Դ��,ֻ��ժ�Բ���:http://ifeve.com/java-synchronousqueue/
 *
 * 				ʵ��2:���ź�����ʵ��ͬ������:(�ź���ʵ��������AQS��ʵ�ֵ�,AQS�ͻ���Ƶ��̵߳Ĺ���)
 * 					��thread01.A04Semaphore��
 * 
 * ��AQS��֪ʶ������ArrayBlockingQueue:
 * 		����:һ�����̲߳���take,put��ʱ����lock,����AQS����, Ȼ�����������������,���߿���,�ͻ����condition.await��������Ӧ
 * 			������������,�����������е��̱߳����ѵ�ʱ���ֻ����½���AQS������.
 * 					
 *   |--------------------------------------------------|
 * 	 |			   	|-------->condition(take����)------> 	|
 * 	 |->AQS����------	|
 * 	 |				|-------->condition(put����)-----	-->	|
 * 	 |--------------------------------------------------|												|
 * 
 * @author lgr
 *
 */
public class A01Queue {

	/**
	 * ����
	 * 		10���߳�ȫ����take��ʱ������ס��.
	 * 		put�߳�, ÿ��1��putһ��, ��Ӧ��һ��take�ỽ��(����Ҫput10��֮��,take��ȫ������).
	 * 		���ѵ�take�������,��ȡ���ڴ�����SynchronousQueue�Ƿ��ǹ�ƽ��,
	 * 			Ҳ����new SynchronousQueue<>(true)��new SynchronousQueue<>()������.
	 * 			true:	����ģʽ
	 * 			false:	ջģʽ
	 * 			Ĭ����false.
	 * 		����������take��put, ��ʵ��֮��Ȼ.
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
	 * �����. ��Ϊû�и�PriorityBlockingQueue����һ��Comparator.
	 * 		���ҷŽ�ȥ��Ԫ��Ҳ��û��ʵ��Comparable
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
