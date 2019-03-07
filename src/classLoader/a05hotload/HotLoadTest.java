package classLoader.a05hotload;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * 1.什么是热加载：程序运行之后，已经加载了类A；后来在程序运行期间，类A被改动了，需要重新对A进行加载。
 * 
 * 2.热加载流程：
 * 		①程序启动，main()相当于一个while(true)，不停地再循环，将需要用的类加载到内存； 
 * 		②默认由AppClassLoader加载classPath中的类（遵循父委托机制）；
 * 		③程序运行期间，将需要做热加载的类的源文件在程序之外（如NotePad编辑器中）做修改，再重新编译成.class文件，
 * 			并放到自定义的可以做热加载的加载器的path下；
 * 		④程序猿是知道哪些类是会经常被改动，需要进行热加载的（我们就叫这些类为善变类）。在程序中，需要用到这些善变类的地方(如需要实例化一个善变类)，
 * 			就按照2.②中的方法，判断善变类是否需要重新加载。
 * 		⑤善变类需要统一由自定义加载器进行加载。
 * 	
 * 	3.热加载的使用场景（什么时候能用热加载，什么时候不能用）：
 * 		外界不能强依赖于"被热加载的类"，避免该热加载的类的实例一直处于根可达，不被回收掉。否则可能出现很多问题。
 * 		如：
			前提：①A类被加载了两次，这里分别用oldA和newA表示前后两次加载到内存中的A的大Class对象。②a是A的实例。
			a instanceof(A){
				A aa = (A)a;
				aa.show();//这个show()方法是newA中新添加的方法，oldA中并没有这个方法，所以执行下面的aa.show()会出错。
			}
 * 	
 * 	4.类的热加载器的具体实现：a05hotload.HotLoadTool.HotLoadTool(String path)
 */
public class HotLoadTest {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, InterruptedException, MalformedURLException{
		
		HotLoadTool hotLoadTool = new HotLoadTool("Q:\\ClassLoaderTest");
		
		Class<?> clazz;
		try {
			clazz = hotLoadTool.loadClass("a05hotload.Bean");
			clazz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		TimeUnit.SECONDS.sleep(30);
		
		Class<?> clazz2;
		try {
			clazz2 = hotLoadTool.loadClass("a05hotload.Bean");
			clazz2.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
