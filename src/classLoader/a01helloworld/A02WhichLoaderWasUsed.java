package classLoader.a01helloworld;

/**
 * 测试自定义的类和核心类库中的类，分别使用了哪个加载器。
 * 测试加载器的父类是谁。
 */
public class A02WhichLoaderWasUsed {
	public static void main(String[] args) {
		ClassLoader c1 = Test.class.getClassLoader();
		System.out.println("c1 is : " + c1);
		System.out.println("c1's parent ClassLoader is : " + c1.getParent());
		System.out.println("c1's grandpa ClassLoader is : " + c1.getParent().getParent());
		
		ClassLoader c2 = int.class.getClassLoader();
		System.out.println("c2 is : " + c2);
		System.out.println("c2's parent ClassLoader is : " + c2.getParent());
	}
}

class Test{
	
}