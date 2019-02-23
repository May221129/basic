package thread.a06important;

/**
 * ���´����ʾ��interrupt���������ж��̣߳��������³���һֱ������ȼ���Ȼ��ִ��interrupt����������ִ����֮���ַ����̻߳����ܡ�û���̲߳���ͣ������   
 * 	��ôinterrupt���жϷ�����������ô�ã� ����������������֪ͨ����߳�һ���ж���Ϣ������Ȼ���̴߳���һ�������Ϣ��Ȼ������Ӧ����
 * ��ô����ж��أ�
 * 	1:���������ȥ�����ж�һ���̣߳��߳�ʵ��.interrupt()
 *     	Thread.interrupt�����ʵ����  Thread.currentThread().interrupt()
 * 	2:�ж��߳��ֳַ��ж��������еģ��ͷ��������е�
 * 		2.1 �������е��̵߳��жϣ���������еĴ�����д
 * 			�̴߳�������Ҫ����Thread.interrupted()����boolean���жϸ��߳��Ƿ��жϡ�
 *  		����boolean��ô������Ӧ�Ĵ���
 * 		2.2 ������״̬���߳�(�̱߳������״̬, wait,sleep,park,join�ȶ��ᵼ���̱߳�����):��E04Interrupt2.java
 *  		thread��ĳЩ�������׳�InterruptedException �쳣��  ����sleep wait join
 *  		һ��interrupt����ô��Щ�����ͻ��׳�InterruptedException������Ϳ��Բ�׽��������.
 *  		��ʱ���interrupt���൱�ڴ���̵߳Ĺ���״̬.
 *  		ע��:synchronizedҲ�ᵼ���̱߳�����, ����synchronized��֧�ֱ����, ��������ȱ��, lock���������ȱ��.
 * 	3��Thread.interrupt()������һ��֪ͨ�� ������֪ͨ��û�б�ǡ�õĲ�׽������ô���̵߳�����ʱû���κ�Ӱ��ġ���β�׽ס���֪ͨ�����ǵڶ����н���.
 */
public class C01Interrupt1 {//����2.1
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.interrupted()){//�� ִ����ٺ�������жϽ����Ϊ����ǰThread=���жϡ�״̬�ˣ��������ж�����������whileѭ����
                    System.out.println(Thread.currentThread().getPriority());
                }
            }
        });
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        Thread.sleep(3000);
        System.out.println("Ҫ�ж���");
        t1.interrupt();//�� ���д�������ã�ֻ�Ǹı���t1�̵߳�interrupt״̬��
        System.out.println("�жϽ���"); 
    }
}
