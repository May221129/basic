package thread.a06important;

/**
 * ����2.2
 */
public class C01Interrupt2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	System.out.println("�ҿ�ʼִ��������");
					Thread.sleep(100000);
					System.out.println("���Լ��ѵģ�");
				} catch (InterruptedException e) {
					System.out.println("�ұ������ˣ���ʼ�����ɣ�");
					for(int i = 0; i < 10; i++){
						System.out.println(Thread.currentThread().getName() + " ==> i");
					}
				}
            }
        });
        t1.start();
        Thread.sleep(3000);
        System.out.println("Ҫ�ж���");
        t1.interrupt();//�� ���д�������ã�ֻ�Ǹı���t1�̵߳�interrupt״̬��
        System.out.println("�жϽ���"); 
    }
}
