package classLoader.a01helloworld;

/**
 * BootstrapClassLoader��ExtClassLoader �� AppClassLoader �ļ���·����
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
		 * ���·����ʵ���ǵ�ǰjava����Ŀ¼bin�������ŵ��Ǳ������ɵ�class�ļ���
		 */
		System.out.println(System.getProperty("java.class.path"));
		
	}
}
