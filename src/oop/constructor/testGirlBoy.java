package oop.constructor;

//构造器中使用关键词this的练习：Girl Boy
public class testGirlBoy {
	public static void main(String[] args) {
		Boy boy = new Boy();
		boy.setName("李光荣");
		boy.setAge(26);
		Girl girl = new Girl();
		girl.setName("赖丽梅");
		
		boy.marry(girl);
		boy.shout();
		System.out.println();
		
		girl.marry(boy);
	}
}
