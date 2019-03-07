package classLoader.a04loadclassandinitclass;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import classLoader.a02myclassloader.A02MyClassLoaderExtendsURLClassLoader;

/**
 * 1.测试：类的加载和类的初始化的关系
 * 	package a04loadclassandinitclass;
	public class A extends a04loadclassandinitclass.B{
		static{
			System.out.println(A.class.getName() + "我被初始化了!");
		}
		public A(){
			System.out.println("执行A的构造函数！");
		}
		public void test(){
			System.out.println("A实例的test()执行成功！");
		}
	}
	
	package a04loadclassandinitclass;
	public class B {
		static{
			System.out.println(B.class.getName() + "我被初始化了!");
		}
		public B(){
			System.out.println("执行B的构造函数！");
		}
	}
 * 
 * 2.运行结果：
 * 	a04loadclassandinitclass.B我被初始化了!
	a04loadclassandinitclass.A我被初始化了!
	执行B的构造函数！
	执行A的构造函数！
	A实例的test()执行成功！
	A的父类：class a04loadclassandinitclass.B
	
 * 3.结论：
 * 	①主动引用才会引起类的初始化（加载就不会）。
 * 	②类的初始化用的加载器，就是加载该类时用的那个加载器。
 */
public class A01WhichloaderIsUsedToInitializeTheClass {
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		File file = new File("Q:\\ClassLoaderTest");
		URL[] urls = new URL[]{file.toURI().toURL()};
		A02MyClassLoaderExtendsURLClassLoader myClassLoader = new A02MyClassLoaderExtendsURLClassLoader(urls);
		Class<?> clazz = myClassLoader.loadClass("a04loadclassandinitclass.A");
		
		Object instance = clazz.newInstance();
		Method method = clazz.getDeclaredMethod("test", null);
		method.invoke(instance, null);
		
		System.out.println("A的父类：" + clazz.getSuperclass());
	}
}
