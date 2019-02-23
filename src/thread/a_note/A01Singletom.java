package thread.a_note;
/**
 * 通过懒汉式的单例模式，来分析线程安全问题：
 * 
 * 一. 为什么会有线程安全问题：多个线程对同一共享数据（传进来的类或成员变量或静态变量）进行非原子性操作。
 * 	注意：即使是原子性操作，当多个原子性操作累加在一起时，也可能会出现线程安全问题，如单例模式：
 * 		if(instance == null){
			instance = new Singleton1();
		}
 * 
 * 二. 怎么解决线程安全问题：synchronized
 * 	重点：synchronized(锁)，这里的锁是属于哪个对象的？（类对象的锁==>类.class； 创建的对象的锁）
 * 	
 * 三. synchronized的使用：
 * 	1. 同步代码块：
 * 		synchronized(同步监视器){
 *			需要被同步的代码块（即：操作共享数据的代码）；
 *		}
 *		注： ①同步监视器（锁）：由一个类的对象来充当，哪个线程获得该监视器，就能执行{}里的代码；
 *		   ②静态方法中可以由“类.class”这个类对象来充当
 *		   ③所有线程必须使用同一把锁；
 *		   ④共享数据：多个线程操作同一个成员变量；
 *	2. 同步方法：将 操作共享数据的方法声明为synchronized。
 *		同步方法（非静态的）的锁为this。
 *		同步方法（静态的）的锁为当前类本身。
 *		注意：慎用synchronized来同步方法：通过继承Thread类创建多线程的方式，在使用时是有多个对象的，
 *			this表示的锁就不是唯一的，此时如果资源是用static修饰的成员变量，就会有线程安全问题。
 * 
 * 四. 原子操作和非原子操作：
 * 	1.原子操作：
 *  	① all assignments of primitive types except for long and double：对除long和double之外的原生数据类型的操作，一般指获取和赋值操作。
 *  	（jvm版本是32位时才需除去long和double，64位的则不存在该问题。）
 *  	② all assignments of references：对引用的操作，一般指获取和赋值操作。
 *  	③ all operations of java.concurrent.Atomic* classes：java.concurrent（并发包）下的Atomic*类。
 *  	④ all assignments to volatile longs and doubles：jvm版本是32位时操作long和double，如果声明该long或double变量时加上volatile，就是原子操作了。
 * 	2.非原子操作：上面的4中情况之外的，都是非原子操作。
 *  	如：i++ 是非原子操作，它的操作分为3步：① 拿到i ②i+1 ③把结果赋值给自己
 *  
 *  五、线程通信：
 *  	1.两个线程之间的配合，一种线程做+1，一种线程做-1.money要一直维持在0~1.见：a04communication.BankTest
 *  	2.一个线程类，创建出两个线程，随机给一个正整数，线程甲：从1加到3（“count++”三次），线程乙：从4加到6，线程甲：从7加到9，…… 
 *  		见：a04communication.BankTest3
 *  	3.自己实现锁，见：a04communication.MyLockTest
 *  	4.自己实现读写锁：见：a04communication.MyReadAndWriteLockTest1
 */

public class A01Singletom {
	
}

/**
 * 为什么Singleton1存在线程安全问题：
 * 答：
 * 从静态变量 instance=null ，到静态变量实例化，是一种对静态变量的修改，它的结果是不确定的，有2种可能情况：
 * 1. 如果构造函数new SingletonClass();语句的执行是一个很短暂的过程，则不会出现2个以上的线程同时进入if(instance==null)语句块去执行实例化语句的情况。
 * 2. 如果构造函数new SingletonClass();语句的执行是一个很漫长的过程，一个线程执行实例化未完成，另一个线程就能进入if(instance==null)语句块，执行实例化语句。程序不允许创建2个一样的静态变量所以会报错。
 */
class Singleton1{
	private Singleton1(){}
	private static Singleton1 instance = null;
	public static Singleton1 getInstance(){
		if(instance == null){
			instance = new Singleton1();
		}
		return instance;
	}
}

/**
 * 对Singleton1线程安全问题进行处理：加锁
 * 
 * 虽然下面这个singleton2是线程安全的，但效率很低：每个线程都需要去判断instance是否为null,锁只有一把，线程需要排队，所以导致了效率低。
 * 
 * 同理：线程安全的类效率更低：
 * 因为锁机制，每次执行有锁机制的方法时，都需要去申请锁，拿到锁、执行完锁范围内的代码后，还需要释放锁，“申请锁、释放锁”这个过程拖慢了执行效率。
 */
class Singleton2{
	private static Singleton2 instance = null;
	private Singleton2(){ }
	public static synchronized Singleton2 getInstance(){
		if(instance == null){
			instance = new Singleton2();
		}
		return instance;
	}
}

/**
 * 对Singleton2线程安全但效率低下进行处理：体会“锁的范围——需要被同步的代码块（即为操作共享数据的代码）”
 */
class Singleton3{
	private Singleton3(){}
	private static Singleton3 instance = null;
	public static Singleton3 getInstance(){
		//第一层判断：对象创建好后，使用这个对象时做判断用。
		if(instance == null){
			synchronized(Singleton3.class){
				//第二层判断：第一次创建对象时用，避免线程安全问题；
				if(instance == null){
					instance = new Singleton3();
				}
			}
		}
		return instance;
	}
}
/**
 * 思考：为什么Singleton3要有两层判断？
 * 
 * 1. 如果只有一层判断：可能会创建2个instance！
 * 	线程1 ： 同时执行判断：对象为null - 拿到锁 - 创建对象 - 释放锁 - 返回instance；
 * 	线程2 ： 同时执行判断：对象为null - 等待线程1释放锁- 拿到线程1释放的锁- 创建对象 - 释放锁 - 返回instance；
 * 
 * 2. 如果有两层判断：只会创建一个instance！
 * 	线程1： 同时执行判断：对象为null - 拿到锁 - 再次判断：对象为null - 创建对象 - 释放锁 - 返回instance；
 * 	线程2： 同时执行判断：对象为null - 等待线程1释放锁- 拿到线程1释放的锁- 再次判断：对象不为null（线程1创建了对象） - 释放锁 - 返回instance；
 */
class Singleton4{
	private Singleton4(){}
	private static Singleton4 instance = null;
	public static Singleton4 getInstance(){
		//只做一层判断：会出错！
		if(instance == null){
			synchronized(Singleton4.class){
				instance = new Singleton4();
			}
		}
		return instance;
	}
}
