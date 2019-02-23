package classLoader.a03tallclassdependlowclass;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import classLoader.a02myclassloader.A02MyClassLoaderExtendsURLClassLoader;

/**
 * 1.测试：
 * 		package a03tallclassdependlowclass.a02bean;
		public class A1 extends B1{
			static{
				System.out.println(A1.class.getName() + " --> 我初始化成功了");
			}
			public A1(){
				System.out.println("执行A1的构造器");
			}
		}
		
		package a03tallclassdependlowclass.a02bean;
		public class B1 extends A2 {
			static {
				System.out.println(B1.class.getName() + " --> 我初始化成功了");
			}
			public B1(){
				System.out.println("执行B1的构造器");
			}
		}
		
		package a03tallclassdependlowclass.a02bean;
		public class A2 {
			static{
				System.out.println(A2.class.getName() + " --> 我初始化成功了");
			}
			public A2(){
				System.out.println("执行A2的构造器");
			}
		}
 * 	
 * 2.前提：
 * 	①自定义加载器的加载路径：Q:\\ClassLoaderTest。
 * 	②main()方法所在的类，是由AppClassLoader进行加载的。
 * 	③加载器A的父加载器是AppClassLoader。
 * 	④A加载的类：A1、A2;             AppClassLoader：B1
 * 
 * 3.运行结果【cmd命令行运行】：
 * 	Q:\mystudy\workplace\javase\bin>java a03tallclassdependlowclass.A02MyClassLoaderHaveInit
 * 	Exception in thread "main" java.lang.NoClassDefFoundError: a03tallclassdependlowclass/a02bean/A2
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
        at a03tallclassdependlowclass.A02MyClassLoaderHaveInit.main(A02MyClassLoaderHaveInit.java:60)
Caused by: java.lang.ClassNotFoundException: a03tallclassdependlowclass.a02bean.A2
        at java.net.URLClassLoader.findClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at sun.misc.Launcher$AppClassLoader.loadClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        ... 25 more
        
 * 3.结论：
 * 	3.1  加载一个类，不会引发该类被初始化，只有主动引用该类才会引发该类的初始化。
 *  3.2 加载器加载顺序是遵循父委托机制，但不能逆向加载（详见A01MyClassLoaderNotInit的4.2），
 *  	但是初始化一个类却需要先初始化其父类，A2加载失败，也就意味着A2类不能被初始化，从而导致B1类无法完成初始化，最终导致A1类的初始化也失败了。
 *  	A1都没完成类的初始化，自然不能通过反射newInstance。
 *  
 * 4.用cmd命令来运行程序：
 * 	java -Djava.ext.dirs=""  -cp D:\jars\commons\commons-beanutils-1.9.3,abcdefg.jar  -Xmx1g -Xms1g -Xmn -Xss a03tallclassdependlowclass.A02MyClassLoaderHaveInit
 * 	解释：java：运行      -Djava.ext.dirs=""：设置系统配置      
 * 		-cp：该项目依赖的各种jar包（即eclipse中buildPath的那些jar），或依赖的jar包的路径；在Windows中用";"符号进行分割， linux用":"。
 * 		-Xmx1g -Xms1g -Xmn -Xss ：设置虚拟机各个区（堆、新生代、老年代等）的内存分配
 * 		a03tallclassdependlowclass.A02MyClassLoaderHaveInit：全类名
 * 	提问：eclipse中运行程序时，是eclipse帮我们设置好了默认的运行路径（..../bin/全类名），且classPath的路径也很明确。
 * 		那自己用cmd命令运行时，AppClassLoader怎么加载到你的mian()方法的？
 * 		答：jdk版本比较低的时候，是需要我们在系统中配置好classPath（配置：.;），jdk版本高的帮我们配置好了，
 * 		所以"java a03tallclassdependlowclass.A02MyClassLoaderHaveInit"命令，即：java classPaht.
 * 		a03tallclassdependlowclass.A02MyClassLoaderHaveInit = . = classPaht
 * 	
 * 5.特别注意！！！
 * 	在eclipse跑测试会不准确，因为当我把a03tallclassdependlowclass.a01bean包中的A1,A2类删除掉后，B1会报错，
 * 	这时候eclipse会偷偷把Objcet当做是父类而非A2，所以整个的继承关系都乱了。也就导致了结果是不正确的。所以我们要用cmd命令行来直接运行代码。
 */
public class A02MyClassLoaderHaveInit {
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException {
		File file = new File("Q:\\ClassLoaderTest");
		URL[] urls = new URL[]{file.toURI().toURL()};
		A02MyClassLoaderExtendsURLClassLoader loader = new A02MyClassLoaderExtendsURLClassLoader(urls);
		Class clazz = loader.loadClass("a03tallclassdependlowclass.a02bean.A1");
		
		//看A1类的父类是谁：
		System.out.println("A1的父类 --》 " + clazz.getSuperclass());//class a03tallclassdependlowclass.a02bean.B1
		
		//看A1类的爷爷是谁：本来应该是A2的，但是结果出人意料，是Object：
		System.out.println("A1的爷爷 --》 " + clazz.getSuperclass().getSuperclass());//class java.lang.Object
		
		//通过反射，获取到A1的实例：
		Object instance = clazz.newInstance();
		System.out.println(instance.toString());
		Method method = clazz.getDeclaredMethod("test", null);
		method.invoke(clazz, null);
	}
}
