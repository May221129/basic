package classLoader.a06tomcatusingclassloader;

/**
 * 探究：tomcat的类加载机制：
	1.tomcat自己实现的几个类，及其加载域：
		①JSPLoader：负责加载Jsp，每一个Jsp文件都对应一个Jsp加载器。
		②WebAppClassLoader：负载加载单个Web应用下classes目录以及lib目录的类库，只能当前应用使用。
		③SharedClassLoader：负载加载/shared目录的类库，可被所有的web应用使用，但tomcat不可使用。
		④CatalinaCLassLoader：负责加载/server目录的类库，只能被tomcat使用。
		⑤CommonCLassLoader：负责加载/common目录的类库，这儿存放的类库可被tomcat以及所有的应用使用。
		注：CommonClassLoader的父加载器就是APPClassLoader了。
	
	2.tomcat容器中的加载器加载顺序
		①默认是不遵循父委托机制的（开启：在"...\apache-tomcat-版本号\conf\Context.xml"文件中设置了<Loader delegate = "true">）。此时的加载顺序：BootstrapClassLoader-ExtClassLoader-APPClassLoader-WebAppClassLoader。
		②开启了父委托机制后的加载顺序是：BootstrapClassLoader-ExtClassLoader-APPClassLoader-CommonClassLoader-WebAppClassLoader。
		两者的区别：CommonClassLoader。为什么：
			①CommonClassLoader加载的是tomcat及该tomcat容器中各个应用共同需要的jar包；
			②如果一个jar，在WebAPPClassLoader加载的ClassPath中有一份，在CommonClassLoader加载的"...\apache-tomcat-版本号\lib"中也有一份，那一般会选择用ClassPath下的（定制化高），而非lib中大家公用的，这个有点像就近原则。
			③因为②中的就近原则，所以要让WebAPPClassLoader先加载。
	
	3.tomcat加载器详解：
		https://blog.csdn.net/qq_34212276/article/details/78367127
		只需要看到“来看看源码中关于这三个类加载器的创建吧，如下代码：”部分就行了。后面的源码可作为深入理解。
 */
public class TomcatUsingClassLoader {
	
}
