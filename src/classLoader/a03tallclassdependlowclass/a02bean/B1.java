package classLoader.a03tallclassdependlowclass.a02bean;

public class B1 {//extends a03tallclassdependlowclass.a02bean.A2 
	static {
		System.out.println(B1.class.getName() + " --> �ҳ�ʼ���ɹ���");
	}
	public B1(){
		super();
		System.out.println("ִ��B1ʵ���Ĺ�����");
	}
}
