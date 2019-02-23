package classLoader.a_note;

/**
ClassLoader的重点整理：
一、JVM加载器（博客：https://blog.csdn.net/briblue/article/details/54973413）
	1.三个系统加载器：BootstrapClassLoader，ExtClassLoader，APPClassLoader。
		1.1 父委托机制；
		1.2 loadClass(){
			findLoadedClass();
			parent.loadCLass();
			findClass(){
				defineClass();
			}
		      }
	2.各个加载器之间，最大的不同在于“加载域的不同”。

二、热加载
	1.什么是热加载？
	2.实现热加载的关键：
		①每次都要拿到最新的被热加载类的Class。
		②一个加载器只能加载同一个类一次，所以对于已经加载过了的类，需要new出新的加载器来重新加载。
		③使用场景规范：外界不能强依赖于"被热加载的类"，避免该热加载的类的实例一直处于根可达，不被回收掉。否则可能出现问题。
			如：
			前提：①A类被加载了两次，这里分别用oldA和newA表示前后两次加载到内存中的A的大Class对象。②a是A的实例。
			a instanceof(A){
				A aa = (A)a;
				aa.show();//这个show()方法是newA中新添加的方法，oldA中并没有这个方法，所以执行下面的aa.show()会出错。
			}

三、逆向依赖问题
	①逆向依赖时能够依赖得到吗？看测试
	②解决方法：通过上下文加载器。

四、上下文加载器：CurrentContextClassLoader

五、如何判断两个类是否是同一个类："加载器相同+全类名"相同，那么就是同一个类。

五、tomcat的类加载机制（博客：https://blog.csdn.net/qq_34212276/article/details/78367127）
	1.tomcat自己实现的几个类，及其加载域：
		①JSPLoader：每个JSP文件都有一个自己的JSPLoader。顾名思义，加载JSP文件。
		②WebAppClassLoader：当前应用的ClassPath。
		③SharedClassLoader：所有应用、不含Tomcat。
		④CatalinaCLassLoader：仅Tomcat。
		⑤CommonCLassLoader：所有应用及Tomcat。
		注：CommonClassLoader的父加载器就是APPClassLoader了。
	2.tomcat容器中的加载器加载顺序
		①默认是不遵循父委托机制的（开启：在"...\apache-tomcat-版本号\conf\Context.xml"文件中设置了<Loader delegate = "true">）。此时的加载顺序：BootstrapClassLoader-ExtClassLoader-APPClassLoader-WebAppClassLoader。
		②开启了父委托机制后的加载顺序是：BootstrapClassLoader-ExtClassLoader-APPClassLoader-CommonClassLoader-WebAppClassLoader。
		两者的区别：CommonClassLoader。为什么：
			①CommonClassLoader加载的是tomcat及该tomcat容器中各个应用共同需要的jar包；
			②如果一个jar，在WebAPPClassLoader加载的ClassPath中有一份，在CommonClassLoader加载的"...\apache-tomcat-版本号\lib"中也有一份，那一般会选择用ClassPath下的（定制化高），而非lib中大家公用的，这个有点像就近原则。
			③因为②中的就近原则，所以要让WebAPPClassLoader先加载。
 */
public class ClassLoaderNote {

}
