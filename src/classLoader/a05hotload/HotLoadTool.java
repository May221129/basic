package classLoader.a05hotload;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 一、实现热加载的几个问题---------------------------------------------------------------------------------------
 * 	1 一般情况，加载类都不用我们自定义的加载器，热加载既然要由自己控制类的重新加载，就得实现一个自定义加载器，
 * 		该加载器需要对外提供加载的功能，且是热加载。
 * 	2 如何判断一个类已经被改动了？
 * 		答：见 Class<?> loadClass(String name)中的lastModified。
 * 	3 当一个类有改动的时候，如何加载新的类进来；没有改动时，又如何获取到之前已经加载好的类。
 * 		答：见  二、3.
 * 	4 重新加载了一份类信息到内存，会覆盖掉之前加载的那份吗？如果不会，那使用时怎么选择？
 * 		答：不会覆盖，重新加载了几次，内存中就会有几份。通过前面加载的类信息创建的实例，依旧是用原来那份类信息，不会更改；
 * 		但是new一个新的实例就是用最新那份类信息。所以在有热加载的时候，我们写代码需要注意，一个实例如果已经没有用了，
 * 		就要让它及时被回收（根不可达），这样一来，就算内存中有多份类信息，但老的类信息其实也是没有用了的（没实例了）。
 * 	5 为什么需要做热加载的类，不能放到classPath中让APPClassLoader做加载。
 * 		答：①【用AppClassLoader做热加载】回收一个类的条件很苛刻，需要该类没有任何实例+该类没有其他类依赖+加载该类的加载器也要背回收（加载器被回收，
 * 		意味着该加载器加载的所有类都要背回收）+其他，要满足这些条件时很难的，所以一个类一旦被加载到内存中，
 * 		几乎难以被回收。随着不断地重新加载A，AppClassLoader的缓存中就会存在很多份A的类信息，这不合理。
 * 	  	③每个加载器都有个用于放“自己加载进来的类”的缓存，AppClassLoader加载了A，自定义加载器再加载A，
 * 		各自的缓存中都会有A的类信息，这样类信息根本没法合理进行管理，明显不合理。
 *
 * 二、HotLoadTool去实现类的热加载功能的要素-------------------------------------------------------------------------
 * 	1.把"热加载一个类"这个功能抽成HotLoadTool，用户只需要提供path和全类名，HotLoadTool就会返回该类最新的Class对象。
		public HotLoadTool(URL[] urls){...}//载域path传进来
		Class<?> loadClass(String name)//把全类名传进来
 * 	2.Map<全类名，Class> clazzs;  Map<全类名，.class文件的最后一次修改时间> fileLastModified;
 * 	3.loadClass(){//热加载功能的实现
 *  	//该类是否被加载过(通过比较.class文件的lastModified来判断)：
 *  		//加载过且.class文件没被改过：
 *  			//直接从map中获取Class
 *  	//没加载的，或加载过但.class文件被改过：
 *  		//new一个新的ClassLoader进行加载
 *   }
 */
public class HotLoadTool {
	
	private static Map<String, Class> clazzs = new HashMap<>();//Map<全类名，Class>
	private static Map<String, Long> fileLastModified = new HashMap<>();//Map<全类名，.class文件的最后一次修改时间>
	private String path;
	
	public HotLoadTool(String path) {
		this.path= path;
	}
	
	public Class<?> loadClass(String name) throws ClassNotFoundException, MalformedURLException  {
		File file = new File(getFilePath(path, name));
		//注意下面的 file.getPath():
		long lastModified = new File(this.getFileName(file.getPath())).lastModified();//.class文件的最后一次修改时间
		
		if(clazzs.containsKey(name) && fileLastModified.get(name) == lastModified){
			System.out.println("---------- 已经被加载过，且.clazz文件没有被修改过 ------------");
			return clazzs.get(name);
		}
		
		System.out.println("---------- 可能：①已经被加载过，但.clazz文件被修改过；②没有被加载过 -----------");
		Class<?> clazz = new HotLoadClassLoader02(this.getURLs(path)).loadClass(name);
		clazzs.put(name, clazz);
		fileLastModified.put(name, lastModified);
		return clazz;
	}
	
	/**
	 * 将用户提供的path，转成自定义加载器HotLoadClassLoader02所需要的url
	 */
	public URL[] getURLs(String path) throws MalformedURLException{
		File file = new File(path);
		return new URL[]{file.toURI().toURL()};
	}
	
	public String getFilePath(String path, String name){
		return path + "\\" + name;
	}
	
	private String getFileName(String name) {
		String newName = name.replace(".", "\\");
		return newName + ".java";
	}
}
