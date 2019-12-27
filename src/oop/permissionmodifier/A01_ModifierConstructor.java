package oop.permissionmodifier;

/**
 * 哪些权限修饰符可以用来修饰构造函数？
 * 
 * 答：四种都可以，不同的权限修饰符修饰构造函数后，该构造函数可被访问的范围和权限修饰符的范围一致。
 * 
 * @author May
 * 2019年12月27日
 */
public class A01_ModifierConstructor {
	public A01_ModifierConstructor() {
		
	}
	protected A01_ModifierConstructor(String a) {
		
	}
	A01_ModifierConstructor(int a){
		
	}
	private A01_ModifierConstructor(char a) {
		
	}
}
