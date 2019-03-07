package classLoader.a04loadclassandinitclass;

public class B {
	static{
		System.out.println(B.class.getName() + "我被初始化了!");
	}
	public B(){
		System.out.println("执行B的构造函数！");
	}
}
