package thread.a05volatile;

/**
 * ��Java�������ʵս����27ҳ�Ĵ��롪���ֶβ��ɼ���
 * ���̺߳�ReaderThread�̲߳�����
 * 
 * @author May
 * 2019��11��14��
 */
public class A02Novisible2 {
	private static boolean ready;
	private static int number;
	
	private static class ReaderThread extends Thread {
		@Override
		public void run() {
			while(!ready) {
				Thread.yield();//����������д���ע�͵��������Բ��Ե��ֶβ��ɼ�����
			}
			System.out.println("number == " + number);
		}
	}

	public static void main(String[] args) {
		new ReaderThread().start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		number = 42;
		ready = true;
	}
}
