package classLoader.a02myclassloader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 	±Ê¼Ç¼û£ºa02myclassloader.A01Test
 */
public class A02Test {
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
		File file = new File("Q:\\ClassLoaderTest");
		URL[] urls = new URL[]{file.toURI().toURL()};
		A02MyClassLoaderExtendsURLClassLoader classLoader = new A02MyClassLoaderExtendsURLClassLoader(urls);
		Class clazz = classLoader.loadClass("a02myclassloader.bean.Bean");
		try {
			Object bean = clazz.newInstance();
			Method method = clazz.getDeclaredMethod("test", null);
			method.invoke(bean, null);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
