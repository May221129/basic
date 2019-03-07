package thread.a05volatile;
/**
 * 用volatile修饰对象是什么情况——总结:
 * 	1：volatile修饰的引用类型, 能及时的将这个引用指向的对象中的基本类型及时的同步. 
 * 
 * 	2：A没有被修饰volatile, SubA的中的某个属性有被修饰volatile, 那么获取那个属性的时候, 也能及时同步.
 * 
 * 	3:A没有被修饰为volatile, 局部变量声明volatile SubA s = a.getSubS(),  符合第一点中所讲的.
 * 
 * 	4:仅A被修饰为volatile. 用链式编程objA.getSub().getAge(), 那么subA可以及时同步, 但是将SUbA赋值给某个非volatile修饰
 * 		的时候就不行了.[SubA s = a.getSubS()直接这么写是不能及时同步的。]
 * 
 * 	总的来说, volatile的作用不能跨层. 
 * 
 * 以下为程序可以停止的情况总结：
 * 	前提：以下三种情况除了说明有被声明为volatile之外的都是没有被声明为volatile的.
 * 	情况1：
 * 		只有类R中ObjectA属性被修饰为volatile
 * 		run方法为:
 * 			public void run() {
 *				while(objA.getSub().getAge() == 10){
 *			
 *				}
 *			}
 *		说明:估计是因为objA.getSub().getAge()这样的链式编程并没有先将sub对象拿回工作内存,而是直接
 *			在主存中取了sub的age属性。
 *		     试了下:前提一样, 如果不是用链式编程的话,也就是说将getSub先付给成员变量SubObjectA(且该成员变量没有被修饰为volatile)
 *			那么就结束不了
 * 	情况2:
 * 		只有在SubObjectA中,private volatile int age;
 * 		run方法:
 * 	        public void run() {
	        	SubObjectA sub = objA.getSub();
	        	while(sub.getAge() == 10){
	        		
	        	}
	        }
 * 		说明:SubObjectA sub = objA.getSub();这样sub对象就只会取一次且放在工作内存中，
 * 			想要age可见就直接在sub类中将age属性生命为volatile.
 * 			注意:本来想在ObjectA中将sub属性声明为volatile,但一点鸟用没有(可能原因:objA.getSUb()得到的对象确实是被声明为volatile，
 * 			但是赋值给SubObjectA sub,却没有被声明为volatile, 可能编译器只认该引用有没有被声明而已)。
 * 	情况3:
 * 		class R extends Thread{
		    private ObjectA objA;
		    private volatile SubObjectA sub;
		    public R(ObjectA objA ){
		    	this.objA = objA;
		    }
	        @Override
	        public void run() {
	        	sub = objA.getSub();
	        	while(sub.getAge() == 10){
	        		
	        	}
	        }
		}
		说明:既然在ObjectA中将sub属性声明为volatile,但一点鸟用没有。且又不想在sub类中将age属性声明为volatile，
			那么就在R类中将sub声明为volatile即可。
 * @author lgr
 */
public class A03VolatileTest {
	public static void main(String[] args) {
		ObjectA a = new ObjectA();
		SubObjectA s = new SubObjectA();
		s.setAge(10);
		a.setAge(10);
		a.setSub(s);
		Thread t = new R(a);
		
		t.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		s.setAge(20);
		a.setAge(20);
	}
}

class R extends Thread{
	private volatile ObjectA objA;
	private SubObjectA sub;
	public R(ObjectA objA ){
		this.objA = objA;
	}
	@Override
	public void run() {
		sub = objA.getSub();
		while(sub.getAge() == 10){
			
		}
	}
}

class ObjectA{
	private SubObjectA sub;
	private int age;
	
	public SubObjectA getSub() {
		return sub;
	}
	public void setSub(SubObjectA sub) {
		this.sub = sub;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
class SubObjectA{
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}


