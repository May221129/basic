package oop.finaltest;
//final�����գ�����ϰ��
public class Something1 {
	public static void main(String[] args){
		Other o = new Other();
		new Something1().addOne(o);
	}
	public void addOne(final Other o){//final���ε���o,ֻҪo�����޸ģ���������ǶԵġ�
//		o = new Other();�����Ǵ���ģ�
//		o = null;�����Ǵ���ģ�
		o.i++;
	}
}
class Other{
	public int i;
}