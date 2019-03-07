package classLoader.a03tallclassdependlowclass;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

import classLoader.a02myclassloader.A02MyClassLoaderExtendsURLClassLoader;

/**
 * 1.测试：
 * 		package a03tallclassdependlowclass.a01bean;
		public class A1 extends B1{
			static{
				System.out.println(A1.class.getName());
			}
		}
		
		package a03tallclassdependlowclass.a01bean;
		public class A2 {
			static{
				System.out.println(A2.class.getName());
			}
		}
		
		package a03tallclassdependlowclass.a01bean;
		public class B1 extends A2 {
			static {
				System.out.println(B1.class.getName());
			}
		}
		
 * 2.前提：
 * 	①自定义加载器的加载路径：Q:\\ClassLoaderTest。
 * 	②main()方法所在的类，是由AppClassLoader进行加载的。
 * 	③加载器A的父加载器是AppClassLoader。
 * 	④A加载的类：A1、A2;      AppClassLoader：B1
 * 	⑤A1中没有主动引用B1，B1中也没有主动引用A2,即它们都没有触发被依赖类的初始化。
 * 
 * 3.程序运行结果【用cmd命令行运行应用】：
 * 	Q:\mystudy\workplace\javase\bin>java a03tallclassdependlowclass.A01MyClassLoaderNotInit
	Exception in thread "main" java.lang.NoClassDefFoundError: a03tallclassdependlowclass/a01bean/A2
        at java.lang.ClassLoader.defineClass1(Native Method)
        at java.lang.ClassLoader.defineClass(Unknown Source)
        at java.security.SecureClassLoader.defineClass(Unknown Source)
        at java.net.URLClassLoader.defineClass(Unknown Source)
        at java.net.URLClassLoader.access$100(Unknown Source)
        at java.net.URLClassLoader$1.run(Unknown Source)
        at java.net.URLClassLoader$1.run(Unknown Source)
        at java.security.AccessController.doPrivileged(Native Method)
        at java.net.URLClassLoader.findClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at sun.misc.Launcher$AppClassLoader.loadClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at java.lang.ClassLoader.defineClass1(Native Method)
        at java.lang.ClassLoader.defineClass(Unknown Source)
        at java.security.SecureClassLoader.defineClass(Unknown Source)
        at java.net.URLClassLoader.defineClass(Unknown Source)
        at java.net.URLClassLoader.access$100(Unknown Source)
        at java.net.URLClassLoader$1.run(Unknown Source)
        at java.net.URLClassLoader$1.run(Unknown Source)
        at java.security.AccessController.doPrivileged(Native Method)
        at java.net.URLClassLoader.findClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at a03tallclassdependlowclass.A01MyClassLoaderNotInit.main(A01MyClassLoaderNotInit.java:65)
Caused by: java.lang.ClassNotFoundException: a03tallclassdependlowclass.a01bean.A2
        at java.net.URLClassLoader.findClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at sun.misc.Launcher$AppClassLoader.loadClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        ... 25 more
 * 
 * 4.结论：
 * 	4.1 加载一个类，如果该类有父类，是先加载该类，再加载其父类（只有加载了该类，才会知道它有父类）。【debug时可以观察到】
 * 	4.2 加载一个类时遵循父委托机制，A1 extends B1，先加载A1（执行A.loadClass()方法，且遵循父
 * 		委托机制），那么加载类B1时，同样执行"A加载器.loadClass()"（当然，这里依旧遵循父委托机制）。
 * 		加载B1的时候发现它有父类A2，所以由AppClassLoader成功加载完B1后，同样用AppClassLoader去
 * 		加载A2且要遵循父委托机制，AppClassLoader、ExtClassLoader、BootstrapClassLoader
 * 		都无法加载A2(因为A2是由A加载器负责加载的，而A加载器是最底层的加载器，且加载顺序遵循父委托机制，
 * 		是逐层往父类上看是否能加载的，而不能往下看子加载器是否能加载，所以A2无法成功加载到内存)。【debug时可以观察到】
 * 		==>总而言之：加载只能遵循父委托机制逐层往上走，而不能逆向去委托更底层的加载器做加载。
 * 		注：①父委托机制具体加载流程见：Q:\mystudy\studynotes\jvm_gc_classloader\classloader\ClassLoader_我的理解.docx 中的1.4双亲委托。
 * 		   ②类A依赖类B：如该类有父类，如该类的类变量是其他类，等等。
 * 
 * 5.特别注意！！！
 * 	在eclipse跑测试会不准确，因为当我把a03tallclassdependlowclass.a01bean包中的A1,A2类删除掉后，B1会报错，
 * 	这时候eclipse会偷偷把Objcet当做是父类而非A2，所以整个的继承关系都乱了。也就导致了结果是不正确的。所以我们要用cmd命令行来直接运行代码。
 */
public class A01MyClassLoaderNotInit {
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException {
		File file = new File("Q:\\ClassLoaderTest");
		URL[] urls = new URL[]{file.toURI().toURL()};
		A02MyClassLoaderExtendsURLClassLoader loader = new A02MyClassLoaderExtendsURLClassLoader(urls);
		//注意，B1 extends A2，因为A2是由A加载器加载的，所以没在classPath中，所以class B1类会报错，
		//为了让项目没有报错信号，我就暂时把B1类注释掉了。如果要跑该main，就要把B1的注释去掉。
		Class clazz = loader.loadClass("a03tallclassdependlowclass.a01bean.A1");
	}
}
