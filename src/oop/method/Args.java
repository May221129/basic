package oop.method;
//�ɱ�������βεķ���
public class Args {
	public static void main(String[] args) {
		Hello h = new Hello();
		h.sayHello();
		h.sayHello("Hello life");
		h.sayHello("Hello world","Hello life");
	}
}
class Hello{
	//�������������������أ�
	public void sayHello(){
		System.out.println("Hello World");
	}
	public void sayHello(String s1){
		System.out.println(s1);
	}
//	public void sayHello(String[] s1){
//		for(int i = 0; i < s1.length; i++){
//			System.out.println(s1[i]);
//		}
//	}	
	public void sayHello(String...s1){//�ɱ�������βεķ��������ÿ��Ժ������������ͬ�����������Կ���ֻд�����������
		for(int i = 0; i < s1.length; i++){
			System.out.println(s1[i]);
		}
	}
}
