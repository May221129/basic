package classLoader.a02myclassloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * URLClassLoader是ExtClassLoader和AppClassLoader的父类，该类已经实现得很好了，
 * 	所以如果想要自定一个类加载器，只需要继承URLClassLoader，然后再自定义的加载器中调用父类
 * 	的构造器将URL传给父类即可。
 * 
 * 重点：各个构造器最大的不同点在于其所需加载的类/资源所在的路径不同。
 */
public class A02MyClassLoaderExtendsURLClassLoader extends URLClassLoader{

	public A02MyClassLoaderExtendsURLClassLoader(URL[] urls) {
		super(urls);
	}
}
