package classLoader.a05hotload;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

/**
 * 该类的热加载加载器虽然没实现成功，但是思路是对的。
 * 思路：
 * 	loadClass(){
 * 		findLoadedClass();//缓存
 * 		parent.loadClass();//父委托
 * 		findClass(){//需要热加载的类，都直接跳过缓存和父委托，直接进行加载。但是，失败了，因为defineClass()有做一层过滤，一个类只会被同一加载器加载一次。
 * 			defineClass();
 * 		}
 * 	}
 * 	①加载器的缓存：因为有缓存的存在，一个类只会同一个加载器加载一次。
 * 	②父委托机制：除了需要热加载的类，像Object、String等需要三个系统加载器加载的类就得依赖父委托机制进行加载。
 */
public class HotLoadClassLoader01 extends URLClassLoader{
	/**
	 * 需要做热加载的类都放到hotClass中。
	 * 问题：不灵活。在程序运行期间，类A成了需要热加载的类，这时候不能再往hotClass中添加该类。
	 */
	private Set<String> hotClass = new HashSet<String>();
	
	{
		hotClass.add("a05hotload.Bean");
	}
	
	public HotLoadClassLoader01(URL[] urls) {
		super(urls);
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if(hotClass.contains(name)){
			return super.findClass(name);
		}
		return super.loadClass(name);
	}
}
