package oop.constructor;

//��������ʹ�ùؼ���this����ϰ��Girl Boy
public class testGirlBoy {
	public static void main(String[] args) {
		Boy boy = new Boy();
		boy.setName("�����");
		boy.setAge(26);
		Girl girl = new Girl();
		girl.setName("����÷");
		
		boy.marry(girl);
		boy.shout();
		System.out.println();
		
		girl.marry(boy);
	}
}
