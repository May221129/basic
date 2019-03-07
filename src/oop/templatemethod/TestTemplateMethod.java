package oop.templatemethod;
//模板方法设计模式：
public class TestTemplateMethod {
	public static void main(String[] args) {
		SubTemplate s = new SubTemplate();
		s.spendTime();
	}
}
abstract class Template{//父类：模板
	
	public abstract void code();
//==>把不确定的方法，写成abstract类型，由子类继承时根据实际需求再重写完整
	
	public void spendTime(){//把确定的方法写好
		long start = System.currentTimeMillis();//开始时间
		this.code();//调用程序
		long end = System.currentTimeMillis();//结束时间
		System.out.println("执行这个程序花费时长： " + (end - start));
	}
}

class SubTemplate extends Template{//子类
	public void code(){
		int i1 = 10;
		int i2 = 100;
		int i3 = i1 * i2;
		System.out.println(i3);
	}
}