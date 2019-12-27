package oop.object;

import org.junit.Test;

/**
 * Object的clone()方法：实现对象的浅复制，只有实现了Cloneable接口才可以调用该方法，否则抛出CloneNotSupportedException异常
 * 
 * @author May
 * 2019年12月26日
 */
public class A02Method_Clone {
	/**
	 * A没有实现Cloneable接口，所以会抛出不支持克隆的异常
	 */
	@Test
	public void unImplementsCloneable() {
		A a = new A();
		try {
			a.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * AA实现了Cloneable接口，所以不再抛出不支持克隆的异常
	 */
	@Test
	public void implementsCloneable() {
		AA aa = new AA();
		try {
			aa.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}

class A{
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

class AA implements Cloneable{
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}