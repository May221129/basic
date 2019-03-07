package thread.a05volatile;
/**
 * ��volatile���ζ�����ʲô��������ܽ�:
 * 	1��volatile���ε���������, �ܼ�ʱ�Ľ��������ָ��Ķ����еĻ������ͼ�ʱ��ͬ��. 
 * 
 * 	2��Aû�б�����volatile, SubA���е�ĳ�������б�����volatile, ��ô��ȡ�Ǹ����Ե�ʱ��, Ҳ�ܼ�ʱͬ��.
 * 
 * 	3:Aû�б�����Ϊvolatile, �ֲ���������volatile SubA s = a.getSubS(),  ���ϵ�һ����������.
 * 
 * 	4:��A������Ϊvolatile. ����ʽ���objA.getSub().getAge(), ��ôsubA���Լ�ʱͬ��, ���ǽ�SUbA��ֵ��ĳ����volatile����
 * 		��ʱ��Ͳ�����.[SubA s = a.getSubS()ֱ����ôд�ǲ��ܼ�ʱͬ���ġ�]
 * 
 * 	�ܵ���˵, volatile�����ò��ܿ��. 
 * 
 * ����Ϊ�������ֹͣ������ܽ᣺
 * 	ǰ�᣺���������������˵���б�����Ϊvolatile֮��Ķ���û�б�����Ϊvolatile��.
 * 	���1��
 * 		ֻ����R��ObjectA���Ա�����Ϊvolatile
 * 		run����Ϊ:
 * 			public void run() {
 *				while(objA.getSub().getAge() == 10){
 *			
 *				}
 *			}
 *		˵��:��������ΪobjA.getSub().getAge()��������ʽ��̲�û���Ƚ�sub�����ûع����ڴ�,����ֱ��
 *			��������ȡ��sub��age���ԡ�
 *		     ������:ǰ��һ��, �����������ʽ��̵Ļ�,Ҳ����˵��getSub�ȸ�����Ա����SubObjectA(�Ҹó�Ա����û�б�����Ϊvolatile)
 *			��ô�ͽ�������
 * 	���2:
 * 		ֻ����SubObjectA��,private volatile int age;
 * 		run����:
 * 	        public void run() {
	        	SubObjectA sub = objA.getSub();
	        	while(sub.getAge() == 10){
	        		
	        	}
	        }
 * 		˵��:SubObjectA sub = objA.getSub();����sub�����ֻ��ȡһ���ҷ��ڹ����ڴ��У�
 * 			��Ҫage�ɼ���ֱ����sub���н�age��������Ϊvolatile.
 * 			ע��:��������ObjectA�н�sub��������Ϊvolatile,��һ������û��(����ԭ��:objA.getSUb()�õ��Ķ���ȷʵ�Ǳ�����Ϊvolatile��
 * 			���Ǹ�ֵ��SubObjectA sub,ȴû�б�����Ϊvolatile, ���ܱ�����ֻ�ϸ�������û�б���������)��
 * 	���3:
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
		˵��:��Ȼ��ObjectA�н�sub��������Ϊvolatile,��һ������û�С����ֲ�����sub���н�age��������Ϊvolatile��
			��ô����R���н�sub����Ϊvolatile���ɡ�
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


