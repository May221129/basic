package thread.a01extendsthread;
/**
 * ���̣߳�Thread�����ĵ��ã�
 */
public class TestThreadMethod04 {
	public static void main(String[] args) {
		SubThread1 st = new SubThread1();
		st.start();
		st.setPriority(Thread.MAX_PRIORITY);//Ҳ����ֱ��д��st.setPriority(10);
		st.setName("���߳�");
		Thread.currentThread().setName("���������������߳�");
		for(int i = 1; i <= 100; i++){
			System.out.println( Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + " : " + i);
//			if(i % 10 == 0){
//				Thread.currentThread().yield();//���ô˷������߳��ͷŵ�ǰCPU��ִ��Ȩ��
//			}
//			if(i == 50){
//				try {
//					st.join();
////join():��A�߳��е���B�̵߳�join()��������ʾ����ִ�е��˷���ʱ��A�߳�ִֹͣ�У�ֱ��B�߳�ִ���꣬A�̲߳Ž���ִ��join()֮��δִ�еĴ��롣
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
		}
		System.out.println(st.isAlive());//�жϵ�ǰ�߳��Ƿ��
	}
}

class SubThread1 extends Thread{
	public void run(){
		for(int i = 1; i <= 100; i++){
//			try {
//				Thread.currentThread().sleep(50);//��ʽ���õ�ǰ�߳�˯��L����
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			System.out.println( Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ": " + i);
		}
	}
}
