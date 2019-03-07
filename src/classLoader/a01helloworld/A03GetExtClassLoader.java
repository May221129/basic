package classLoader.a01helloworld;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 1.前提：AppClassLoader和ExtClassLoader都是系统加载器，且他们都是Launcher类的静态内部类。
 * 2.问题：如何获取到它们呢？
 * 3.场景：获取到ExtClassLoader并显式地将其指定为自定义加载器的父类。
 * 4.获取ExtClassLoader：
 * 		private ClassLoader parent = new Launcher().getClassLoader().getParent();
 * 	①“new Launcher().getClassLoader()”得到AppClassLoader；
 * 	②“AppClassLoader.getParent()”得到ExtClassLoader；
 * 	③ExtClassLoader是Launcher的静态内部类，且其修饰符是缺省，根据缺省的范围，这无法直接new出ExtClassLoader；
 * 	注：想要验证上面所说的，可以直接看Launcher类的源码（不难，一看就懂）。
 */
public class A03GetExtClassLoader extends URLClassLoader {

	public A03GetExtClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}
}
