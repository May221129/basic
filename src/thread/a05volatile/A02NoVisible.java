package thread.a05volatile;

/**
 * ���ԡ����ֶβ��ɼ�
 * 
 * 1.׼����
 * 		�߳�PrintBean��ֻҪflag = true	����һֱִ��while�еĴ��루ע�⣺Ϊ�˲��Խ���ܹ�������ʳ��֡��ֶβ��ɼ����⣬������while��ûд���룩.
 * 		�߳�SetBean���ı�flag��ֵ��flag = false��
 * 	2.���ԣ�
 * 		�߳�PrintBean��������ִ�в�ִ������
 * 		���߳�SetBean����������ִ��������ִ���޸�flagֵǰ��˯1���ԭ��
 * 			ȷ���߳�PrintBean�Ѿ��ɹ�������������ִ����run()������SetBean�̲߳�ȥ�޸�flagֵ�ġ�
 * 			��������߳�PrintBean���߳�SetBeanͬʱ��������ʱ��SetBean���޸���flag��ֵ��PrintBean��ִ��run()����ʱ�ж�flag��Ϊfalse�ˡ�
 * 	3.�����
 * 		�߳�PrintBean��һֱִ��while����ͣ�£���ʹ����SetBean�߳��޸���flag��ֵΪfalse��PrintBean�߳�Ҳ������ ==�� ������ֶβ��ɼ���
 * 		���flag��������volatile�ؼ������޸ģ��Ϳ��Ա�������������������Լ�A02Visible��
 * 	
 * 	4.���ۣ�
 * 		��1�����ֻ�ǵ��߳��ǲ������ֶεĿɼ�������ġ�
 * 		��2����volatile�ؼ��������α������Ϳ��Ա���ñ����ڶ��̻߳����³����ֶεĲ��ɼ����⡣
 * 		��3��volatileֻ�Ǹ������ֶεĿɼ��Ժ��ֶε����������⣬������ζ�ű�volatile���εı��������̰߳�ȫ�ģ����Լ���A03UseVolatileIsThreadSafe
 * 
 * @author May
 * 2019��11��14��
 */
public class A02NoVisible {
	public static void main(String[] args) {
		Bean bean = new Bean();
		PrintBean printBean = new PrintBean(bean);
		SetBean setBean = new SetBean(bean);
		printBean.start();
		setBean.start();
	}
}

class SetBean extends Thread{
	private Bean bean;
	
	public SetBean(Bean bean) {
		this.bean = bean;
	}
	
	public void run() {
		try {
			currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		bean.setFlag(false);
		System.out.println("flag == false.");
	}
}

class PrintBean extends Thread{
	private Bean bean;
	public PrintBean(Bean bean) {
		this.bean = bean;
	}
	@Override
	public void run() {
		while(bean.getFlag()) {
			//ע�⣺Ϊ�˲��Խ���ܹ�������ʳ��֡��ֶβ��ɼ����⣬������while��ûд���롣
		}
		System.out.println("PrintBean����whileѭ���ˡ�");
	}
}

class Bean{
	private boolean flag = true;
	
	public void setFlag(boolean bool) {
		flag = bool;
	}
	
	public boolean getFlag() {
		return this.flag;
	}
}