package thread.a05volatile;

/**
 * volatile�������������Եģ���֤���ֶεĿɼ��ԣ��Լ���ֹ���ֶ�������������֤���ֶε��̰߳�ȫ���⡣
 * 1.ʲô���ֶεĿɼ��ԣ� ==�� �����ԡ�
 * 2.ʲô���ֶ������� ==> �ֶ��������ǲ���ϵͳ�����ģ���������Ϊ�ֲ���ԭ��ʱ��ֲ��Ի�ռ�ֲ��ԣ���
 * 		���Ǿ�����Щ�ֶλᱻ�����򣬽����������ʱ�������Ƕ����ö�֪�����Ժ��ѽ��в��ԡ�
 * 3.���volatile�ܹ���֤�ֶεĿɼ��ԣ��Լ���ֹ�����ε��ֶ������򣬾Ϳ�A01Volatile���ɡ�
 * 		A02VolatileTest �� A03VolatileTest�������඼���Ϲ����Ĳ��ԡ�
 * 
 * ����������Եľ����ֶεĿɼ��ԣ�
 * 	1.׼����
 * 		�߳�sa��ֻҪflag = true	����һֱִ��while�еĴ��루ע�⣺Ϊ�˲��Խ���ܹ�������ʳ��֡��ֶβ��ɼ����⣬������while��ûд���룩.
 * 		�߳�setA���ı�flag��ֵ��flag = false��
 * 	2.���ԣ�
 * 		�߳�sa��������ִ�в�ִ������
 * 		���߳�setA����������ִ��������ִ���޸�flagֵǰ��˯1���ԭ��
 * 			ȷ���߳�sa�Ѿ��ɹ�������������ִ����run()������setA�̲߳�ȥ�޸�flagֵ�ġ�
 * 			��������߳�sa���߳�setAͬʱ��������ʱ��setA���޸���flag��ֵ��sa��ִ��run()����ʱ�ж�flag��Ϊfalse�ˡ�
 * 	3.�����
 * 		�߳�sa��һֱִ��while����ͣ�£���ʹ����setA�߳��޸���flag��ֵΪfalse��sa�߳�Ҳ������ ==�� ������ֶβ��ɼ���
 * 		���flag��������volatile�ؼ������޸ģ��Ϳ��Ա����������������
 * 	
 * 	4.���ۣ�
 * 		��1�����ֻ�ǵ��߳��ǲ������ֶεĿɼ�������ġ�
 * 		��2����volatile�ؼ��������α������Ϳ��Ա���ñ����ڶ��̻߳����³����ֶεĲ��ɼ����⡣
 * 		��3��volatileֻ�Ǹ������ֶεĿɼ��Ժ��ֶε����������⣬������ζ�ű�volatile���εı��������̰߳�ȫ�ġ�
 * 
 *  5.sychronized���ܹ���֤�ֶεĿɼ��ԡ�jdk�ṩ��LockҲ����ˡ�
 *  
 *  6.�ֶοɼ��Ժ͡�ÿ���̶߳����Լ��Ļ���(����̵߳Ĺ����ڴ�)���й�ϵ��
 *  	�ٲ���û����volatile�������ֶ�ʱ�������̶߳����ȴ����ڴ�(��)�и���һ�����ݵ��Լ��Ĺ����ڴ��У�Ȼ������Լ������ڴ��е����ݣ�����ٸ��µ����ڴ��С�
 *  	�ڵ��ֶα�volatile���κ󣬸����̲߳������ֶ�ʱ������ֱ�������ڴ��н��в����ġ�
 */
public class A01Volatile {
	public static void main(String[] args) {
		A a = new A();
		ShowA sa = new ShowA(a);
		SetA setA = new SetA(a);
		sa.start();
		
//		try {
//			TimeUnit.SECONDS.sleep(3);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		setA.start();
	}
}
class SetA extends Thread{
	private A a;
	public SetA(A a) {
		this.a = a;
	}
	@Override
	public void run() {
		try {
			a.setFlag();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class ShowA extends Thread{
	private A a;
	public ShowA(A a) {
		this.a = a;
	}
	@Override
	public void run() {
		a.show();
	}
}
class A{
//	private volatile boolean flag = true;
	private Boolean flag = true;
	public void show(){
		while(flag){
//			System.out.println("������������С���ǡ�");
		}
		System.out.println("flag = " + flag);
	}
	public void setFlag() throws InterruptedException {
		Thread.sleep(1000);
		flag = false;
		System.out.println("�Ҹ���flag��");
	}
}