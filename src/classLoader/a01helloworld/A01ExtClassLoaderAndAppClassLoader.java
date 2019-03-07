package classLoader.a01helloworld;

/**
 * BootstrapClassLoader、ExtClassLoader 和 AppClassLoader 的加载路径。
 *
 */
public class A01ExtClassLoaderAndAppClassLoader {
	public static void main(String[] args) {
		
		System.out.println(System.getProperty("sun.boot.class.path"));
		
		System.out.println("-----------------------------");
		
		System.out.println(System.getProperty("java.ext.dirs"));
		
		System.out.println("-----------------------------");
		
		/**
		 * Q:\mystudy\workplace\javase\bin;......
		 * 这个路径其实就是当前java工程目录bin，里面存放的是编译生成的class文件。
		 */
		System.out.println(System.getProperty("java.class.path"));
		
	}
}
