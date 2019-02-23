/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread.a06important;

/**
 * ��A�̵߳�ִ�е�ʱ��,ִ�е���B.join(), ��ôA�߳̾ͻύ��CPUִ��Ȩ,ֱ��B�߳�ִ�н���,A�̲߳��ܼ���ִ�� �� ע����������߳�û��ϵ��
 * �����������: ���̡߳�t1��t2����. ���߳�ִ��t1.join,  ���߳��ó�CPUִ��Ȩ,  ֻ��t1, t2����. �ȵ�t1����, ���̲߳��ܼ���.
 * 	�����t1.join�ŵ�t2.start��ǰ��,  ��ô����t1ִ����֮��,  ���̺߳�t2һ��ִ��.
 * @author ligr
 */
public class B02Join {
    public static void main(String[] args) throws InterruptedException {
        Demo t1 = new Demo();
        t1.setName("t1");
        Demo t2 = new Demo();
        t2.setName("t2");
        Thread.currentThread().setName("���߳�");
        
        t1.start();
        t2.start();
        t1.join();
        
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " .... " + i);
        }
    }

    private static class Demo extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " .... " + i);
            }
        }
    }
}
