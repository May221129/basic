package oop.finaltest;
//final（最终）的练习：
public class Something1 {
	public static void main(String[] args){
		Other o = new Other();
		new Something1().addOne(o);
	}
	public void addOne(final Other o){//final修饰的是o,只要o不被修改，那这个就是对的。
//		o = new Other();这样是错误的！
//		o = null;这样是错误的！
		o.i++;
	}
}
class Other{
	public int i;
}