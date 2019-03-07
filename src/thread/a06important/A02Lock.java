/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread.a06important;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1.在1.5之前用的是synchronized ，在1.5之后用的是LOCK
 * 	地址：http://www.cnblogs.com/dolphin0520/p/3923167.html
 * 
 * 2.ReentrantLock（Lock接口的实现类）相对于synchronized的优势：
 *		（1）ReentrantLock可以通过tryLock()去判断线程是否拿到了锁，没拿到锁可以执行其他的逻辑；
 *		（2）ReentrantLock可以通过tryLock(long timeout, TimeUnit unit)控制线程的等待时间，超过了指定时间还没拿到锁就去执行其他逻辑；
 *			Lock lock = ...;
 *			if(tryLock(long timeout, TimeUnit unit)) {//timeout：等待时长， timeunit：时间单位。
 *			    try{
 *        			//处理任务
 *    			}catch(Exception ex){
 *        
 *    			}finally{
 *        			lock.unlock();   //释放锁
 *    			} 
 *			}else {
 *   			//如果不能获取锁，则直接做其他事情
 *			}
 *		（3）synchronized不是可中断锁，而ReentrantLock是可中断锁中，线程的挂起状态可以被中断：通过lockInterruptibly()锁。
 *			如：当线程A拿到了该锁，线程B没拿到锁被挂起了，线程B等待了1分钟后可以通过interrupt()方法中断等待；
 *		（4）synchronized是非公平锁，它无法保证等待的线程获取锁的顺序。
 *			ReentrantLock和ReentrantReadWriteLock，它们在默认情况下是非公平锁，但是可以设置为公平锁：
 *				new ReentrantLock(boolean fair)//fair为true时，即公平锁
 *				new ReentrantReadWriteLock(boolean fair)
 * 
 * 3.lockInterruptibly相对于lock方法就是在等待获取锁的时候可以响应中断。
 * 
 * 4.公平锁: 按等待时间的长短, 一次调用, 应该就简单队列就可以搞定(先进先出)
 * 	  非公平锁: 不按等待时间来处理. synchronized和new ReentrantLock()都是非公平锁 .
 * 		4.1 new ReentrantLock(true)就是公平锁
 * 		4.2 ReentrantLock的实现:  ReentranLock内部是包了AbstractQueuedSynchronizer. 
 * 			AbstractQueuedSynchronizer的实现是一个队列. 将线程wait住放到队列中, 然后一个个放行
 * 			读写锁虽然已经被实现了ReentrantReadWriteLock readLock writeLock
 * 		4.3 MyReadWriteLock这是自己的实现. 
 * 			实现:
 * 			（1）ReentrantLock分为公平锁和非公平锁，所以，ReentrantLock内部只有两个sync的实现
 *				ReentrantLock中的sync的设计也是很好玩的.可以一看
 *			（2）AbstractQueuedSynchronizer的tryAcquire方法由子类实现.
 *				ReentrantLock的公平sync所实现的tryAcquire方法,就有体现出可重入锁的特性.
 *			（3）AQS用state字段来表示状态, ReentrantLock利用AQS的state来表示某线程已经获取锁,且还可以表示重入锁的特性.
 *				在tryAcquire方法中,用CAS去设置state, 当设置成功就说明当前线程获取锁成功.
 *			（4）tryAcquire方法是尝试获取锁,根据队列的状态，已经状态字state来判定是否可以获取锁.具体待详细论证.
 *				中间会涉及到可重入的控制
 *	5.awaitNanos方法,唤醒顺序跟挂起的顺序并不一定一致, 也就是说唤醒顺序是随机的.
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
