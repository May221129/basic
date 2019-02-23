package oop.method;
//可变个数的形参的方法
public class Args {
	public static void main(String[] args) {
		Hello h = new Hello();
		h.sayHello();
		h.sayHello("Hello life");
		h.sayHello("Hello world","Hello life");
	}
}
class Hello{
	//下面三个方法构成重载：
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
	public void sayHello(String...s1){//可变个数的形参的方法，作用可以涵盖上面的三个同名方法，所以可以只写这个方法即可
		for(int i = 0; i < s1.length; i++){
			System.out.println(s1[i]);
		}
	}
}
