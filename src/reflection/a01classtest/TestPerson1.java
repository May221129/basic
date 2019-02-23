package reflection.a01classtest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

//如何获取Class的实例（3种方法）：
//要求掌握！！！
import org.junit.Test;

public class TestPerson1 {
	@Test
	public void test() throws Exception{
		
//		1、调用运行时类本身的.class属性：
		Class clazz = Person.class;
		System.out.println(clazz.getName());
		
//		2、通过运行时类的对象获取：
		Person p = new Person();
		Class clazz1 = p.getClass();
		System.out.println(clazz1.getName());
		
//		3、通过Class的静态方法来获取：
//		通过此方法，体会反射的动态性。
		String className = "teseclass.Person";
		Class clazz2 = Class.forName(className);
		System.out.println(clazz2.getName());
		
//		4、通过类的加载器（了解即可）：
		ClassLoader classLoader = this.getClass().getClassLoader();
		Class clazz5 = classLoader.loadClass(className);
		System.out.println(clazz5.getName());
		
//		要掌握：
//		方式一：在具体的一个包里，获取指定文件的内容：
		ClassLoader cl = this.getClass().getClassLoader();
		InputStream is = cl.getResourceAsStream("a01teseclass\\hello.properties");
		Properties pro = new Properties();
		pro.load(is);
		String s = pro.getProperty("user");
		System.out.println(s);
		
		String s1 = pro.getProperty("password");
		System.out.println(s1);
		
//		方式二：在当前项目下，获取指定文件的内容：
		FileInputStream fis = new FileInputStream(new File("hello.txt"));
		Properties pros = new Properties();
		pro.load(fis);
		String str = pros.getProperty("user");
		System.out.println(str);
	}
}
