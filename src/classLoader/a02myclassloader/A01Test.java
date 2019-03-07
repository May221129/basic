package classLoader.a02myclassloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 1.本类继承了ClassLoader,并重写其findClass(String name)方法。
 * 	
 * 2.不论是继承ClassLoader还是继承URLClassLoader的自定义加载器：
 * 		①可以通过"path + 全类名"加载.class文件。
 * 		②不能通过"path + 全类名"加载jar包中的.class文件，因为jar包是一层目录结构，需要做处理。
 * 	==》	注：
 * 		①path:加载器指定的加载域  ==》  new A01MyClassLoaderExtendsClassLoader("Q:/ClassLoaderTest");
 * 			loadClass("a02myclassloader.bean.Bean")中的"a02myclassloader.bean.Bean"：全类名；
 * 			其中，包名就是加载域下创建与包结构的目录结构。
 * 		②buildPath：将指定路径下的jar包加入到classPath中。
 * 
 * 3.重点：各个构造器最大的不同点在于其所需加载的类/资源所在的路径不同。
 * 
 * 4.自定义加载器时选择谁当父加载器要慎重：父委托机制是java虚拟机定义好的，不能随意打破。
 * 		如：将BootstrapClassLoader作为MyClassLoader的父加载器，那么在由MyClassLoader加载的类中，如果有用到原本是由Ext
 * 		加载的类，这里都无法使用了，因为MyClassLoaderLoader委托Bootstrap去加载，Bootstrap加载不到，返回到MyClassLoader
 * 		也加载不到。结果就是，要么别用到Ext加载的类，要么MyClassLoader自己去实现这个类。
 */
public class A01Test {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		A01MyClassLoaderExtendsClassLoader classLoader = new A01MyClassLoaderExtendsClassLoader("Q:/ClassLoaderTest");
		try {
			Class clazz = classLoader.loadClass("a02myclassloader.bean.Bean");
			if(clazz != null){
				try {
					Object bean = clazz.newInstance();
					Method method = clazz.getDeclaredMethod("test", null);
					method.invoke(bean, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
