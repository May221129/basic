package classLoader.a01helloworld;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import sun.misc.Launcher;

public class A03Test {
	public static void main(String[] args) throws MalformedURLException {
		ClassLoader parent = new Launcher().getClassLoader().getParent();
		File file = new File("Q:\\ClassLoaderTest");
		URL[] urls = new URL[]{file.toURL()};
		A03GetExtClassLoader classLoader = new A03GetExtClassLoader(urls, parent);
		System.out.println(classLoader.getParent() + " == " + parent);
	}
}
