package oop.object;

/**
 * 证明“Object是所有类的超类”。
 * 
 * 证据一：将下文；
 * 证据二：创建的所有类型的对象都可以指向Object引用。
 * 
 * @author May
 * 2019年12月17日
 */
public class A01_ObjectIsTheSuperClassOfAllClasses {
	
	/**
	 * 证据一、ObjectNote默认其顶级父类是Object，所以可以重写Object中的方法。
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
