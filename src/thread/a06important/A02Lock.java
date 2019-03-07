/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread.a06important;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1.��1.5֮ǰ�õ���synchronized ����1.5֮���õ���LOCK
 * 	��ַ��http://www.cnblogs.com/dolphin0520/p/3923167.html
 * 
 * 2.ReentrantLock��Lock�ӿڵ�ʵ���ࣩ�����synchronized�����ƣ�
 *		��1��ReentrantLock����ͨ��tryLock()ȥ�ж��߳��Ƿ��õ�������û�õ�������ִ���������߼���
 *		��2��ReentrantLock����ͨ��tryLock(long timeout, TimeUnit unit)�����̵߳ĵȴ�ʱ�䣬������ָ��ʱ�仹û�õ�����ȥִ�������߼���
 *			Lock lock = ...;
 *			if(tryLock(long timeout, TimeUnit unit)) {//timeout���ȴ�ʱ���� timeunit��ʱ�䵥λ��
 *			    try{
 *        			//��������
 *    			}catch(Exception ex){
 *        
 *    			}finally{
 *        			lock.unlock();   //�ͷ���
 *    			} 
 *			}else {
 *   			//������ܻ�ȡ������ֱ������������
 *			}
 *		��3��synchronized���ǿ��ж�������ReentrantLock�ǿ��ж����У��̵߳Ĺ���״̬���Ա��жϣ�ͨ��lockInterruptibly()����
 *			�磺���߳�A�õ��˸������߳�Bû�õ����������ˣ��߳�B�ȴ���1���Ӻ����ͨ��interrupt()�����жϵȴ���
 *		��4��synchronized�Ƿǹ�ƽ�������޷���֤�ȴ����̻߳�ȡ����˳��
 *			ReentrantLock��ReentrantReadWriteLock��������Ĭ��������Ƿǹ�ƽ�������ǿ�������Ϊ��ƽ����
 *				new ReentrantLock(boolean fair)//fairΪtrueʱ������ƽ��
 *				new ReentrantReadWriteLock(boolean fair)
 * 
 * 3.lockInterruptibly�����lock���������ڵȴ���ȡ����ʱ�������Ӧ�жϡ�
 * 
 * 4.��ƽ��: ���ȴ�ʱ��ĳ���, һ�ε���, Ӧ�þͼ򵥶��оͿ��Ը㶨(�Ƚ��ȳ�)
 * 	  �ǹ�ƽ��: �����ȴ�ʱ��������. synchronized��new ReentrantLock()���Ƿǹ�ƽ�� .
 * 		4.1 new ReentrantLock(true)���ǹ�ƽ��
 * 		4.2 ReentrantLock��ʵ��:  ReentranLock�ڲ��ǰ���AbstractQueuedSynchronizer. 
 * 			AbstractQueuedSynchronizer��ʵ����һ������. ���߳�waitס�ŵ�������, Ȼ��һ��������
 * 			��д����Ȼ�Ѿ���ʵ����ReentrantReadWriteLock readLock writeLock
 * 		4.3 MyReadWriteLock�����Լ���ʵ��. 
 * 			ʵ��:
 * 			��1��ReentrantLock��Ϊ��ƽ���ͷǹ�ƽ�������ԣ�ReentrantLock�ڲ�ֻ������sync��ʵ��
 *				ReentrantLock�е�sync�����Ҳ�Ǻܺ����.����һ��
 *			��2��AbstractQueuedSynchronizer��tryAcquire����������ʵ��.
 *				ReentrantLock�Ĺ�ƽsync��ʵ�ֵ�tryAcquire����,�������ֳ���������������.
 *			��3��AQS��state�ֶ�����ʾ״̬, ReentrantLock����AQS��state����ʾĳ�߳��Ѿ���ȡ��,�һ����Ա�ʾ������������.
 *				��tryAcquire������,��CASȥ����state, �����óɹ���˵����ǰ�̻߳�ȡ���ɹ�.
 *			��4��tryAcquire�����ǳ��Ի�ȡ��,���ݶ��е�״̬���Ѿ�״̬��state���ж��Ƿ���Ի�ȡ��.�������ϸ��֤.
 *				�м���漰��������Ŀ���
 *	5.awaitNanos����,����˳��������˳�򲢲�һ��һ��, Ҳ����˵����˳���������.
 */
public class A02Lock {
    public static void main(String[] args) {
		
    	ReentrantLock lock = new ReentrantLock();
		lock.lock();
		lock.unlock();
		
		ReentrantReadWriteLock loc = new ReentrantReadWriteLock();
		loc.readLock().lock();
		
		loc.readLock().unlock();
		
		loc.writeLock().lock();
		
		loc.writeLock().unlock();
	}
}
