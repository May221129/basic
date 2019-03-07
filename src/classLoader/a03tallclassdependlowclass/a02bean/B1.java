package classLoader.a03tallclassdependlowclass.a02bean;

public class B1 {//extends a03tallclassdependlowclass.a02bean.A2 
	static {
		System.out.println(B1.class.getName() + " --> 我初始化成功了");
	}
	public B1(){
		super();
		System.out.println("执行B1实例的构造器");
	}
}
